package com.jdc.one.traders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.one.traders.model.repo.AccountRepo;

@Service
public class TradersApiUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOne((root, query, builder) -> 
			builder.equal(root.get("email"), username))
					.map(acc -> User.builder()
							.username(username)
							.password(acc.getPassword())
							.authorities(AuthorityUtils.createAuthorityList(acc.getRole().name()))
							.disabled(acc.isSuspend())
							.build())
					.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
