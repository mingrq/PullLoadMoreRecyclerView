package com.ming.pullloadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ming.pullloadmorerecyclerview_lib.PullLoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author MingRuQi
 * E-mail mingruqi@sina.cn
 * DateTime 2019/2/18 11:31
 */
public class StaggeredgridActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagg);
        StaggAdapter linerAdapter = new StaggAdapter(StaggeredgridActivity.this);
        PullLoadMoreView pullLoadMoreView = findViewById(R.id.pull_stagg);
        pullLoadMoreView
                .setLayoutType(PullLoadMoreView.STAGGEREDGRIDLAYOUT)
                .setSpacing(2,20,20,true,true)
                .setAdapter(linerAdapter)
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

           List<Integer> mHeight=new ArrayList<>();
            for (int i = 0; i <= contents.size(); i++) {
                //依次给给图片设置宽高
                mHeight.add((int)(300+Math.random()*400));
            }
        linerAdapter.setContents(contents,mHeight);
    }
}
