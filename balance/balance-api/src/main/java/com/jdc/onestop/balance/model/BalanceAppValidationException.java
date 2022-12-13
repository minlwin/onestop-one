package com.jdc.onestop.balance.model;

import java.util.List;

import org.springframework.validation.FieldError;

public class BalanceAppValidationException extends BalanceAppException{

	private List<FieldError> errors;
	
	public BalanceAppValidationException(List<FieldError> errors) {
		this.errors = errors;
	}

	private static final long serialVersionUID = 1L;
	
	public List<String> getMessages() {
		return errors.stream().map(FieldError::getDefaultMessage).toList();
	}
	
}
