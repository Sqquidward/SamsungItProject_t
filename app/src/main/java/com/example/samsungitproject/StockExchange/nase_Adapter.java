package com.example.samsungitproject.StockExchange;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import java.util.List;

public class nase_Adapter extends RecyclerView.Adapter<nase_Adapter.ViewHolder>{
    Context context;
    List<StockExchange> articles;

    public nase_Adapter(Context context, List<StockExchange> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public nase_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.se_na_card, parent, false);
        return new nase_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull nase_Adapter.ViewHolder holder, int position) {
        StockExchange list = articles.get(position);
        holder.name.setText(CheckWord(list.getName()));
        holder.year.setText(list.getYear());
        holder.country.setText(list.getCountry());

        Glide.with(context).load(articles.get(position)
                .getImage())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);

        holder.trade24h.setText(list.getTrade_volume().substring(0, 8));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.getUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public String CheckWord(String word){
        return word.replaceAll("Exchange", "");
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, year, country, trade24h;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_nase_ch);
            year = itemView.findViewById(R.id.marketcaprank_nase_ch);
            country = itemView.findViewById(R.id.country_nase);
            image = itemView.findViewById(R.id.imageView_nase);
            trade24h = itemView.findViewById(R.id.trade_btc24h_nase);

        }
    }
}

