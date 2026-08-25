package com.example.product_api.mapper;

import com.example.product_api.dto.ProductCreateRequest;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.dto.ProductUpdateRequest;
import com.example.product_api.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    /**
     * Converts creation data into a Product Entity
     * @param request Data related with it's creation
     * @return Product Entity
     */
    public Product toEntity(ProductCreateRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    /**
     * Converts an Entity into a response DTO
     * @param product Persisted Entity response
     * @return Public representation of the product
     */
    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getIsActive()
        );
    }

    /**
     * Converts a List of Entities into a List of Response DTOs
     * @param products List of Entity Products
     * @return List of DTO ProductResponse
     */
    public List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Updates a Product Entity based off of ProductUpdateRequest
     * @param request Update Request DTO
     * @param product Product Entity
     */
    public void updateEntity(ProductUpdateRequest request, Product product) {
        product.setName(request.name());
        product.setPrice(request.price());
        product.setIsActive(request.isActive());
    }
}
