package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 默认空数据页面
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/3/3 23:51
 */
public class NoDataView extends RelativeLayout {

    public NoDataView(Context context) {
        this(context,null);
    }

    public NoDataView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public NoDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
