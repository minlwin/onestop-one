package com.jdc.onestop.directory.api.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.onestop.directory.model.ServiceDirectoryAppException;
import com.jdc.onestop.directory.model.dto.ErrorResult;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResult handle(ServiceDirectoryAppException exception) {
		return new ErrorResult(exception.getType(), exception.getMessages());
	}
}
