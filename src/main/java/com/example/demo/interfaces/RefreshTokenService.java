package com.example.demo.interfaces;

import com.example.demo.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long customerId);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    void deleteByCustomerId(Long customerId);

}
