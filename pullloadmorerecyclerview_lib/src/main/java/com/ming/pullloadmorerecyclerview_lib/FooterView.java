package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/3/7 16:35
 */
public class FooterView extends RelativeLayout {

    private RelativeLayout noMoreLayout;
    private RelativeLayout moreingLayout;
    private RelativeLayout errorLayout;

    public FooterView(@NonNull Context context) {
        this(context,null);
    }

    public FooterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FooterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_footer, this);
        noMoreLayout = findViewById(R.id.layout_nomore);
        moreingLayout = findViewById(R.id.layout_moreing);
        errorLayout = findViewById(R.id.layout_loaderror);
        /*初始化加载进度条*/
        ImageView iv_moreing = findViewById(R.id.iv_moreing);
        RotateAnimation animation = new RotateAnimation(0,+359,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(700);
        LinearInterpolator interpolator = new LinearInterpolator();
        animation.setInterpolator(interpolator);
        animation.setRepeatCount(Animation.INFINITE);
        iv_moreing.startAnimation(animation);
    }
    /**
     * 设置正在加载数据状态
     *
     */
    public void setLoadMoreing() {
        noMoreLayout.setVisibility(GONE);
        errorLayout.setVisibility(GONE);
        moreingLayout.setVisibility(VISIBLE);

    }
    /**
     * 设置完成加载数据无数据状态
     *
     */
    public void setLoadNoMore() {
        noMoreLayout.setVisibility(VISIBLE);
        moreingLayout.setVisibility(GONE);
        errorLayout.setVisibility(GONE);
    }
    /**
     * 设置加载数据错误状态
     *
     */
    public void setLoadError() {
        noMoreLayout.setVisibility(GONE);
        moreingLayout.setVisibility(GONE);
        errorLayout.setVisibility(VISIBLE);
    }

}
