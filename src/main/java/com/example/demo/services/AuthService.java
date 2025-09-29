package com.example.demo.services;

import com.example.demo.Dto.auth.AuthRequestDto;
import com.example.demo.Dto.auth.AuthResponseDto;
import com.example.demo.Dto.auth.ChangePasswordRequest;
import com.example.demo.Dto.auth.LoginResponseDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.Customer;
import com.example.demo.entities.RefreshToken;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UnauthorizedException;
import com.example.demo.interfaces.AuthInterface;
import com.example.demo.interfaces.RefreshTokenService;
import com.example.demo.mappers.CustomerMapper;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RefreshTokenRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService implements AuthInterface {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthService(CustomerRepository customerRepository,  CustomerService customerService, RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public AuthResponseDto registerAndGenerateToken(CustomerDto customerDto) {
        customerDto.setRole("CUSTOMER");
        Customer customer = customerService.createAndReturnEntity(customerDto);

        String token = jwtUtil.generateAccessToken(customer.getEmail(), customer.getRole());

        return new AuthResponseDto(token, customer);
    }

    @Override
    public LoginResponseDto login(AuthRequestDto authRequestDto) {
        Customer customer = customerRepository.findByEmail(authRequestDto.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));

        if (!passwordEncoder.matches(authRequestDto.getPassword(), customer.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateAccessToken(customer.getEmail(), customer.getRole());
       // String refreshToken = jwtUtil.generateRefreshToken(customer.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(customer.getId());


        System.out.println("Este es del login refrehs" + refreshToken);

        CustomerResponseDto customerDto = CustomerMapper.toDto(customer);
        return new LoginResponseDto(accessToken, refreshToken.getToken(), customerDto);

    }

    @Override
    public LoginResponseDto refresh(String refreshToken) {
        return refreshTokenService.findByToken(refreshToken)
                .map((refreshToken1 -> refreshTokenService.verifyExpiration(refreshToken1)))
                .map(refreshToken1 -> {
                    System.out.println("refresh token" + refreshToken1);
                    Customer customer = refreshToken1.getCustomer();
                    String accessToken = jwtUtil.generateAccessToken(
                            customer.getEmail(),
                            customer.getRole()
                    );
                    CustomerResponseDto customerDto = CustomerMapper.toDto(customer);
                    return new LoginResponseDto(accessToken, refreshToken1.getToken(), customerDto);
                })
                .orElseThrow(() -> new NotFoundException("Refresh token not found"));
    }

    @Override
    @Transactional
    public void logout(String token) {
       RefreshToken tokenStored = refreshTokenService.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"));

         refreshTokenRepository.delete(tokenStored);
    }



    //Cambio de password estando logueado
    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));


        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), customer.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");

        }

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), customer.getPassword())) {
            throw new IllegalArgumentException("New password must be different from the current password");
        }

        String encodedNewPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());

        customer.setPassword(encodedNewPassword);
        customerRepository.save(customer);

    }



}
