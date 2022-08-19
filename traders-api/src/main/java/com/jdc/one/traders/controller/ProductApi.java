package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping
	ProductDto create(@RequestBody ProductInputDto dto) {
		return service.save(dto);
	}

	@PutMapping
	ProductDto update(@RequestBody ProductInputDto dto) {
		return service.save(dto);
	}
}
