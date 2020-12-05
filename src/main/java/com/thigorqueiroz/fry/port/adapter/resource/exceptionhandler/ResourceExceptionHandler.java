package com.thigorqueiroz.fry.port.adapter.resource.exceptionhandler;

import com.thigorqueiroz.fry.domain.model.common.BusinessException;
import com.thigorqueiroz.fry.domain.model.common.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {
    //TODO: REVIEW ALL HTTP STATUS, MAYBE I SHOULD CREATE A MECANISM TO HANDLE GENERIC AND SPECIFIC EXCEPTIONS
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, ResponseEntity.badRequest().body(ex.getMessage()), HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleEntityNotFoundException(BusinessException ex, WebRequest request) {
        return handleExceptionInternal(ex, ResponseEntity.badRequest().body(ex.getMessage()), HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request);
    }
}
