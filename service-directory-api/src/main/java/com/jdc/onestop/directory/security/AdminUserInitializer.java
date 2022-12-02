package com.jdc.onestop.directory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jdc.onestop.directory.model.entity.Account;
import com.jdc.onestop.directory.model.entity.Account.Role;
import com.jdc.onestop.directory.model.repo.AccountRepo;

@Component
public class AdminUserInitializer {

	@Autowired
	private AccountRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Value("${app.admin.name}")
	private String name;
	@Value("${app.admin.email}")
	private String email;
	@Value("${app.admin.password}")
	private String password;
	
	@EventListener(classes = ContextRefreshedEvent.class)
	public void createAdminUser() {
		if(repo.count() == 0) {
			// Create Admin User
			var admin = new Account();
			admin.setName(name);
			admin.setEmail(email);
			admin.setPassword(encoder.encode(password));
			admin.setActive(true);
			admin.setRole(Role.Manager);
			repo.save(admin);
		}
	}
}
