package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Sale;

public record SaleVO(
	long id,
	ProductDto product,
	int buyerId
		) {

	public static SaleVO from(Sale sale) {
		return null;
	}
}
