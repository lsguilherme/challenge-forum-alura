package com.example.alura.forum.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record Issue(String message, HttpStatus statusCode, Date timestamp) {
}
