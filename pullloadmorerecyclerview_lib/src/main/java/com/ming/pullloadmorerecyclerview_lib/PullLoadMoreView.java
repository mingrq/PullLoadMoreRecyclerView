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
    private int SpanCount;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout headLayout;
    private RecyclerView recyclerView;
    private RelativeLayout footLayout;
    //适配器
    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;
    private int horizontalSpacing;
    private int verticalSpacing;

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
     * 设置适配器
     */
    public PullLoadMoreView setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * 设置分割线  LinerLayout布局使用
     */
    public PullLoadMoreView setDivider(int height, int color) {
        RecycleViewDivider divider = new RecycleViewDivider(context, layoutType);
        divider.setDrvider(height, color);
        recyclerView.addItemDecoration(divider);
        return this;
    }

    /**
     * 设置间距  GridLayout、StaggeredGridLayout布局使用
     * <p>
     *
     * @param SpanCount         跨距数
     * @param horizontalSpacing 水平间距
     * @param verticalSpacing   垂直间距
     * @param verticalMargin         是否需要上下边距
     * @param horizontalMargin        是否需要左右边距
     * @return
     */
    public PullLoadMoreView setSpacing(int SpanCount, int horizontalSpacing, int verticalSpacing, boolean horizontalMargin, boolean verticalMargin) {
        this.SpanCount = SpanCount;
        this.horizontalSpacing = horizontalSpacing;
        this.verticalSpacing = verticalSpacing;
        RecycleViewDivider divider = new RecycleViewDivider(context, layoutType);
        divider.setSpacing(SpanCount, horizontalSpacing, verticalSpacing, horizontalMargin, verticalMargin);
        recyclerView.addItemDecoration(divider);
        return this;
    }

    /**
     * 设置自定义分割线
     */
   /* public PullLoadMoreView setCustomDivider(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
        return this;
    }*/

    /**
     * 提交
     */
    public void commit() {
        switch (layoutType) {
            case LINERLAYOUT:
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                break;
            case GRIDLAYOUT:
                gridLayoutManager = new GridLayoutManager(context, SpanCount, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGEREDGRIDLAYOUT:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(SpanCount, StaggeredGridLayoutManager.VERTICAL);
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
        }
        recyclerView.setAdapter(adapter);
    }

}
