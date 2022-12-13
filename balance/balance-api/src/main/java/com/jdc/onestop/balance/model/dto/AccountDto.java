package com.jdc.onestop.balance.model.dto;

import com.jdc.onestop.balance.model.entity.Account;
import com.jdc.onestop.balance.model.entity.Account.Role;

public record AccountDto(
		int id,
		String name,
		String email,
		Role role,
		String phone,
		boolean denied
		) {

	public static AccountDto from(Account acc) {
		return new AccountDto(acc.getId(), acc.getName(), acc.getEmail(), acc.getRole(), acc.getPhone(), acc.isDenied());
	}
}
