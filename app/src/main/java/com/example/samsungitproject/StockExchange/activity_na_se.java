package com.example.samsungitproject.StockExchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.samsungitproject.Main.MainActivity;
import com.example.samsungitproject.R;
import com.example.samsungitproject.StockExchange.Models.StockExchange;
import com.example.samsungitproject.StockMarket.ApiClient_sm;
import com.example.samsungitproject.StockMarket.Models.StockMarket;
import com.example.samsungitproject.StockMarket.activity_na_sm;
import com.example.samsungitproject.StockMarket.nasm_Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_na_se extends AppCompatActivity {

    RecyclerView recyclerView_nase;
    nase_Adapter adapter;
    List<StockExchange> nase_articles = new ArrayList<>();
    LinearLayout cvSm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_na_se);


        recyclerView_nase = findViewById(R.id.recyclerview_nase);
        recyclerView_nase.setLayoutManager(new LinearLayoutManager(this));
        retrieveJson_nase(50, 1);
    }

    public void retrieveJson_nase(int per_page, int page){
        Call<List<StockExchange>> call = ApiClient_se.getInstance().getApi().getStockExchange(per_page, page);
        call.enqueue(new Callback<List<StockExchange>>() {
            @Override
            public void onResponse(Call<List<StockExchange>> call, Response<List<StockExchange>> response) {
                if(response.isSuccessful() && response.body() != null){
                    nase_articles.clear();
                    nase_articles = response.body();
                    adapter = new nase_Adapter(activity_na_se.this, nase_articles);
                    recyclerView_nase.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<StockExchange>> call, Throwable t) {
                Toast.makeText(activity_na_se.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });

    }
}