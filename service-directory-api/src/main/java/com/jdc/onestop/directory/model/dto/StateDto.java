package com.jdc.onestop.directory.model.dto;

public record StateDto(
	int id,
	String name,
	String burmeseName,
	String region,
	String capital,
	boolean deleted
		) {

}
