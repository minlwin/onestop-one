package com.jdc.onestop.directory.model.dto;

public record DistrictDto(
		int id,
		String name,
		String burmeseName,
		StateDto state,
		boolean deleted
		) {

}
