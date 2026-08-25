package com.example.product_api.config;

import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

/** * Carga inicial de dados para popular o banco durante a inicialização da aplicação * */
@Configuration
public class InitialDataCharge implements CommandLineRunner {
    private final ProductRepository repository;

    public InitialDataCharge(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            List<Product> initialProducts = List.of(
                    Product.builder()
                            .name("Notebook Dell Inspiron")
                            .price(new BigDecimal("4500.00"))
                            .isActive(true)
                            .build(),
                    Product.builder()
                            .name("Logitech G703 LightSpeed")
                            .price(new BigDecimal("300.00"))
                            .isActive(true)
                            .build(),
                    Product.builder()
                            .name("Attack Shark")
                            .price(new BigDecimal("350.00"))
                            .isActive(true)
                            .build(),
                    Product.builder()
                            .name("Monitor 29")
                            .price(new BigDecimal("1500.00"))
                            .isActive(true)
                            .build(),
                    Product.builder()
                            .name("Bluetooth headphones (discontinued)")
                            .price(new BigDecimal("215.00"))
                            .isActive(false)
                            .build()
            );

            repository.saveAll(initialProducts);
        }
    }
}
