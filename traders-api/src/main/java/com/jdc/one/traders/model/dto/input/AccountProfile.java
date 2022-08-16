package com.jdc.one.traders.model.dto.input;

import java.util.List;

import com.jdc.one.traders.model.dto.entity.Account;

public record AccountProfile(
	int id,
	String name,
	PersonalInfoDto personalInfo,
	List<BankingDto> bankingInfo,
	List<AddressDto> address
		) {
	
	public static AccountProfile from(Account entity) {
				
		return new AccountProfile(
				entity.getId(), 
				entity.getName(), 
				PersonalInfoDto.from(entity.getProfile()), 
				entity.getProfile().getBankingInfo().stream()
					.map(BankingDto::from).toList(), 
				entity.getProfile().getAddress().stream()
					.map(AddressDto::from).toList());
	}

}
