package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.product.util.CommonUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<?> handleException(Exception exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		return CommonUtils.createErrorResponseMessage("Method Not Allowed!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(exception = HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		return CommonUtils.createErrorResponseMessage("Required request body is missing!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(exception = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		return CommonUtils.createErrorResponseMessage("Http request method is not supported!", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(exception = AlreadyExistsException.class)
	public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(exception = AlreadyRestoredException.class)
	public ResponseEntity<?> handleAlreadyRestoredException(AlreadyRestoredException exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(exception = ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException exception) {
		return CommonUtils.createErrorResponse(exception.getErrors(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(exception = UserVerificationException.class)
	public ResponseEntity<?> handleUserVerificationException(UserVerificationException exception) {
		return CommonUtils.createErrorResponseMessage(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
}
