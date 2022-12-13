package com.jdc.onestop.balance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BalanceAppSecurity {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain http(HttpSecurity http) throws Exception {
		
		http.csrf().and().cors().disable();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeHttpRequests()
			.requestMatchers("/security/**").permitAll()
			.requestMatchers(HttpMethod.POST, "/category/**").hasAnyAuthority("Admin")
			.requestMatchers(HttpMethod.PUT, "/category/**").hasAnyAuthority("Admin")
			.requestMatchers("/account/**").hasAnyAuthority("Admin")
			.anyRequest().authenticated();
			
		
		return http.build();
	}
	
	@Bean
	UserDetailsService adminUserDetailsService(PasswordEncoder encoder) {
		return new InMemoryUserDetailsManager(
				User.builder()
					.username("admin@gmail.com")
					.password(encoder.encode("admin"))
					.authorities("Admin")
					.build()
				);
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
