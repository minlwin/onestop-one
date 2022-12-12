package com.jdc.onestop.balance.model.dto;

public record ErrorDto(
		Type type,
		String message
		) {

	public enum Type {
		Validation, Business, Platform
	}
}
