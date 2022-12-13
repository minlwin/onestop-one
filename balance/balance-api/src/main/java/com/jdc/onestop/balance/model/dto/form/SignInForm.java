package com.jdc.onestop.balance.model.dto.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotBlank;

public record SignInForm(
		@NotBlank(message = "Please enter email address.")
		String email,
		@NotBlank(message = "Please enter password.")
		String password
		) {

	public Authentication token() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
