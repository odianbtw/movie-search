package com.odian.moviesearch.unit.api.controller;

import com.odian.moviesearch.core.application.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionHandlerControllerTest {

    private static final ExceptionHandlerController subject = new ExceptionHandlerController();

    @Test
    public void testHandleNotFoundException () {
        var responseError = subject.handleNotFound(new NotFoundException("Something doesn't exist"));
        assertEquals(404, responseError.getStatusCode().value());
        Assertions.assertNotNull(responseError.getBody());
        assertEquals("Something doesn't exist", responseError.getBody().message());
    }
}
