package com.example.demo.services;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.entities.*;
import com.example.demo.entities.utilities.OrderState;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.ShoppingCartEmptyException;
import com.example.demo.mappers.OrderItemMapper;
import com.example.demo.mappers.OrderMapper;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
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

        if (shoppingCart.getCartItems().isEmpty()) {
            throw new ShoppingCartEmptyException("Shopping Cart is empty");

        }

        Order order = OrderMapper.toEntity(orderDto);

        order.setCustomer(customer);

        List<OrderItem> orderItems = shoppingCart.getCartItems()
                .stream()
                .map((orderItem -> OrderItemMapper.fromCartItem(orderItem, order)))
                .toList();

        order.setState(OrderState.PENDING);
        order.setOrderItems(orderItems);
        order.setTotal(shoppingCart.getTotal());
        orderRepository.save(order);

        shoppingCart.getCartItems().clear();
        shoppingCart.setTotal(BigDecimal.ZERO);
        shoppingCartRepository.save(shoppingCart);

        return OrderMapper.toDto(order);

    }

    @Override
    @Transactional()
    public OrderResponseDto delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

       OrderResponseDto orderResponseDto = OrderMapper.toDto(order);
       orderRepository.delete(order);
       return orderResponseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto detailOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        return OrderMapper.toDto(order);
    }


}
