package com.example.demo.services;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.*;
import com.example.demo.exceptions.EmailAlreadyUsedException;
import com.example.demo.mappers.CustomerMapper;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.mappers.ShoppingMapper;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class CustomerService implements com.example.demo.interfaces.CustomerService {

    public final CustomerRepository customerRepository;
    public final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public CustomerResponseDto create(CustomerDto customerDto) {

        // Creo el customer y le seteo los atributos
        Customer customer = new Customer();
        if (customerRepository.existsByEmail(customerDto.getEmail())){
            throw new EmailAlreadyUsedException("Email already exists");
        }
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));


        // Seteo orders si hay
        if (customerDto.getOrderDto() != null && !customerDto.getOrderDto().isEmpty()) {
            List<Order> orders = customerDto.getOrderDto()
                    .stream()
                    .map(OrderMapper::toEntity)
                    .peek(order -> order.setCustomer(customer))
                    .toList();
            customer.setOrders(orders);
        }

        // Seteo shopping cart si hay
        if (customerDto.getShoppingCartDto() != null) {
            ShoppingCart shoppingCart = ShoppingMapper.toEntity(customerDto.getShoppingCartDto());
            shoppingCart.setCustomer(customer);
            customer.setShoppingCart(shoppingCart);
        }

        // Persisto solo el customer
        customerRepository.save(customer);

        return CustomerMapper.toDto(customer);
    }




}
