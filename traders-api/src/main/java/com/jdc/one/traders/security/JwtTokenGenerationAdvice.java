package com.jdc.one.traders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class JwtTokenGenerationAdvice implements ResponseBodyAdvice<Object>{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Value("${app.jwt.token}")
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
		
		jwtTokenProvider.generate(auth).ifPresent(token -> {
			var servletResponse = (ServletServerHttpResponse)response;
			servletResponse.getServletResponse().addHeader(tokenName, token);
		});
		
		return body;
	}

}
