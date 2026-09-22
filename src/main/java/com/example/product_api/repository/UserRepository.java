package com.example.product_api.repository;

import com.example.product_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório do usuário
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Método para procurar usuário pelo username
     * @param nome Username do usuário
     * @return Optional User
     * @throws UsernameNotFoundException Exceção lançada quando usuário não é encontrado
     */
    Optional<User> findUserByNome(String nome) throws UsernameNotFoundException;
}
