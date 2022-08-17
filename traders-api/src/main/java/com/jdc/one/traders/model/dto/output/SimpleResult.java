package com.jdc.one.traders.model.dto.output;

public record SimpleResult(
		String message,
		boolean success
		) {
	
	public static SimpleResult success(String message) {
		return new SimpleResult(message, true);
	}

	public static SimpleResult fails(String message) {
		return new SimpleResult(message, false);
	}
}
