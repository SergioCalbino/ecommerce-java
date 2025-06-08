package com.example.demo.mappers;

import com.example.demo.Dto.orderItem.OrderItemDto;
import com.example.demo.Dto.orderItem.OrderItemResponseDto;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Order;
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

    public static OrderItem fromCartItem(CartItem cartItem, Order order){

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setUnitPrice(cartItem.getProduct().getPrice());
        orderItem.setOrder(order);

        return orderItem;

    }

}
