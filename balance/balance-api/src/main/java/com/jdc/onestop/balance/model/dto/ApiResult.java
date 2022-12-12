package com.jdc.onestop.balance.model.dto;

public record ApiResult<T>(
		boolean success,
		T data
		) {
	
	public static ApiResult<ErrorDto> error(ErrorDto dto) {
		return new ApiResult<>(false, dto);
	}
	
	public static<T> ApiResult<T> from(T data) {
		return new ApiResult<>(true, data);
	}
}
