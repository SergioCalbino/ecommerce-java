package com.example.demo.services;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;

public interface OrderService {

    OrderResponseDto create(OrderDto orderDto);
    OrderResponseDto delete(Long id);
    OrderResponseDto detailOrder(Long id);
}
