package com.awin.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*This class is use to send exceptional information */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> handler(Exception ex, WebRequest request) {

		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setTitle("Server Error");
		errors.setDescription("Not able to process request "+ex.getMessage());
		errors.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
