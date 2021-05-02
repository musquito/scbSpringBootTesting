package com.scb.bookstore.exception;

public class UnexpectedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4657807883613537795L;

	public UnexpectedException(String errorMessage) {
        super(errorMessage);
    }
}
