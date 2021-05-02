package com.scb.bookstore.exception;

import lombok.Getter;

@Getter
public class DatabaseException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8174900010677479575L;
	private String developerMessage;

    public DatabaseException(String errorMessage, String developerMessage) {
        super(errorMessage);
        this.developerMessage = developerMessage;
    }
}
