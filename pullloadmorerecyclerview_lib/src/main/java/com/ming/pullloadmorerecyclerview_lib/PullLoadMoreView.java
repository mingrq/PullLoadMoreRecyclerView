package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * 下拉刷新上拉加载控件
 * ===============================
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2018/12/27 11:28
 */
public class PullLoadMoreView extends FrameLayout {
    public static final int LINERLAYOUT = 0x00000;//线性布局
    public static final int GRIDLAYOUT = 0x00010;//网格布局
    public static final int STAGGEREDGRIDLAYOUT = 0x00020;//瀑布流

    /*布局类型*/
    private int layoutType;
    private int column;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout headLayout;
    private RecyclerView recyclerView;
    private RelativeLayout footLayout;
    //适配器
    private RecyclerView.Adapter adapter;

    public PullLoadMoreView(@NonNull Context context) {
        this(context, null);
    }

    public PullLoadMoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullLoadMoreView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.pullloadmoreview, this);
        /*swipeRefreshLayout = findViewById(R.id.swiperefresh);
        headLayout = findViewById(R.id.head_layout);*/
        recyclerView = findViewById(R.id.recycle);
       /* footLayout = findViewById(R.id.foot_layout);*/
    }
    /*---------------------------对外方法------------------------------------*/


    /**
     * 设置布局方式
     *
     * @param layoutType 布局方式
     */
    public PullLoadMoreView setLayoutType(int layoutType) {
        this.layoutType = layoutType;
        return this;
    }

    /**
     * 设置布局列数
     *
     * @param column 列数
     */
    public PullLoadMoreView setLayoutColumn(int column) {
        this.column = column;
        return this;
    }

    /**
     * 设置适配器
     */
    public PullLoadMoreView setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * 提交
     */
    public void commit() {
        switch (layoutType) {
            case LINERLAYOUT:
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                break;
            case GRIDLAYOUT:
                recyclerView.setLayoutManager(new GridLayoutManager(context, column));
                break;
            case STAGGEREDGRIDLAYOUT:
                break;
        }
        recyclerView.setAdapter(adapter);
    }

}
