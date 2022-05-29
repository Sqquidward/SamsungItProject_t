package com.example.samsungitproject.Trending;

import com.example.samsungitproject.StockMarket.Models.StockMarket;
import com.example.samsungitproject.Trending.Models.Coins;
import com.example.samsungitproject.Trending.Models.trending;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface_tr {

    @GET("api/v3/search/trending")
    Call<Coins> getTrending();

}
