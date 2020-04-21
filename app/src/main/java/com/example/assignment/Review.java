package com.example.assignment;

public class Review {

    private String reviewText;
    private String rating;


    private StockItem stockItem;

    public Review(){}

    public Review(String reviewText, String rating, StockItem stockItem) {

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }


}
