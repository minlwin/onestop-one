package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.entity.Account.Role;

public record LoginUserDto(
		int id,
		String name,
		Role role,
		String email
		) {
	
	public LoginUserDto(Account account) {
		this(account.getId(), account.getName(), account.getRole(), account.getEmail());		
	}
}
