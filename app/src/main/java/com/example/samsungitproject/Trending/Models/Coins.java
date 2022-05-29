package com.example.samsungitproject.Trending.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Coins {
    @SerializedName("coins")
    private List<items> coins;

    public List<items> getCoins() {
        return coins;
    }

    public void setCoins(List<items> coins) {
        this.coins = coins;
    }
}
