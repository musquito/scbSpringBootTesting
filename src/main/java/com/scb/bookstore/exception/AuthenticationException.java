package com.scb.bookstore.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1486760298025223380L;
	private String developerMessage;

    public AuthenticationException(String errorMessage, String developerMessage) {
        super(errorMessage);
        this.developerMessage = developerMessage;
    }
}
