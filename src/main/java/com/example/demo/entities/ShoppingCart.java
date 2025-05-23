package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    public ShoppingCart() {
    }

    public ShoppingCart(Customer customer, List<CartItem> cartItems) {
        this.customer = customer;
        this.cartItems = cartItems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // El carrito pertenece a un único cliente
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // El carrito puede tener muchos ítems (CartItem)
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
