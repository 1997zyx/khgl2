package com.yhkhgl.top.ui.activity;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;


import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.ui.presenters.SetPasswordPresenter;

import java.util.HashMap;
import java.util.List;

public class SetPasswordActivity extends BaseActivity<SetPasswordPresenter> implements MainView {
    EditText et_password_jiu,et_password,et_password_two;
    CheckBox checkbox_password_jiu,checkbox_password,checkbox_password_two;
    ImageView fanhui;
    @Override
    protected SetPasswordPresenter createPresenter() {
        return new SetPasswordPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void initView() {
        fanhui=findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_password_jiu=findViewById(R.id.et_password_jiu);
        et_password=findViewById(R.id.et_password);
        et_password_two=findViewById(R.id.et_password_two);
        checkbox_password_jiu=findViewById(R.id.checkbox_password_jiu);
        checkbox_password=findViewById(R.id.checkbox_password);
        checkbox_password_two=findViewById(R.id.checkbox_password_two);
        checkbox_password_jiu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password_jiu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password_jiu.setSelection(et_password_jiu.getText().length());
                } else {
                    et_password_jiu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password_jiu.setSelection(et_password_jiu.getText().length());
                }
            }
        });
        et_password_jiu.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkbox_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password_jiu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password_jiu.setSelection(et_password_jiu.getText().length());
                } else {
                    et_password_jiu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password_jiu.setSelection(et_password_jiu.getText().length());
                }
            }
        });
        et_password_jiu.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkbox_password_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password_two.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password_two.setSelection(et_password_two.getText().length());
                } else {
                    et_password_two.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password_two.setSelection(et_password_two.getText().length());
                }
            }
        });
        et_password_two.setTransformationMethod(PasswordTransformationMethod.getInstance());
        Button btn_next=findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_password=et_password_jiu.getText().toString().trim();
                String new_password=et_password.getText().toString().trim();
                String two_password=et_password_two.getText().toString().trim();
                if (user_password==null||user_password.equals("")){
                    showError("请输入旧密码");
                    return;
                }
                if (new_password==null||new_password.equals("")){
                    showError("请输入新密码");
                    return;
                }
                if (two_password==null||two_password.equals("")){
                    showError("请确认新密码");
                    return;
                }
               if (!new_password.equals(two_password)){
                   showError("两次新密码不一致");
                   return;
               }
                HashMap<String,String> map=new HashMap<>();
                map.put("token",App.token);
                map.put("user_password",user_password);
                map.put("new_password",new_password);
                map.put("two_password",two_password);
                mPresenter.setPassword(map);
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
        if (o.getErrcode() == 0){
            showError("修改成功，请重新登录");
            App.finishAll();
            App.token=null;
            Intent intent = new Intent(SetPasswordActivity.this, LoginActivity.class);
            startActivity(intent);
        }
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
