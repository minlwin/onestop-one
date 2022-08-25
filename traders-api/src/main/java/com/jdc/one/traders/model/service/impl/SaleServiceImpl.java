package com.jdc.one.traders.model.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.one.traders.model.dto.entity.Sale;
import com.jdc.one.traders.model.dto.entity.SaleConversation;
import com.jdc.one.traders.model.dto.entity.Sale.Status;
import com.jdc.one.traders.model.dto.input.PaidInputDto;
import com.jdc.one.traders.model.dto.input.SaleInputDto;
import com.jdc.one.traders.model.dto.input.SaleStatusInputDto;
import com.jdc.one.traders.model.dto.output.SaleSummary;
import com.jdc.one.traders.model.dto.output.SaleVO;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.repo.AddressRepo;
import com.jdc.one.traders.model.repo.ProductRepo;
import com.jdc.one.traders.model.repo.SaleRepo;
import com.jdc.one.traders.model.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	private SaleRepo saleRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private AddressRepo addressRepo;

	@Override
	public List<SaleSummary> search(Optional<String> seller, Optional<String> buyer, Optional<String> status,
			Optional<String> keyword) {
		Specification<Sale> spec = Specification.where(null);
		
		// buyer
		var buyerWhere = buyer
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Sale> where = (root, query, builder) -> builder.equal(root.get("buyer").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(buyerWhere);
		
		
		// status
		var statusWhere = status
				.filter(id -> StringUtils.hasLength(id))
				.map(Status::valueOf)
				.map(s -> {
			Specification<Sale> where = (root, query, builder) -> builder.equal(root.get("status"), s);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(statusWhere);

		// seller
		var sellerWhere = buyer
				.filter(id -> StringUtils.hasLength(id))
				.map(Integer::parseInt)
				.filter(id -> id > 0)
				.map(id -> {
			Specification<Sale> where = (root, query, builder) -> builder.equal(root.get("product").get("seller").get("id"), id);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(sellerWhere);
		
		// keywords
		var keywordWhere = keyword.filter(StringUtils::hasLength).map(c -> {
			Specification<Sale> where = (root, query, builder) -> builder.or(
				builder.like(builder.lower(root.get("product").get("name")),c.toLowerCase().concat("%")),
				builder.like(builder.lower(root.get("product").get("category").get("name")),c.toLowerCase().concat("%"))
			);
			return where;
		}).orElse(Specification.where(null));

		spec = spec.and(keywordWhere);
		
		return saleRepo.findAll(spec).stream().map(SaleSummary::new).toList();
	}

	@Override
	public SaleVO findById(long id) {
		return saleRepo.findById(id).map(SaleVO::from).orElseThrow();
	}

	@Override
	@Transactional
	public SaleVO order(SaleInputDto dto) {
		
		var sale = new Sale();
		sale.setProduct(productRepo.getReferenceById(dto.product()));
		sale.setBuyer(accountRepo.getReferenceById(dto.buyer()));
		sale.setShipping(addressRepo.getReferenceById(dto.shipping()));
		
		sale.setStatus(Status.Order);
		sale.setOrderDate(LocalDateTime.now());
		sale = saleRepo.save(sale);
		
		return findById(sale.getId());
	}

	@Override
	@Transactional
	public SaleVO updateStatus(long id, SaleStatusInputDto dto) {
		var sale = saleRepo.getReferenceById(id);
		sale.setStatus(dto.status());
		
		switch(dto.status()) {
		case Paid:
			sale.setPaidDate(LocalDateTime.now());
			break;
		case Shipped:
			sale.setShippedDate(LocalDateTime.now());
			break;
		case Cancel:
			sale.setCloseDate(LocalDateTime.now());
			break;
		case Finish:
			sale.setCloseDate(LocalDateTime.now());
			sale.getProduct().setSoldOut(true);
			break;
		default:
			break;
		}
		
		if(StringUtils.hasLength(dto.remark())) {
			var conversation = new SaleConversation();
			conversation.setMessage(dto.remark());
			conversation.setSender(accountRepo.getReferenceById(dto.updateUser()));
			sale.addConversation(conversation);
		}
		return findById(id);
	}

	@Override
	@Transactional
	public SaleVO paid(PaidInputDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
