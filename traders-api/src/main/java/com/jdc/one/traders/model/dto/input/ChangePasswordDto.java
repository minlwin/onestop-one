package com.jdc.one.traders.model.dto.input;

public record ChangePasswordDto(
		String oldPass,
		String newPass
		) {

}
