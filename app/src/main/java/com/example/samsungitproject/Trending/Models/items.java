package com.example.samsungitproject.Trending.Models;

import com.google.gson.annotations.SerializedName;

public class items {
    @SerializedName("item")
    private trending item;

    public trending getItem() {
        return item;
    }

    public void setItem(trending item) {
        this.item = item;
    }
}
