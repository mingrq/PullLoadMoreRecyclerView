# 最新版本
 1.0.0
#

### 使用
#
```
在 build.gradle 中添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.mingrq:PullLoadMoreRecyclerView:Tag'
	}
```
### 方法

### _----------------LinerLayout布局方法---------------_

#
### PullLoadMoreView setInitDividerEnable(boolean isDividerEnable)
#### 是否需要分割线  _LinerLayout布局使用_


#
### PullLoadMoreView setInitDivider(int height, int color)
#### 设置分割线  _LinerLayout布局使用_

#
### PullLoadMoreView setInitCustomDivider(RecyclerView.ItemDecoration itemDecoration)
#### 设置自定义分割线 _LinerLayout布局使用_

#
### PullLoadMoreView setReverseLayoutEnable(boolean isReverseLayoutEnable)
#### 设置反转布局是否激活 _LinerLayout布局使用_

#
### PullLoadMoreView setStackFromEndEnable(boolean isStackFromEndEnable)
#### 设置从底部开始显示数据是否激活 _LinerLayout布局使用_
   
 </br>
 </br>
 </br>
### _----------------GridLayout、StaggeredGridLayout布局方法---------------_

#
### PullLoadMoreView setInitSpacing(int SpanCount, int horizontalSpacing, int verticalSpacing, boolean horizontalMargin, boolean verticalMargin)
#### 设置间距  _GridLayout、StaggeredGridLayout布局使用_

</br>
 </br>
 </br>
### _----------------公用初始化方法---------------_

#
### PullLoadMoreView setInitRefreshAndMoreEnable(boolean isRefreshEnable, boolean isMoreEnable) 
#### 是否需要下拉刷新上拉加载功能

#
### PullLoadMoreView setInitLayoutType(int layoutType, int orientation)
#### 设置布局方式

#
### PullLoadMoreView setInitOnPullLoadListener(PullLoadListener pullLoadListener)
#### 设置下拉刷新监听

#
### PullLoadMoreView setInitOnLoadMoreListener(LoadMoreListener loadMoreListener)
#### 设置上拉加载监听

#
### PullLoadMoreView setInitAdapter(RecyclerView.Adapter adapter)
#### 设置适配器

#
### PullLoadMoreView setInitItemTouchAnimation(ItemTouchHelper.Callback callback)
#### 设置item拖动动画

#
### PullLoadMoreView setInitFooterViewEnable(boolean isFooterViewEnable)
#### 是否需要脚布局

#
### PullLoadMoreView setInitFooterView(View customFooterView, PullLoadMoreFooterCallBack footerCallBack)
#### 设置脚布局
customFooterView：自定义脚布局</br>
footerCallBack：脚布局状态回调函数
```
   /**
     * 脚布局状态回调接口
     */
    public interface PullLoadMoreFooterCallBack {
        //设置完成加载数据无更多数据
        void noMore(View footerView);

        //设置正在加载数据
        void moreing(View footerView);

        //设置加载数据错误
        void moreError(View footerView);
    }
```

#
### PullLoadMoreView setInitNoDataPage(View noDataPage)
#### 设置空数据页面

#
### PullLoadMoreView setInitConnectFailedPage(View connectFailedPage)
#### 设置网络连接失败页面

#
### commit()
#### 提交

</br>
 </br>
 </br>
### _----------------控件操作方法---------------_

#
### RecyclerView getRecyclerView()
#### 获取RecyclerView

#
### SwipeRefreshLayout getSwipeRefreshLayout()
#### 获取SwipeRefreshLayout

#
### boolean getItemTouchIsEnabled()
#### 获取是否启用了item滑动

#
### void setRefreshing(boolean isRefreshing)
#### 设置刷新状态-只是状态

#
###  void complete()
#### 完成下拉刷新或上拉加载

#
### void onRefresh()
#### 刷新

#
### void onError()
#### 设置脚布局--数据错误状态

#
### void onNoMore()
#### 设置脚布局--没有更多状态

#
### void onMore()
#### 设置脚布局--正在加载更多状态

#
### void openNoDataPage()
#### 打开空数据页面

#
### void openConnectFailedPage()
#### 打开网络连接失败页面

#
### void showDataView()
#### 移除空数据和网络错误页面显示数据页面

