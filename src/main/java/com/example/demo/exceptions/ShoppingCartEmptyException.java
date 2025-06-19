package com.example.demo.exceptions;

public class ShoppingCartEmptyException extends RuntimeException {
  public ShoppingCartEmptyException(String message) {
    super(message);
  }
}
