package com.yhkhgl.top.ui.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhkhgl.top.R;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BasePresenter;

public class AboutAsActivity extends BaseActivity {




    @Override
    protected BasePresenter createPresenter() {
        return null;
    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_as;
    }

    @Override
    protected void initView() {
        TextView code_tv=findViewById(R.id.code_tv);
        code_tv.setText( "版本号"+getAppVersionName(this));

        fixTitlePadding(findViewById(R.id.rl_title));
        ImageView imageView=findViewById(R.id.fanhui);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
    public static String getAppVersionName(Context context) {
        String appVersionName = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionName;
    }

}
