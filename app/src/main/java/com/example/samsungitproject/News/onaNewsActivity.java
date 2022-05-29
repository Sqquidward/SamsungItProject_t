package com.example.samsungitproject.News;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.samsungitproject.R;

public class onaNewsActivity extends AppCompatActivity {

    TextView tvTitle, tvSource, tvData, tvContent;
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ona_news);

        String name = getIntent().getStringExtra("name");
        String source = getIntent().getStringExtra("source");
        String data = getIntent().getStringExtra("data");
        String content = getIntent().getStringExtra("content");
        String image = getIntent().getStringExtra("image");
        String url = getIntent().getStringExtra("url");

        tvTitle = findViewById(R.id.tv_titleona);
        tvContent = findViewById(R.id.tv_maintextona);
        tvData = findViewById(R.id.tv_dataona);
        tvSource = findViewById(R.id.tv_sourceona);
        iv = findViewById(R.id.ona_imageNews);

        tvTitle.setText(name);
        tvData.setText(data);
        tvSource.setText(source);
        tvContent.setText(content);

        Glide.with(onaNewsActivity.this)
                .load(image)
                .into(iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });



    }
}