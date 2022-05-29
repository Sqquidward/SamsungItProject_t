package com.example.samsungitproject.Graphic.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class graphic {
    @SerializedName("prices")
    private List<List<Double>> Prices;

    public List<List<Double>> getPrices() {
        return Prices;
    }

    public void setPrices(List<List<Double>> prices) {
        Prices = prices;
    }
}
