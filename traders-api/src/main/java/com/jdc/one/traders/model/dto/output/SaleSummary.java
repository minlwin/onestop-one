package com.jdc.one.traders.model.dto.output;

import java.util.List;

import com.jdc.one.traders.model.dto.entity.Sale.Status;

public record SaleSummary(
	long id,
	int productId,
	String product,
	List<String> photos,
	int ownerId,
	String owner,
	int buyerId,
	String buyer,
	int price,
	Status status
		) {

}
