package com.example.ManageMate.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomError extends RuntimeException{
    private String errorMessage;
    private String errorCode;
}
