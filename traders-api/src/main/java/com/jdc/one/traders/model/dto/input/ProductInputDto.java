package com.jdc.one.traders.model.dto.input;

import java.util.List;
import java.util.Map;

import com.jdc.one.traders.model.dto.entity.Product.Condition;

public record ProductInputDto(
		int id,
		String name,
		String category,
		int sellerId,
		Condition condition,
		int price,
		Map<String, String> features,
		List<String> photos		
		) {

}
