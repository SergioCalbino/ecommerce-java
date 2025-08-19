package com.example.demo.interfaces;


import com.example.demo.Dto.customer.CustomerDto;
import com.example.demo.Dto.customer.CustomerResponseDto;
import com.example.demo.entities.Customer;

public interface CustomerService {

    CustomerResponseDto create(CustomerDto customerDto);
    Customer createAndReturnEntity(CustomerDto customerDto);
    CustomerResponseDto myProfile(String email);
    CustomerResponseDto editProfile(String email, CustomerDto customerDto);

}
