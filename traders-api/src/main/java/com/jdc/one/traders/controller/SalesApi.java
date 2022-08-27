package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.input.SaleInputDto;
import com.jdc.one.traders.model.dto.output.SaleSummary;
import com.jdc.one.traders.model.dto.output.SaleVO;
import com.jdc.one.traders.model.service.SaleService;

@RestController
@RequestMapping("sale")
public class SalesApi {
	
	@Autowired
	private SaleService service;
	
	@GetMapping
	List<SaleSummary> search(
		@RequestParam Optional<String> seller,
		@RequestParam Optional<String> buyer,
		@RequestParam Optional<String> status,
		@RequestParam Optional<String> keyword
			) {
		return service.search(seller, buyer, status, keyword);
	}

	@GetMapping("{id}")
	SaleVO findById(@PathVariable long id) {
		return service.findById(id);
	}
	
	@PostMapping
	SaleVO order(@RequestBody SaleInputDto dto) {
		return service.order(dto);
	}
	
}
