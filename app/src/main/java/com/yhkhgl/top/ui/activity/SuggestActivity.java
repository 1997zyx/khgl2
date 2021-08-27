package com.yhkhgl.top.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.ui.presenters.SuggestPresent;
import com.yhkhgl.top.utils.ShareUtil;

import java.util.HashMap;
import java.util.List;

public class SuggestActivity extends BaseActivity<SuggestPresent> implements MainView {
    ImageView fanhui;
    Button btn_next;
    EditText et_content, phone_et;
    ImageView kefu;
    @Override
    protected SuggestPresent createPresenter() {
        return new SuggestPresent(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_suggest;
    }

    @Override
    protected void initView() {
        phone_et = findViewById(R.id.phone_et);
        et_content = findViewById(R.id.et_content);
        fixTitlePadding(findViewById(R.id.rl_title));
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString().trim();
                String phone = phone_et.getText().toString().trim();
                if (content == null || content.equals("")) {
                    showError("请输入您要反馈的内容");
                    return;
                }
                if (phone == null || phone.equals("")) {
                    showError("请输入您的联系方式");
                    return;
                }
                if (phone.length() < 11) {
                    showError("请输入正确的联系方式");
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("token", App.token);
                map.put("content", content);
                map.put("mobile", phone);
                mPresenter.setSuggest(map);

            }
        });
        kefu = findViewById(R.id.kefu);
        kefu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ShareUtil.saveimage(SuggestActivity.this,kefu);
                return false;
            }
        });

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
        showError(o.getErrmsg());
        finish();
    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {

    }
}
