package com.example.samsungitproject.StockMarket;

import com.example.samsungitproject.News.ApiClient_news;
import com.example.samsungitproject.News.ApiInterface_news;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient_sm {
    public static final String BASE_URL ="https://api.coingecko.com/";
    private static ApiClient_sm apiClient;
    private static Retrofit retrofit;

    private  ApiClient_sm(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient_sm getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient_sm();
        }
        return apiClient;
    }

    public ApiInterface_sm getApi(){
        return retrofit.create(ApiInterface_sm.class);
    }
}
