package com.github.sibyamadou.qualitytrack.controller;

import com.github.sibyamadou.qualitytrack.service.DuplicateBugException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.github.sibyamadou.qualitytrack.service.BugNotFoundException;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateBugException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDuplicate(DuplicateBugException exception) {
        return Map.of("error", exception.getMessage());
    }

    @ExceptionHandler(BugNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(BugNotFoundException exception) {
        return Map.of("error", exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidData(IllegalArgumentException exception) {
        return Map.of("error", exception.getMessage());
    }
}