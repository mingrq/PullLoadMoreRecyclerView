package com.ming.pullloadmorerecyclerview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button linerButton = findViewById(R.id.liner);
        Button gridButton = findViewById(R.id.grid);
        Button staButton = findViewById(R.id.stagg);
        Button listButton = findViewById(R.id.lsit);
        linerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LinerActivity.class));
            }
        });
        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GridActivity.class));
            }
        });
        staButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StaggeredgridActivity.class));
            }
        });
        ImageView imageView = findViewById(R.id.images);
        Glide.with(this).load("http://img0.imgtn.bdimg.com/it/u=2847223552,3832031298&fm=26&gp=0.jpg").into(imageView);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListsActivity.class));
            }
        });
    }
}