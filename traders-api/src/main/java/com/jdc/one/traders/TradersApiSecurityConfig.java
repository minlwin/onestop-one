package com.jdc.one.traders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jdc.one.traders.model.dto.entity.Account.Role;
import com.jdc.one.traders.security.JwtTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TradersApiSecurityConfig {
	
	@Autowired
	private JwtTokenAuthenticationFilter authenticationFilter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
		return http
					.cors().and()
					.csrf().disable()
					.authorizeRequests()
						.mvcMatchers("/admin/**").hasAnyAuthority(Role.Admin.toString())
						.mvcMatchers("/security/**", "/category/**", "/seller/top/**", "/product", "/product/{id}", "/photo/**").permitAll()
						.anyRequest().authenticated().and()
						.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
					.build();
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
		
	
}
