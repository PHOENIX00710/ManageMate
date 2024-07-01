package com.example.ManageMate.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse{
    private String errorMessage;
    private String errorCode;
    private HttpStatus httpStatus;
}
