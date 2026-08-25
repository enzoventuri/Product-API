package com.example.product_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ErrorResponse(
        @Schema(description = "Status HTTP code", example = "400")
        Integer Status,

        @Schema(description = "Short description about the type of error", example = "Resource not found!")
        String error,

        @Schema(description = "Detailed message about the error", example = "Product not found with ID: 1")
        String message,

        @Schema(description = "URI that originated the error", example = "/produto/-1")
        String path,

        @Schema(description = "Date and hour of the error", example = "2026-08-22T19:17:48")
        LocalDateTime timestamp

) {
    /**
     * Useful constructor to generate the response attributing it to the local hour automatically
     * @param Status
     * @param error
     * @param message
     * @param path
     * @return ErrorResponse Entity
     */
    public static ErrorResponse create(
            Integer Status, String error, String message,
            String path) {

        return new ErrorResponse(Status, error, message, path,
                LocalDateTime.now());
    }
}
