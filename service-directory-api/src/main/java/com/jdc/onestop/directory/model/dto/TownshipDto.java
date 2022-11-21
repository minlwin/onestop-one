package com.jdc.onestop.directory.model.dto;

public record TownshipDto(
		int id,
		String name,
		String burmeseName,
		DistrictDto district,
		boolean deleted
		) {

}
