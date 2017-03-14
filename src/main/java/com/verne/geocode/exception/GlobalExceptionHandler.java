package com.verne.geocode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler that returns correct status code when an exception occurs
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler for when an IllegalArgumentException occurs
     * @param e
     * @param request
     * @return Bad request ResponseEntity
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> illegalArgumentException(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, null, null, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handler for when a GeoLocationServiceException occurs
     * @param e
     * @param request
     * @return Internal server error ResponseEntity
     */
    @ExceptionHandler({GeoLocationServiceException.class})
    public ResponseEntity<Object> geoLocationServiceException(RuntimeException e, WebRequest request) {
        return new ResponseEntity(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
