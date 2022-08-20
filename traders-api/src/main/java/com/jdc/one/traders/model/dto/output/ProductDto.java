package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.one.traders.model.dto.entity.Product;
import com.jdc.one.traders.model.dto.entity.Product.Condition;

public record ProductDto(
		int id,
		String name,
		int categoryId,
		String category,
		int sellerId,
		String seller,
		Condition condition,
		int price,
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
		LocalDateTime publishAt,
		Map<String, String> features,
		List<String> photos,
		boolean soldOut
		) {

	public ProductDto(Product p) {
		this(p.getId(), p.getName(), p.getCategory().getId(), p.getCategory().getName(), p.getSeller().getId(), p.getSeller().getName(), 
				p.getCondition(), p.getPrice(), p.getPublishAt(), p.getFeatures(), p.getPhotos(), p.isSoldOut());
	}
}
