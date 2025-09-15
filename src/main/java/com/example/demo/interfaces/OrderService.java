package com.example.demo.interfaces;

import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderResponseDto create(OrderDto orderDto);
    OrderResponseDto delete(Long id);
    OrderResponseDto detailOrder(Long id);
    List<OrderResponseDto> getOrders();
    Page<OrderResponseDto> findAll(Pageable pageable);
}
