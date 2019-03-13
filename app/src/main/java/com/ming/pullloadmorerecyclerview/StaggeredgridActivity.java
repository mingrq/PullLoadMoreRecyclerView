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
                .setSpacing(2, 10, 10, true, false)
                .setAdapter(linerAdapter)
                .setNeedRefreshAndMore(true,true)
                .commit();
        List<String> contents = new ArrayList<>();
         contents.add("http://hbimg.b0.upaiyun.com/dc2bffb262eab801646348ebacaa899f7786670f3c1fb-ZSyM5d_fw658");
        contents.add("http://pic40.nipic.com/20140330/13328950_214049330194_2.jpg");
        contents.add("http://imgtu.5011.net/uploads/content/20170418/6165531492499231.jpg");
        contents.add("http://hbimg.b0.upaiyun.com/7d4c6aef54358fd38e49d50d297515ac214b31fb1983b-3tvkKK_fw658");
        contents.add("http://img3.imgtn.bdimg.com/it/u=3902476913,1347930991&fm=27&gp=0.jpg");
        contents.add("http://00.minipic.eastday.com/20170422/20170422173237_a786ee17d6fa822448fedce3a5d539bc_4.jpeg ");
        contents.add("http://img0.ph.126.net/wtK03WIxAP5o5ToRavijKQ==/6631354738235145792.jpg");
        contents.add("http://korea.people.com.cn/NMediaFile/2016/0713/FOREIGN201607130819000359343628549.jpg");
        contents.add("http://img1.3lian.com/img013/v5/21/d/84.jpg");
        contents.add("http://img17.3lian.com/d/file/201702/14/3d1d78481dbe5db4802f4b1eb548f365.jpg");
        contents.add("http://img15.3lian.com/2015/f2/57/d/93.jpg");
        contents.add("http://img1.3lian.com/2015/w6/53/d/85.jpg");
        contents.add("http://pic1.win4000.com/pic/b/18/c1601227067.jpg");
        contents.add("http://pic41.nipic.com/20140601/18681759_143805185000_2.jpg");
        contents.add("http://pic30.nipic.com/20130605/12949204_213054651194_2.jpg");
        contents.add("http://pic46.nipic.com/20140814/19268738_232534528000_2.jpg");
        contents.add("http://hbimg.b0.upaiyun.com/dc2bffb262eab801646348ebacaa899f7786670f3c1fb-ZSyM5d_fw658");
        contents.add("http://pic40.nipic.com/20140330/13328950_214049330194_2.jpg");
        contents.add("http://imgtu.5011.net/uploads/content/20170418/6165531492499231.jpg");
        contents.add("http://hbimg.b0.upaiyun.com/7d4c6aef54358fd38e49d50d297515ac214b31fb1983b-3tvkKK_fw658");
        contents.add("http://img3.imgtn.bdimg.com/it/u=3902476913,1347930991&fm=27&gp=0.jpg");
        contents.add("http://00.minipic.eastday.com/20170422/20170422173237_a786ee17d6fa822448fedce3a5d539bc_4.jpeg ");
        contents.add("http://img0.ph.126.net/wtK03WIxAP5o5ToRavijKQ==/6631354738235145792.jpg");
        contents.add("http://korea.people.com.cn/NMediaFile/2016/0713/FOREIGN201607130819000359343628549.jpg");
        contents.add("http://img1.3lian.com/img013/v5/21/d/84.jpg");
        contents.add("http://img17.3lian.com/d/file/201702/14/3d1d78481dbe5db4802f4b1eb548f365.jpg");
        contents.add("http://img15.3lian.com/2015/f2/57/d/93.jpg");
        contents.add("http://img1.3lian.com/2015/w6/53/d/85.jpg");
        contents.add("http://pic1.win4000.com/pic/b/18/c1601227067.jpg");
        contents.add("http://pic41.nipic.com/20140601/18681759_143805185000_2.jpg");
        contents.add("http://pic30.nipic.com/20130605/12949204_213054651194_2.jpg");
        contents.add("http://pic46.nipic.com/20140814/19268738_232534528000_2.jpg");
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
        linerAdapter.setContents(data);
    }
}
