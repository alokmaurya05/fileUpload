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

		CustomErrorResponse error = new CustomErrorResponse();
		error.setTimestamp(LocalDateTime.now());
		error.setTitle("Server Error "+request.getContextPath());
		error.setDescription("Not able to process request "+ex.getClass());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<CustomErrorResponse> productHandler(Exception ex, WebRequest request) {

		CustomErrorResponse error = new CustomErrorResponse();
        error.setTimestamp(LocalDateTime.now());
        error.setTitle("Product Proccessing Exception");
        error.setDescription(ex.getMessage()+"--"+request.toString());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
