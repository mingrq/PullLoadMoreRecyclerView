package com.ming.pullloadmorerecyclerview_lib.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.ming.pullloadmorerecyclerview_lib.R;

/**
 * 默认网络连接失败页面
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/3/3 23:52
 */
public class ConnectFailedView extends RelativeLayout{
    public ConnectFailedView(Context context) {
        this(context,null);
    }

    public ConnectFailedView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ConnectFailedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_connectfailed, this);
    }
}
