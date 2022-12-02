package com.jdc.onestop.directory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.onestop.directory.model.repo.AccountRepo;

@Service
public class ApiUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repo.findOneByEmail(email)
				.map(account -> User.builder()
						.username(email)
						.password(account.getPassword())
						.authorities(account.getRole().name())
						.accountLocked(!account.isActive())
						.disabled(account.isDeleted())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(email));
	}
}
