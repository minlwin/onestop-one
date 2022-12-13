package com.jdc.onestop.balance.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdc.onestop.balance.security.meta.SecurityComponent;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SecurityComponent
public class SecurityTokenFilter extends OncePerRequestFilter{

	@Autowired
	private SecurityTokenProvider provider;
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
