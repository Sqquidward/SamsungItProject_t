package com.example.samsungitproject.StockMarket;

import com.example.samsungitproject.News.Models.Headlines_news;
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface_sm {
    @GET("api/v3/coins/markets")
    Call<List<StockMarket>> getStockMarket(
            @Query("vs_currency") String vs_currency,
            @Query("order") String order,
            @Query("per_page") int per_page,
            @Query("page") int page,
            @Query("sparkline") boolean sparkline);


    @GET("api/v3/coins/markets")
    Call<List<StockMarket>> getStockMarket_change(
            @Query("vs_currency") String vs_currency,
            @Query("ids") String id,
            @Query("order") String order,
            @Query("per_page") int per_page,
            @Query("page") int page,
            @Query("sparkline") boolean sparkline);
}
