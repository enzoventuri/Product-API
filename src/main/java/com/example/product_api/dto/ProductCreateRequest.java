package com.example.product_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Necessary data to create a Product
 *
 * @param name Product's commercial name
 * @param price Product's selling price
 */
@Schema(description = "Data utilized to create a product")

public record ProductCreateRequest (
    @Schema(
            description = "Product name",
            example = "Notebook Dell"
    )
    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters long!")
    String name,

    @Schema(
            description = "Product price",
            example = "4500.00"
    )
    @NotNull(message = "Price is required!")
    @Positive(message = "The price should be positive!")
    BigDecimal price

)

{}
