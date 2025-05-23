package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    public Customer() {
    }

    public Customer(String name, String email, String password, String address, List<Order> orders, ShoppingCart shoppingCart) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.orders = orders;
        this.shoppingCart = shoppingCart;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String address;


    // Un cliente puede tener muchas órdenes (relación 1:N)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    // Un cliente tiene un único carrito (relación 1:1)
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
