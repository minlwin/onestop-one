package com.jdc.onestop.directory.model;

import java.util.List;

import org.springframework.validation.FieldError;

import com.jdc.onestop.directory.model.dto.ErrorResult.ErrorType;

public class ServiceDirectoryAppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private ErrorType type;
	private List<String> messages;
	
	public ServiceDirectoryAppException(List<FieldError> errors) {
		type = ErrorType.Validation;
		messages = errors.stream()
				.map(FieldError::getDefaultMessage).toList();
	}
	
	public ServiceDirectoryAppException(String message) {
		type = ErrorType.Business;
		messages = List.of(message);
	}
	
	public ServiceDirectoryAppException(ErrorType type, List<String> messages) {
		super();
		this.type = type;
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}
	
	public ErrorType getType() {
		return type;
	}

}
