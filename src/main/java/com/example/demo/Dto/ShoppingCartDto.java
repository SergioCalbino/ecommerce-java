package com.example.demo.Dto;

import com.example.demo.entities.Customer;

import java.util.List;

public class ShoppingCartDto {

    public ShoppingCartDto(){}

    public ShoppingCartDto(Customer customer, List<CartItemDto> cartItemDto){

        this.customer = customer;
        this.cartItemDto = cartItemDto;


    }

    private Long id;
    private Customer customer;
    private List<CartItemDto> cartItemDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartItemDto> getCartItemDto() {
        return cartItemDto;
    }

    public void setCartItemDto(List<CartItemDto> cartItemDto) {
        this.cartItemDto = cartItemDto;
    }
}
