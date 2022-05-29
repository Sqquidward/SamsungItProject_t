package com.example.samsungitproject.StockExchange;

import com.example.samsungitproject.StockMarket.ApiClient_sm;
import com.example.samsungitproject.StockMarket.ApiInterface_sm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient_se {
    public static final String BASE_URL ="https://api.coingecko.com/";
    private static ApiClient_se apiClient;
    private static Retrofit retrofit;

    private  ApiClient_se(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient_se getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient_se();
        }
        return apiClient;
    }

    public ApiInterface_se getApi(){
        return retrofit.create(ApiInterface_se.class);
    }

}
