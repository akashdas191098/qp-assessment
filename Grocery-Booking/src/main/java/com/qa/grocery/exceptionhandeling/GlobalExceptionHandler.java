package com.qa.grocery.exceptionhandeling;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qa.grocery.constants.ErrorConstants;

@ComponentScan(basePackages = "com.qa.grocery")
@RestControllerAdvice(basePackages = "com.qa.grocery")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorInfo> globalExceptionHandler(APIException ex){
		ErrorInfo apiResponse = new ErrorInfo(ErrorConstants.status,ex.getMessage());
		
		return new ResponseEntity<ErrorInfo>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
