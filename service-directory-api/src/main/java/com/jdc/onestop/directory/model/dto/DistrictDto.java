package com.jdc.onestop.directory.model.dto;

import com.jdc.onestop.directory.model.entity.District;

public record DistrictDto(
		int id,
		String name,
		String burmeseName,
		StateDto state,
		boolean deleted
		) {

	public static DistrictDto from(District entity) {
		return new DistrictDto(
				entity.getId(), 
				entity.getName(), 
				entity.getBurmeseName(), 
				StateDto.from(entity.getState()), 
				entity.isDeleted());
	}

}
