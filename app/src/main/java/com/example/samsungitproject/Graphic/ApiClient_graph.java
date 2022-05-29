package com.example.samsungitproject.Graphic;

import com.example.samsungitproject.News.ApiClient_news;
import com.example.samsungitproject.News.ApiInterface_news;
import com.example.samsungitproject.StockMarket.ApiInterface_sm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient_graph {
    public static final String BASE_URL ="https://api.coingecko.com/";
    private static ApiClient_graph apiClient;
    private static Retrofit retrofit;

    private  ApiClient_graph(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient_graph getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient_graph();
        }
        return apiClient;
    }

    public ApiInterface_graph getApi(){
        return retrofit.create(ApiInterface_graph.class);
    }
}
