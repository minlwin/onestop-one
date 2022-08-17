package com.jdc.one.traders.model.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.entity.Address;
import com.jdc.one.traders.model.dto.entity.Profile;
import com.jdc.one.traders.model.dto.input.AccountProfile;
import com.jdc.one.traders.model.dto.input.AddressDto;
import com.jdc.one.traders.model.dto.input.BankingDto;
import com.jdc.one.traders.model.dto.input.SignInDto;
import com.jdc.one.traders.model.dto.input.SignUpDto;
import com.jdc.one.traders.model.dto.output.LoginResultDto;
import com.jdc.one.traders.model.dto.output.LoginUserDto;
import com.jdc.one.traders.model.dto.output.TopSellerDto;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.repo.ProfileRepo;
import com.jdc.one.traders.model.repo.TownshipRepo;
import com.jdc.one.traders.model.service.AccountSecurity;
import com.jdc.one.traders.model.service.AccountService;
import com.jdc.one.traders.model.service.ProfileService;

@Service
public class AccountServiceImpl implements AccountSecurity, AccountService, ProfileService {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private TownshipRepo townshipRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	

	@Override
	public LoginResultDto signIn(SignInDto dto) {
		var auth = authManager.authenticate(dto.authenticationToken());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return findByEmail(dto.email()).map(LoginUserDto::new).map(LoginResultDto::success)
				.orElse(LoginResultDto.error("There is no account with email. %s".formatted(dto.email())));
	}

	@Transactional
	@Override
	public LoginResultDto signUp(SignUpDto dto) {
		
		// Create Account
		var account = dto.account();
		account.setPassword(encoder.encode(dto.password()));
		accountRepo.save(account);
		
		// Authenticate
		var auth = authManager.authenticate(dto.authenticationToken());
		SecurityContextHolder.getContext().setAuthentication(auth);

		return findByEmail(dto.email()).map(LoginUserDto::new).map(LoginResultDto::success)
				.orElse(LoginResultDto.error("There is no account with email. %s".formatted(dto.email())));
	}

	@Override
	public Optional<Account> findByEmail(String email) {
		return accountRepo.findOne((root, query, builder) -> 
				builder.equal(root.get("email"), email));
	}

	@Override
	public List<TopSellerDto> getTopSellers(Optional<Integer> limit) {
		return accountRepo.getTopSeller(limit.map(size -> PageRequest.ofSize(size)).orElse(null));
	}

	@Transactional
	@Override
	public AccountProfile save(AccountProfile dto) {
		
		var account = accountRepo.findById(dto.id()).orElseThrow(EntityNotFoundException::new);
		account.setName(dto.name());
		
		var profile = account.getProfile();
		
		if(null == profile) {
			profile = new Profile();
			profile.setAccount(account);
			profile = profileRepo.save(profile);
		}
		
		profile.setCoverImage(dto.personalInfo().coverImage());
		profile.setGender(dto.personalInfo().gender());
		profile.setDateOfBirth(dto.personalInfo().dateOfBirth());
		profile.setGreeting(dto.personalInfo().greeting());
		
		final Profile finalProfile = profile;
		
		dto.bankingInfo().stream().map(BankingDto::getEntity)
			.forEach(finalProfile::addBankingInfo);
		
		dto.address().stream().map(this::getAddress)
			.forEach(finalProfile::addAddress);
		
		return findOne(dto.id());
	}

	@Override
	public AccountProfile findOne(int id) {
		return accountRepo.findById(id)
				.map(AccountProfile::from)
				.orElseThrow(EntityNotFoundException::new);
	}
	
	private Address getAddress(AddressDto dto) {
		var address = new Address();
		address.setId(dto.id());
		address.setAddress(dto.address());
		address.setName(dto.name());
		address.setDeleted(dto.deleted());
		address.setTownship(townshipRepo.findById(dto.township())
				.orElseThrow(EntityNotFoundException::new));
		return address;
	}
	
	
}
