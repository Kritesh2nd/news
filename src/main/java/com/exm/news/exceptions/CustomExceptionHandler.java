package com.exm.news.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.http.ProblemDetail;

import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class,SignatureException.class,ExpiredJwtException.class,MethodArgumentNotValidException.class,AccessDeniedException.class})
    public ProblemDetail handleSecurityException(Exception ex) {
        
    	ProblemDetail errorDetail = null;
    	
    	System.out.println("er in advice: "+ex);
        
    	if (ex instanceof BadCredentialsException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("reason", "Username or password dosen't match");
        }
    	
    	if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("reason", "Invalid token");
        }
    	
    	if (ex instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("reason", "Expired token");
        }
    	
    	if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("reason", "Username or password is incorrect");
        }
//    	if (ex instanceof MethodArgumentNotValidException) {
//            errorDetail = ProblemDetail
//                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
//            errorDetail.setDetail("Invalid value in input field");
//            errorDetail.setProperty("reason", "Invalid Input");
//        }

        return errorDetail;
    }

}
