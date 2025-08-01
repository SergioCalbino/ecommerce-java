package com.example.demo.services;

import com.example.demo.Dto.password.PasswordAndResetTokenDto;
import com.example.demo.entities.Customer;
import com.example.demo.entities.PasswordAndResetToken;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PasswordResetTokenRepository;
import com.example.demo.repositories.RefreshTokenRepository;
import org.springframework.cglib.core.Local;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService implements com.example.demo.interfaces.PasswordResetService {

    private final CustomerRepository customerRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetService(CustomerRepository customerRepository, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.customerRepository = customerRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Override
    public void resetPassword(String email) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        System.out.println("Customer " + customer);

        String token = UUID.randomUUID().toString();

        System.out.println("Este es el token para cambiar contraseña " + token);

        PasswordAndResetToken resetToken = new PasswordAndResetToken();
        resetToken.setCustomer(customer);
        resetToken.setToken(token);
        resetToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));

        System.out.println("Estoy por guardar");

        passwordResetTokenRepository.save(resetToken);

    }

    @Override
    public void updatePassword(String token, String newPassword) {
        PasswordAndResetToken findToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found or expired"));

        if (findToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        Customer customer = findToken.getCustomer();
        customer.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        customerRepository.save(customer);

        passwordResetTokenRepository.delete(findToken);
    }
}
