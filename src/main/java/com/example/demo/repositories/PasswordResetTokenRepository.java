package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import com.example.demo.entities.PasswordAndResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PasswordResetTokenRepository extends JpaRepository<PasswordAndResetToken, Long> {
    Optional<PasswordAndResetToken> findByToken(String name);
    Optional<PasswordAndResetToken> findByCustomer(Customer customer);
}
