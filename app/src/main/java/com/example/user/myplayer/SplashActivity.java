package com.example.user.myplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import Utils.PrefUtils;

public class SplashActivity extends AppCompatActivity {

    RelativeLayout rl_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
        initAni();
    }

    private void initView() {
        rl_splash = (RelativeLayout) findViewById(R.id.activity_splash);
    }

    private void initData() {

    }

    private void initAni() {
        AnimationSet set = new AnimationSet(false);
        //渐变
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        set.addAnimation(alphaAnimation);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpToNext();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rl_splash.setAnimation(set);
    }

    private void jumpToNext() {
        boolean isGuideShow = PrefUtils.getBoolean(this, "is_guide_show", false);
        Intent intent = new Intent();
        if (isGuideShow) {
            intent.setClass(this, MainActivity.class);
        } else {
            intent.setClass(this, GuideActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
