package com.cg.eauction.exceptions;

public class InvalidUserNameException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidUserNameException(String message) {
		super(message);
	}
	
}
