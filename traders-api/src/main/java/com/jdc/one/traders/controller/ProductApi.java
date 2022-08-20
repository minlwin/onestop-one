package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.one.traders.model.dto.input.ProductInputDto;
import com.jdc.one.traders.model.dto.output.ProductDto;
import com.jdc.one.traders.model.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductApi {
	
	@Autowired
	private ProductService service;

	@GetMapping
	List<ProductDto> search(
			@RequestParam Optional<String> category,
			@RequestParam Optional<String> seller,
			@RequestParam Optional<String> keyword
			) {
		return service.search(category, seller, keyword);
	}
	
	@GetMapping("{id}")
	ProductDto findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	ProductDto create(@RequestBody ProductInputDto dto) {
		return service.save(dto);
	}

	@PutMapping
	ProductDto update(@RequestBody ProductInputDto dto) {
		return service.save(dto);
	}
	
	@PutMapping("{id}/photos")
	ProductDto uploadPhotos(@PathVariable int id, @RequestParam MultipartFile[] files) {
		return service.uploadPhoto(id, files);
	}
}
