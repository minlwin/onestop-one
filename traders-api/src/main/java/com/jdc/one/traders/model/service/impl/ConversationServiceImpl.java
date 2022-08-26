package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.one.traders.model.dto.entity.Conversation;
import com.jdc.one.traders.model.dto.entity.ConversationMessage;
import com.jdc.one.traders.model.dto.entity.pk.ConversationPk;
import com.jdc.one.traders.model.dto.input.ConversationMessageDto;
import com.jdc.one.traders.model.dto.output.ConversationSummary;
import com.jdc.one.traders.model.dto.output.ConversationVO;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.repo.ConversationRepo;
import com.jdc.one.traders.model.repo.ProductRepo;
import com.jdc.one.traders.model.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService{
	
	@Autowired
	private ConversationRepo conversationRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private ProductRepo productRepo;

	@Override
	public List<ConversationSummary> search(Optional<String> product, Optional<String> owner, Optional<String> sender) {
		
		Specification<Conversation> spec = Specification.where(null);
		
		// Product
		var prodWhjere = product
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Conversation> where = (root, query, builder) -> builder.equal(root.get("product").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(prodWhjere);
		
		// sender
		var senderWhere = sender
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Conversation> where = (root, query, builder) -> builder.equal(root.get("sender").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(senderWhere);

		// owner
		var ownerWhere = owner
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Conversation> where = (root, query, builder) -> builder.equal(root.get("product").get("seller").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(ownerWhere);
		
		return conversationRepo.findAll(spec).stream().map(ConversationSummary::new).toList();
	}

	@Override
	public ConversationVO findById(ConversationPk id) {
		return conversationRepo.findById(id)
				.map(ConversationVO::new)
				.orElse(dummy(id));
	}
	
	private ConversationVO dummy(ConversationPk id) {
		var sender = accountRepo.findById(id.getSenderId()).orElseThrow();
		var product = productRepo.findById(id.getProudctId()).orElseThrow();
		return ConversationVO.getInstance(sender, product);
	}
	
	@Override
	@Transactional
	public void addMessage(ConversationMessageDto dto) {		
		var conversation = conversationRepo.findById(dto.conversationId())
				.orElseGet(() -> {
					var entity = new Conversation();
					entity.setProduct(productRepo.getReferenceById(dto.productId()));
					entity.setSender(accountRepo.getReferenceById(dto.senderId()));
					return conversationRepo.save(entity);
				});
		
		var message = new ConversationMessage();
		message.setMessage(dto.message());
		message.setSpeaker(accountRepo.getReferenceById(dto.messageSenderId()));
		conversation.addMessage(message);
	}

}
