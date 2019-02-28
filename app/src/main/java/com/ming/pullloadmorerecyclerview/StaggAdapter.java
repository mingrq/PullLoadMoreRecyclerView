package com.ming.pullloadmorerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ming.pullloadmorerecyclerview_lib.GirlItemData;

import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 15:27
 */
public class StaggAdapter extends RecyclerView.Adapter<StaggAdapter.MyViewHolder> {
    private Context context;
    private List<GirlItemData> contents;
    private List<Integer> mheight;

    public StaggAdapter(Context context) {
        this.context = context;
    }

    public void setContents(List<GirlItemData> contents) {
        this.contents = contents;
        notifyDataSetChanged();
    }

    public void addContents(List<GirlItemData> data) {
        contents = data;
        notifyDataSetChanged();
    }

    int pokemonsList[] = {R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7,
            R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11,
            R.drawable.p12, R.drawable.p13, R.drawable.p14, R.drawable.p15,
            R.drawable.p16, R.drawable.p17, R.drawable.p18, R.drawable.p19,
            R.drawable.p20, R.drawable.p21};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_stagg, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        if (context != null) {
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//Item的宽度，或图片的宽度
            int swidth = (screenWidth-60) / 2;
            int h = contents.get(i).getHeight();
            int w = contents.get(i).getWidth();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) myViewHolder.imageView.getLayoutParams();
            float sc = w/(h*1f);
            int vh = (int) (swidth/sc);
            params.height = vh;
            myViewHolder.imageView.setLayoutParams(params);
            Glide.with(context).load(contents.get(i).getUrl()).into(myViewHolder.imageView);
            myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,String.valueOf(contents.get(i).getWidth()+" h:"+contents.get(i).getHeight()+" vw"+myViewHolder.imageView.getWidth()+" vh"+myViewHolder.imageView.getHeight()),Toast.LENGTH_SHORT).show();
                }
            });
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
