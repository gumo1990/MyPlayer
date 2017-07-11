package com.example.user.myplayer;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

/**
 * Created by whq on 17/7/7 0007.
 */

public class MainActivity extends TabActivity implements CompoundButton.OnCheckedChangeListener {

    private TabHost host;
    public static final String TAB_TJ = "tab_tuijian";
    public static final String TAB_ZJ = "tab_zhongjiang";
    public static final String TAB_ZX = "tab_zixun";
    public static final String TAB_MY = "tab_my";

    private RadioButton[] radioButtons;
    private boolean firstCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintab);
        firstCreate = true;
        host = getTabHost();
        host.addTab(host.newTabSpec(TAB_TJ).setIndicator(TAB_TJ)
                .setContent(new Intent(this, TuijianAcitity.class)));
        host.addTab(host.newTabSpec(TAB_ZJ).setIndicator(TAB_ZJ)
                .setContent(new Intent(this, ZhongJiangActivity.class)));
        host.addTab(host.newTabSpec(TAB_ZX).setIndicator(TAB_ZX)
                .setContent(new Intent(this, ZiXunActivity.class)));
        host.addTab(host.newTabSpec(TAB_MY).setIndicator(TAB_MY)
                .setContent(new Intent(this, MyAcitity.class)));

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_navigation);
        radioButtons = new RadioButton[4];

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = (RadioButton) radioGroup.findViewWithTag("radio_button" + i);
            radioButtons[i].setOnCheckedChangeListener(this);

        }
        host.setCurrentTab(0);
        radioButtons[0].setChecked(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < radioButtons.length; i++) {
                if (buttonView == radioButtons[i]) {
                    if (firstCreate) {
                        host.setCurrentTab(i);
                    } else {
                        host.setCurrentTab(i);
                    }
                }
                firstCreate = false;
            }

        }
    }
}

