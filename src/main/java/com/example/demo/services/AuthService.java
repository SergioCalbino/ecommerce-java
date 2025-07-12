package com.example.demo.services;

import com.example.demo.Dto.auth.AuthRequestDto;
import com.example.demo.Dto.auth.AuthResponseDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.entities.Customer;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UnauthorizedException;
import com.example.demo.interfaces.AuthInterface;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthInterface {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthService(CustomerRepository customerRepository,  CustomerService customerService,PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public AuthResponseDto registerAndGenerateToken(CustomerDto customerDto) {
        customerDto.setRole("CUSTOMER");
        Customer customer = customerService.createAndReturnEntity(customerDto);

        String token = jwtUtil.generateToken(customer.getEmail(), customer.getRole());

        return new AuthResponseDto(token);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        Customer customer = customerRepository.findByEmail(authRequestDto.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));

        if (!passwordEncoder.matches(authRequestDto.getPassword(), customer.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(customer.getEmail(), customer.getRole());

        return new AuthResponseDto(token);


    }
}
