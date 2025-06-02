package com.example.demo.mappers;


import com.example.demo.Dto.CustomerDto;
import com.example.demo.Dto.CustomerResponseDto;
import com.example.demo.entities.Customer;

public class CustomerMapper {

    public static CustomerResponseDto toDto(Customer customer) {

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setAddres(customer.getAddress());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setPassword(customer.getPassword());
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
}
