package com.jdc.onestop.balance.model.dto.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record SignInForm(
		String email,
		String password
		) {

	public Authentication token() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
