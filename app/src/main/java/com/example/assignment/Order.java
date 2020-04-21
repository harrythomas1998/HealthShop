package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String paymentMethod;
    private String userEmail;
    private double total;
    private List<StockItem> items = new ArrayList<>();

    public Order(){

    }

    public Order(String paymentMethod, String userEmail, double total, List<StockItem> items) {
        this.paymentMethod = paymentMethod;
        this.userEmail = userEmail;
        this.total = total;
        this.items = items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getUser() {
        return userEmail;
    }

    public void setUser(String user) {
        this.userEmail = user;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<StockItem> getItems() {
        return items;
    }

    public void setItems(List<StockItem> items) {
        this.items = items;
    }
}
