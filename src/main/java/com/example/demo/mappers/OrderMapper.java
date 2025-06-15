package com.example.demo.mappers;

import com.example.demo.Dto.order.OrderDto;
import com.example.demo.Dto.order.OrderResponseDto;
import com.example.demo.entities.Order;

import java.util.ArrayList;

public class OrderMapper {

    public static OrderResponseDto toDto(Order order) {

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setDate(order.getDate());
        orderResponseDto.setCustomerResponseDto(
                CustomerMapper.toSummaryDto(order.getCustomer())
        );
        //mapeo los orderItem porque es una lista y pueden venir varios
        orderResponseDto.setOrderItemResponseDto(
                order.getOrderItems().stream()
                        .map(orderItem -> OrderItemMapper.toDto(orderItem))
                        .toList()
        );

        //Aca no mapeo porque es un solo customer
       // orderResponseDto.setCustomerResponseDto(
               // CustomerMapper.toDto(order.getCustomer())
        //);

        if (orderResponseDto.getCustomerResponseDto() != null) {
            orderResponseDto.setCustomerId(order.getCustomer().getId());
        }

        orderResponseDto.setTotal(order.getTotal());
        orderResponseDto.setState(order.getState());

    return orderResponseDto;

    }

    public static Order toEntity(OrderDto orderDto) {

        Order order = new Order();

        order.setCustomer(CustomerMapper.toEntity(orderDto.getCustomerDto()));
        order.setDate(orderDto.getDate());
        order.setState(orderDto.getState());
        order.setTotal(orderDto.getTotal());
        if (orderDto.getOrderItemDto() != null) {
            order.setOrderItems(orderDto.getOrderItemDto()
                    .stream()
                    .map((orderItem) -> OrderItemMapper.toEntity(orderItem))
                    .toList()
            );
        } else {
            order.setOrderItems(new ArrayList<>());
        }



        return order;


    }



}


