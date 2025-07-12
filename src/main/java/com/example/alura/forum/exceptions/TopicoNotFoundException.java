package com.example.alura.forum.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class TopicoNotFoundException extends RuntimeException {

    private final Issue issue;

    public TopicoNotFoundException(String message) {
        super(message);
        this.issue = new Issue(message, HttpStatus.NOT_FOUND, new Date());
    }
}
