package com.example.product_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        @Schema(example = "Notebook Acer")
        @NotBlank(message = "Name is required!")
        @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters long!")
        String name,

        @Schema(example = "1999.99")
        @NotNull(message = "Price is required!")
        @Positive(message = "The price should be positive!")
        BigDecimal price,

        @Schema(example = "false")
        @NotNull(message = "isActive parameter is required!")
        Boolean isActive
) { }
