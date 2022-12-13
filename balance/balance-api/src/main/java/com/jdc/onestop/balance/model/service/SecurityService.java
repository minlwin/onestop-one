package com.jdc.onestop.balance.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.balance.model.dto.AccountDto;
import com.jdc.onestop.balance.model.dto.form.SignInForm;
import com.jdc.onestop.balance.model.dto.form.SignUpForm;

@Service
public class SecurityService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private PasswordGenerator passGenerator;
	
	@Autowired
	private AccountService service;

	public AccountDto signIn(SignInForm form) {
		var authentication = authManager.authenticate(form.token());
		SecurityContextHolder.getContext().setAuthentication(authentication);;
		return service.findByEmail(form.email());
	}

	@Transactional
	public String signUp(SignUpForm form) {
		var password = passGenerator.generate();
		var entity = form.entity(password);
		service.save(entity);
		return password;
	}

}
