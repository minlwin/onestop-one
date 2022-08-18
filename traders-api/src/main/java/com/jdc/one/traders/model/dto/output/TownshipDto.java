package com.jdc.one.traders.model.dto.output;

import com.jdc.one.traders.model.dto.entity.Township;

public record TownshipDto(
		int id,
		String name,
		int divisionId,
		String division
		) {

	public TownshipDto(Township t) {
		this(t.getId(), t.getName(), t.getDivision().getId(), t.getDivision().getName());
	}
}
