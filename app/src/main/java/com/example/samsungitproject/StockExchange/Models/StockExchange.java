package com.example.samsungitproject.StockExchange.Models;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StockExchange {
    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private String country;

    @SerializedName("year_established")
    private String year;


    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    @SerializedName("trust_score")
    private String trust_score;

    @SerializedName("trade_volume_24h_btc")
    private String trade_volume;

    @Expose
    @SerializedName("trade_volume_24h_btc_normalized")
    private String trade_volume_norm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrust_score() {
        return trust_score;
    }

    public void setTrust_score(String trust_score) {
        this.trust_score = trust_score;
    }

    public String getTrade_volume() {
        return trade_volume;
    }

    public void setTrade_volume(String trade_volume) {
        this.trade_volume = trade_volume;
    }

    public String getTrade_volume_norm() {
        return trade_volume_norm;
    }

    public void setTrade_volume_norm(String trade_volume_norm) {
        this.trade_volume_norm = trade_volume_norm;
    }
}
