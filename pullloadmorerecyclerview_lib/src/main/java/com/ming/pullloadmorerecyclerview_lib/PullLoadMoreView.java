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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

    private static final int NODATA = 0x114254;//空数据页面
    private static final int CONNECTFAILED = 0x114255;//网络连接错误页面


    /*布局类型*/
    private int layoutType;
    private int SpanCount;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    //适配器
    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;
    private int horizontalSpacing;
    private int verticalSpacing;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private PullLoadMoreListener pullLoadMoreListener = null;
    private View connectFailedPage = null;
    private View noDataPage = null;
    private FrameLayout frameLayout;

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
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.recycle);
        frameLayout = findViewById(R.id.fly_pullloadmore);
    }
    /**---------------------------对外方法------------------------------------*/


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
     * @param verticalMargin    是否需要上下边距
     * @param horizontalMargin  是否需要左右边距
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
    public PullLoadMoreView setCustomDivider(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
        return this;
    }

    /**
     * 是否需要下拉刷新上拉加载功能
     *
     * @param isRefresh 刷新
     * @param isMore    加载更多
     */
    public PullLoadMoreView setNeedRefreshAndMore(boolean isRefresh, boolean isMore) {
        this.isRefresh = isRefresh;
        this.isMore = isMore;
        return this;
    }

    /**
     * 设置刷新状态
     *
     * @param isRefreshing
     */
    public void setRefreshing(boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    /**
     * 设置正在加载数据状态
     *
     * @param isLoadMoreing
     */
    public void setLoadMoreing(boolean isLoadMoreing) {

    }

    /**
     * 设置下拉刷新上拉加载监听
     *
     * @param pullLoadMoreListener
     */
    public void setOnPullLoadMoreListener(PullLoadMoreListener pullLoadMoreListener) {
        this.pullLoadMoreListener = pullLoadMoreListener;
    }

    /**
     * 设置空数据页面
     *
     * @param noDataPage
     * @return
     */
    public PullLoadMoreView setNoDataPage(View noDataPage) {
        this.noDataPage = noDataPage;
        return this;
    }

    /**
     * 打开空数据页面
     */
    public void openNoDataPage() {
        if (noDataPage == null) {
            //没有自定义，使用默认页面
            noDataPage = new NoDataView(context);
            noDataPage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "空数据", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (frameLayout != null) {
            frameLayout.addView(noDataPage);
            showPage(NODATA);
        }
    }


    /**
     * 设置网络连接失败页面
     *
     * @param connectFailedPage
     * @return
     */
    public PullLoadMoreView setConnectFailedPage(View connectFailedPage) {
        this.connectFailedPage = connectFailedPage;
        return this;
    }

    /**
     * 打开网络连接失败页面
     */
    public void openConnectFailedPage() {
        if (connectFailedPage == null) {
            //没有自定义，使用默认页面
            connectFailedPage = new ConnectFailedView(context);
            connectFailedPage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "网络连接失败", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (frameLayout != null) {
            frameLayout.addView(connectFailedPage);
            showPage(CONNECTFAILED);
        }
    }

    /**
     * 设置显示的页面
     * @param page
     */
    private void showPage(int page) {
        recyclerView.setVisibility(GONE);
        if (noDataPage != null) {
            if (page == NODATA) {
                noDataPage.setVisibility(VISIBLE);
            } else {
                noDataPage.setVisibility(GONE);
            }
        }
        if (connectFailedPage != null) {
            if (page == CONNECTFAILED) {
                connectFailedPage.setVisibility(VISIBLE);
            } else {
                connectFailedPage.setVisibility(GONE);
            }
        }
    }

    /**
     * 上拉加载更多状态
     */
    public void moreing(){

    }
    /**
     * 没有更多数据状态
     */
    public void noMore(){

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
                gridLayoutManager = new GridLayoutManager(context, SpanCount, LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGEREDGRIDLAYOUT:
                staggeredGridLayoutManager = new StaggeredGridLayoutManager(SpanCount, StaggeredGridLayoutManager.VERTICAL);
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                staggeredGridLayoutManager.invalidateSpanAssignments();
                break;
        }
        swipeRefreshLayout.setEnabled(isRefresh);//设置是否启动下拉刷新
        //为RecyclerView设置刷新监听
        if (isRefresh && pullLoadMoreListener != null) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    pullLoadMoreListener.onRefresh();
                }
            });
        }
        recyclerView.setAdapter(adapter);
    }


    /*------------------------------------------接口---------------------------------------------------------*/

    /**
     * 上拉加载下拉刷新监听接口
     */
    public interface PullLoadMoreListener {
        //刷新回调
        void onRefresh();

        //加载更多回调
        void onLoadMore();
    }


}


