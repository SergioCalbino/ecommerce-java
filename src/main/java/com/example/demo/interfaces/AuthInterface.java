package com.example.demo.interfaces;

import com.example.demo.Dto.auth.AuthRequestDto;
import com.example.demo.Dto.auth.AuthResponseDto;
import com.example.demo.Dto.customer.CustomerDto;

public interface AuthInterface {


    AuthResponseDto registerAndGenerateToken(CustomerDto customerDto);
    AuthResponseDto login(AuthRequestDto authRequestDto);

}
