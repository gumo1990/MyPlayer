package com.example.user.myplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import java.util.ArrayList;

import Bean.NewsBean;
import Utils.GlobalConstants;
import Utils.PrefUtils;

/**
 * Created by whq on 17/7/7 0007.
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private Button btnstart;//开始体验
    private int[] mImageIds = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private ArrayList<ImageView> mList;
    private boolean inWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        btnstart = (Button) findViewById(R.id.btn_start);
      //  getDataFromNet();
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               PrefUtils.putBoolean(GuideActivity.this, "is_guide_show", true);
                if (inWeb) {
                    //进入web
                    startActivity(new Intent(GuideActivity.this, WebViewActivity.class));
                } else {
                    //进入main
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                }

                finish();
            }
        });

    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mList.add(view);
        }
        GuideAdapter guideAdapter = new GuideAdapter();
        mViewPager.setAdapter(guideAdapter);
        mViewPager.setOnPageChangeListener(this);

    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == mImageIds.length - 1) {
            btnstart.setVisibility(View.VISIBLE);
        } else {
            btnstart.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpMethod.GET, GlobalConstants.NEWS_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        processData(result);
                        System.out.println("解析结果:------111"+result );
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        Toast.makeText(GuideActivity.this, "访问出错", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 解析json数据
     */
    private void processData(String json) {
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(json, NewsBean.class);
        System.out.println("解析结果:------" + newsBean);
        inWeb = newsBean.isweb;
    }

}
