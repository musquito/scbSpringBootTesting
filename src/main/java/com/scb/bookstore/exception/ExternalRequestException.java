package com.scb.bookstore.exception;

import lombok.Getter;

@Getter
public class ExternalRequestException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4116485891174923069L;
	private String developerMessage;

    public ExternalRequestException(String errorMessage, String developerMessage) {
        super(errorMessage);
        this.developerMessage = developerMessage;
    }
}
