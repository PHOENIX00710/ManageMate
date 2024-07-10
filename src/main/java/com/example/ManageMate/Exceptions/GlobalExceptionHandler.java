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
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponse> handleException(NotFound notFound){
        ErrorResponse errorResponse= new ErrorResponse(
                notFound.getErrorMessage(),
                notFound.getErrorCode(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FieldEmpty.class)
    public ResponseEntity<ErrorResponse> handleException(FieldEmpty fieldEmpty){
        ErrorResponse errorResponse= new ErrorResponse(
                fieldEmpty.getErrorMessage(),
                fieldEmpty.getErrorCode(),
                HttpStatus.NO_CONTENT
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }
}
