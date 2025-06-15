package com.example.demo.controllers;

import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.order.CreateOrderDto;
import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.herlpers.ApiResponse;
import com.example.demo.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/add-order")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto) {
        System.out.println("orderResponseDto = " + orderDto.getCustomerDto().getAddress());

        OrderResponseDto orderResponseDto = orderService.create(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                200,
                "Order created successfully",
                orderResponseDto
        ));

    }



}
