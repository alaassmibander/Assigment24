package com.example.assigment24.Exception;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class APIException extends RuntimeException {

    private String message;
}