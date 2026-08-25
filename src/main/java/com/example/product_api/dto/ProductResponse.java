package com.example.product_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Public representation of a product returned by the API
 * @param id Product Identifier
 * @param name Product name
 * @param price Product selling price
 * @param isActive Current product situation
 */

public record ProductResponse (
        @Schema(description = "Unique identifier of a product",
                example = "1")
        Long id,

        @Schema(description = "Product's name",
                example = "Notebook Dell")
        String name,

        @Schema(description = "Product's selling price",
                example = "4500.00")
        BigDecimal price,

        @Schema(description = "Indicates whether the product is " +
                "active or not", example = "true")
        boolean isActive
)
{ }
