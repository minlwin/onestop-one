package com.jdc.one.traders.model.dto.input;

import com.jdc.one.traders.model.dto.entity.Address;

public record AddressDto(
		int id,
		String name,
		String address,
		int township,
		boolean deleted
		) {
	
	public static AddressDto from(Address entity) {
		return new AddressDto(
				entity.getId(),
				entity.getName(),
				entity.getAddress(),
				entity.getTownship().getId(),
				entity.isDeleted()
		);
	}
}
