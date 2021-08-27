package com.yhkhgl.top.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BasePresenter;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;

import java.util.List;

public class InfoDetailActivity extends BaseActivity<GuanLiPresent> implements MainView {

    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onMainSuccess(BaseModel<List<MainBean>> o) {

    }

    @Override
    public void onTextSuccess(BaseModel<BaseCallBackBean> o) {

    }

    @Override
    public void onUpLoadImgSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onTableListSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onSuccess(BaseModel<Object> o, int i) {

    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {

    }
}