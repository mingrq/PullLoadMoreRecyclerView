package com.ming.pullloadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
        GridAdapter linerAdapter = new GridAdapter(this);
        PullLoadMoreView pullLoadMoreView = findViewById(R.id.pull_grid);
        pullLoadMoreView
                .setLayoutType(PullLoadMoreView.GRIDLAYOUT)
                .setSpacing(2,20,20,false,false
                )
                .setAdapter(linerAdapter)
                .commit();
        List<Integer> contents = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            contents.add(i);
        }
        linerAdapter.setContents(contents);
    }
}
