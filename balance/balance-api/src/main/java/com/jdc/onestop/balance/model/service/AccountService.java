package com.jdc.onestop.balance.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.onestop.balance.model.BalanceAppBusinessException;
import com.jdc.onestop.balance.model.dto.AccountDto;
import com.jdc.onestop.balance.model.dto.form.PasswordForm;
import com.jdc.onestop.balance.model.dto.form.SignUpForm;
import com.jdc.onestop.balance.model.entity.Account;
import com.jdc.onestop.balance.model.entity.Account.Role;
import com.jdc.onestop.balance.model.repo.AccountRepo;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Transactional(readOnly = true)
	public AccountDto findByEmail(String email) {
		return repo.findOneByEmail(email)
				.map(AccountDto::from).orElseThrow();
	}
	
	public void save(Account entity) {
		entity.setPassword(encoder.encode(entity.getPassword()));
		repo.save(entity);
	}

	public AccountDto save(String username, SignUpForm form) {
		return null;
	}

	public void changePassword(String username, PasswordForm form) {
		var account = repo.findOneByEmail(username).orElseThrow();
		
		if(!encoder.matches(form.oldPass(), account.getPassword())) {
			throw new BalanceAppBusinessException("Please check your old password.");
		}
		
		account.setPassword(encoder.encode(form.newPass()));
	}

	@Transactional(readOnly = true)
	public AccountDto findById(int id) {
		return repo.findById(id).map(AccountDto::from)
				.orElseThrow(() -> new BalanceAppBusinessException(
						"There is no account with id %d.".formatted(id)));
	}

	public AccountDto updateRole(int id, Role value) {
		return repo.findById(id)
				.map(entity -> {
					entity.setRole(value);
					return AccountDto.from(entity);
				}).orElseThrow(() -> new BalanceAppBusinessException(
						"There is no account with id %d.".formatted(id)));	
	}

	public AccountDto updateStatus(int id, Boolean value) {
		return repo.findById(id)
				.map(entity -> {
					entity.setDenied(value);
					return AccountDto.from(entity);
				}).orElseThrow(() -> new BalanceAppBusinessException(
						"There is no account with id %d.".formatted(id)));
	}

	@Transactional(readOnly = true)
	public List<AccountDto> search(Optional<Role> role, Optional<String> keyword) {
		return repo.findAll(withRole(role).and(withName(keyword.filter(StringUtils::hasLength))))
				.stream()
				.filter(a -> !a.getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
				.map(AccountDto::from).toList();
	}
	
	private Specification<Account> withRole(Optional<Role> role) {
		return role.isEmpty() ? Specification.where(null) :
			(root, query, cb) -> cb.equal(root.get("role"), role.get());
	}
	
	private Specification<Account> withName(Optional<String> name) {
		return name.isEmpty() ? Specification.where(null) :
			(root, query, cb) -> cb.like(cb.lower(root.get("name")), name.get().toLowerCase().concat("%"));
	}
}
