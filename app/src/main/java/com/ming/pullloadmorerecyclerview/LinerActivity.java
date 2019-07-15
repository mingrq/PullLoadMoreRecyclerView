package com.ming.pullloadmorerecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.widget.Toast;

import com.ming.pullloadmorerecyclerview_lib.PullLoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 11:31
 */
public class LinerActivity extends AppCompatActivity {

    private PullLoadMoreView pullLoadMoreView;
    private LinerAdapter linerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liner);
        linerAdapter = new LinerAdapter(getBaseContext());
        pullLoadMoreView = findViewById(R.id.pull_liner);
        final List<Integer> contents = new ArrayList<>();
        final int[] k = {0};
        pullLoadMoreView
                .setInitAdapter(linerAdapter)
                .setInitFooterViewEnable(false)
                .setInitRefreshAndMoreEnable(false,false)
                .setInitLayoutType(PullLoadMoreView.LINERLAYOUT,PullLoadMoreView.VERTICAL)
               // .setInitItemMoveAnimation(new DragItemTouchHelper(linerAdapter))
                .setInitOnPullLoadListener(new PullLoadMoreView.PullLoadListener() {
                    @Override
                    public void onRefresh() {
                        contents.clear();
                        k[0] = 0;
                        for (int i = 0; i <10; i++) {
                            contents.add(i);
                        }
                        pullLoadMoreView.getSwipeRefreshLayout().setRefreshing(false);
                        linerAdapter.setContents(contents);
                    }
                })
                .setInitOnLoadMoreListener(new PullLoadMoreView.LoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        final List<Integer> contenta = new ArrayList<>();
                        if (k[0] < 2) {
                            k[0]++;
                            for (int i = 0; i < 1; i++) {
                                contenta.add(i);
                            }
                            //((SimpleItemAnimator) pullLoadMoreView.getRecyclerView().getItemAnimator()).setSupportsChangeAnimations(false);
                           // linerAdapter.addContents(contenta);
                            pullLoadMoreView.complete();
                        }
                    }
                })
                .commit();
        pullLoadMoreView.onRefresh();
    }
}
