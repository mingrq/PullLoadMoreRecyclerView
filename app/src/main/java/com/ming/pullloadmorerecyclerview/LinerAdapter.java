package com.ming.pullloadmorerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 15:27
 */
public class LinerAdapter extends RecyclerView.Adapter<LinerAdapter.MyViewHolder> implements ItemTouchHelperListener {
    private Context context;
    private List<Integer> contents;

    public LinerAdapter(Context context) {
        this.context = context;
    }

    public void setContents(List<Integer> contents) {
        this.contents = contents;
        notifyDataSetChanged();
    }

    public void addContents(List<Integer> addcontents) {
        int i = contents.size();
        contents.addAll(addcontents);
        notifyItemRangeChanged(i, contents.size() - i);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == 0) {
            view = View.inflate(context, R.layout.item_linewr, null);
        } else {
            view = View.inflate(context, R.layout.item_liner, null);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Integer prev = contents.remove(fromPosition);
        contents.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.textView.setText("test:" + i);
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
