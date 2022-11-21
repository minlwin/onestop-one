package com.jdc.onestop.directory.model;

import java.util.List;

public class ServiceDirectoryAppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;
	
	public ServiceDirectoryAppException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
