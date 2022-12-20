package com.jdc.onestop.balance.api.advice;

import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.onestop.balance.model.BalanceAppBusinessException;
import com.jdc.onestop.balance.model.BalanceAppValidationException;
import com.jdc.onestop.balance.model.dto.ApiResult;
import com.jdc.onestop.balance.model.dto.ErrorDto;
import com.jdc.onestop.balance.model.dto.ErrorDto.Type;

@RestControllerAdvice
public class BalanceApiExceptionHandlerAdvices {

	@ExceptionHandler
	ApiResult<ErrorDto> handle(BalanceAppValidationException e) {
		return ApiResult.error(new ErrorDto(Type.Validation, e.getMessages()));
	}
	
	@ExceptionHandler
	ApiResult<ErrorDto> handle(BalanceAppBusinessException e) {
		return ApiResult.error(new ErrorDto(Type.Validation, List.of(e.getMessage())));
	}
	
}
