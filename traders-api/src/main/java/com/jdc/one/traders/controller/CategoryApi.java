package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.output.CategoryDto;
import com.jdc.one.traders.model.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryApi {
	

	@Autowired
	private CategoryService service;

	@GetMapping
	List<CategoryDto> search(@RequestParam Optional<Integer> limit) {
		return service.topCategories(limit);
	}
	
}
