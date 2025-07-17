package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.RefreshToken;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RefreshTokenRepository;
import com.example.demo.security.JwtUtil;
import io.jsonwebtoken.Jwt;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService implements com.example.demo.interfaces.RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, CustomerRepository customerRepository, JwtUtil jwtUtil){
        this.refreshTokenRepository = refreshTokenRepository;
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(Long customerId) {
        long refreshTokenExpirationMs = 7 * 24 * 60 * 60 * 1000;

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        refreshTokenRepository.findByCustomer(customer).ifPresent(refreshToken -> {
            System.out.println("Token previo encontrado y eliminado: " + refreshToken.getToken());
            refreshTokenRepository.delete(refreshToken);
            refreshTokenRepository.flush();
        });

        String refreshToken = jwtUtil.generateRefreshToken(customer.getEmail());
        System.out.println("Este es el refresh: " + refreshToken);

        RefreshToken token = new RefreshToken();
        token.setCustomer(customer);
        token.setToken(refreshToken);
        token.setExpiredDate(Instant.now().plusMillis(refreshTokenExpirationMs));

        return refreshTokenRepository.save(token);
    }

    @Override
    @Transactional
    public RefreshToken verifyExpiration(RefreshToken token) {
       if (token.getExpiredDate().isBefore(Instant.now())) {
           refreshTokenRepository.delete(token);
           throw new IllegalArgumentException("Token has expired, try again");
       }
       return token;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        System.out.println("🔍 Buscando token: " + token);
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public void deleteByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        refreshTokenRepository.deleteByCustomer(customer);

    }
}
