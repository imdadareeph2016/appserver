package com.imdadareeph.appserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author imdadareeph
 * @Created on 2018-MAY-02
 */
@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<ExceptionResponse> resourceConflict(ResourceConflictException ex) {
    ExceptionResponse response = new ExceptionResponse();
    response.setErrorCode("Conflict");
    response.setErrorMessage(ex.getMessage());
    return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
  }
}
