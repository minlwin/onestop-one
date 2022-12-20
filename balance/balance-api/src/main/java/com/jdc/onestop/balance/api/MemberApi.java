package com.jdc.onestop.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.balance.model.dto.AccountDto;
import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.form.PasswordForm;
import com.jdc.onestop.balance.model.dto.form.ProfileForm;
import com.jdc.onestop.balance.model.service.AccountService;

@RestController
@RequestMapping("member")
public class MemberApi {
	
	@Autowired
	private AccountService service;

	@PutMapping("profile")
	@PreAuthorize("#username == authentication.name")
	ApiResult<AccountDto> save(
			@RequestHeader("balance-user") String username, 
			@Validated @RequestBody ProfileForm form, 
			BindingResult result) {
		return ApiResult.from(service.save(username, form));
	}
	
	@PutMapping("password")
	@PreAuthorize("#username == authentication.name")
	ApiResult<String> changePass(
			@RequestHeader("balance-user") String username, 
			@Validated @RequestBody PasswordForm form, 
			BindingResult result) {
		
		service.changePassword(username, form);
		
		return ApiResult.from("Your password has been changed successfully.");
	}
}
