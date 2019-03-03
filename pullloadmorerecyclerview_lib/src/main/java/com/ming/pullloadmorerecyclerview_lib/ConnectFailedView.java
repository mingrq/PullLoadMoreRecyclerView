package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

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
    }
}
