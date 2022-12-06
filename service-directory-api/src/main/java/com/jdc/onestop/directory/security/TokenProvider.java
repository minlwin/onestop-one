package com.jdc.onestop.directory.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenProvider {

	public Optional<String> generate(Authentication auth) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Authentication> authenticate(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
