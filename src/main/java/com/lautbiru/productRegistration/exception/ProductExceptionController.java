package com.lautbiru.productRegistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception) {
        return new ResponseEntity<>("Product Not Found!!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DuplicationIdException.class)
    public ResponseEntity<Object> exception(DuplicationIdException exception) {
        return new ResponseEntity<>("Product ID is duplicated!!", HttpStatus.BAD_REQUEST);
    }
}
