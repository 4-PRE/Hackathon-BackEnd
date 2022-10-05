package com.example.hackathon2022.global.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleBusinessLogic(CustomException exception) {
        exception.printStackTrace();

        return new ResponseEntity<>(new ErrorResponse(exception.getStatus().value(),
                exception.getMessage()), exception.getStatus());
    }

}
