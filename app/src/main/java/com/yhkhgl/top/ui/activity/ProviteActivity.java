package com.yhkhgl.top.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.yhkhgl.top.R;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BasePresenter;


public class ProviteActivity extends BaseActivity {

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_provite;

    }

    @Override
    protected void initView() {
    fixTitlePadding(findViewById(R.id.rl_title));
        ImageView fanhui=findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
