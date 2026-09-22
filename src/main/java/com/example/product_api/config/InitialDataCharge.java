package com.example.product_api.config;

import com.example.product_api.entity.Product;
import com.example.product_api.entity.User;
import com.example.product_api.enums.Roles;
import com.example.product_api.repository.ProductRepository;
import com.example.product_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

/** * Carga inicial de dados para popular o banco durante a inicialização da aplicação * */
@Configuration
@RequiredArgsConstructor
public class InitialDataCharge implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
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

            productRepository.saveAll(initialProducts);
        }

        if (userRepository.count() == 0) {
            List<User> initialUsers = List.of(
                    User.builder()
                            .nome("Enzo")
                            .password(encoder.encode("Enzo123!"))
                            .role(Roles.ADMIN)
                            .build(),
                    User.builder()
                            .nome("Enzo2")
                            .password(encoder.encode("Enzo1234!"))
                            .role(Roles.CLIENT)
                            .build()
            );

            userRepository.saveAll(initialUsers);
        }
    }

}
