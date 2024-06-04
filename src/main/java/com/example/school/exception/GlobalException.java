package com.example.school.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
/*
 * types of expceptions
 * 
 * MethodArgumentNotValidException
 * NoSuchElementException
 * RuntimeException
 * DuplicateKeyException
 * 
 * */
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        String message = (ex.getMessage()!=null)?ex.getMessage():"Invalid request";
        errorMap.put("error", message);
        return errorMap;
	}
	 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class) 
	public Map<String, String> handleNoSuchElementException(NoSuchElementException ex){
		Map<String,String> errorMap = new HashMap<>();
		String message = (ex.getMessage()!=null)?ex.getMessage():"Resources not found";
		errorMap.put("error", message);
		return errorMap;
	}

	
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(RuntimeException.class)
	public Map<String, String> handleRuntimeException(RuntimeException ex){
		Map<String,String> errorMap = new HashMap<>();
		String message = (ex.getMessage()!=null)?ex.getMessage():"Request cannot be accepted";
		errorMap.put("error", message);
		return errorMap;
	}

	
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	@ExceptionHandler(DuplicateKeyException.class)
	public Map<String, String> handleIllegalArgumentException(IllegalArgumentException ex){
		Map<String,String> errorMap = new HashMap<>();
		String message = (ex.getMessage()!=null)?ex.getMessage():"I am a teapot";
		errorMap.put("error", message);
		return errorMap;
	}
	
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(DuplicateElementException.class)
	public Map<String, String> handelDuplicateElementException(RuntimeException ex){
		Map<String, String> errorMap = new HashMap<>();
		String message = (ex.getMessage()!=null)?ex.getMessage():"Cannot add duplicate element";
		errorMap.put("errpor", message);
		return errorMap;
	}	

}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
