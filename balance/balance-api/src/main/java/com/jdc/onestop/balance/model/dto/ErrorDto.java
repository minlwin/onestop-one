package com.jdc.onestop.balance.model.dto;

import java.util.List;

public record ErrorDto(
		Type type,
		List<String> messages
		) {

	public enum Type {
		Validation, Business, Platform
	}
}
