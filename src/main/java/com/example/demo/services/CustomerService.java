package com.example.demo.services;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.*;
import com.example.demo.exceptions.EmailAlreadyUsedException;
import com.example.demo.exceptions.NotFoundException;
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


    // Creo el customer. Este metodo luego lo utilizo en la creacion comun y en la creacion con token.
    @Override
    public Customer createAndReturnEntity(CustomerDto customerDto) {
        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new EmailAlreadyUsedException("Email already exists");
        }

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setTelephone(customerDto.getTelephone());
        customer.setRole(customerDto.getRole());

        // Orders
        if (customerDto.getOrderDto() != null && !customerDto.getOrderDto().isEmpty()) {
            List<Order> orders = customerDto.getOrderDto()
                    .stream()
                    .map(OrderMapper::toEntity)
                    .peek(order -> order.setCustomer(customer))
                    .toList();
            customer.setOrders(orders);
        }

        // ShoppingCart
        if (customerDto.getShoppingCartDto() != null) {
            ShoppingCart shoppingCart = ShoppingMapper.toEntity(customerDto.getShoppingCartDto());
            shoppingCart.setCustomer(customer);
            customer.setShoppingCart(shoppingCart);
        }

        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDto myProfile(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        System.out.println("Estoy en el my profile");

        return CustomerMapper.toDto(customer);

    }

    @Override
    public CustomerResponseDto editProfile(String email, CustomerDto customerDto) {
        Customer customerDb = customerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        if (customerDto.getName() != null && !customerDto.getName().isBlank()) {
            customerDb.setName(customerDto.getName());
        }

        if (customerDto.getEmail() != null && !customerDto.getEmail().isBlank()) {
            customerDb.setEmail(customerDto.getEmail());
        }

        if (customerDto.getTelephone() != null && !customerDto.getTelephone().isBlank()) {
            customerDb.setTelephone(customerDto.getTelephone());

        }

        if (customerDto.getAddress() != null && !customerDto.getAddress().isBlank()) {
            customerDb.setAddress(customerDto.getAddress());

        }



        Customer customer = customerRepository.save(customerDb);



        return CustomerMapper.toDto(customer);

    }

    // 👉 Versión que devuelve el DTO para otras situaciones
    @Override
    public CustomerResponseDto create(CustomerDto customerDto) {
        Customer customer = createAndReturnEntity(customerDto);
        return CustomerMapper.toDto(customer);
    }


}
