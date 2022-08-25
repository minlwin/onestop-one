package com.jdc.one.traders.model.dto.input;

import com.jdc.one.traders.model.dto.entity.Sale.Status;

public record SaleStatusInputDto(
	int updateUser,
	Status status,
	String remark
		) {

}
