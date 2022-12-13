package com.jdc.onestop.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.onestop.balance.model.repo.AccountRepo;
import com.jdc.onestop.balance.security.meta.SecurityComponent;

@SecurityComponent
@Transactional(readOnly = true)
public class BalanceAppUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepo accountRepo;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return accountRepo.findOneByEmail(username)
				.map(account -> User.builder()
						.username(username)
						.password(account.getPassword())
						.authorities(account.getRole().name())
						.accountLocked(account.isDenied())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
