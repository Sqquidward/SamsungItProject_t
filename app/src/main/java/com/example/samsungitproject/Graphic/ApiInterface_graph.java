package com.example.samsungitproject.Graphic;

import com.example.samsungitproject.Graphic.Models.graphic;
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface_graph {
    @GET("api/v3/coins/{currency}/market_chart?vs_currency=usd&days=0.04166666666&interval=1%20hours")
    Call<graphic> getGraphics(
            @Path("currency") String version,
            @Query("vs_currency") String vs_currency,
            @Query("days") String days,
            @Query("interval") String intreval);

}
