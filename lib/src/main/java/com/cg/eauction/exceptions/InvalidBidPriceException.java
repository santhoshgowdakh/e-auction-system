package com.cg.eauction.exceptions;

public class InvalidBidPriceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidBidPriceException(String message) {
		super(message);
	}

	
}
