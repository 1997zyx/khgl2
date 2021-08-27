package com.yhkhgl.top.ui.activity;

import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.yhkhgl.top.R;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends BaseActivity {
    HashMap<String,String> map=new HashMap<>();
    List<HashMap> list=new ArrayList<>();
    LinearLayout ll_content;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        fixTitlePadding(findViewById(R.id.ll_title));
        map.put("key1","val1");
        list.add(map);
        LogUtils.e(list);
    }

    @Override
    protected void initData() {

    }
}
