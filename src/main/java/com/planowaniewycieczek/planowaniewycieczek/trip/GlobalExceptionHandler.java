package com.planowaniewycieczek.planowaniewycieczek.trip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Wystąpił błąd: ", e);  // Logujemy pełny wyjątek
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Wystąpił błąd: " + e.getMessage());
    }
}
