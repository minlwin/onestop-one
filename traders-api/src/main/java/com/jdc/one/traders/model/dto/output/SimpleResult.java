package com.jdc.one.traders.model.dto.output;

public record PhotoUploadResult(
		String message,
		boolean success
		) {
	
	public static PhotoUploadResult success(String message) {
		return new PhotoUploadResult(message, true);
	}

	public static PhotoUploadResult fails(String message) {
		return new PhotoUploadResult(message, false);
	}
}
