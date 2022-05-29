package com.example.samsungitproject.News;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samsungitproject.News.Models.Articles_news;
import com.example.samsungitproject.R;
import com.example.samsungitproject.Trending.Models.items;

import java.util.List;

public class News_Adapter extends RecyclerView.Adapter<News_Adapter.ViewHolder> {
    Context context;
    List<Articles_news> articles;

    public News_Adapter(Context context, List<Articles_news> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_main_card, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles_news a = articles.get(position);
        holder.tvSource.setText(a.getSource().getName());
        Glide.with(context).load(articles.get(position)
                .getUrlToImage())
                .apply(RequestOptions.centerCropTransform()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, onaNewsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", a.getTitle());
                intent.putExtra("source", a.getSource().getName());
                intent.putExtra("data", Date(a.getPublishedAt()));
                intent.putExtra("content", a.getContent());
                intent.putExtra("image", a.getUrlToImage());
                intent.putExtra("url", a.getUrl());
                context.startActivity(intent);
            }
        });
    }

    public String Date(String Data){
        String smtdata =  Data.substring(0, 4)+"."+Data.substring(5, 7)+"."+Data.substring(8, 10);
        return smtdata;
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSource;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSource = itemView.findViewById(R.id.Source);
            image = itemView.findViewById(R.id.image_news);
        }
    }
}
