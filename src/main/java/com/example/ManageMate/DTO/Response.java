package com.example.ManageMate.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Boolean success;
    private String message;
    private Object obj;
}
