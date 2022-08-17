package com.jdc.one.traders.model.service;

import java.util.Optional;

import com.jdc.one.traders.model.dto.entity.Account;
import com.jdc.one.traders.model.dto.input.ChangePasswordDto;
import com.jdc.one.traders.model.dto.input.SignInDto;
import com.jdc.one.traders.model.dto.input.SignUpDto;
import com.jdc.one.traders.model.dto.output.LoginResultDto;
import com.jdc.one.traders.model.dto.output.SimpleResult;

public interface AccountSecurity {

	LoginResultDto signIn(SignInDto dto);
	LoginResultDto signUp(SignUpDto dto);
	SimpleResult changePass(int account, ChangePasswordDto dto);
	Optional<Account> findByEmail(String email);
}
