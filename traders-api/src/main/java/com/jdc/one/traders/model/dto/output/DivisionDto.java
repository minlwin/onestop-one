package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Division;

public record DivisionDto(
		int id,
		String name
		) {
	
	public DivisionDto(Division division) {
		this(division.getId(), division.getName());
	}
}
