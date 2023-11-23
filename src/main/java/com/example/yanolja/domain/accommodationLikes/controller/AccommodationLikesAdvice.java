package com.example.yanolja.domain.user.controller;

import com.example.yanolja.domain.user.exception.EmailDuplicateError;
import com.example.yanolja.domain.user.exception.InvalidEmailException;
import com.example.yanolja.domain.user.exception.InvalidPhonenumberError;
import com.example.yanolja.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(value = {
        EmailDuplicateError.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleEmailDuplicateError(
        EmailDuplicateError exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        InvalidEmailException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleInvalidEmailException(
        InvalidEmailException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }

    @ExceptionHandler(value = {
        InvalidPhonenumberError.class
    })
    public ResponseEntity<ResponseDTO<Object>> handlePhonenumberException(
        InvalidPhonenumberError exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ResponseDTO.res(HttpStatus.BAD_REQUEST,
                exception.getMessage()));
    }
}