package com.example.demo.mappers;

import com.example.demo.Dto.orderItem.OrderItemDto;
import com.example.demo.Dto.orderItem.OrderItemResponseDto;
import com.example.demo.Dto.product.ProductResponseDto;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.Product;

public class OrderItemMapper {

    public static OrderItemResponseDto toDto(OrderItem orderItem){


        OrderItemResponseDto dto = new OrderItemResponseDto();
        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        //orderItemResponseDto.setOrderResponseDto(
                //OrderMapper.toDto(orderItem.getOrder()));
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setSubtotal(orderItem.getSubtotal());
        dto.setProductResponseDto(ProductMapper.toDto(orderItem.getProduct()));


        return dto;

    }

    public static OrderItem toEntity(OrderItemDto orderItemDto) {

        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity(orderItemDto.getQuantity());
        Product product = ProductMapper.fromDto(orderItemDto.getProductDto(), null);
        orderItem.setProduct(product);
        orderItem.setUnitPrice(orderItemDto.getUnitPrice());
        orderItem.setOrder(null);
        orderItem.setSubtotal(orderItemDto.getSubtotal());

        return orderItem;

    }

    public static OrderItem fromCartItem(CartItem cartItem, Order order){

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setUnitPrice(cartItem.getProduct().getPrice());
        orderItem.setOrder(order);
        orderItem.setSubtotal(cartItem.getSubtotal());

        return orderItem;

    }

}
