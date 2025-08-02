package com.example.demo.services;


import com.example.demo.entities.Customer;
import com.example.demo.entities.PasswordAndResetToken;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService implements com.example.demo.interfaces.PasswordResetService {



    private final CustomerRepository customerRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;

    public PasswordResetService(CustomerRepository customerRepository, PasswordResetTokenRepository passwordResetTokenRepository, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
    }
    @Value("${app.frontend.reset-password-url}")
    private String resetPasswordUrl;

    @Value("${app.reset-token.expiration-minutes}")
    private int expirationMinutes;

    @Override
    public void resetPassword(String email) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        System.out.println("Customer " + customer);

        passwordResetTokenRepository.findByCustomer(customer)
                .ifPresent(existingToken -> {
                    System.out.println("Token was eliminated " + existingToken.getToken());
                    passwordResetTokenRepository.delete(existingToken);
                });

        String token = UUID.randomUUID().toString();

        System.out.println("Este es el token para cambiar contraseña " + token);

        PasswordAndResetToken resetToken = new PasswordAndResetToken();
        resetToken.setCustomer(customer);
        resetToken.setToken(token);
        resetToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));

        System.out.println("Estoy por guardar");

        passwordResetTokenRepository.save(resetToken);

        // Armar el link con frontend (o una URL de tu backend para procesar)
        String resetLink = resetPasswordUrl + "?token=" + token;

        // Enviar email
        String subject = "Restablece tu contraseña";
        String body = "Hola " + customer.getName() + ",\n\n"
                + "Para restablecer tu contraseña hacé clic en el siguiente enlace:\n"
                + resetLink + "\n\n"
                + "Si no solicitaste esto, ignorá este mensaje.";

        emailService.sendEmail(customer.getEmail(), subject, body);

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
