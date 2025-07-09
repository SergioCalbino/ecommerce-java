package com.example.demo.controllers;


import com.example.demo.Dto.auth.AuthRequestDto;
import com.example.demo.Dto.auth.AuthResponseDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.security.JwtUtil;
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

    public AuthController(CustomerRepository customerRepository, CustomerService customerService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody CustomerDto customerDto){
        customerDto.setRole("CUSTOMER");
        customerService.create(customerDto);

        Customer customer = customerRepository.findByEmail(customerDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        String token = jwtUtil.generateToken(customer.getEmail(), customer.getRole());

        return ResponseEntity.ok((new AuthResponseDto(token)));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request){
        Customer customer = customerRepository.findByEmail(request.getEmail()).orElse(null);
        if (customer == null || !passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        String token = jwtUtil.generateToken(customer.getEmail(), customer.getRole());
        return ResponseEntity.ok(new AuthResponseDto(token));
    }

}
