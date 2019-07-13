package com.ming.pullloadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ming.pullloadmorerecyclerview_lib.PullLoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 11:31
 */
public class GridActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        GridAdapter gridAdapter = new GridAdapter(this);
        final PullLoadMoreView pullLoadMoreView = findViewById(R.id.pull_grid);
        pullLoadMoreView
                .setInitLayoutType(PullLoadMoreView.GRIDLAYOUT)
                .setInitSpacing(4, 10, 10, true, true)
                .setInitAdapter(gridAdapter);

        // pullLoadMoreView.openNoDataPage();
        //pullLoadMoreView.openConnectFailedPage();
        List<Integer> contents = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            contents.add(i);
        }
        gridAdapter.setContents(contents);
        pullLoadMoreView.setInitOnLoadMoreListener(new PullLoadMoreView.LoadMoreListener() {

            @Override
            public void onLoadMore() {
                Toast.makeText(GridActivity.this, "test", Toast.LENGTH_LONG).show();
                //pullLoadMoreView.setFooterType(PullLoadMoreView.NOMORE);
            }
        });
        pullLoadMoreView.setInitOnPullLoadListener(new PullLoadMoreView.PullLoadListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(GridActivity.this, "ghjghg", Toast.LENGTH_LONG).show();
            }
        });

        pullLoadMoreView.commit();
    }
}
