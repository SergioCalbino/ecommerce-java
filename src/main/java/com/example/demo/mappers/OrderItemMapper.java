package com.example.demo.mappers;

import com.example.demo.Dto.OrderItemDto;
import com.example.demo.Dto.OrderItemResponseDto;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Product;

public class OrderItemMapper {

    public static OrderItemResponseDto toDto(OrderItem orderItem){

        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        orderItemResponseDto.setQuantity(orderItem.getQuantity());
        orderItemResponseDto.setUnitPrice(orderItem.getUnitPrice());
        orderItemResponseDto.setOrderResponseDto(
                OrderMapper.toDto(orderItem.getOrder()));

        return orderItemResponseDto;

    }

    public static OrderItem toEntity(OrderItemDto orderItemDto) {

        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity(orderItemDto.getQuantity());
        Product product = ProductMapper.fromDto(orderItemDto.getProductDto(), null);
        orderItem.setProduct(product);
        orderItem.setUnitPrice(orderItemDto.getUnitPrice());
        orderItem.setOrder(null);

        return orderItem;

    }

}
