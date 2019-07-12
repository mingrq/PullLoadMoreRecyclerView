package com.ming.pullloadmorerecyclerview_lib.layout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ming.pullloadmorerecyclerview_lib.R;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/3/7 16:35
 */
public class FooterView extends RelativeLayout {
    public static final int NOMORE = 0x000123741;
    public static final int MOREING = 0x000123742;
    public static final int LOADMOREERROR = 0x00123743;


    private ImageView ivMoreing;
    private RotateAnimation animation;
    private TextView tvFooter;

    public FooterView(@NonNull Context context) {
        this(context, null);
    }

    public FooterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_footer, this);
        /*初始化加载进度条*/
        ivMoreing = findViewById(R.id.iv_moreing);
        tvFooter = findViewById(R.id.tv_footer_title);
    }

    /**
     * 设置脚布局状态
     */
    public void setFooterType(int type) {
        switch (type) {
            case MOREING://设置正在加载数据状态
                ivMoreing.setVisibility(VISIBLE);
                tvFooter.setText("正在加载更多");
                animation = new RotateAnimation(0, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(850);
                animation.setInterpolator(new LinearInterpolator());
                animation.setRepeatCount(Animation.INFINITE);
                ivMoreing.startAnimation(animation);
                break;
            case NOMORE://设置完成加载数据无数据状态
                ivMoreing.clearAnimation();
                ivMoreing.setVisibility(GONE);
                tvFooter.setText("我是有底线的");
                break;
            case LOADMOREERROR://设置加载数据错误状态
                ivMoreing.clearAnimation();
                ivMoreing.setVisibility(GONE);
                tvFooter.setText("数据加载错误");
                break;
        }

    }

}
