package com.example.samsungitproject.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsungitproject.Authorization.User;
import com.example.samsungitproject.Graphic.ApiClient_graph;
import com.example.samsungitproject.Graphic.Models.graphic;
import com.example.samsungitproject.News.ApiClient_news;
import com.example.samsungitproject.News.Models.Articles_news;
import com.example.samsungitproject.News.Models.Headlines_news;
import com.example.samsungitproject.News.News_Adapter;
import com.example.samsungitproject.News.naNewsActivity;
import com.example.samsungitproject.R;
import com.example.samsungitproject.StockExchange.ApiClient_se;
import com.example.samsungitproject.StockExchange.Models.StockExchange;
import com.example.samsungitproject.StockExchange.se_Adapter;
import com.example.samsungitproject.StockMarket.ApiClient_sm;
import com.example.samsungitproject.StockMarket.Models.StockMarket;
import com.example.samsungitproject.StockMarket.nasm_Adapter;
import com.example.samsungitproject.StockMarket.sm_Adapter;
import com.example.samsungitproject.StockMarket.activity_na_sm;
import com.example.samsungitproject.StockExchange.activity_na_se;
import com.example.samsungitproject.Trending.ApiClient_tr;
import com.example.samsungitproject.Trending.Models.Coins;
import com.example.samsungitproject.Trending.Models.items;
import com.example.samsungitproject.Trending.tr_Adapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //LineChart
    LineChart mpLinechart;

    //Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase db;

    //tv, bt
    TextView tvNews, tvSm, tvSe, name, text_test, price_course2, price_course1, price_course3, name_course1, name_course2, name_course3;
    Button ChangeCrypto, change_Course1, change_Course2, change_Course3, ChangeInterval;
    ImageView icon;

    //Graphic
    List<List<Double>> articles_graphic = new ArrayList<>();


    //News
    final String API_KEY = "90e5580936944b0697cdeafdf92f87e6";
    News_Adapter news_adapter;
    List<Articles_news> articles_news = new ArrayList<>();
    RecyclerView recyclerView_news;

    //Trending
    tr_Adapter tr_adapter;
    List<items> articles_tr = new ArrayList<>();
    RecyclerView recyclerView_tr;

    //Stock Market
    sm_Adapter sm_adapter;
    List<StockMarket> sm_articles = new ArrayList<>();
    RecyclerView recyclerView_sm;

    RecyclerView recyclerView_sm_ch;
    nasm_Adapter adapter_sm_ch;
    List<StockMarket> sm_change_articles = new ArrayList<>();

    //Stock Exchange
    se_Adapter se_adapter;
    List<StockExchange> se_articles = new ArrayList<>();
    RecyclerView recyclerView_se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inizalizing
        //ChangeCrypto
        ChangeCrypto = findViewById(R.id.change_crypto);
        change_Course1 = findViewById(R.id.change_course1);
        change_Course2 = findViewById(R.id.change_course2);
        change_Course3 =findViewById(R.id.change_course3);

        //ChangeInterval
        ChangeInterval = findViewById(R.id.change_interval);

        //Stock Exchange
        tvSe = findViewById(R.id.tv_StockExchange);
        recyclerView_se = findViewById(R.id.recyclerview_se);
        recyclerView_se.setLayoutManager(new LinearLayoutManager(this));

        //Setting
        icon = findViewById(R.id.icon_setting);

        //News
        tvNews = findViewById(R.id.tvNewsMain);
        recyclerView_news = findViewById(R.id.recyclerview_news);
        recyclerView_news.setLayoutManager(new LinearLayoutManager(this));

        //Stock Market
        tvSm = findViewById(R.id.tvStockMarketMain);
        recyclerView_sm = findViewById(R.id.recyclerview_sm);
        recyclerView_sm.setLayoutManager(new LinearLayoutManager(this));

        recyclerView_sm_ch = findViewById(R.id.recyclerview_change_sm);
        recyclerView_sm_ch.setLayoutManager(new LinearLayoutManager(this));


        //Course
        recyclerView_tr = findViewById(R.id.recyclerview_trending);
        recyclerView_tr.setLayoutManager(new LinearLayoutManager(this));

        name_course1 = findViewById(R.id.name_course1);
        name_course2 = findViewById(R.id.name_course2);
        name_course3 = findViewById(R.id.name_course3);
        price_course1 = findViewById(R.id.price_course1);
        price_course2 = findViewById(R.id.price_course2);
        price_course3 = findViewById(R.id.price_course3);

        //Click
        //News
        tvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, naNewsActivity.class);
                startActivity(i);
            }
        });

        //Setting
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });

        //StockExchange
        tvSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_na_se.class);
                startActivity(i);
            }
        });

        //StockMarket
        tvSm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_na_sm.class);
                startActivity(i);
            }
        });

        //Save_change_sm
        ChangeCrypto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_changeCrypto();
            }
        });

        change_Course1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                source_changeCrypto("course_1");
            }
        });

        change_Course2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                source_changeCrypto("course_2");
            }
        });

        change_Course3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                source_changeCrypto("course_3");
            }
        });

        //Change interval
        ChangeInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_changeInterval();
            }
        });

        //Call function
        //News
        retrieveJson_news("crypto", API_KEY);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);recyclerView_news.setLayoutManager(horizontalLayoutManagaer);

        //Stock market
        retrieveJson_sm("usd", "market_cap_desc", 25, 1, false);
        show_changeCrypto();

        //Firebase
        fb_Name();

        //Stock Exchange
        retrieveJson_se(20, 1);

        //Trending
        retrieveJson_trending();

        retrieveJson_graphic();

        courseMain();




    }

    public void retrieveJson_graphic(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        name = findViewById(R.id.user_name);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String currency = user.currency;
                String interval = user.interval;

                Call<graphic> call = ApiClient_graph.getInstance().getApi().getGraphics(currency,"usd", interval, "1%20hours");
                call.enqueue(new Callback<graphic>() {
                    @Override
                    public void onResponse(Call<graphic> call, Response<graphic> response) {
                        if(response.isSuccessful() && response.body().getPrices()!= null){
                            articles_graphic.clear();
                            articles_graphic = response.body().getPrices();

                            //ArrayList
                            ArrayList<Entry> dataVals = new ArrayList<Entry>();
                            for(int i = 0; i<articles_graphic.size(); i++){
                                double count = articles_graphic.get(i).get(1);
                                dataVals.add(new Entry(i, (float) count));
                            }
                            mpLinechart = findViewById(R.id.main_LineChart);
                            LineDataSet lineDataset1 = new LineDataSet(dataVals, "");
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            YAxis yAxisLeft =   mpLinechart.getAxisLeft();
                            XAxis xAxis =  mpLinechart.getXAxis();
                            Legend l =  mpLinechart.getLegend();
                            yAxisLeft.setTextColor(Color.rgb(255, 255, 255));
                            xAxis.setTextColor(Color.rgb(255, 255, 255));
                            mpLinechart.getAxisRight().setDrawLabels(false);
                            lineDataset1.setDrawValues(false);
                            lineDataset1.setDrawCircles(false);
                            dataSets.add(lineDataset1);
                            LineData data = new LineData(dataSets);
                            mpLinechart.setData(data);
                            mpLinechart.invalidate();
                        }
                    }
                    @Override
                    public void onFailure(Call<graphic> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void save_changeInterval(){
        db = FirebaseDatabase.getInstance();
        DatabaseReference  root =
                db.getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("interval");


        final String [] list = {"1 hour", "4 hours",  "12 hours","24 hours","48 hours"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        //mBuilder.setTitle("Choose");
        mBuilder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){

                    root.setValue("0.041666666");
                }
                if(i == 1){
                    root.setValue("0.166666666");
                }
                if(i == 2){
                    root.setValue("0.5");
                }
                if(i == 3){
                    root.setValue("1");
                }
                if(i == 4){
                    root.setValue("2");
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    public void retrieveJson_news(String country, String apiKey){
        Call<Headlines_news> call = ApiClient_news.getInstance().getApi().getHeadlines("crypto", "90e5580936944b0697cdeafdf92f87e6");
        call.enqueue(new Callback<Headlines_news>() {
            @Override
            public void onResponse(Call<Headlines_news> call, Response<Headlines_news> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    articles_news.clear();
                    articles_news = response.body().getArticles();
                    news_adapter = new News_Adapter(MainActivity.this, articles_news);
                    recyclerView_news.setAdapter(news_adapter);
                    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView_news.setLayoutManager(horizontalLayoutManagaer);

                }
            }

            @Override
            public void onFailure(Call<Headlines_news> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrieveJson_trending(){
        Call<Coins> call = ApiClient_tr.getInstance().getApi().getTrending();
        call.enqueue(new Callback<Coins>() {
            @Override
            public void onResponse(Call<Coins> call, Response<Coins> response) {
                if(response.isSuccessful() && response.body().getCoins() != null){
                    articles_tr.clear();
                    articles_tr = response.body().getCoins();
                    tr_adapter = new tr_Adapter(MainActivity.this, articles_tr);
                    recyclerView_tr.setAdapter(tr_adapter);
                }
            }

            @Override
            public void onFailure(Call<Coins> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrieveJson_se(int per_page, int page){
        Call<List<StockExchange>> call = ApiClient_se.getInstance().getApi().getStockExchange(per_page, page);
        call.enqueue(new Callback<List<StockExchange>>() {
            @Override
            public void onResponse(Call<List<StockExchange>> call, Response<List<StockExchange>> response) {
                if(response.isSuccessful() && response.body() != null){
                    se_articles.clear();
                    se_articles = response.body();
                    se_adapter = new se_Adapter(MainActivity.this, se_articles);
                    recyclerView_se.setAdapter(se_adapter);

                }
            }

            @Override
            public void onFailure(Call<List<StockExchange>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void retrieveJson_sm(String vs_currency, String order, int per_page, int page, boolean sparkline){
        Call<List<StockMarket>> call = ApiClient_sm.getInstance().getApi().getStockMarket(vs_currency, order, per_page, page, sparkline);
        call.enqueue(new Callback<List<StockMarket>>() {
            @Override
            public void onResponse(Call<List<StockMarket>> call, Response<List<StockMarket>> response) {
                if(response.isSuccessful() && response.body() != null){
                                sm_articles.clear();
                                sm_articles = response.body();
                                sm_adapter = new sm_Adapter(MainActivity.this, sm_articles);
                                recyclerView_sm.setAdapter(sm_adapter);

                }
            }

            @Override
            public void onFailure(Call<List<StockMarket>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void courseMain(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        name = findViewById(R.id.user_name);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user!=null){
                    String course1 = user.course_1;
                    String course2 = user.course_2;
                    String course3 = user.course_3;


                Call<List<StockMarket>> call = ApiClient_sm.getInstance().getApi().getStockMarket_change("usd", course1, "market_cap_desc", 1, 1, false);
                call.enqueue(new Callback<List<StockMarket>>() {
                    @Override
                    public void onResponse(Call<List<StockMarket>> call, Response<List<StockMarket>> response) {
                        if (response.isSuccessful() && response.body() != null) {{
                            sm_change_articles.clear();
                            sm_change_articles = response.body();

                            StockMarket object = sm_change_articles.get(0);
                            name_course1.setText(object.getTitle());
                            price_course1.setText(object.getPrice());



                            adapter_sm_ch = new nasm_Adapter(MainActivity.this, sm_change_articles);
                            recyclerView_sm_ch.setAdapter(adapter_sm_ch);}}}
                    @Override
                    public void onFailure(Call<List<StockMarket>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
                    }});

                    Call<List<StockMarket>> call2 = ApiClient_sm.getInstance().getApi().getStockMarket_change("usd", course2, "market_cap_desc", 1, 1, false);
                    call2.enqueue(new Callback<List<StockMarket>>() {
                        @Override
                        public void onResponse(Call<List<StockMarket>> call, Response<List<StockMarket>> response) {
                            if (response.isSuccessful() && response.body() != null) {{
                                sm_change_articles.clear();
                                sm_change_articles = response.body();

                                StockMarket object = sm_change_articles.get(0);
                                name_course2.setText(object.getTitle());
                                price_course2.setText(object.getPrice());



                                adapter_sm_ch = new nasm_Adapter(MainActivity.this, sm_change_articles);
                                recyclerView_sm_ch.setAdapter(adapter_sm_ch);}}}
                        @Override
                        public void onFailure(Call<List<StockMarket>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
                        }});


                    Call<List<StockMarket>> call3 = ApiClient_sm.getInstance().getApi().getStockMarket_change("usd", course3, "market_cap_desc", 1, 1, false);
                    call3.enqueue(new Callback<List<StockMarket>>() {
                        @Override
                        public void onResponse(Call<List<StockMarket>> call, Response<List<StockMarket>> response) {
                            if (response.isSuccessful() && response.body() != null) {{
                                sm_change_articles.clear();
                                sm_change_articles = response.body();

                                StockMarket object = sm_change_articles.get(0);
                                name_course3.setText(object.getTitle());
                                price_course3.setText(object.getPrice());



                                adapter_sm_ch = new nasm_Adapter(MainActivity.this, sm_change_articles);
                                recyclerView_sm_ch.setAdapter(adapter_sm_ch);}}}
                        @Override
                        public void onFailure(Call<List<StockMarket>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
                        }});


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void retrive_Course(String course, int digit){


 }

    public void fb_Name(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        name = findViewById(R.id.user_name);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user!=null){
                    name.setText(user.name+" "+user.lastname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }







    private void save_changeCrypto() {
        db = FirebaseDatabase.getInstance();
        DatabaseReference  root =
                db.getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("currency");


        final String [] list = {"Bitcoin", "BNB","Etheteum","USD Coin","Tether", "XRP", "Binance USD", "Cardano", "Solana"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        //mBuilder.setTitle("Choose");
        mBuilder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){

                    root.setValue("bitcoin");
                    Toast.makeText(MainActivity.this, "Bitcoin", Toast.LENGTH_LONG).show();
                }
                if(i == 1){
                    root.setValue("binancecoin");
                    Toast.makeText(MainActivity.this, "BNB", Toast.LENGTH_LONG).show();
                }
                if(i == 2){
                    root.setValue("ethereum");
                    Toast.makeText(MainActivity.this, "Ethereum", Toast.LENGTH_LONG).show();
                }
                if(i == 3){
                    root.setValue("usd-coin");
                    Toast.makeText(MainActivity.this, "USD Coin", Toast.LENGTH_LONG).show();
                }
                if(i == 4){
                    root.setValue("tether");
                    Toast.makeText(MainActivity.this, "Tether", Toast.LENGTH_LONG).show();
                }

                if(i == 5){
                    root.setValue("ripple");
                    Toast.makeText(MainActivity.this, "XRP", Toast.LENGTH_LONG).show();
                }

                if(i == 6){
                    root.setValue("binance-usd");
                    Toast.makeText(MainActivity.this, "Binance USD", Toast.LENGTH_LONG).show();
                }

                if(i == 7){
                    root.setValue("cardano");
                    Toast.makeText(MainActivity.this, "Cardano", Toast.LENGTH_LONG).show();
                }
                if(i == 8){
                    root.setValue("solana");
                    Toast.makeText(MainActivity.this, "Solana", Toast.LENGTH_LONG).show();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void source_changeCrypto(String text) {
        db = FirebaseDatabase.getInstance();
        DatabaseReference root =
                db.getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(text);
        final String [] list = {"Bitcoin", "BNB","Etheteum","USD Coin","Tether", "XRP", "Binance USD", "Cardano", "Solana"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        //mBuilder.setTitle("Choose");
        mBuilder.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){

                    root.setValue("bitcoin");
                    Toast.makeText(MainActivity.this, "Bitcoin", Toast.LENGTH_LONG).show();
                }
                if(i == 1){
                    root.setValue("binancecoin");
                    Toast.makeText(MainActivity.this, "BNB", Toast.LENGTH_LONG).show();
                }
                if(i == 2){
                    root.setValue("ethereum");
                    Toast.makeText(MainActivity.this, "Ethereum", Toast.LENGTH_LONG).show();
                }
                if(i == 3){
                    root.setValue("usd-coin");
                    Toast.makeText(MainActivity.this, "USD Coin", Toast.LENGTH_LONG).show();
                }
                if(i == 4){
                    root.setValue("tether");
                    Toast.makeText(MainActivity.this, "Tether", Toast.LENGTH_LONG).show();
                }

                if(i == 5){
                    root.setValue("ripple");
                    Toast.makeText(MainActivity.this, "XRP", Toast.LENGTH_LONG).show();
                }

                if(i == 6){
                    root.setValue("binance-usd");
                    Toast.makeText(MainActivity.this, "Binance USD", Toast.LENGTH_LONG).show();
                }

                if(i == 7){
                    root.setValue("cardano");
                    Toast.makeText(MainActivity.this, "Cardano", Toast.LENGTH_LONG).show();
                }
                if(i == 8){
                    root.setValue("solana");
                    Toast.makeText(MainActivity.this, "Solana", Toast.LENGTH_LONG).show();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    public void show_changeCrypto() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String currency = user.currency;
                Call<List<StockMarket>> call = ApiClient_sm.getInstance().getApi().getStockMarket_change("usd", currency, "market_cap_desc", 1, 1, false);
                call.enqueue(new Callback<List<StockMarket>>() {
                    @Override
                    public void onResponse(Call<List<StockMarket>> call, Response<List<StockMarket>> response) {
                        if (response.isSuccessful() && response.body() != null) {{
                                sm_change_articles.clear();
                                sm_change_articles = response.body();
                                adapter_sm_ch = new nasm_Adapter(MainActivity.this, sm_change_articles);
                                recyclerView_sm_ch.setAdapter(adapter_sm_ch);}}}
                    @Override
                    public void onFailure(Call<List<StockMarket>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
                    }}); }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }

}