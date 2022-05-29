package com.example.samsungitproject.News;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsungitproject.Main.MainActivity;
import com.example.samsungitproject.News.Models.Articles_news;
import com.example.samsungitproject.News.Models.Headlines_news;
import com.example.samsungitproject.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class naNewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    final String API_KEY = "90e5580936944b0697cdeafdf92f87e6";
    naNews_Adapter adapter;
    List<Articles_news> articles = new ArrayList<>();
    LinearLayout cvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_na_news);

        recyclerView = findViewById(R.id.recyclerview_nanews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrieveJson("crypto", API_KEY);
        cvNews = findViewById(R.id.cv_newsna);
        cvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(naNewsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void retrieveJson(String query, String apiKey){
        //apikey=pub_5503902c01c4a1e7c70a7f838bc4e8ee1ff7&q=crypto&language=en
        Call<Headlines_news> call = ApiClient_news.getInstance().getApi().getHeadlines("crypto", "90e5580936944b0697cdeafdf92f87e6");
        call.enqueue(new Callback<Headlines_news>() {
            @Override
            public void onResponse(Call<Headlines_news> call, Response<Headlines_news> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new naNews_Adapter(naNewsActivity.this, articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines_news> call, Throwable t) {
                Toast.makeText(naNewsActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });

    }
}