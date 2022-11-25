package com.jdc.onestop.directory.model.dto;

import com.jdc.onestop.directory.model.entity.Township;

public record TownshipDto(
		int id,
		String name,
		String burmeseName,
		DistrictDto district,
		boolean deleted
		) {

	public static TownshipDto from(Township entity) {
		return new TownshipDto(
				entity.getId(),
				entity.getName(), 
				entity.getBurmeseName(), 
				DistrictDto.from(entity.getDistrict()), 
				entity.isDeleted());
	}
}
