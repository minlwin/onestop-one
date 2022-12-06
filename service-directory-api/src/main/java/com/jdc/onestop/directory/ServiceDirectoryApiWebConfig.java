package com.jdc.onestop.directory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@PropertySource("classpath:/app-token.properties")
public class ServiceDirectoryApiWebConfig implements WebMvcConfigurer{

	@Value("${app.token.name}")
	private String tokenName;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.exposedHeaders(tokenName)
			.allowedHeaders(tokenName);
	}
}
