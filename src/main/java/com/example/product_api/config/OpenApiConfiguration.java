package com.example.product_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Management API")
                        .version("1.0")
                        .description("RESTful API developed to " +
                                "control, add and maintain a " +
                                "catalog of products")
                        .contact(new Contact()
                                .name("Enzo Venturi")
                                .email("enzo_venturi@estudante.sesisenai.org.br")

                        )

                );
    }
}
