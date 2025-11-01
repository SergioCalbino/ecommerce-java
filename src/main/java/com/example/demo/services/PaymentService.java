package com.example.demo.services;


import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.Dto.payment.PaymentRequestDto;
import com.example.demo.Dto.preference.PreferenceItemDto;
import com.example.demo.entities.Customer;
import com.example.demo.mercadopago.AppConfig;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final AppConfig mercadoPagoConfig;
    private final OrderService orderService;
    private final CustomerService customerService;

    @Value("${app.frontend.payment.success-url}")
    private String successUrl;

    @Value("${app.frontend.payment.failure-url}")
    private String failureUrl;

    @Value("${app.frontend.payment.pending-url}")
    private String pendingUrl;

    public PaymentService(AppConfig mercadoPagoConfig, OrderService orderService, CustomerService customerService) {
        this.mercadoPagoConfig = mercadoPagoConfig;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(mercadoPagoConfig.getAccessToken());

    }

    public String createPaymentPreference(List<PreferenceItemDto> item) {
        try {
            List<PreferenceItemRequest> preferenceItems = new ArrayList<>();
            for (PreferenceItemDto itemDto: item) {
                BigDecimal formattedPrice = itemDto.getUnitPrice().setScale(2, RoundingMode.HALF_UP);
                preferenceItems.add(
                        PreferenceItemRequest.builder()
                                .title(itemDto.getTitle())
                                .quantity(itemDto.getQuantity())
                                .unitPrice(formattedPrice)
                                .currencyId("ARS")
                                .build()
                );
            }
            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success(successUrl)
                    .failure(failureUrl)
                    .pending(pendingUrl)
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(preferenceItems)
                    .backUrls(backUrls)
                   // .autoReturn("approved")
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            return preference.getId();

        } catch (MPApiException e) {
            System.out.println("Error to create payment preference: " +  e.getMessage());
            System.err.println("Status Code: " + e.getStatusCode());
            System.err.println("Response Body: " + e.getApiResponse().getContent());
            throw new RuntimeException("Error to communicate with Mercado Pago: " + e.getMessage());

        } catch (MPException e) {
            System.out.println("Error del sdk: " + e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Transactional
    public OrderResponseDto processPayment(PaymentRequestDto paymentRequest) throws MPException, MPApiException {

        PaymentClient client = new PaymentClient();
        PaymentCreateRequest createRequest = PaymentCreateRequest.builder()
                .transactionAmount(paymentRequest.getTransactionAmount())
                .token(paymentRequest.getToken())
                .description("Purchase in Ecommerce")
                .installments(paymentRequest.getInstallments())
                .paymentMethodId(paymentRequest.getPaymentMethodId())
                .payer(PaymentPayerRequest.builder().email(paymentRequest.getPayer().getEmail()).build()).build();

        Payment payment = client.create(createRequest);

        if ("approved".equals(payment.getStatus())) {
            // Obtenemos al usuario (asegúrate que getCustomerByEmail exista en tu CustomerService)
            Customer currentUser = customerService.getCustomerByEmail(paymentRequest.getPayer().getEmail());

            // Llamamos a tu lógica existente para crear la orden, pasándole el ID del pago
            return orderService.createOrderFromCart(currentUser, payment.getId().toString());

        } else {
            // Loguea más detalles del rechazo para poder depurar
            System.err.println("Pago rechazado por Mercado Pago. Status: " + payment.getStatus() + ", Detail: " + payment.getStatusDetail());
            throw new RuntimeException("El pago fue rechazado: " + payment.getStatusDetail());
        }
    }




}
