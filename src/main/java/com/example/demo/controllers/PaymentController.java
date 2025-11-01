package com.example.demo.controllers;

import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.Dto.payment.PaymentRequestDto;
import com.example.demo.Dto.preference.PreferenceItemDto;
import com.example.demo.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Endpoint para crear la Preferencia de Pago en Mercado Pago.
     * Recibe la lista de productos del carrito y devuelve un preferenceId.
     */
    @PostMapping("/create-preference")
    public ResponseEntity<?> createPreference(@RequestBody List<PreferenceItemDto> items) {
        try {
            String preferenceId = paymentService.createPaymentPreference(items);
            // Devolvemos el ID en un formato JSON simple: { "preferenceId": "..." }
            return ResponseEntity.ok(Map.of("preferenceId", preferenceId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error al crear la preferencia de pago", "message", e.getMessage()));
        }
    }

    /**
     * Endpoint para procesar el pago final.
     * Recibe los datos del pago desde el frontend, procesa el pago con Mercado Pago,
     * y si es exitoso, crea la orden en la base de datos.
     */
    @PostMapping("/process-payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequestDto paymentRequest) {
        try {
            OrderResponseDto savedOrder = paymentService.processPayment(paymentRequest);
            // Si todo sale bien, devolvemos los datos de la orden creada
            return ResponseEntity.ok(Map.of("order", savedOrder));
        } catch (Exception e) {
            // Si el pago falla o hay otro error, devolvemos un mensaje claro.
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }
}