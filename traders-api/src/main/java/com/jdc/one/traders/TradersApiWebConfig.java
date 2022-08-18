package com.jdc.one.traders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class TradersApiWebConfig implements WebMvcConfigurer{
	
	@Value("${app.photo.location}")
	private String photoLocation;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/photo/**")
			.addResourceLocations("file:%s".formatted(photoLocation));
	}
}
