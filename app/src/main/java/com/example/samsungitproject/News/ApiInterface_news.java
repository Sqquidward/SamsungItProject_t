package com.example.samsungitproject.News;

import com.example.samsungitproject.News.Models.Headlines_news;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface_news {
    @GET("everything")
    //q=crypto&apiKey=90e5580936944b0697cdeafdf92f87e6
    Call<Headlines_news> getHeadlines(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
