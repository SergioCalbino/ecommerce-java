package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import com.example.demo.entities.RefreshToken;
import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByCustomer(Customer customer);
    void deleteByCustomer(Customer customer);

}
