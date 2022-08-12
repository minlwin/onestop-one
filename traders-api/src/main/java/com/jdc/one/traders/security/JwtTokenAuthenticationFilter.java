package com.jdc.one.traders.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Value("${app.jwt.token}")
	private String tokenName;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		jwtTokenProvider.authenticate(request.getHeader(tokenName));
		
		filterChain.doFilter(request, response);
	}

}
