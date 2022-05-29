package com.example.samsungitproject.StockMarket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samsungitproject.R;
import com.example.samsungitproject.StockMarket.Models.StockMarket;

import java.util.List;

public class nasm_Adapter extends RecyclerView.Adapter<nasm_Adapter.ViewHolder>{
    Context context;
    List<StockMarket> articles;

    public nasm_Adapter(Context context, List<StockMarket> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public nasm_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sm_na_card, parent, false);
        return new nasm_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull nasm_Adapter.ViewHolder holder, int position) {
        StockMarket list = articles.get(position);
        holder.symbol.setText(list.getSymbol());
        holder.name.setText(list.getTitle());
        Glide.with(context).load(list.getImage())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);
        holder.price.setText(list.getPrice());
        holder.high24.setText(list.getHigh_24());
        holder.marketcaprank.setText(list.getMarket_cap_rank());
        holder.low24.setText(list.getLow_24());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ok", Toast.LENGTH_LONG).show();//Вот это работает
                Intent intent = new Intent(context, onaSmActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", list.getTitle());
                intent.putExtra("photo", list.getImage());
                intent.putExtra("symbol", list.getSymbol());
                intent.putExtra("price", list.getPrice());
                intent.putExtra("image", list.getImage());
                intent.putExtra("high_24", list.getHigh_24());
                intent.putExtra("low_24", list.getLow_24());
                intent.putExtra("atl", list.getAtl());
                intent.putExtra("atl_change_percentage", list.getAtl_change_percentage());
                intent.putExtra("circulating_supply", list.getCirculating_supply());
                intent.putExtra("market_cap_change_24h", list.getMarket_cap_change_24h());
                intent.putExtra("market_cap_rank", list.getMarket_cap_rank());
                intent.putExtra("price_change_24h", list.getPrice_change_24h());
                intent.putExtra("total_volume", list.getTotal_volume());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView symbol, name, price, high24, low24, marketcaprank;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            symbol = (TextView)itemView.findViewById(R.id.id_nasm_ch);
            name = (TextView) itemView.findViewById(R.id.name_nase_ch);
            image = (ImageView) itemView.findViewById(R.id.imageView_nase);
            price = (TextView) itemView.findViewById(R.id.price_nasm_ch);
            high24 = (TextView) itemView.findViewById(R.id.high24_nase_ch);
            low24 = (TextView) itemView.findViewById(R.id.low_24_nasm_ch);
            marketcaprank = (TextView) itemView.findViewById(R.id.marketcaprank_nase_ch);

        }
    }
}
