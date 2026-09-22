package com.example.product_api.service;

import com.example.product_api.entity.User;
import com.example.product_api.entity.UserProductDetails;
import com.example.product_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service usado para retornar uma Entidade User com base no Username
 */
@Service
@RequiredArgsConstructor
public class UserProductDetailsService implements UserDetailsService {
    private final UserRepository repository;

    /**
     * Retorna Entidade User com base no Username
     * @param username the username identifying the user whose data is required.
     * @return UserDetails+
     * @throws UsernameNotFoundException Usuário não encontrado com o Username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found for: " + username));

        return new UserProductDetails(user);
    }
}
