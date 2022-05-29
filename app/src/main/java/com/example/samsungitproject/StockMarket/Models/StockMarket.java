package com.example.samsungitproject.StockMarket.Models;

import com.google.gson.annotations.SerializedName;

public class StockMarket {
    @SerializedName("symbol")
    private String symbol;

    @SerializedName("name")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("current_price")
    private String price;

    @SerializedName("high_24h")
    private String high_24;

    @SerializedName("market_cap_rank")
    private String market_cap_rank;

    @SerializedName("low_24h")
    private String low_24;

    @SerializedName("total_volume")
    private String total_volume;

    @SerializedName("price_change_24h")
    private String price_change_24h;

    @SerializedName("market_cap_change_24h")
    private String market_cap_change_24h;

    @SerializedName("circulating_supply")
    private String circulating_supply;

    @SerializedName("atl")
    private String atl;

    @SerializedName("atl_change_percentage")
    private String atl_change_percentage;

    public StockMarket(String symbol, String title, String image, String price, String high_24, String market_cap_rank, String low_24, String total_volume, String price_change_24h, String market_cap_change_24h, String circulating_supply, String atl, String atl_change_percentage) {
        this.symbol = symbol;
        this.title = title;
        this.image = image;
        this.price = price;
        this.high_24 = high_24;
        this.market_cap_rank = market_cap_rank;
        this.low_24 = low_24;
        this.total_volume = total_volume;
        this.price_change_24h = price_change_24h;
        this.market_cap_change_24h = market_cap_change_24h;
        this.circulating_supply = circulating_supply;
        this.atl = atl;
        this.atl_change_percentage = atl_change_percentage;
    }

    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHigh_24() {
        return high_24;
    }

    public void setHigh_24(String high_24) {
        this.high_24 = high_24;
    }

    public String getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(String market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public String getLow_24() {
        return low_24;
    }

    public void setLow_24(String low_24) {
        this.low_24 = low_24;
    }

    public String getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(String total_volume) {
        this.total_volume = total_volume;
    }

    public String getPrice_change_24h() {
        return price_change_24h;
    }

    public void setPrice_change_24h(String price_change_24h) {
        this.price_change_24h = price_change_24h;
    }

    public String getMarket_cap_change_24h() {
        return market_cap_change_24h;
    }

    public void setMarket_cap_change_24h(String market_cap_change_24h) {
        this.market_cap_change_24h = market_cap_change_24h;
    }

    public String getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(String circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public String getAtl() {
        return atl;
    }

    public void setAtl(String atl) {
        this.atl = atl;
    }

    public String getAtl_change_percentage() {
        return atl_change_percentage;
    }

    public void setAtl_change_percentage(String atl_change_percentage) {
        this.atl_change_percentage = atl_change_percentage;
    }
}
