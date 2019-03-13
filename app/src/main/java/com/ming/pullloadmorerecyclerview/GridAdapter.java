package com.ming.pullloadmorerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 15:27
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {
    private Context context;
    private List<Integer> contents;

    public GridAdapter(Context context) {
        this.context = context;
    }

    public void setContents(List<Integer> contents) {
        this.contents = contents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_liner, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int i) {
                    if (i < 6) {
                        return 2;
                    }
                    if (i>=6&&i<12){
                        return 4;
                    }
                    return 1;
                }
            });
        }
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView.setText("测试内容" + String.valueOf(contents.get(i)));
    }

    @Override
    public int getItemCount() {
        return contents != null ? contents.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_liner);
        }
    }
}
