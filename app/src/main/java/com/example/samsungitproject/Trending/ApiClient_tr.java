package com.example.samsungitproject.Trending;

import com.example.samsungitproject.StockMarket.ApiClient_sm;
import com.example.samsungitproject.StockMarket.ApiInterface_sm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient_tr {
    public static final String BASE_URL ="https://api.coingecko.com/";
    private static ApiClient_tr apiClient;
    private static Retrofit retrofit;

    private  ApiClient_tr(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient_tr getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient_tr();
        }
        return apiClient;
    }

    public ApiInterface_tr getApi(){
        return retrofit.create(ApiInterface_tr.class);
    }
}
