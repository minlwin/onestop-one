package com.jdc.one.traders.model.dto.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.entity.Account.Role;

public record SignUpDto(
		String name,
		String email,
		String password
		) {
	
	public Account account() {
		var account = new Account();
		account.setEmail(email);
		account.setName(name);
		account.setRole(Role.Member);
		account.setSuspend(false);
		return account;
	}
	
	public UsernamePasswordAuthenticationToken authenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
	
}
