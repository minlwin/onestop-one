package com.jdc.one.traders.model.dto.output;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdc.one.traders.model.dto.entity.Product.Condition;
import com.jdc.one.traders.model.dto.entity.Sale;
import com.jdc.one.traders.model.dto.entity.Sale.Status;

public record SaleSummary(
	long id,
	int productId,
	String product,
	String category,
	Condition condition,
	int sellerId,
	String seller,
	int buyerId,
	String buyer,
	int price,
	Status status,
	@JsonFormat(pattern = "yyyy-MM-dd HH:ss")
	LocalDateTime statusChangeDate
		) {
	
	public SaleSummary(Sale entity) {
		this(
				entity.getId(),
				entity.getProduct().getId(),
				entity.getProduct().getName(),
				entity.getProduct().getCategory().getName(),
				entity.getProduct().getCondition(),
				entity.getProduct().getSeller().getId(),
				entity.getProduct().getSeller().getName(),
				entity.getBuyer().getId(),
				entity.getBuyer().getName(),
				entity.getProduct().getPrice(),
				entity.getStatus(),
				entity.getUpdateDate()
		);
	}

}
