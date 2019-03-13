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
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

    public static final int NOMORE = 0x000123741;//设置完成加载数据无数据状态
    public static final int MOREING = 0x000123742;//设置正在加载数据状态
    public static final int LOADMOREERROR = 0x00123743;//设置加载数据错误状态

    /*布局类型*/
    private int layoutType;
    private int SpanCount;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    //适配器
    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private boolean isRefresh = true;
    private boolean isMore = true;
    private PullLoadMoreListener pullLoadMoreListener = null;
    private View connectFailedPage = null;
    private View noDataPage = null;
    private FrameLayout frameLayout;
    private LinearLayoutManager linearLayoutManager;
    private View footerView = null;
    private PullLoadMoreFooterCallBack footerCallBack;

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

    /**
     * 脚布局状态回调
     */
    public interface PullLoadMoreFooterCallBack {
        //设置完成加载数据无数据
        void noMore(View footerView);

        //设置正在加载数据
        void moreing(View footerView);

        //设置加载数据错误
        void moreError(View footerView);
    }


    public class PullLoadMoreViewAdapter extends RecyclerView.Adapter {
        private final int FOOTER = 0x0009012453;

        private RecyclerView.Adapter adapter;

        private boolean isLoadMoreItem(int position) {
            return isMore && position == getItemCount() - 1;
        }

        public PullLoadMoreViewAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && holder.getLayoutPosition() == getItemCount() - 1) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                // 如果是刷新、加载更多或头布局、脚布局独占一行，否则按照设置展示
                p.setFullSpan(true); // 设置独占一行
            }
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            adapter.onAttachedToRecyclerView(recyclerView);
            final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager.SpanSizeLookup spanSizeLookup = ((GridLayoutManager) manager).getSpanSizeLookup();
                GridLayoutManager.SpanSizeLookup sizeLookup = new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int i) {
                        int type = getItemViewType(i);
                        if (type == FOOTER) {
                            return SpanCount;
                        }
                        return spanSizeLookup.getSpanSize(i);
                    }
                };
                ((GridLayoutManager) manager).setSpanSizeLookup(sizeLookup);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isLoadMoreItem(position)) {
                return FOOTER;
            } else {
                return adapter.getItemViewType(position);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == FOOTER) {
                //脚布局
                if (footerView == null) {
                    footerView = new FooterView(context);
                }
                return new FooterViewHolder(footerView);

            }
            return adapter.onCreateViewHolder(viewGroup, i);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (isLoadMoreItem(i)) {
                return;
            }
            adapter.onBindViewHolder(viewHolder, i);
        }

        @Override
        public int getItemCount() {
            int count = adapter == null ? 0 : adapter.getItemCount();
            if (count == 0) {
                return 0;
            }
            return isMore ? count + 1 : count;
        }

        @Override
        public long getItemId(int position) {
            return adapter.getItemId(position);
        }

        @Override
        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            adapter.registerAdapterDataObserver(observer);
        }

        @Override
        public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            if (adapter.hasObservers()) {
                adapter.unregisterAdapterDataObserver(observer);
            }
        }
    }


    private class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
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
        RecycleViewDivider divider = new RecycleViewDivider(context, layoutType, isRefresh, isMore);
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
        RecycleViewDivider divider = new RecycleViewDivider(context, layoutType, isRefresh, isMore);
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
     * 设置下拉刷新上拉加载监听
     *
     * @param pullLoadMoreListener
     */
    public PullLoadMoreView setOnPullLoadMoreListener(PullLoadMoreListener pullLoadMoreListener) {
        this.pullLoadMoreListener = pullLoadMoreListener;
        return this;
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
     * 设置脚布局
     */
    public PullLoadMoreView setFooterView(View customFooterView, PullLoadMoreFooterCallBack footerCallBack) {
        this.footerView = customFooterView;
        this.footerCallBack = footerCallBack;
        return this;
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
     * 提交
     */
    public void commit() {
        switch (layoutType) {
            case LINERLAYOUT:
                linearLayoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(linearLayoutManager);
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
        if (isMore && pullLoadMoreListener != null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    int lastPosition = -1;
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //滑动停止
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if (layoutManager instanceof LinearLayoutManager) {
                            lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        }
                        if (layoutManager instanceof GridLayoutManager) {
                            lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                        }
                        if ((layoutManager instanceof StaggeredGridLayoutManager)) {
                            int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                            lastPosition = findMax(lastPositions);
                        }
                    }
                    int itemcount = recyclerView.getAdapter().getItemCount() - 1;
                    if (lastPosition == itemcount) {
                        //滑动到最后一个
                        pullLoadMoreListener.onLoadMore();

                    }
                }

                //找到数组中的最大值
                private int findMax(int[] lastPositions) {
                    int max = lastPositions[0];
                    for (int value : lastPositions) {
                        if (value > max) {
                            max = value;
                        }
                    }
                    return max;
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }
        PullLoadMoreViewAdapter pullLoadMoreViewAdapter = new PullLoadMoreViewAdapter(adapter);
        recyclerView.setAdapter(pullLoadMoreViewAdapter);
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
     * 设置脚布局状态
     */
    public void setFooterType(int type) {
        if (isMore && footerView != null && footerCallBack != null) {
            //使用自定义脚布局
            switch (type) {
                case MOREING://设置正在加载数据状态
                    footerCallBack.moreing(footerView);
                    break;
                case NOMORE://设置完成加载数据无数据状态
                    footerCallBack.noMore(footerView);
                    break;
                case LOADMOREERROR://设置加载数据错误状态
                    footerCallBack.moreError(footerView);
                    break;
            }
        } else {
            ((FooterView) footerView).setFooterType(type);
        }

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
     *
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
}