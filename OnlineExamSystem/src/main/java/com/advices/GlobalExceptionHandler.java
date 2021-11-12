package com.advices;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundedException.class)
    public ResponseEntity<?> dataNotFoundedException(DataNotFoundedException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

	
	  @ExceptionHandler(Exception.class) 
	  public ResponseEntity<?> globalExcpetionHandler(Exception ex, WebRequest request) { ErrorDetails
	  errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),request.getDescription(false)); 
	  return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR); 
	  }
	 
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<?> customValidationError(MethodArgumentNotValidException ex)
	  {
		  ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(), ex.getBindingResult().getFieldError().getDefaultMessage(), 
				  "validation error");
		  return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	  }
}
