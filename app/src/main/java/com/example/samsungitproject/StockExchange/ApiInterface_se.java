package com.example.samsungitproject.StockExchange;

import com.example.samsungitproject.StockExchange.Models.StockExchange;
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface_se {
    @GET("api/v3/exchanges")
    Call<List<StockExchange>> getStockExchange(
            @Query("per_page") int per_page,
            @Query("page") int page
    );
}


//pi/v3/exchanges?per_page=10&page=1
