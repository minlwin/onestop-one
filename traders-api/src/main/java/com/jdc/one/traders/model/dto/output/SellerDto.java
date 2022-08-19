package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Account;

public record SellerDto(
		int id,
		String name
		) {
	
	public static SellerDto from(Account account) {
		return new SellerDto(account.getId(), account.getName());
	}
}
