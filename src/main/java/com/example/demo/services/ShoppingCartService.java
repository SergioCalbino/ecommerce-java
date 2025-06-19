package com.example.demo.services;

import com.example.demo.Dto.cartItem.RemoveItemDto;
import com.example.demo.Dto.customer.CustomerCartDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.Dto.product.ProductDto;
import com.example.demo.Dto.shoppingCart.ShoppingCartResponseDto;

public interface ShoppingCartService {

    ShoppingCartResponseDto addToShoppingCart(CustomerDto customerDto, ProductDto productDto, Integer quantity);
    CustomerCartDto removeFromShoppingCart(RemoveItemDto removeItemDto);
}
