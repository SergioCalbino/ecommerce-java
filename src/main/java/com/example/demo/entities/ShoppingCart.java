package com.example.demo.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // El carrito pertenece a un único cliente
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // El carrito puede tener muchos ítems (CartItem)
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private BigDecimal total;

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

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item: this.cartItems) {
             total = total.add(item.getSubtotal());
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
