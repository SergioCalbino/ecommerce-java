package com.example.demo.mappers;

import com.example.demo.Dto.ShoppingCartDto;
import com.example.demo.Dto.ShoppingCartResponseDto;
import com.example.demo.entities.ShoppingCart;

public class ShoppingMapper {

    public static ShoppingCartResponseDto toDto(ShoppingCart shoppingCart){

        if(shoppingCart == null) {
            return null;
        }

        ShoppingCartResponseDto shoppingResponseDto = new ShoppingCartResponseDto();

        shoppingResponseDto.setCartItemResponseDto(
                shoppingCart.getCartItems()
                        .stream()
                        .map(CartItemMapper::toDto)
                        .toList()
        );

        //shoppingResponseDto.setCustomerResponseDto(CustomerMapper.toDto(shoppingCart.getCustomer()));
       //
        if (shoppingCart.getCustomer() != null) {
            shoppingResponseDto.setCustomerId(shoppingCart.getCustomer().getId());
        }



        return shoppingResponseDto;



    }

    public static ShoppingCart toEntity(ShoppingCartDto shoppingCartDto) {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(shoppingCartDto.getCustomer());
        shoppingCart.setCartItems(shoppingCart.getCartItems());

        return shoppingCart;
    }

}
