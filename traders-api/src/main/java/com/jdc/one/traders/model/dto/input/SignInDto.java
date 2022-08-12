package com.jdc.one.traders.model.dto.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record SignInDto(
		String email,
		String password
		) {
	
	public UsernamePasswordAuthenticationToken authenticationToken() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
