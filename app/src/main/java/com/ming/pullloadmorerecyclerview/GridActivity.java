package com.ming.pullloadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ming.pullloadmorerecyclerview_lib.PullLoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 11:31
 */
public class GridActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        GridAdapter gridAdapter = new GridAdapter(this);
        PullLoadMoreView pullLoadMoreView = findViewById(R.id.pull_grid);
        pullLoadMoreView
                .setLayoutType(PullLoadMoreView.GRIDLAYOUT)
                .setSpacing(3,20,20,true,true)
                .setAdapter(gridAdapter)
                .commit();
        List<Integer> contents = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            contents.add(i);
        }
        gridAdapter.setContents(contents);
        pullLoadMoreView.setOnPullLoadMoreListener(new PullLoadMoreView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(GridActivity.this,"ghjghg",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }
}
