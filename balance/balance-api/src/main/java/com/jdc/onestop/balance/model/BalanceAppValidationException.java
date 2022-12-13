package com.jdc.onestop.balance.model;

import java.util.List;

public class BalanceAppValidationException extends BalanceAppException{

	private List<String> errors;
	
	public BalanceAppValidationException(List<String> errors) {
		this.errors = errors;
	}

	private static final long serialVersionUID = 1L;
	
	public List<String> getMessages() {
		return errors;
	}
	
}
