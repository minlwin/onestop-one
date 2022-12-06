package com.jdc.onestop.directory.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenAuthFilter extends OncePerRequestFilter{

	@Autowired
	private TokenProvider provider;
	@Value("${app.token.name}")
	private String tokenName;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		provider.authenticate(request.getHeader(tokenName))
			.ifPresent(SecurityContextHolder.getContext()::setAuthentication);
		
		filterChain.doFilter(request, response);
	}

}
