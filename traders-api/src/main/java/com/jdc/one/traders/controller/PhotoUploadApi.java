package com.jdc.one.traders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.service.PhotoService;

@RestController
@RequestMapping("photo")
public class PhotoUploadApi {
	
	@Autowired
	private PhotoService service;

	@PostMapping("{account}")
	String uploadPhoto(
			@PathVariable int account, 
			@RequestParam MultipartFile file) {
		return service.save(account, file);
	}
}
