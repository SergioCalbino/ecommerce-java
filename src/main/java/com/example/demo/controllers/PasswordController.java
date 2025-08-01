package com.example.demo.controllers;

import com.example.demo.Dto.password.EmailDto;
import com.example.demo.Dto.password.NewPasswordAndTokenDto;
import com.example.demo.Dto.password.PasswordAndResetTokenDto;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.services.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class PasswordController {

    private final PasswordResetService passwordResetService;

    public PasswordController(PasswordResetService passwordResetService){
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> reset(@RequestBody EmailDto request ){
        passwordResetService.resetPassword(request.getEmail());
        return ResponseEntity.ok("Token was generated succesfully");
    }

    @PostMapping("/reset")
    public ResponseEntity<?> uppdatePassword(@RequestBody NewPasswordAndTokenDto newPasswordAndTokenDto){
        passwordResetService.updatePassword(newPasswordAndTokenDto.getToken(), newPasswordAndTokenDto.getPassword());
        return ResponseEntity.ok("Password was updated successfully");
    }


}
