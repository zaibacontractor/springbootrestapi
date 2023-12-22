package com.example.useradvisormanagement.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7053912676684757403L;
	Integer code = HttpStatus.BAD_REQUEST.value();

	public ResourceNotFoundException(Integer code, String message) {

		super(message);
		this.code = code;
	}

	public ResourceNotFoundException(String message) {

		super(message);

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}

