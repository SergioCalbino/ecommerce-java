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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements com.example.demo.interfaces.OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ShoppingCartRepository shoppingCartRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public OrderResponseDto createOrderFromCart(Customer customer, String paymentId) {

        ShoppingCart shoppingCart = customer.getShoppingCart();

        if (shoppingCart == null || shoppingCart.getCartItems().isEmpty()) {
            throw new ShoppingCartEmptyException("Shopping Cart is empty");

        }

        //Creo la orden vacia
        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(new Date());
        order.setTotal(shoppingCart.getTotal());
        order.setState(OrderState.PAID);
        order.setPaymentId(paymentId);

        List<OrderItem> orderItems = shoppingCart.getCartItems()
                .stream()
                .map((orderItem -> OrderItemMapper.fromCartItem(orderItem, order)))
                .toList();

        order.setOrderItems(orderItems);
      //  order.setTotal(shoppingCart.getTotal());
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

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponseDto> getOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map((order) -> OrderMapper.toDto(order))
                .toList();
    }



    @Transactional(readOnly = true)
    @Override
    public Page<OrderResponseDto> findAll(Pageable pageable) {
        Page<Order> ordersPage = orderRepository.findAll( pageable);
        return ordersPage.map(OrderMapper::toDto);
    }


}



