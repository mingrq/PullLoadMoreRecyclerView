package com.ming.pullloadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ming.pullloadmorerecyclerview_lib.PullLoadMoreView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 11:31
 */
public class StaggeredgridActivity extends AppCompatActivity {

    private StaggAdapter linerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagg);
        EventBus.getDefault().register(this);
        linerAdapter = new StaggAdapter(StaggeredgridActivity.this);
        final PullLoadMoreView pullLoadMoreView = findViewById(R.id.pull_stagg);
        pullLoadMoreView
                .setLayoutType(PullLoadMoreView.STAGGEREDGRIDLAYOUT)
                .setSpacing(4, 20, 20, true, false)
                .setAdapter(linerAdapter)
                .setNeedRefreshAndMore(false,true)
                .commit();
        List<String> contents = new ArrayList<>();
        contents.add("http://img5.imgtn.bdimg.com/it/u=639238630,2179659181&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=1258659760,2193329985&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2847223552,3832031298&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=2713007455,3038975744&fm=26&gp=0.jpg");
        contents.add("http://img4.imgtn.bdimg.com/it/u=1286488394,241255406&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=2583418828,2614677295&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=116011478,4126655434&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=639238630,2179659181&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=1258659760,2193329985&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2847223552,3832031298&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=2713007455,3038975744&fm=26&gp=0.jpg");
        contents.add("http://img4.imgtn.bdimg.com/it/u=1286488394,241255406&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=2583418828,2614677295&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=116011478,4126655434&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=639238630,2179659181&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=1258659760,2193329985&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2847223552,3832031298&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=2713007455,3038975744&fm=26&gp=0.jpg");
        contents.add("http://img4.imgtn.bdimg.com/it/u=1286488394,241255406&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=2583418828,2614677295&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=116011478,4126655434&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=639238630,2179659181&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=1258659760,2193329985&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2847223552,3832031298&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=2713007455,3038975744&fm=26&gp=0.jpg");
        contents.add("http://img4.imgtn.bdimg.com/it/u=1286488394,241255406&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img5.imgtn.bdimg.com/it/u=266745161,658804068&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=2661055612,1771836516&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=2583418828,2614677295&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=116011478,4126655434&fm=26&gp=0.jpg");
        contents.add("http://img1.imgtn.bdimg.com/it/u=2583418828,2614677295&fm=26&gp=0.jpg");
        contents.add("http://img0.imgtn.bdimg.com/it/u=116011478,4126655434&fm=26&gp=0.jpg");
        List<StaggeredGridImageDataBean> data = new ArrayList<>();
        for (int j = 0; j < contents.size(); j++) {
            StaggeredGridImageDataBean s = new StaggeredGridImageDataBean();
            s.setUrl(contents.get(j));
            data.add(s);
        }
        StaggeredGridImageDataService.startService(StaggeredgridActivity.this, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dataEvent(List<StaggeredGridImageDataBean> data) {
        linerAdapter.addContents(data);
    }
}
