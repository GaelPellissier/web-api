package com.wapps.homestock.webapi;

import com.wapps.homestock.lib.exception.AlreadyExistsException;
import com.wapps.homestock.lib.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity notFoundException(NotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity alreadyExistsException(AlreadyExistsException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}
