package com.example.assignment;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;

public class StockItem {

    private String name;
    private String brand;
    private String category;
    private String image;
    private String key;
    private double price;
    private int stock;

    ArrayList<Review> reviews = new ArrayList<>();

    public StockItem(){}

    public StockItem(String name, String brand, String category, String image, double price, int stock, ArrayList<Review> reviews) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Exclude
    public String getKey(){ return key; }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
