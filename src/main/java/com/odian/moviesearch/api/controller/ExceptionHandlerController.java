package com.odian.moviesearch.api.controller;

import com.odian.moviesearch.api.model.ErrorDTO;
import com.odian.moviesearch.core.exceptions.DaoException;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> notFound (NotFoundException exception) {
        var error = new ErrorDTO(404, exception.getMessage(), Instant.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDTO> badRequest(BindException exception) {
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorDTO error = new ErrorDTO(400, errorMessage, Instant.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> notFound (ValidationException exception) {
        var error = new ErrorDTO(409, exception.getMessage(), Instant.now());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<ErrorDTO> notFound (DaoException exception) {
        var error = new ErrorDTO(500, exception.getMessage(), Instant.now());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
