package com.example.product_api.entity;

import com.example.product_api.enums.Roles;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade representando dados principais do usuário
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "nome",
            unique = true,
            nullable = false
    )
    private String nome;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            nullable = false
    )
    private Roles role;

}
