package com.scb.bookstore.controller;

import com.scb.bookstore.exception.*;
import com.scb.bookstore.model.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceController {

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiErrorResponse handleAuthenticationException(AuthenticationException e) {
		return new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e.getDeveloperMessage());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ApiErrorResponse handleUsernameNotFoundException(UsernameNotFoundException e) {
		return new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
	}

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ApiErrorResponse handleDataNotFoundException(DataNotFoundException e) {
		return new ApiErrorResponse(HttpStatus.NO_CONTENT.value(), e.getMessage(), null);
	}

	@ExceptionHandler(DatabaseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleDatabaseException(DatabaseException e) {
		return new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getDeveloperMessage());
	}

	@ExceptionHandler(UnexpectedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse handleDuplicatedDataException(UnexpectedException e) {
		return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.getMessage());
	}

	@ExceptionHandler(ExternalRequestException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse handleExternalRequestException(ExternalRequestException e) {
		return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.getMessage());
	}

}
