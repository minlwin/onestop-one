package com.jdc.onestop.balance.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.balance.model.BalanceAppBusinessException;
import com.jdc.onestop.balance.model.dto.BalanceDto;
import com.jdc.onestop.balance.model.dto.BalanceListDto;
import com.jdc.onestop.balance.model.dto.ErrorDto.Type;
import com.jdc.onestop.balance.model.dto.SingleInputForm;
import com.jdc.onestop.balance.model.dto.form.BalanceForm;
import com.jdc.onestop.balance.model.entity.Balance;
import com.jdc.onestop.balance.model.repo.AccountRepo;
import com.jdc.onestop.balance.model.repo.BalanceDetailsRepo;
import com.jdc.onestop.balance.model.repo.BalanceRepo;
import com.jdc.onestop.balance.model.repo.CategoryRepo;

@Service
@Transactional
public class BalanceService {
	
	@Autowired
	private BalanceRepo balanceRepo;
	@Autowired
	private BalanceDetailsRepo detailsRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private AccountRepo accountRepo;


	public BalanceDto create(BalanceForm form) {
		
		var entity = balanceRepo.save(form.entity(
			categoryRepo::getReferenceById,
			accountRepo.findOneByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow()));
		entity = balanceRepo.save(entity);
		
		for(var details : form.details()) {
			entity.addDetails(details.entity());
		}
		
		return findById(entity.getId());
	}

	public BalanceDto update(int id, BalanceForm form) {
		
		balanceRepo.findById(id).ifPresent(entity -> {
			// Update Balance Information
			entity.setIssueAt(form.issueAt());
			entity.setRemark(form.remark());
			entity.setCategory(categoryRepo.getReferenceById(form.categoryId()));
			
			form.details().forEach(dto -> {
				
				if(dto.id() == 0) {
					// If new details, then add new
					entity.addDetails(dto.entity());
				} else {
					
					if(dto.deleted()) {
						// if deleted, then delete
						detailsRepo.deleteById(dto.id());
					} else {
						// update details data
						detailsRepo.findById(dto.id()).ifPresent(details -> {
							details.setArticle(dto.article());
							details.setQuentity(dto.quentity());
							details.setUnitPrice(dto.unitPrice());
						});
					}
				}
				
			});
		});
		
		return findById(id);
	}

	public BalanceDto updateFixedStatus(int id, SingleInputForm<Boolean> form) {
		balanceRepo.findById(id).ifPresent(entity -> entity.setDeleted(form.value()));
		return findById(id);
	}

	public BalanceDto delete(int id, SingleInputForm<Boolean> form) {
		balanceRepo.findById(id).ifPresent(entity -> entity.setFixed(form.value()));
		return findById(id);
	}

	public BalanceDto deleteDetails(int id, SingleInputForm<Integer> form) {
		detailsRepo.deleteById(form.value());
		return findById(id);
	}

	@Transactional(readOnly = true)
	public BalanceDto findById(int id) {
		return balanceRepo.findById(id).map(BalanceDto::from).orElseThrow(() -> new BalanceAppBusinessException(""));
	}

	@Transactional(readOnly = true)
	public List<BalanceListDto> search(Optional<Type> type, Optional<Integer> category, Optional<String> keyword, Optional<LocalDate> from, Optional<LocalDate> to) {
		return balanceRepo.findAll(
				withCategory(category)
					.and(withType(type))
					.and(withFrom(from).and(withTo(to))
					.and(withKeyword(keyword.filter(StringUtils::hasLength))))
				).stream().map(BalanceListDto::from).toList();
	}
	
	private Specification<Balance> withCategory(Optional<Integer> where) {
		return where.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("category").get("id"), where.get());
	}

	private Specification<Balance> withType(Optional<Type> where) {
		return where.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.equal(root.get("category").get("type"), where.get());
	}

	private Specification<Balance> withFrom(Optional<LocalDate> where) {
		return where.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.greaterThanOrEqualTo(root.get("issueAt"), where.get());
	}

	private Specification<Balance> withTo(Optional<LocalDate> where) {
		return where.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.lessThanOrEqualTo(root.get("issueAt"), where.get());
	}

	private Specification<Balance> withKeyword(Optional<String> where) {
		return where.isEmpty() ? Specification.where(null) : 
			(root, query, cb) -> cb.like(cb.lower(root.get("remark")), where.get().toLowerCase().concat("%"));
	}

}
