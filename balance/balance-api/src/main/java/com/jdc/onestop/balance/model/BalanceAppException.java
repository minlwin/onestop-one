package com.jdc.onestop.balance.model;

public class BalanceAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected BalanceAppException() {
		super();
	}

	protected BalanceAppException(String message, Throwable cause) {
		super(message, cause);
	}

	protected BalanceAppException(String message) {
		super(message);
	}

	protected BalanceAppException(Throwable cause) {
		super(cause);
	}

	
}
