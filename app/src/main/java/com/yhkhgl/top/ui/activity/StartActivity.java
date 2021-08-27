package com.yhkhgl.top.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BasePresenter;

import static java.lang.Thread.sleep;

public class StartActivity extends BaseActivity {

    Handler mHandler = new Handler();
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                SharedPreferences sp = getPreferences(MODE_PRIVATE);
//                boolean isFirst = sp.getBoolean("isFirst", true);
                Intent intent = new Intent();
//                if (isFirst) {
//                    sp.edit().putBoolean("isFirst", false).commit();
//                    //如果用户是第一次安装应用并进入
//                    intent.setClass(SplashActivity.this, LoginActivity.class);
//                } else {
//                    intent.setClass(SplashActivity.this, MainActivity.class);
//                }
                if (App.token ==  null || App.token.equals("")){
                    intent.setClass(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(StartActivity.this,GuanLiActivity.class);
                    startActivity(intent);
                }
                startActivity(intent);
                finish();

            }
        }, 1000);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
