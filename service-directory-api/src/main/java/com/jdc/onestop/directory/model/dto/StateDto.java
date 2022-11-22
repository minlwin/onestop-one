package com.jdc.onestop.directory.model.dto;

import com.jdc.onestop.directory.model.entity.State;

public record StateDto(
	int id,
	String name,
	String burmeseName,
	String region,
	String capital,
	boolean deleted
		) {

	public static StateDto from(State entity) {
		return new StateDto(
				entity.getId(), 
				entity.getName(), entity.getBurmeseName(), 
				entity.getRegion(), entity.getCapital(), entity.isDeleted());
	}

}
