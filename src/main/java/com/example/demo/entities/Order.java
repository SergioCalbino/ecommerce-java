package com.example.demo.entities;

import com.example.demo.entities.utilities.OrderState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    public Order(){

    }

    public Order(Date date, Customer customer, List<OrderItem> orderItems, BigDecimal total, OrderState state, String paymentId) {
        this.date = date;
        this.customer = customer;
        this.orderItems = orderItems;
        this.total = total;
        this.state = state;
        this.paymentId = paymentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    // Cada orden pertenece a un cliente (N:1)
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private String paymentId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : this.orderItems) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;

    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
