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
    private final int LINERLAYOUT = 0x00000;//线性布局
    private final int GRIDLAYOUT = 0x00010;//网格布局
    private final int STAGGEREDGRIDLAYOUT = 0x00020;//瀑布流
    /*布局类型*/
    private int layoutType;

    private int SpanCount;
    private int color;
    private int height;
    private Context context;

    private int horizontalSpacing;
    private int verticalSpacing;

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     */
    public RecycleViewDivider(Context context,int layoutType) {
        this.context = context;
        this.layoutType = layoutType;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        switch (layoutType){
            case LINERLAYOUT:
                outRect.top = height;
                break;
            case GRIDLAYOUT:
            case STAGGEREDGRIDLAYOUT:
                outRect.bottom = horizontalSpacing;
                outRect.left = verticalSpacing;
                int index=parent.getChildLayoutPosition(view);
                if (index%SpanCount==0){
                    outRect.left=0;
                }
                break;
        }

    }


    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        switch (layoutType){
            case LINERLAYOUT:
                Paint paint = new Paint();
                paint.setColor(color);
                int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View view = parent.getChildAt(i);
                    int index = parent.getChildAdapterPosition(view);
                    //第一个ItemView不需要绘制
                    if (index == 0) {
                        continue;
                    }
                    float dividerTop = view.getTop() - height;
                    float dividerLeft = parent.getPaddingLeft();
                    float dividerBottom = view.getTop();
                    float dividerRight = parent.getWidth() - parent.getPaddingRight();
                    c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
                }
                break;
            case GRIDLAYOUT:
            case STAGGEREDGRIDLAYOUT:

                break;
        }

    }

    /*----------------------------------对外方法--------------------------------------------*/

    /**
     * 设置分割线颜色和高度  LinerLayout布局使用
     *
     * @param color
     * @param width
     */
    public void setDrvider(int width, int color) {
        this.color = color;
        this.height = width;
    }


    /**
     * 设置间距  GridLayout、StaggeredGridLayout布局使用
     *
     * @param SpanCount 跨距数
     * @param Spacing 水平间距
     * @param verticalSpacing   垂直间距
     */
    public void setSpacing(int SpanCount,int Spacing, int verticalSpacing) {
        this.SpanCount = SpanCount;
        this.horizontalSpacing = Spacing;
        this.verticalSpacing = verticalSpacing;
    }
}
