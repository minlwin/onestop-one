package com.jdc.one.traders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.input.ChangePasswordDto;
import com.jdc.one.traders.model.dto.input.SignInDto;
import com.jdc.one.traders.model.dto.input.SignUpDto;
import com.jdc.one.traders.model.dto.output.LoginResultDto;
import com.jdc.one.traders.model.dto.output.SimpleResult;
import com.jdc.one.traders.model.service.AccountSecurity;

@RestController
@RequestMapping("security")
public class SecurityApi {
	
	@Autowired
	private AccountSecurity security;

	@PostMapping("signin")
	LoginResultDto signIn(@RequestBody SignInDto dto) {
		return security.signIn(dto);
	}
	
	@PostMapping("signup")
	LoginResultDto signUp(@RequestBody SignUpDto dto) {
		return security.signUp(dto);
	}
	
	@PostMapping("changepass/{account}")
	SimpleResult changePass(@PathVariable int account, @RequestBody ChangePasswordDto dto) {
		return security.changePass(account, dto);
	}
}
