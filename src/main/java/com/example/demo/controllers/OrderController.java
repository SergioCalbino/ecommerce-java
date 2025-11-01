package com.example.demo.controllers;

import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.herlpers.ApiResponse;
import com.example.demo.interfaces.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        OrderResponseDto orderResponseDto = orderService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Order was deleted",
                orderResponseDto
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showOrder(@PathVariable Long id){
        OrderResponseDto orderResponseDto = orderService.detailOrder(id);
        return ResponseEntity.ok(new ApiResponse<>(
                200,
                "Order detail",
                orderResponseDto
        ));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("orders-paginated")
    public ResponseEntity<?> list(@PageableDefault(page = 0, size = 5, sort = "date", direction = Sort.Direction.ASC)Pageable pageable) {
        Page<OrderResponseDto> orderResponseDtoList = orderService.findAll(pageable);
        if (orderResponseDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDtoList);
    }




}
