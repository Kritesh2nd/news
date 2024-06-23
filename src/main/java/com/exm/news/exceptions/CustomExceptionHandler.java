package com.exm.news.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.server.NotAcceptableStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatusCode;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;


@RestControllerAdvice
public class CustomExceptionHandler {

/*


 
invalid input format : bad request : 400 
empty input field : ||

invalid basic authentication : forbidden : 403
invalid token :  ||
expired token :  ||

unauthorized : access denied : 406 
unauthorized : not acceptable : 406

 * */
	@ExceptionHandler(Exception.class)
	public ProblemDetail handelSecurityException(Exception ex) {
		
		System.out.println("which exception: "+ex.toString());
		ProblemDetail errorDetail = null;
		
	
		if(ex instanceof BadCredentialsException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Invalid input"); 
		}
		
		if(ex instanceof IllegalArgumentException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Invalid Credentials"); 
		}
		
		if(ex instanceof SignatureException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Expired or Invalid token"); 
		}
		
		if(ex instanceof ExpiredJwtException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Invalid username or password"); 
		}
		
		errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "ex"); 
//		
//		
//		
//		if(ex instanceof Forbidden) {
//			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "Authentication Failure"); 
//		}
//		
//		 if(ex instanceof AccessDeniedException) {
//			 errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406), ex.getMessage());
//			 errorDetail.setProperty("access_denied_reason", "Unauthorized!");
//		 }
//		 
//		 if(ex instanceof NotAcceptableStatusException) {
//			 errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406), ex.getMessage());
//			 errorDetail.setProperty("access_denied_reason", "Not Acceptable!");
//		 }
		 
		 return errorDetail;
	}
}





























