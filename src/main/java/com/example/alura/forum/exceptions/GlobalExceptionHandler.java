package com.example.alura.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TopicoNotFoundException.class)
    public ResponseEntity<Issue> handleTopicoNotFoundException(TopicoNotFoundException e){
        var issue = new Issue(e.getMessage(), HttpStatus.NOT_FOUND, new Date());

        return new ResponseEntity<>(issue, HttpStatus.NOT_FOUND);
    }
}
