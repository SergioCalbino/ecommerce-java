package com.example.demo.Dto.customer;

import com.example.demo.Dto.shoppingCart.CartSummaryDto;

public class CustomerCartDto {

    private String name;
    private String email;
    private String address;
    private Long id;
    private CartSummaryDto shoppingCart;

    public CustomerCartDto() {
    }

    public CustomerCartDto(String name, String email, String address, Long id, CartSummaryDto shoppingCart) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.id = id;
        this.shoppingCart = shoppingCart;

    }

    public CartSummaryDto getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(CartSummaryDto shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
