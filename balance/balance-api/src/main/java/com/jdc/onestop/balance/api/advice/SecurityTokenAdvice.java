package com.jdc.onestop.balance.api.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.jdc.onestop.balance.security.SecurityTokenProvider;

@RestControllerAdvice
public class SecurityTokenAdvice implements ResponseBodyAdvice<Object>{

	@Autowired
	private SecurityTokenProvider provider;
	@Value("${app.token.name}")
	private String tokenName;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		
		var auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			// Generate Token
			provider.generate(auth).ifPresent(token -> {
				// Add Token to Header
				response.getHeaders().add(tokenName, token);
			});
			
		}
		return body;
	}

}
