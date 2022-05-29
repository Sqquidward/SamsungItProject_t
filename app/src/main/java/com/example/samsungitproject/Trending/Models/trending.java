package com.example.samsungitproject.Trending.Models;

import com.google.gson.annotations.SerializedName;

public class trending {
    // "id": "terra-luna-2",
    //        "coin_id": 25767,
    //        "name": "Terra",
    //        "symbol": "LUNA",
    //        "market_cap_rank": 21,
    //        "thumb": "https://assets.coingecko.com/coins/images/25767/thumb/01_Luna_color.png?1653556122",
    //        "small": "https://assets.coingecko.com/coins/images/25767/small/01_Luna_color.png?1653556122",
    //        "large": "https://assets.coingecko.com/coins/images/25767/large/01_Luna_color.png?1653556122",
    //        "slug": "terra",
    //        "price_btc": 0.000198225240829782,
    //        "score": 0

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("market_cap_rank")
    private String market_cap_rank;

    @SerializedName("large")
    private String large;

    @SerializedName("price_btc")
    private String price_btc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(String market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }
}
