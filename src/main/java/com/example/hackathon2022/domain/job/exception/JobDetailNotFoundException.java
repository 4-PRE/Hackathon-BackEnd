package com.example.hackathon2022.domain.job.exception;

import com.example.hackathon2022.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class JobDetailNotFoundException extends CustomException {

    public JobDetailNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
