package com.ming.pullloadmorerecyclerview_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/19 17:18
 */
public class RecycleViewDivider extends RecyclerView.ItemDecoration {
    private int color;
    private int width;
    private Context context;

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     */
    public RecycleViewDivider(Context context) {
        this.context = context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,0,0,width);
    }

    /**
     * 设置分割线颜色和高度
     *
     * @param color
     * @param width
     */
    public void setDrvider(int width, int color) {
        this.color = color;
        this.width = width;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setColor(color);
        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getMeasuredWidth() - parent.getPaddingRight(), parent.getMeasuredHeight() - parent.getPaddingBottom(), paint);
        //c.drawRect(0,0, parent.getRight() , parent.getHeight(), paint);

    }
}
