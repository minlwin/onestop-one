package com.jdc.onestop.directory.model.dto;

import java.util.List;

public record ErrorResult(
		ErrorType type,
		List<String> messages
		) {

	public enum ErrorType {
		Validation, Business, Platform
	}
}
