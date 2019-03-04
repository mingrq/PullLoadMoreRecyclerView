package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 默认空数据页面
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/3/3 23:51
 */
public class NoDataView extends FrameLayout {

    public NoDataView(Context context) {
        this(context, null);
    }

    public NoDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoDataView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_nodata, this);
    }
}
