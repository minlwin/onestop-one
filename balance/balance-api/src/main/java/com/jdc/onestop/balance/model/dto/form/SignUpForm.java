package com.jdc.onestop.balance.model.dto.form;

import com.jdc.onestop.balance.model.entity.Account;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
		@NotBlank(message = "Please enter member name.")
		String name,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter email address.")
		String email
		) {

	public Account entity(String password) {
		return new Account(name, email, phone, password);
	}

}
