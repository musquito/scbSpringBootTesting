package com.scb.bookstore.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3620081545434243252L;
	private String developerMessage;

    public DataNotFoundException(String errorMessage, String developerMessage) {
        super(errorMessage);
        this.developerMessage = developerMessage;
    }
}
