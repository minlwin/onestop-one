package com.jdc.onestop.directory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ServiceDirectoryApiSecurity {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.cors().and().csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/public/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/location/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/location/**").hasAuthority("Manager")
				.requestMatchers(HttpMethod.PUT, "/location/**").hasAnyAuthority("Manager")
				.anyRequest().authenticated()
				.and().build();
	}
}
