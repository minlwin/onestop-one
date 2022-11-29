package com.jdc.onestop.directory.api.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.ErrorResult;
import com.jdc.onestop.directory.model.dto.ErrorResult.ErrorType;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResult handle(ServiceDirectoryAppException exception) {
		return new ErrorResult(exception.getType(), exception.getMessages());
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResult handle(ConstraintViolationException exception) {
		var messages = exception.getConstraintViolations().stream().map(a -> a.getMessage()).toList();
		return new ErrorResult(ErrorType.Business, messages);
	}
}
