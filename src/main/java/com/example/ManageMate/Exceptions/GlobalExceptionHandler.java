package com.example.ManageMate.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<ErrorResponse> handleException(CustomError customError){
        ErrorResponse errorResponse= new ErrorResponse(
                customError.getErrorMessage(),
                customError.getErrorCode(),
                HttpStatus.BAD_GATEWAY
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
