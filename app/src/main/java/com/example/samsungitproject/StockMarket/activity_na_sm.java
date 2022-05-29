package com.example.samsungitproject.StockMarket;

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
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_na_sm extends AppCompatActivity {

    RecyclerView recyclerView_nasm;
    nasm_Adapter adapter;
    List<StockMarket> nasm_articles = new ArrayList<>();
    LinearLayout cvSm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_na_sm);

        cvSm = findViewById(R.id.cv_smna);
        cvSm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_na_sm.this, MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView_nasm = findViewById(R.id.recyclerview_nasm);
        recyclerView_nasm.setLayoutManager(new LinearLayoutManager(this));
        retrieveJson_nasm("usd", "market_cap_desc", 50, 1, false);
    }

    public void retrieveJson_nasm(String vs_currency, String order, int per_page, int page, boolean sparkline){
        Call<List<StockMarket>> call = ApiClient_sm.getInstance().getApi().getStockMarket(vs_currency, order, per_page, page, sparkline);
        call.enqueue(new Callback<List<StockMarket>>() {
            @Override
            public void onResponse(Call<List<StockMarket>> call, Response<List<StockMarket>> response) {
                if(response.isSuccessful() && response.body() != null){
                    nasm_articles.clear();
                    nasm_articles = response.body();
                    adapter = new nasm_Adapter(activity_na_sm.this, nasm_articles);
                    recyclerView_nasm.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<StockMarket>> call, Throwable t) {
                Toast.makeText(activity_na_sm.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });

    }
}