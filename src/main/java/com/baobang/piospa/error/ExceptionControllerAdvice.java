package com.baobang.piospa.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.model.DataResult;

/**
  * @author BaoBang
  * @Created Apr 17, 2018
  * 
  */
@ControllerAdvice
@RestController
public class ExceptionControllerAdvice{
 
	@ExceptionHandler(Exception.class)
	public DataResult<Object> exceptionHandler(Exception ex) {
		return new DataResult<Object>(
				HttpStatus.NOT_FOUND.value(),
				ex.getMessage(),
				null);
	}
}