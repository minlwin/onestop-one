package com.jdc.onestop.balance.model.dto.form;

import com.jdc.onestop.balance.model.entity.Account;

public record SignUpForm(
		String name,
		String phone,
		String email
		) {

	public Account entity(String password) {
		return new Account(name, email, phone, password);
	}

}
