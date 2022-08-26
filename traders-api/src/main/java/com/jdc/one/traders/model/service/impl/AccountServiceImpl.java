package com.jdc.one.traders.model.service.impl;

import java.nio.file.Path;
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
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.entity.Address;
import com.jdc.one.traders.model.dto.entity.Profile;
import com.jdc.one.traders.model.dto.input.AccountProfile;
import com.jdc.one.traders.model.dto.input.AddressDto;
import com.jdc.one.traders.model.dto.input.BankingDto;
import com.jdc.one.traders.model.dto.input.ChangePasswordDto;
import com.jdc.one.traders.model.dto.input.SignInDto;
import com.jdc.one.traders.model.dto.input.SignUpDto;
import com.jdc.one.traders.model.dto.output.AddressVO;
import com.jdc.one.traders.model.dto.output.LoginResultDto;
import com.jdc.one.traders.model.dto.output.LoginUserDto;
import com.jdc.one.traders.model.dto.output.SellerDto;
import com.jdc.one.traders.model.dto.output.SimpleResult;
import com.jdc.one.traders.model.dto.output.TopSellerDto;
import com.jdc.one.traders.model.repo.AccountRepo;
import com.jdc.one.traders.model.repo.AddressRepo;
import com.jdc.one.traders.model.repo.BankingInfoRepo;
import com.jdc.one.traders.model.repo.ProfileRepo;
import com.jdc.one.traders.model.repo.TownshipRepo;
import com.jdc.one.traders.model.service.AccountSecurity;
import com.jdc.one.traders.model.service.AccountService;
import com.jdc.one.traders.model.service.PhotoService;
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

	@Autowired
	private PhotoService photoService;

	@Autowired
	private BankingInfoRepo bankRepo;
	@Autowired
	private AddressRepo addressRepo;

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
		return accountRepo.findOne((root, query, builder) -> builder.equal(root.get("email"), email));
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

		if (null == profile) {
			profile = new Profile();
			profile.setAccount(account);
			account.setProfile(profile);
		}

		profile.setCoverImage(dto.personalInfo().coverImage());
		profile.setGender(dto.personalInfo().gender());
		profile.setDateOfBirth(dto.personalInfo().dateOfBirth());
		profile.setGreeting(dto.personalInfo().greeting());

		final Profile finalProfile = profile;
		finalProfile.getBankingInfo().clear();
		finalProfile.getAddress().clear();

		dto.bankingInfo().stream().map(BankingDto::getEntity).map(entity -> {
			if (entity.getId() > 0) {
				return bankRepo.save(entity);
			}
			return entity;
		}).forEach(finalProfile::addBankingInfo);

		dto.address().stream().map(this::getAddress).map(entity -> {
			if (entity.getId() > 0) {
				return addressRepo.save(entity);
			}
			return entity;
		}).forEach(finalProfile::addAddress);

		return findOne(dto.id());
	}

	@Override
	public AccountProfile findOne(int id) {
		return accountRepo.findById(id).map(AccountProfile::from).orElseThrow(EntityNotFoundException::new);
	}

	private Address getAddress(AddressDto dto) {
		var address = new Address();
		address.setId(dto.id());
		address.setAddress(dto.address());
		address.setName(dto.name());
		address.setDeleted(dto.deleted());
		address.setTownship(townshipRepo.findById(dto.township()).orElseThrow(EntityNotFoundException::new));
		return address;
	}

	@Override
	@Transactional
	public SimpleResult changePass(int accountId, ChangePasswordDto dto) {

		var account = accountRepo.findById(accountId).orElseThrow(EntityNotFoundException::new);

		if (dto.oldPass().equals(dto.newPass())) {
			return SimpleResult.fails("You can't use same password with old password.");
		}

		if (!encoder.matches(dto.oldPass(), account.getPassword())) {
			return SimpleResult.fails("Please check your old password.");
		}

		account.setPassword(encoder.encode(dto.newPass()));

		return SimpleResult.success("Your password has been changed successfully!");
	}

	@Override
	@Transactional
	public AccountProfile updateProfileImage(int id, MultipartFile file) {
		return accountRepo.findById(id).flatMap(
				account -> photoService.create(Path.of("profiles"), "profile-%d".formatted(id), file)
				.map(fileName -> {
					var profile = account.getProfile();
					if(null == profile) {
						profile = new Profile();
						profile.setAccount(account);
						account.setProfile(profile);
					}
					profile.setCoverImage(fileName);
					return account.getId();
				})).map(accountId -> findOne(accountId)).orElseThrow();
	}

	@Override
	public SellerDto findToSellerById(int id) {
		return accountRepo.findById(id).map(SellerDto::from).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	@Transactional
	public void addBanking(int id, BankingDto dto) {
		accountRepo.findById(id).ifPresent(account -> {
			var profile = account.getProfile();
			
			if(null == profile) {
				profile = new Profile();
				profile.setAccount(account);
				account.setProfile(profile);
			}
			
			profile.addBankingInfo(dto.getEntity());
		});
	}

	@Override
	@Transactional
	public AddressVO addAddress(int id, AddressDto dto) {
		return accountRepo.findById(id).map(account -> {
			var profile = account.getProfile();
			
			if(null == profile) {
				profile = new Profile();
				profile.setAccount(account);
				account.setProfile(profile);
				accountRepo.flush();
				profile = profileRepo.save(profile);
			}
			
			var address = getAddress(dto);
			address.setProfile(profile);
			return addressRepo.save(address);
		}).map(AddressVO::from).orElseThrow();
	}

	@Override
	public AddressVO findAddressById(int id) {
		return addressRepo.findById(id).map(AddressVO::from).orElseThrow();
	}

}
