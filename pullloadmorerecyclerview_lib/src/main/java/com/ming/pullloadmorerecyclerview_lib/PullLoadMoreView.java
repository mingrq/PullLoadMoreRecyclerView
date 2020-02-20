package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ming.pullloadmorerecyclerview_lib.layout.FooterView;

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

    public static final int HORIZONTAL = 0;//横向布局
    public static final int VERTICAL = 1;//纵向布局

    private static final int DATA = 0x114253;//数据页面
    private static final int NODATA = 0x114254;//空数据页面
    private static final int CONNECTFAILED = 0x114255;//网络连接错误页面

    public static final int FOOTERNOMORE = 0x000123741;//设置脚布局为完成加载数据无数据状态
    public static final int FOOTERMOREING = 0x000123742;//设置脚布局为正在加载数据状态
    public static final int FOOTERLOADMOREERROR = 0x00123743;//设置脚布局为加载数据错误状态

    /*布局类型*/
    private int layoutType = LINERLAYOUT;
    private int SpanCount;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    //适配器
    private RecyclerView.Adapter adapter;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private boolean isRefreshEnable = true;
    private boolean isMoreEnable = true;
    private boolean isHaveMore= true;
    private PullLoadListener pullLoadListener = null;
    private LoadMoreListener loadMoreListener = null;
    private View connectFailedPage = null;
    private View noDataPage = null;
    private FrameLayout frameLayout;
    private LinearLayoutManager linearLayoutManager;
    private View footerView = null;
    private PullLoadMoreFooterCallBack footerCallBack;
    private RecycleViewDivider divider;
    private int height = 1;
    private int color = R.color.defultDivider;
    private int horizontalSpacing;
    private int verticalSpacing;
    private boolean horizontalMargin;
    private boolean verticalMargin;
    private RecyclerView.ItemDecoration itemDecoration;
    private boolean isCustomDivider = false;
    private boolean isDividerEnable = true;
    private boolean isFooterViewEnable = true;
    //布局方向
    private int orientation = 1;
    //是否反转布局
    private boolean isReverseLayoutEnable = false;

    private PullLoadMoreViewAdapter pullLoadMoreViewAdapter;
    private boolean itemAnimationEnable = false;
    private boolean isStackFromEndEnable = false;

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
     * 下拉刷新监听接口
     */
    public interface PullLoadListener {
        //刷新回调
        void onRefresh();
    }

    /**
     * 上拉加载监听接口
     */
    public interface LoadMoreListener {
        //加载更多回调
        void onLoadMore();
    }

    /**
     * 脚布局状态回调
     */
    public interface PullLoadMoreFooterCallBack {
        //设置完成加载数据无更多数据
        void noMore(View footerView);

        //设置正在加载数据
        void moreing(View footerView);

        //设置加载数据错误
        void moreError(View footerView);
    }


    public class PullLoadMoreViewAdapter extends RecyclerView.Adapter {
        private final int FOOTER = 0x009012453;

        private RecyclerView.Adapter adapter;

        private boolean isLoadMoreItem(int position) {
            return isFooterViewEnable && isMoreEnable && position == getItemCount() - 1;
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
            return isMoreEnable && isFooterViewEnable ? count + 1 : count;
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

    /**
     * 设置显示的页面
     *
     * @param page
     */
    private void showPage(int page) {
        //空数据页面
        if (noDataPage != null) {
            if (page == NODATA) {
                noDataPage.setVisibility(VISIBLE);
            } else {
                noDataPage.setVisibility(GONE);
            }
        }
        //网络连接错误页面
        if (connectFailedPage != null) {
            if (page == CONNECTFAILED) {
                connectFailedPage.setVisibility(VISIBLE);
            } else {
                connectFailedPage.setVisibility(GONE);
            }
        }
        //正常数据页面
        if (recyclerView != null) {
            if (page == DATA) {
                recyclerView.setVisibility(VISIBLE);
            } else {
                recyclerView.setVisibility(GONE);
            }
        }
    }

    /**
     * GridLayout、StaggeredGridLayout布局设置间隔
     */
    private void setDivider() {
        if (divider == null) {
            divider = new RecycleViewDivider(context, layoutType, isRefreshEnable, isMoreEnable);
        }
        divider.setSpacing(SpanCount, horizontalSpacing, verticalSpacing, horizontalMargin, verticalMargin);
        recyclerView.addItemDecoration(divider);
    }

    /**
     * 设置脚布局状态
     */
    private void setFooterType(int type) {
        if (isFooterViewEnable && isMoreEnable && footerView != null && footerCallBack != null) {
            //使用自定义脚布局
            switch (type) {
                case FOOTERMOREING://设置正在加载数据状态
                    footerCallBack.moreing(footerView);
                    break;
                case FOOTERNOMORE://设置完成加载数据无数据状态
                    footerCallBack.noMore(footerView);
                    break;
                case FOOTERLOADMOREERROR://设置加载数据错误状态
                    footerCallBack.moreError(footerView);
                    break;
            }
        } else {
            //默认脚布局
            ((FooterView) footerView).setFooterType(type);
        }
    }

    /**---------------------------对外方法------------------------------------*/


//-------------------------------------LinerLayout布局使用-------------------------------------

    /**
     * 是否需要分割线
     */
    public PullLoadMoreView setInitDividerEnable(boolean isDividerEnable) {
        this.isDividerEnable = isDividerEnable;
        return this;
    }

    /**
     * 设置分割线  LinerLayout布局使用
     */
    public PullLoadMoreView setInitDivider(int height, int color) {
        this.height = height;
        this.color = color;
        isCustomDivider = false;
        return this;
    }

    /**
     * 设置自定义分割线
     */
    public PullLoadMoreView setInitCustomDivider(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        isCustomDivider = true;
        return this;
    }

    /**
     * 设置反转布局是否激活
     */
    public PullLoadMoreView setReverseLayoutEnable(boolean isReverseLayoutEnable) {
        this.isReverseLayoutEnable = isReverseLayoutEnable;
        return this;
    }

    /**
     * 设置从底部开始显示数据是否激活
     */
    public PullLoadMoreView setStackFromEndEnable(boolean isStackFromEndEnable) {
        this.isStackFromEndEnable = isStackFromEndEnable;
        return this;
    }


//------------------------------GridLayout、StaggeredGridLayout布局使用---------------------------------------

    /**
     * 是否需要下拉刷新上拉加载功能
     *
     * @param isRefreshEnable 刷新
     * @param isMoreEnable    加载更多
     */
    public PullLoadMoreView setInitRefreshAndMoreEnable(boolean isRefreshEnable, boolean isMoreEnable) {
        this.isRefreshEnable = isRefreshEnable;
        this.isMoreEnable = isMoreEnable;
        return this;
    }

    /**
     * 设置布局方式
     *
     * @param layoutType  布局方式
     * @param orientation 布局方向
     * @return
     */
    public PullLoadMoreView setInitLayoutType(int layoutType, int orientation) {
        this.layoutType = layoutType;
        this.orientation = orientation;
        return this;
    }

    /**
     * 设置下拉刷新监听
     *
     * @param pullLoadListener
     */
    public PullLoadMoreView setInitOnPullLoadListener(PullLoadListener pullLoadListener) {
        this.pullLoadListener = pullLoadListener;
        return this;
    }

    /**
     * 设置上拉加载监听
     *
     * @param loadMoreListener
     */
    public PullLoadMoreView setInitOnLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
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
    public PullLoadMoreView setInitSpacing(int SpanCount, int horizontalSpacing, int verticalSpacing, boolean horizontalMargin, boolean verticalMargin) {
        this.SpanCount = SpanCount;
        this.horizontalSpacing = horizontalSpacing;
        this.verticalSpacing = verticalSpacing;
        this.horizontalMargin = horizontalMargin;
        this.verticalMargin = verticalMargin;
        return this;
    }
//------------------------------公用初始化方法---------------------------------------

    /**
     * 设置适配器
     */
    public PullLoadMoreView setInitAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * 设置item拖动动画
     */
    public PullLoadMoreView setInitItemTouchAnimation(ItemTouchHelper.Callback callback) {
        itemAnimationEnable = true;
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        return this;
    }

    /**
     * 是否需要脚布局
     */
    public PullLoadMoreView setInitFooterViewEnable(boolean isFooterViewEnable) {
        this.isFooterViewEnable = isFooterViewEnable;
        return this;
    }

    /**
     * 设置脚布局
     */
    public PullLoadMoreView setInitFooterView(View customFooterView, PullLoadMoreFooterCallBack footerCallBack) {
        this.footerView = customFooterView;
        this.footerCallBack = footerCallBack;
        return this;
    }

    /**
     * 设置空数据页面
     *
     * @param noDataPage
     * @return
     */
    public PullLoadMoreView setInitNoDataPage(View noDataPage) {
        this.noDataPage = noDataPage;
        frameLayout.addView(noDataPage);
        return this;
    }

    /**
     * 设置网络连接失败页面
     *
     * @param connectFailedPage
     * @return
     */
    public PullLoadMoreView setInitConnectFailedPage(View connectFailedPage) {
        this.connectFailedPage = connectFailedPage;
        frameLayout.addView(connectFailedPage);
        return this;
    }

    /**
     * 提交
     */
    public void commit() {
        //设置是否启动下拉刷新
        swipeRefreshLayout.setEnabled(isRefreshEnable);
        //设置控件布局方式
        switch (layoutType) {
            case LINERLAYOUT:
                linearLayoutManager = new LinearLayoutManager(context);

                if (orientation == 0) {
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                }
                //设置布局正序还是倒序
                linearLayoutManager.setReverseLayout(isReverseLayoutEnable);

                //设置数据是否从底部开始显示
                linearLayoutManager.setStackFromEnd(isStackFromEndEnable);
                recyclerView.setLayoutManager(linearLayoutManager);
                //是否需要分割线
                if (isDividerEnable) {
                    //是否使用自定义分割线
                    if (isCustomDivider) {
                        recyclerView.addItemDecoration(itemDecoration);
                    } else {
                        //使用默认的分割线
                        if (divider == null) {
                            divider = new RecycleViewDivider(context, layoutType, isRefreshEnable, isMoreEnable);
                        }
                        divider.setDrvider(height, color);
                        recyclerView.addItemDecoration(divider);
                    }
                }
                break;
            case GRIDLAYOUT:
                gridLayoutManager = new GridLayoutManager(context, SpanCount, LinearLayoutManager.VERTICAL, false);
                if (orientation == 0) {
                    gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
                }
                recyclerView.setLayoutManager(gridLayoutManager);
                //使用间隔
                setDivider();
                break;
            case STAGGEREDGRIDLAYOUT:
                staggeredGridLayoutManager = new StaggeredGridLayoutManager(SpanCount, StaggeredGridLayoutManager.VERTICAL);
                if (orientation == 0) {
                    staggeredGridLayoutManager.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                }
                staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                recyclerView.setLayoutManager(staggeredGridLayoutManager);
                staggeredGridLayoutManager.invalidateSpanAssignments();
                //使用间隔
                setDivider();
                break;
        }

        //为RecyclerView设置刷新监听
        if (isRefreshEnable && pullLoadListener != null) {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    isHaveMore=true;
                    pullLoadListener.onRefresh();
                }
            });
        }
        //为RecyclerView设置加载更多监听
        if (isMoreEnable && pullLoadListener != null) {
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
                        if (isMoreEnable && isHaveMore)
                            //调用回调方法
                            loadMoreListener.onLoadMore();
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

        //设置脚布局
        if (isFooterViewEnable){
            //脚布局
            if (footerView == null) {
                footerView = new FooterView(context);
            }
        }
        //设置适配器
        pullLoadMoreViewAdapter = new PullLoadMoreViewAdapter(adapter);
        recyclerView.setAdapter(pullLoadMoreViewAdapter);
    }

    //-------------------------------------------------------控件操作方法--------------------------------------------------------------

    /**
     * 获取RecyclerView
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 获取SwipeRefreshLayout
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    /**
     * 获取是否启用了item滑动
     */
    public boolean getItemTouchIsEnabled() {
        return itemAnimationEnable;
    }

    /**
     * 设置刷新状态-只是状态
     *
     * @param isRefreshing
     */
    public void setRefreshing(boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    /**
     * 完成下拉刷新或上拉加载
     */
    public void complete() {
        //结束下拉刷新
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 刷新
     */
    public void onRefresh() {
        if (pullLoadListener != null){
            isHaveMore=true;
            pullLoadListener.onRefresh();
        }
    }

    /**
     * 设置脚布局--数据错误状态
     */
    public void onError() {
        setFooterType(FOOTERLOADMOREERROR);
    }

    /**
     * 设置脚布局--没有更多状态
     */
    public void onNoMore() {
        isHaveMore=false;
        setFooterType(FOOTERNOMORE);
    }

    /**
     * 设置有更多状态----如果使用onNoMore后，在不刷新页面的情况下再次使用加载更多回调必须先调用这个方法
     */
    public void onHaveMore() {
        isHaveMore=true;
    }

    /**
     * 设置脚布局--正在加载更多状态
     */
    public void onMore() {
        setFooterType(FOOTERMOREING);
    }

    /**
     * 打开空数据页面
     */
    public void openNoDataPage() {
        if (noDataPage == null) {
            //没有自定义，使用默认页面
            noDataPage = View.inflate(context, R.layout.layout_nodata, null);
            noDataPage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();
                }
            });
            frameLayout.addView(noDataPage);
        }
        showPage(NODATA);
    }

    /**
     * 打开网络连接失败页面
     */
    public void openConnectFailedPage() {
        if (connectFailedPage == null) {
            //没有自定义，使用默认页面
            connectFailedPage = View.inflate(context, R.layout.layout_connectfailed, null);
            connectFailedPage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRefresh();
                }
            });
            frameLayout.addView(connectFailedPage);
        }
        showPage(CONNECTFAILED);
    }

    /**
     * 移除空数据和网络错误页面显示数据页面
     */
    public void showDataView() {
        showPage(DATA);
    }
}