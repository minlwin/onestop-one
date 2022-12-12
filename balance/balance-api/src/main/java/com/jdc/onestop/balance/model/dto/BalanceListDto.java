package com.jdc.onestop.balance.model.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record BalanceListDto(
	int id,
	CategoryDto category,
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	LocalDate issueAt,
	int totalCount,
	int totalAmount,
	String remark,
	boolean deleted
		) {

}
