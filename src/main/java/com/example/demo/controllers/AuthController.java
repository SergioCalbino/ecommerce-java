package com.example.demo.controllers;


import com.example.demo.Dto.auth.*;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.entities.RefreshToken;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.AuthService;
import com.example.demo.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(CustomerRepository customerRepository, CustomerService customerService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthService authService){
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody CustomerDto customerDto){
        AuthResponseDto authResponseDto = authService.registerAndGenerateToken(customerDto);


        return ResponseEntity.ok(authResponseDto);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request){
        LoginResponseDto loginResponseDto = authService.login(request);


        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenResponseDto refreshToken){
        LoginResponseDto loginResponseDto = authService.refresh(refreshToken.getRefreshToken());
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenResponseDto refreshToken){
        authService.logout(refreshToken.getRefreshToken());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();


        try {
            authService.changePassword(changePasswordRequest, email);
            return ResponseEntity.ok("Password was change Successfullu");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));

        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }



    }

}
