package com.example.samsungitproject.StockExchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samsungitproject.R;
import com.example.samsungitproject.StockExchange.Models.StockExchange;
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.List;

public class se_Adapter extends RecyclerView.Adapter<se_Adapter.ViewHolder>{
    Context context;
    List<StockExchange> articles;

    public se_Adapter(Context context, List<StockExchange> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public se_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.se_main_card, parent, false);
        return new se_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull se_Adapter.ViewHolder holder, int position) {
        StockExchange list = articles.get(position);
        holder.name.setText(CheckWord(list.getName()));
        holder.year.setText(list.getYear());
        holder.country.setText(list.getCountry());

        Glide.with(context).load(articles.get(position)
                .getImage())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    public String CheckWord(String word){
        return word.replaceAll("Exchange", "");
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, year, country;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_se);
            year = itemView.findViewById(R.id.year_se);
            country = itemView.findViewById(R.id.country_se);
            image = itemView.findViewById(R.id.image_se);

        }
    }
}
