package com.product.exception;

import java.util.Map;

public class ValidationException extends Exception {
	
	Map<String, Object> errors;

	public ValidationException(Map<String, Object> errors) {
		super("Validation Failed!");
		this.errors = errors;
	}

	public Map<String, Object> getErrors() {
		return errors;
	}

	public ValidationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
