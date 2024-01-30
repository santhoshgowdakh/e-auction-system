package com.cg.eauction.exceptions;

public class FeedbackNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FeedbackNotFoundException(String message) {
		super(message);
	}
	
}
