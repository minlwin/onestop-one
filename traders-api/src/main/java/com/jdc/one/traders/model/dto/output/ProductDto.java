package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ProductDto(
		int id,
		String name,
		int categoryId,
		String category,
		int sellerId,
		String seller,
		String condition,
		int price,
		LocalDateTime publishAt,
		Map<String, String> features,
		List<String> photos
		) {

}
