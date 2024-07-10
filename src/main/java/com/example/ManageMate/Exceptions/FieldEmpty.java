package com.example.ManageMate.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldEmpty extends RuntimeException {
    private String errorMessage;
    private String errorCode;
}
