package com.jdc.one.traders.model.service;

import java.util.Optional;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.input.SignInDto;
import com.jdc.one.traders.model.dto.input.SignUpDto;
import com.jdc.one.traders.model.dto.output.LoginResultDto;

public interface AccountSecurity {

	LoginResultDto signIn(SignInDto dto);
	LoginResultDto signUp(SignUpDto dto);
	Optional<Account> findByEmail(String email);
}
