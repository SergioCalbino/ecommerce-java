package com.example.demo.exceptions;

public class EmailAlreadyUsedException extends RuntimeException{
    public EmailAlreadyUsedException(String message){
        super(message);
    }
}
