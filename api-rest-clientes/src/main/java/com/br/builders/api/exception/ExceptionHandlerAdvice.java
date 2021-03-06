package com.br.builders.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleException(BusinessException e) {
		return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
	}
}
