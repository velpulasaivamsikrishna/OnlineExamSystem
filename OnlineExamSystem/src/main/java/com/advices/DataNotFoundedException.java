package com.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class DataNotFoundedException extends Exception
{
	public DataNotFoundedException(String message){
	        super(message);
	    }
}
