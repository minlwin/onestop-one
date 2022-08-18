package com.jdc.one.traders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.input.AccountProfile;
import com.jdc.one.traders.model.service.ProfileService;

@RestController
@RequestMapping("profile")
public class ProfileApi {
	
	@Autowired
	private ProfileService service;

	@PutMapping
	AccountProfile updateProfile(@RequestBody AccountProfile profile) {
		return service.save(profile);
	}
	
	@GetMapping("{id}")
	AccountProfile findById(@PathVariable int id) {
		return service.findOne(id);
	}
	
	@PutMapping("image/{id}")
	AccountProfile uploadImage(
			@PathVariable int id, 
			@RequestParam MultipartFile file) {
		return service.updateProfileImage(id, file);
	}
}
