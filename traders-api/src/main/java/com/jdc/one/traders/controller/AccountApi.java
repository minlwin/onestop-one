package com.jdc.one.traders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.input.AccountProfile;
import com.jdc.one.traders.model.service.AccountService;

@RestController
@RequestMapping("account")
public class AccountApi {
	
	@Autowired
	private AccountService service;

	@PostMapping("profile")
	AccountProfile createProfile(@RequestBody AccountProfile profile) {
		return service.save(profile);
	}
	
	@PutMapping("profile")
	AccountProfile updateProfile(@RequestBody AccountProfile profile) {
		return service.save(profile);
	}
	
	@GetMapping("profile/{id}")
	AccountProfile findById(@PathVariable int id) {
		return service.findOne(id);
	}
}
