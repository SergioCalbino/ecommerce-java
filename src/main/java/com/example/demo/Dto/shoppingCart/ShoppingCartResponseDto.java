package com.example.demo.Dto.shoppingCart;

import com.example.demo.Dto.cartItem.CartItemDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.Dto.cartItem.CartItemResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponseDto {

    public ShoppingCartResponseDto(){}

    public ShoppingCartResponseDto(CustomerResponseDto customerResponseDto, List<CartItemResponseDto> cartItemResponseDto){

        this.customerResponseDto = customerResponseDto;
        this.cartItemResponseDto = cartItemResponseDto;


    }

    private Long id;
    @JsonIgnore
    private CustomerResponseDto customerResponseDto;
    private List<CartItemResponseDto> cartItemResponseDto;
    private Long customerId;
    private BigDecimal total;

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

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItemResponseDto itemDto: cartItemResponseDto) {
            total = total.add(itemDto.getSubTotal());
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
