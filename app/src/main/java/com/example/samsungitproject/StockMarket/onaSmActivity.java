package com.example.samsungitproject.StockMarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.samsungitproject.Authorization.User;
import com.example.samsungitproject.Graphic.ApiClient_graph;
import com.example.samsungitproject.Graphic.Models.graphic;
import com.example.samsungitproject.Main.MainActivity;
import com.example.samsungitproject.R;
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

public class onaSmActivity extends AppCompatActivity {

    TextView text, mainname, price, symbol, high_24, low_24, Photo, atl, atl_change_percentage, circulating_supply, market_cap_change_24h, market_cap_rank, price_change_24h, total_volume;
    ImageView image;
    LineChart mpLinechart;
    Button onachange_interval;

    //FireBase
    FirebaseAuth mAuth;
    FirebaseDatabase db;

    List<List<Double>> articles_graphic = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ona_sm);
        String name = getIntent().getStringExtra("name");
        String getprice = getIntent().getStringExtra("price");
        String getSymbol = getIntent().getStringExtra("symbol");
        String getHigh_24 = getIntent().getStringExtra("high_24");
        String getLow_24 = getIntent().getStringExtra("low_24");
        String getStrPhoto = getIntent().getStringExtra("photo");
        String getAtl = getIntent().getStringExtra("atl");
        String getAtl_change_percentage = getIntent().getStringExtra("atl_change_percentage");
        String getCirculating_supply = getIntent().getStringExtra("circulating_supply");
        String getMarket_cap_change_24h = getIntent().getStringExtra("market_cap_change_24h");
        String getMarket_cap_rank = getIntent().getStringExtra("market_cap_rank");
        String getPrice_change_24h = getIntent().getStringExtra("price_change_24h");
        String getTotal_volume = getIntent().getStringExtra("total_volume");

        onachange_interval = findViewById(R.id.onachange_interval);
        mainname = findViewById(R.id.ona_mainName);
        text = findViewById(R.id.name_onesm);
        symbol = findViewById(R.id.symbol_onesm);
        price = findViewById(R.id.price_onesm);
        image = findViewById(R.id.imageView_onesm);
        high_24 = findViewById(R.id.high_24_onesm);
        low_24 = findViewById(R.id.low_24_onesm);
        atl = findViewById(R.id.atl_onesm);
        atl_change_percentage = findViewById(R.id.atl_change_percentage_onesm);
        circulating_supply = findViewById(R.id.circulating_supply_onesm);
        market_cap_change_24h = findViewById(R.id.cap_change_24h_onesm);
        market_cap_rank = findViewById(R.id.market_cap_rank_layout_onesm);
        price_change_24h = findViewById(R.id.price_change_24h_onesm);
        total_volume = findViewById(R.id.total_volume_onesm);

        mainname.setText(name);
        text.setText(name);
        price.setText(getprice);
        symbol.setText(getSymbol);
        high_24.setText(getHigh_24);
        low_24.setText(getLow_24);
        Glide.with(onaSmActivity.this)
                .load(getStrPhoto)
                .into(image);
        atl.setText(getAtl);
        atl_change_percentage.setText(getAtl_change_percentage);
        circulating_supply.setText(getCirculating_supply);
        market_cap_change_24h.setText(getMarket_cap_change_24h);
        market_cap_rank.setText(getMarket_cap_rank);
        price_change_24h.setText(getPrice_change_24h);
        total_volume.setText(getTotal_volume);

        retrieveJson_graphic();

        onachange_interval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_changeInterval();
            }
        });
    }

    public void retrieveJson_graphic(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


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
                            mpLinechart = findViewById(R.id.onasm_LineChart);
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
                        Toast.makeText(onaSmActivity.this, "Watson, we have a problem", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(onaSmActivity.this);
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

}