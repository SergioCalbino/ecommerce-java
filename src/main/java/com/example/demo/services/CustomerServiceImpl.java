package com.example.demo.services;

import com.example.demo.Dto.CustomerDto;
import com.example.demo.Dto.CustomerResponseDto;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.entities.ShoppingCart;
import com.example.demo.mappers.CustomerMapper;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.mappers.ShoppingMapper;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    public final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerResponseDto create(CustomerDto customerDto) {

        // Creo el customer y le seteo los atributos
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(customerDto.getPassword());

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
