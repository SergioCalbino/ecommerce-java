package com.example.demo.interfaces;

import com.example.demo.Dto.auth.AuthRequestDto;
import com.example.demo.Dto.auth.AuthResponseDto;
import com.example.demo.Dto.auth.ChangePasswordRequest;
import com.example.demo.Dto.auth.LoginResponseDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.entities.RefreshToken;

import java.util.Optional;

public interface AuthInterface {


    AuthResponseDto registerAndGenerateToken(CustomerDto customerDto);
    LoginResponseDto login(AuthRequestDto authRequestDto);
    LoginResponseDto refresh(String refreshToken);
    void logout(String refreshToken);
    void changePassword(ChangePasswordRequest changePasswordRequest, String email);

}
