package com.example.product_api.repository;

import com.example.product_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository responsible for accessing Product data
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Checks if a Product exists by its name
     * Ignores the difference between uppercase and lowercase letters.
     * @param name Name to be found
     * @return (@code true) in case the Product's name exists
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Finds Products whose name's contains the informed text
     * @param name Part of the Product's name
     * @return Products found
     */
    List<Product> findByNameContainingIgnoreCase(String name);
}
