package com.jdc.one.traders.model.dto.input;

import com.jdc.one.traders.model.dto.entity.BankingInfo;

public record BankingDto(
		int id,
		String type,
		String accountName,
		String accountNumber,
		boolean deleted
		) {

	public BankingInfo getEntity() {
		return new BankingInfo(id, type, accountName, accountNumber, deleted);
	}
	
	public static BankingDto from(BankingInfo entity) {
		return new BankingDto(
				entity.getId(), 
				entity.getType(), 
				entity.getAccountName(), 
				entity.getAccountNumber(), 
				entity.isDeleted());
	}
}
