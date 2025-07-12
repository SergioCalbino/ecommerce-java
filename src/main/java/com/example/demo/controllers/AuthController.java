package com.example.demo.controllers;


import com.example.demo.Dto.auth.AuthRequestDto;
import com.example.demo.Dto.auth.AuthResponseDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.AuthService;
import com.example.demo.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        AuthResponseDto authResponseDto = authService.login(request);

        return ResponseEntity.ok(authResponseDto);
    }

}
