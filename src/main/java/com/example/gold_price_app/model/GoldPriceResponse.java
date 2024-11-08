// src/main/java/com/example/goldpriceapp/model/GoldPriceResponse.java
package com.example.gold_price_app.model;

public class GoldPriceResponse {
    private String metal;
    private String currency;
    private double price;

    // getters and setters
    public String getMetal() { return metal; }
    public void setMetal(String metal) { this.metal = metal; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
