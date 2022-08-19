package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.output.SellerDto;
import com.jdc.one.traders.model.dto.output.TopSellerDto;
import com.jdc.one.traders.model.service.AccountService;

@RestController
@RequestMapping("seller")
public class TopSellerApi {
	
	@Autowired
	private AccountService service;

	@GetMapping("top")
	List<TopSellerDto> topSellers(
			@RequestParam Optional<Integer> limit) {
		return service.getTopSellers(limit);
	}
	
	@GetMapping("top/{id}")
	SellerDto findById(@PathVariable int id) {
		return service.findToSellerById(id);
	}
}
