package com.example.product_api.dto;

public record LoginRequest(
        String username,
        String password
) {
}
