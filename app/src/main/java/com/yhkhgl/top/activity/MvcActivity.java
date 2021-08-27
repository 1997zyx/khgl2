package com.yhkhgl.top.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;


import com.yhkhgl.top.R;
import com.yhkhgl.top.api.ApiRetrofit;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.base.mvp.BasePresenter;
import com.yhkhgl.top.bean.TextBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * File descripition:   mvc演示
 *
 * @author lp
 * @date 2019/8/9
 */
public class MvcActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvText;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mTvText = findViewById(R.id.tv_text);
        findViewById(R.id.btn_first).setOnClickListener(this);
        findViewById(R.id.btn_second).setVisibility(View.GONE);
        findViewById(R.id.btn_second_2).setVisibility(View.GONE);
        findViewById(R.id.btn_third).setVisibility(View.GONE);
        findViewById(R.id.btn_forth).setVisibility(View.GONE);
        findViewById(R.id.btn_fifth).setVisibility(View.GONE);
        findViewById(R.id.btn_six).setVisibility(View.GONE);
        findViewById(R.id.btn_down).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        getDataApi();
    }

    @SuppressLint("CheckResult")
    private void getDataApi() {
        HashMap<String, String> params = new HashMap<>();
        params.put("type", "junshi");
        params.put("key", "2c1cb93f8c7430a754bc3ad62e0fac06");
        ApiRetrofit.getInstance().getApiService()
                .getText(params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseModel<TextBean>, BaseModel<TextBean>>() {
                    @Override
                    public BaseModel<TextBean> apply(BaseModel<TextBean> objectBaseModel) throws Exception {
                        return objectBaseModel;
                    }
                }).subscribe(new Consumer<BaseModel<TextBean>>() {
            @Override
            public void accept(BaseModel<TextBean> o) throws Exception {
                mTvText.setText(o.getData().toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }
}
