package com.example.samsungitproject.Trending;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsungitproject.R;
import com.example.samsungitproject.StockMarket.Models.StockMarket;
import com.example.samsungitproject.Trending.Models.items;
import com.example.samsungitproject.Trending.Models.trending;

import java.util.List;

public class tr_Adapter extends RecyclerView.Adapter<tr_Adapter.ViewHolder>{
    Context context;
    List<items> articles;

    public tr_Adapter(Context context, List<items> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public tr_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trend_main_card, parent, false);
        return new tr_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull tr_Adapter.ViewHolder holder, int position) {
        items list = articles.get(position);
        holder.title.setText(priceCut(list.getItem().getName()));
        holder.price.setText(priceCut(list.getItem().getPrice_btc()));
    //    holder.symbol.setText(list.getItem().getSymbol());
    }



    public String priceCut(String text){
        if(text.length()>19){
            text = text.substring(0, 19);}
        return text;
    }



    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, symbol, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name_trend);
            price = itemView.findViewById(R.id.tv_price_trend);
        }
    }
}

