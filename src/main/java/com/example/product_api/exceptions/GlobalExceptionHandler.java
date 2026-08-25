package com.example.product_api.exceptions;

import com.example.product_api.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.create(
                HttpStatus.NOT_FOUND.value(),
                "Resource not found!",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            IllegalArgumentException exception, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.create(
                HttpStatus.NOT_FOUND.value(),
                "Invalid request",
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        String errorMessages = exception.getBindingResult()
                .getFieldErrors().stream().map(
                        error -> error.getField() + ": " +
                        error.getDefaultMessage()).collect(Collectors.joining(": "));

        ErrorResponse error = ErrorResponse.create(
                HttpStatus.NOT_FOUND.value(),
                "Validation Error",
                errorMessages,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
