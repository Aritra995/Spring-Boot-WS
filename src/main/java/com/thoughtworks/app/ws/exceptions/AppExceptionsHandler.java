package com.thoughtworks.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.thoughtworks.app.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){

        return getErrorMessageResponseEntity(ex);
    }

	@ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request){

        return getErrorMessageResponseEntity(ex);
    }

    private ResponseEntity<Object> getErrorMessageResponseEntity(Exception ex) {
        String errorMessageDescription = ex.getLocalizedMessage();

        if ( errorMessageDescription == null ) errorMessageDescription = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
