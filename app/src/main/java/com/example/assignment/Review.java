package com.example.assignment;

public class Review {

    private String reviewText;
    private int rating;

    private StockItem stockItem;

    public Review(){}

    public Review(String reviewText, int rating, StockItem stockItem) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.stockItem = stockItem;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }
}
