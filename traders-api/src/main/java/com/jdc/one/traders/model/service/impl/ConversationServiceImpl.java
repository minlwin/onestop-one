package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.one.traders.model.dto.entity.Conversation;
import com.jdc.one.traders.model.dto.entity.ConversationMessage;
import com.jdc.one.traders.model.dto.input.ConversationMessageDto;
import com.jdc.one.traders.model.dto.input.ConversationStart;
import com.jdc.one.traders.model.dto.output.ConversationSummary;
import com.jdc.one.traders.model.dto.output.ConversationVO;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.repo.ConversationMessageRepo;
import com.jdc.one.traders.model.repo.ConversationRepo;
import com.jdc.one.traders.model.repo.ProductRepo;
import com.jdc.one.traders.model.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService{
	
	@Autowired
	private ConversationRepo conversationRepo;
	@Autowired
	private ConversationMessageRepo messageRepo;
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
		var senderWhere = product
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Conversation> where = (root, query, builder) -> builder.equal(root.get("sender").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(senderWhere);

		// owner
		var ownerWhere = product
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Conversation> where = (root, query, builder) -> builder.equal(root.get("product").get("owner").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(ownerWhere);
		
		return conversationRepo.findAll(spec).stream().map(ConversationSummary::new).toList();
	}

	@Override
	public ConversationVO findById(long id) {
		return conversationRepo.findById(id)
				.map(ConversationVO::new)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	@Override
	@Transactional
	public ConversationVO create(ConversationStart dto) {
		var conversation = new Conversation();
		conversation.setProduct(productRepo.findById(dto.productId()).orElseThrow());
		conversation.setSender(accountRepo.findById(dto.customerId()).orElseThrow());
		conversation = conversationRepo.save(conversation);
		return findById(conversation.getId());
	}

	@Override
	@Transactional
	public ConversationVO addMessage(ConversationMessageDto dto) {
		var message = new ConversationMessage();
		message.setMessage(dto.message());
		message.setSpeaker(accountRepo.findById(dto.senderId()).orElseThrow());
		message.setConversation(conversationRepo.findById(dto.conversationId()).orElseThrow());
		messageRepo.save(message);
		return findById(dto.conversationId());
	}

}
