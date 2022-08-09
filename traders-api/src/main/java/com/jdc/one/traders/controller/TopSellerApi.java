package com.jdc.one.traders.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.one.traders.model.dto.output.TopSellerDto;

@RestController
@RequestMapping("seller")
public class TopSellerApi {

	@GetMapping("top")
	List<TopSellerDto> topSellers(
			@RequestParam Optional<Integer> limit) {
		return List.of();
	}
}
