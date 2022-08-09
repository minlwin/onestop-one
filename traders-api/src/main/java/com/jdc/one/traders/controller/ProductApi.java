package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.output.ProductDto;

@RestController
@RequestMapping("product")
public class ProductApi {

	@GetMapping
	List<ProductDto> search(
			@RequestParam Optional<Integer> category,
			@RequestParam Optional<Integer> seller,
			@RequestParam Optional<String> keyword
			) {
		return List.of();
	}
}
