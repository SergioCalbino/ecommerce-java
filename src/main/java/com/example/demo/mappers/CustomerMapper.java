package com.example.demo.mappers;


import com.example.demo.Dto.cartItem.CartItemSummaryDto;
import com.example.demo.Dto.customer.CustomerCartDto;
import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.Dto.shoppingCart.CartSummaryDto;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Product;

import java.util.List;

public class CustomerMapper {

    public static CustomerResponseDto toDto(Customer customer) {

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setId(customer.getId());
        customerResponseDto.setAddres(customer.getAddress());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        //customerResponseDto.setPassword(customer.getPassword());
        customerResponseDto.setOrderResponseDto(
                customer.getOrders()
                        .stream()
                        .map((order) -> OrderMapper.toDto(order))
                        .toList()
        );
       customerResponseDto.setShoppingResponseDto(ShoppingMapper.toDto(customer.getShoppingCart()));

        return customerResponseDto;

    }

    public static Customer toEntity(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setOrders(customerDto.getOrderDto()
                .stream()
                .map((order) -> OrderMapper.toEntity(order))
                .toList()
        );

        return customer;
    }

    public static CustomerCartDto toCleanCartResponse(Customer customer) {

        List<CartItemSummaryDto> items = customer.getShoppingCart().getCartItems()
                .stream()
                .map(item -> {
                    Product p = item.getProduct();
                    return new CartItemSummaryDto(
                            p.getId(),
                            p.getName(),
                            item.getQuantity(),
                            p.getStock(),
                            p.getPrice()

                    );
                }).toList();

        return new CustomerCartDto(
                customer.getName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getId(),
                new CartSummaryDto(items)
        );



    }
}
