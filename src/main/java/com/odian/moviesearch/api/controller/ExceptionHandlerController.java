package com.odian.moviesearch.api.controller;


import com.odian.moviesearch.api.model.ErrorDTO;
import com.odian.moviesearch.core.application.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound (NotFoundException exception) {
        ErrorDTO errorDTO = new ErrorDTO(404, exception.getMessage(), Instant.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDTO);
    }


}
