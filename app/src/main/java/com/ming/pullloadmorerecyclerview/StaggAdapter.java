package com.ming.pullloadmorerecyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

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

    public void setContents(List<String> contents, List<Integer> mheight) {
        this.contents = contents;
        this.mheight = mheight;
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        if (context != null) {
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
//Item的宽度，或图片的宽度
            final int swidth = screenWidth / 2;
            //myViewHolder.imageView.setImageResource(pokemonsList[i]);
            Glide.with(context)
                    .load(contents.get(i))
                    .asBitmap()
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            int width = resource.getWidth();
                            int height = resource.getHeight();
                            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) myViewHolder.imageView.getLayoutParams();
                            layoutParams.height = (swidth / width) * height;
                            myViewHolder.imageView.setLayoutParams(layoutParams);
                            return false;
                        }
                    })
                    .into(myViewHolder.imageView);
            // Picasso.with(context).load(contents.get(i)).resize(50000,60000).onlyScaleDown().into(myViewHolder.imageView);
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
