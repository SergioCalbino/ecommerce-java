package com.example.demo.Dto;

import com.example.demo.entities.Customer;
import org.apache.juli.logging.Log;

import java.util.List;

public class ShoppingCartResponseDto {

    public ShoppingCartResponseDto(){}

    public ShoppingCartResponseDto(CustomerResponseDto customerResponseDto, List<CartItemResponseDto> cartItemResponseDto){

        this.customerResponseDto = customerResponseDto;
        this.cartItemResponseDto = cartItemResponseDto;


    }

    private Long id;
    private CustomerResponseDto customerResponseDto;
    private List<CartItemResponseDto> cartItemResponseDto;
    private Long customerId;

    public Long getCustomerId(){
        return customerId;
    }

    public void setCustomerId(Long customerId){
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerResponseDto getCustomerResponseDto() {
        return customerResponseDto;
    }

    public void setCustomerResponseDto(CustomerResponseDto customerResponseDto) {
        this.customerResponseDto = customerResponseDto;
    }

    public List<CartItemResponseDto> getCartItemResponseDto() {
        return cartItemResponseDto;
    }

    public void setCartItemResponseDto(List<CartItemResponseDto> cartItemResponseDto) {
        this.cartItemResponseDto = cartItemResponseDto;
    }
}
