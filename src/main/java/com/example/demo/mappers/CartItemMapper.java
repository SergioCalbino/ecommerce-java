package com.example.demo.mappers;

import com.example.demo.Dto.CartItemResponseDto;
import com.example.demo.entities.CartItem;

public class CartItemMapper {

    public static CartItemResponseDto toDto(CartItem cartItem) {

        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();

        cartItemResponseDto.setShoppingCartResponseDto(ShoppingMapper.toDto(cartItem.getShoppingCart()));
        cartItemResponseDto.setQuantity(cartItem.getQuantity());
        cartItemResponseDto.setProduct(ProductMapper.toDto(cartItem.getProduct()));


        return cartItemResponseDto;

    }

}
