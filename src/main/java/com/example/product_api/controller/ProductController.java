package com.example.product_api.controller;

import com.example.product_api.dto.ErrorResponse;
import com.example.product_api.dto.ProductCreateRequest;
import com.example.product_api.dto.ProductResponse;
import com.example.product_api.dto.ProductUpdateRequest;
import com.example.product_api.entity.Product;
import com.example.product_api.mapper.ProductMapper;
import com.example.product_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller REST responsável pelos endpoints relacionados ao recurso produto
 * */
@RestController
@RequestMapping("/api/v1/produtos")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Lista todos os produts cadastrados
     * @return Lista de produtos
     * */
    @Operation(
            summary = "Lista de produtos",
            description = "Retorna todos os produtos cadastrados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Produtos retornados com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<ProductResponse>> listProducts() {
        return ResponseEntity.ok(service.listProducts());
    }

    /**
     * Busca um produto pelo seu identificador
     * @param id Identificador do produto
     * @return DTO {@link ProductResponse} com os dados do produto encontrado
     * */

    @Operation(
            summary = "Busca produto por ID",
            description = "Retorna os detalhes de um produto específico com base no seu identificador único"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Produto encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @Parameter(description = "Unique Product identifier", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Busca produtos ativos pelo nome.
     *
     * @param name Termo ou trecho do nome do produto para filtragem.
     * @return Lista contendo os DTOs {@link ProductResponse} encontrados.
     */
    @Operation(
            summary = "Busca produtos por nome",
            description = "Retorna uma lista de produtos ativos cujo nome contenha o termo informado (busca *case-insensitive*)."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetro de busca inválido",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping(params = "name")
    public ResponseEntity<List<ProductResponse>> findByName(
            @Parameter(description = "Term or key value contained inside the product",
            example = "Mouse") @RequestParam String name
    ) {
        return ResponseEntity.ok(service.findByName(name));
    }

    /**
     * Cadastra um novo produto
     * @param request DTO com os dados necessários pra criação do produto
     * @return DTO {@link ProductResponse} com o produto cadastrado e cabeçalho Location
     * */
    @Operation(
            summary = "Cadastro de um produto",
            description = "Cria um novo produto no catálogo e retona o recurso criado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Produto criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<ProductResponse> register(@Valid @RequestBody
        ProductCreateRequest request) {
        ProductResponse product = service.register(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();

        return ResponseEntity.created(uri).body(product);

    }

    /**
     * Atualiza os dados de um produto existente
     * @param id Identificador do produto a ser atualizado
     * @param request DTO co os novos dados do produto
     * @return DTO {@link ProductResponse} atualizado
     * */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @Parameter(description = "Unique identifier of a Product", example = "1")
            @PathVariable Long id, @Valid @RequestBody ProductUpdateRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    /**
     * Remove um produto do catálogo
     * @param id Identificador do produto a ser removido
     * @return Resposta sem conteúdo (HTTP 204 No Content)
     * */
    @Operation (
            summary = "Remove um produto",
            description = "Realiza a exclusão do produto com base no seu identificador"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Produto removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Produto não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(
            @Parameter(description = "Unique identifier of a Product", example = "1")
            @PathVariable Long id
    ) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}
