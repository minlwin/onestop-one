package com.jdc.onestop.balance.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.onestop.balance.model.dto.AccountDto;
import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.SingleInputForm;
import com.jdc.onestop.balance.model.entity.Account.Role;
import com.jdc.onestop.balance.model.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountApi {
	
	@Autowired
	private AccountService service;

	@GetMapping
	ApiResult<List<AccountDto>> search(@RequestParam Optional<Role> role, 
			@RequestParam Optional<String> keyword) {
		return ApiResult.from(service.search(role, keyword));
	}
	
	@GetMapping("{id}")
	ApiResult<AccountDto> findById(@PathVariable int id) {
		return ApiResult.from(service.findById(id));
	}
	
	@PutMapping("{id}/role")
	ApiResult<AccountDto> updateRole(@PathVariable int id, 
			@Validated @RequestBody SingleInputForm<Role> form, BindingResult result) {
		return ApiResult.from(service.updateRole(id, form.value()));
	}

	@PutMapping("{id}/status")
	ApiResult<AccountDto> updateStatus(@PathVariable int id,
			@Validated @RequestBody SingleInputForm<Boolean> form, BindingResult result) {
		return ApiResult.from(service.updateStatus(id, form.value()));
	}
}
