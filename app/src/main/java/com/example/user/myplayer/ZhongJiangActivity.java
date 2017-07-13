package com.example.user.myplayer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import Utils.PullDownElasticImp;
import Utils.PullDownScrollView;

/**
 * Created by whq on 17/7/11 0011.
 */

public class ZhongJiangActivity extends Activity {
    private PullDownScrollView mPullDownScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtai);
        initView();
    }

    private void initView() {
        mPullDownScrollView = (PullDownScrollView) findViewById(R.id.refresh_root);//下拉刷新
        mPullDownScrollView.setPullDownElastic(new PullDownElasticImp(this));
        //下拉刷新时候,重新走接口
        mPullDownScrollView.setRefreshListener(new PullDownScrollView.RefreshListener() {
            @Override
            public void onRefresh() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       //刷新结束调用这个方法
                       mPullDownScrollView.finishRefresh();
                   }
               },2000);
            }
        });
    }
}
