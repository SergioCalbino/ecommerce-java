package com.example.demo.interfaces;

import com.example.demo.Dto.password.PasswordAndResetTokenDto;

public interface PasswordResetService {

    void resetPassword(String email);
    void updatePassword(String token, String email);
}
