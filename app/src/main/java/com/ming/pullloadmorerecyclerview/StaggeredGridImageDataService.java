package com.ming.pullloadmorerecyclerview;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by cl on 2018/5/3.
 */

public class StaggeredGridImageDataService extends IntentService {
    public StaggeredGridImageDataService() {
        super("");
    }

    public static <T extends StaggeredGridImageDataBean> void startService(Context context, List<T> datas) {
        Intent intent = new Intent(context, StaggeredGridImageDataService.class);
        intent.putExtra("imageData", (Serializable) datas);
        intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) datas);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        List<? extends StaggeredGridImageDataBean> datas = (List<? extends StaggeredGridImageDataBean>) intent.getSerializableExtra("imageData");
        handleGirlItemData(datas);
    }

    private void handleGirlItemData(List<? extends StaggeredGridImageDataBean> datas) {
        if (datas.size() == 0) {
            EventBus.getDefault().post("finish");
            return;
        }
        for (StaggeredGridImageDataBean data : datas) {
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(this)
                        .load(data.getUrl())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                data.setWidth(bitmap.getWidth());
                data.setHeight(bitmap.getHeight());
            }
        }
        EventBus.getDefault().post(datas);
    }
}