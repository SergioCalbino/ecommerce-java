package com.example.demo.controllers;

import com.example.demo.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class PasswordController {

    //private final CustomerRepository customerRepository;


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){

       return ResponseEntity.ok(email);


    };


}
