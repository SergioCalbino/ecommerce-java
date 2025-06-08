package com.example.demo.Dto.shoppingCart;

import com.example.demo.Dto.cartItem.CartItemSummaryDto;

import java.util.List;

public class CartSummaryDto {

    List<CartItemSummaryDto> items;

    public CartSummaryDto() {
    }

    public CartSummaryDto(List<CartItemSummaryDto> cartItemSummaryDtoList) {
        this.items = cartItemSummaryDtoList;
    }

    public List<CartItemSummaryDto> getCartSummaryDtoList() {
        return items;
    }

    public void setCartSummaryDtoList(List<CartItemSummaryDto> cartItemSummaryDtoList) {
        this.items = cartItemSummaryDtoList;
    }
}
