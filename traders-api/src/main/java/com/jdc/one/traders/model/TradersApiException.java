package com.jdc.one.traders.model;

public class TradersApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TradersApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public TradersApiException(String message) {
		super(message);
	}

	public TradersApiException(Throwable cause) {
		super(cause);
	}

	
}
