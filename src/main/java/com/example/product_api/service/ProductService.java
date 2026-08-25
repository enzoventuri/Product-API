package com.example.product_api.service;

import com.example.product_api.dto.ProductCreateRequest;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.dto.ProductUpdateRequest;
import com.example.product_api.entity.Product;
import com.example.product_api.exceptions.ProductNotFoundException;
import com.example.product_api.mapper.ProductMapper;
import com.example.product_api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service responsible by the business rules (related with managing products)
 * */

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Creates a new product based on the data after validating the name
     * @param request Object containing entry data for the creation of the product
     * @return DTO (@link ProductResponse) with the Product's persisted data
     * @throws IllegalArgumentException If a Product already exists with the same name the exception is thrown
     */
    @Transactional
    public ProductResponse register(ProductCreateRequest request) {
        if(repository.existsByNameIgnoreCase(request.name())) {
            throw new IllegalArgumentException("A Product already exists with the same name");
        }

        // DTO to Entity
        Product product = mapper.toEntity(request);
        product.setIsActive(true);
        Product saved = repository.save(product);

        // Entity to DTO Response
        return mapper.toResponse(saved);
    }

    /**
     * Returns all products registered
     * @return List of Response DTOs (@link ProductResponse)
     */
    @Transactional(readOnly = true)
    public List<ProductResponse> listProducts() {
        List<Product> products = repository.findAll();

        return mapper.toResponseList(products);
    }

    /**
     * Finds a Product based on its ID
     * @param id Product identifier to be found
     * @return Response DTO (@link ProductResponse)
     * @throws ProductNotFoundException If no Product is found with the specified ID
     */
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ProductNotFoundException("Product not found! Where ID: " + id));
    }

    /**
     * Finds a Product based on its name
     * @param name Product's name to be found
     * @return Response DTO (@link ProductResponse)
     * @throws ProductNotFoundException If no Product is found with the specified name
     */
    @Transactional(readOnly = true)
    public List<ProductResponse> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /**
     * Atualiza todos os dados de um produto existente
     *
     * @param id Identificador do produto a ser atualizado
     * @param request DTO co os novos dados do produto
     * @return DTO {@link ProductResponse} com os dados do produto atualizados
     * @throws ProductNotFoundException Se nenhum produto for encontrado para o ID informado
     */
    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found! With ID: " + id));

        mapper.updateEntity(request, product);

        Product updated = repository.save(product);

        return mapper.toResponse(updated);
    }

    /**
     * Remove um produto da bas de dados pelo seu identificador
     * @param id Identificador do produto a ser removido
     * @throws ProductNotFoundException nennhum produto for encontrado com o ID informado
     * */
    @Transactional
    public void remove(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found! With ID: " + id));

        repository.delete(product);
    }

}
