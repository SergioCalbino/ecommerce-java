package com.example.demo.services;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.entities.*;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mappers.OrderItemMapper;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public OrderResponseDto create(OrderDto orderDto) {

        CustomerDto customerDto = orderDto.getCustomerDto();

        if (customerDto == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        Customer customer = customerRepository.findByEmail(customerDto.getEmail())
                .orElseThrow(()-> new NotFoundException("Customer " + customerDto.getEmail() + " not found"));

        ShoppingCart shoppingCart = customer.getShoppingCart();

        Order order = OrderMapper.toEntity(orderDto);

        order.setCustomer(customer);

        List<OrderItem> orderItems = shoppingCart.getCartItems()
                .stream()
                .map((orderItem -> OrderItemMapper.fromCartItem(orderItem, order)))
                .toList();

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        return OrderMapper.toDto(order);

    }


}
