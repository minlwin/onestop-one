package com.jdc.onestop.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.balance.model.entity.Account;
import com.jdc.onestop.balance.model.entity.Account.Role;
import com.jdc.onestop.balance.model.repo.AccountRepo;
import com.jdc.onestop.balance.security.meta.SecurityComponent;

@SecurityComponent
public class AdminUserInitializer {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AccountRepo repo;
	
	@Value("${app.admin.name}")
	private String adminName;
	
	@Value("${app.admin.email}")
	private String adminMail;
	
	@Value("${app.admin.phone}")
	private String adminPhone;

	@Value("${app.admin.password}")
	private String adminPass;
	
	@Transactional
	@EventListener(classes = ContextRefreshedEvent.class)
	void initialize() {
		if(repo.count() == 0) {
			var account = new Account(adminName, adminMail, adminName, encoder.encode(adminPass));
			account.setRole(Role.Admin);
			repo.save(account);
		}
	}
}
