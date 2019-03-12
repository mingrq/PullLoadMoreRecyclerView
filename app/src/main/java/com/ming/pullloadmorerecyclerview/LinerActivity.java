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
public class LinerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liner);
        LinerAdapter linerAdapter = new LinerAdapter(this);
        PullLoadMoreView pullLoadMoreView = findViewById(R.id.pull_liner);
        pullLoadMoreView
                .setLayoutType(PullLoadMoreView.LINERLAYOUT)
                .setDivider(1,getResources().getColor(R.color.colorPrimaryDark))
                .setAdapter(linerAdapter)
                .setNeedRefreshAndMore(true,true)
                .commit();
        pullLoadMoreView.setOnPullLoadMoreListener(new PullLoadMoreView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        List<Integer> contents = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            contents.add(i);
        }
        linerAdapter.setContents(contents);

    }
}
