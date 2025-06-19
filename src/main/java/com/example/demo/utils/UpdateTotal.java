package com.example.demo.utils;

import com.example.demo.entities.ShoppingCart;

import java.math.BigDecimal;

public class UpdateTotal {

    public static void updateTotal(ShoppingCart shoppingCart){
        BigDecimal total = shoppingCart.getCartItems().stream()
                .map(item -> item.getSubtotal())
                .reduce(BigDecimal.ZERO, (acc, subtotal) -> acc.add(subtotal));
        shoppingCart.setTotal(total);
    }
}
