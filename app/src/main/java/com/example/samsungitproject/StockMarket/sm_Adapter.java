package com.example.samsungitproject.StockMarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samsungitproject.R;
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.List;

public class sm_Adapter extends RecyclerView.Adapter<sm_Adapter.ViewHolder>{
    Context context;
    List<StockMarket> articles;

    public sm_Adapter(Context context, List<StockMarket> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public sm_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sm_main_card, parent, false);
        return new sm_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull sm_Adapter.ViewHolder holder, int position) {
        StockMarket list = articles.get(position);
        holder.title.setText(list.getTitle());
        holder.price.setText(list.getPrice());
        holder.high24.setText(list.getHigh_24());
        holder.low24.setText(list.getLow_24());
    }



    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, price, high24, low24;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name_se);
            price = itemView.findViewById(R.id.price_cm);
            high24 = itemView.findViewById(R.id.year_se);
            low24 = itemView.findViewById(R.id.country_se);

        }
    }
}
