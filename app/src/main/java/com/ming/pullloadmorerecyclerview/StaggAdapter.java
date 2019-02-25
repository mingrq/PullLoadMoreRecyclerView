package com.ming.pullloadmorerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 15:27
 */
public class StaggAdapter extends RecyclerView.Adapter<StaggAdapter.MyViewHolder> {
    private Context context;
    private List<String> contents;
    private List<Integer> mheight;

    public StaggAdapter(Context context) {
        this.context = context;
    }

    public void setContents(List<String> contents,List<Integer> mheight) {
        this.contents = contents;
        this.mheight=mheight;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_stagg, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (context != null) {
         /*   int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//Item的宽度，或图片的宽度
            int width = screenWidth / 2;

            ViewGroup.LayoutParams params = myViewHolder.imageView.getLayoutParams();//得到item的LayoutParams布局参数
            params.height = mheight.get(i);
            params.width=width;
            myViewHolder.itemView.setLayoutParams(params);//把params设置给item布*/
            Glide.with(context).load(contents.get(i)).into(myViewHolder.imageView);
        }

    }

    @Override
    public int getItemCount() {
        return contents != null ? contents.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_stagg);
        }
    }
}
