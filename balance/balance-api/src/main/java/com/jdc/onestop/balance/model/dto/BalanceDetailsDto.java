package com.jdc.onestop.balance.model.dto;

public record BalanceDetailsDto(
	int id,
	String article,
	int quentity,
	int unitPrice
		) {
	
	public int total() {
		return quentity * unitPrice;
	}
}
