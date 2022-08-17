package com.jdc.one.traders.model.dto.input;

import java.util.List;

import com.jdc.one.traders.model.dto.entity.Account;

public record AccountProfile(
	int id,
	String name,
	String email,
	PersonalInfoDto personalInfo,
	List<BankingDto> bankingInfo,
	List<AddressDto> address
		) {
	
	public static AccountProfile from(Account entity) {
		
		if(null == entity.getProfile()) {
			return new AccountProfile(
					entity.getId(), 
					entity.getName(), 
					entity.getEmail(),
					null, 
					null, 
					null);
		}
				
		return new AccountProfile(
				entity.getId(), 
				entity.getName(), 
				entity.getEmail(),
				PersonalInfoDto.from(entity.getProfile()), 
				entity.getProfile().getBankingInfo().stream()
					.map(BankingDto::from).toList(), 
				entity.getProfile().getAddress().stream()
					.map(AddressDto::from).toList());
	}

}
