package com.jdc.onestop.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.balance.model.dto.AccountDto;
import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.form.SignInForm;
import com.jdc.onestop.balance.model.dto.form.SignUpForm;
import com.jdc.onestop.balance.model.service.SecurityService;

@RestController
@RequestMapping("security")
public class SecurityApi {
	
	@Autowired
	private SecurityService service;

	@PostMapping("signin")
	ApiResult<AccountDto> signIn(@Validated @RequestBody SignInForm form, BindingResult result) {
		return ApiResult.from(service.signIn(form));
	}
	
	@PostMapping("signup")
	ApiResult<String> signUp(@Validated @RequestBody SignUpForm form, BindingResult result) {
		return ApiResult.from(service.signUp(form));
	}
	
}
