package com.example.samsungitproject.News;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient_news {

    public static final String BASE_URL ="https://newsapi.org/v2/";
    //https://newsapi.org/v2/
    private static ApiClient_news apiClient;
    private static Retrofit retrofit;

    private  ApiClient_news(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized ApiClient_news getInstance(){
        if(apiClient == null){
            apiClient = new ApiClient_news();
        }
        return apiClient;
    }

    public ApiInterface_news getApi(){
        return retrofit.create(ApiInterface_news.class);
    }
}
