package com.jdc.onestop.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdc.onestop.balance.security.SecurityTokenFilter;

@Configuration
@EnableWebSecurity
public class BalanceAppSecurity {
	
	@Autowired
	private SecurityTokenFilter securityTokenFilter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain http(HttpSecurity http) throws Exception {
		
		return http.csrf().disable().cors().and()
				.authorizeHttpRequests()
				.requestMatchers("/security/**").permitAll()
				.requestMatchers("/account/**").hasAuthority("Admin")
				.requestMatchers(HttpMethod.POST ,"/category/**").hasAuthority("Admin")
				.requestMatchers(HttpMethod.PUT ,"/category/**").hasAuthority("Admin")
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(securityTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
		
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
