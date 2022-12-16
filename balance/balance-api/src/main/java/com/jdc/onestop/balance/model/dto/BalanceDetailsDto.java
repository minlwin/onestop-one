package com.jdc.onestop.balance.model.dto;

import com.jdc.onestop.balance.model.entity.BalanceDetails;

public record BalanceDetailsDto(
	int id,
	String article,
	int quentity,
	int unitPrice
		) {
	
	public int total() {
		return quentity * unitPrice;
	}
	
	public static BalanceDetailsDto from(BalanceDetails entity) {
		return new BalanceDetailsDto(
				entity.getId(), 
				entity.getArticle(), 
				entity.getQuentity(), 
				entity.getUnitPrice());
	}
}
