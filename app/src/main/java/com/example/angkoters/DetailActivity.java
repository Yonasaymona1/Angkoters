package com.example.angkoters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView rute = findViewById(R.id.rute);
        ImageView gambar = findViewById(R.id.gambar);

        rute.setText(getIntent().getStringExtra("rute"));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("gambar")).into(gambar);
    }
}