package com.example.alura.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TopicoNotFoundException.class)
    public ResponseEntity<Issue> handleTopicoNotFoundException(TopicoNotFoundException e){
        var issue = new Issue(e.getMessage(), HttpStatus.NOT_FOUND, new Date());

        return new ResponseEntity<>(issue, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Issue>> handleValidationExceptions(MethodArgumentNotValidException e){
        List<Issue> issues = e.getBindingResult().getAllErrors().stream()
                .map(error ->{
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return new Issue(fieldName + ": " + errorMessage, HttpStatus.BAD_REQUEST, new Date());
                })
                .toList();
        return new ResponseEntity<>(issues, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Issue> handleException(Exception e){
        var issue = new Issue(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date());
        return new ResponseEntity<>(issue, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
