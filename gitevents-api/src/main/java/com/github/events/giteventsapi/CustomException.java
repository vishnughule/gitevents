package com.github.events.giteventsapi;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus errorCode;

	public CustomException() {
		// TODO Auto-generated constructor stub
	}

	public CustomException(HttpStatus error,String message) {
		this.message = message;
		this.errorCode =error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

}
