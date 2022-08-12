package com.jdc.one.traders.model.dto.output;

public record LoginResultDto(
		LoginUserDto loginUser,
		String message,
		boolean success
		) {
	
	public static LoginResultDto success(LoginUserDto loginUser) {
		return new LoginResultDto(loginUser, null, true);
	}
	
	public static LoginResultDto error(String message) {
		return new LoginResultDto(null, message, false);
	}	

}
