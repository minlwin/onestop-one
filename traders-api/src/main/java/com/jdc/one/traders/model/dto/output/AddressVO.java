package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Address;

public record AddressVO(
		int id,
		String name,
		String address,
		int township,
		String townshipName,
		int division,
		String divisionName,
		boolean deleted
		) {

	public static AddressVO from(Address entity) {
		return new AddressVO(
				entity.getId(),
				entity.getName(),
				entity.getAddress(),
				entity.getTownship().getId(),
				entity.getTownship().getName(),
				entity.getTownship().getDivision().getId(),
				entity.getTownship().getDivision().getName(),
				entity.isDeleted()
		);
	}
	
}
