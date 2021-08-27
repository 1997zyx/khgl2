package com.yhkhgl.top.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.ui.presenters.LoginPresenter;
import com.yhkhgl.top.utils.SharePerferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class LoginActivity extends BaseActivity<LoginPresenter>  implements MainView, View.OnClickListener {
    TextView tv_register, tv_forget_password, xieyi_tv;
    CheckBox checkbox_password;
    EditText et_password, et_phone;
    Button btn_login;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        xieyi_tv = findViewById(R.id.xieyi_tv);
        xieyi_tv.setOnClickListener(this);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        tv_forget_password.setOnClickListener(this);
        et_phone = findViewById(R.id.et_phone);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        checkbox_password = findViewById(R.id.checkbox_password);
        et_password = findViewById(R.id.et_password);
        checkbox_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_password.setSelection(et_password.getText().length());
                } else {
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_password.setSelection(et_password.getText().length());
                }
            }
        });
        et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
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
        JSONObject jsonObject = null;
        try {
            hideLoading();
            JSONObject extend = new JSONObject(gson.toJson(o.getExtend()));
            jsonObject = new JSONObject(gson.toJson(o.getData()));
            int service_id = jsonObject.optInt("service_id");
            if (service_id != 0) {
                SharePerferenceUtil.saveValue(LoginActivity.this, "userdata", "service_admin", App.service_admin);
                Intent intent=new Intent(LoginActivity.this,GuanLiActivity.class);
                startActivity(intent);
                finish();
            } else {
                showError("请联系管理官开通权限！");
                return;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSuccess(BaseModel<Object> o, int i) {
        try {
            JSONObject jsonObject = new JSONObject(gson.toJson(o.getData()));
            String token = jsonObject.optString("token");
            String user_name = jsonObject.optString("user_name");
            String user_mobile = jsonObject.optString("user_mobile");
            SharePerferenceUtil.saveValue(LoginActivity.this, "userdata", "token", token);
            App.token = token;
            App.phone = user_mobile;
            SharePerferenceUtil.saveValue(LoginActivity.this, "userdata", "user_name", user_name);
            SharePerferenceUtil.saveValue(LoginActivity.this, "userdata", "user_mobile", user_mobile);
            mPresenter.getInfo();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String phone = et_phone.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (phone == null || phone.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                } else if (phone.length() < 11) {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else if (password == null || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "请输6-20位数字密码", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "请输6-20位数字密码", Toast.LENGTH_SHORT).show();
                } else {
                    showLoading();
                    HashMap<String, String> params = new HashMap<>();
                    params.put("user_mobile", phone);
                    params.put("user_password", password);
                    params.put("registration_id",App.registrationID);
                    params.put("from","hrzp");
                    mPresenter.getLogin(params);
                }
                break;
            case R.id.tv_forget_password:
                Intent intent1 = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(intent1);
                break;
            case R.id.xieyi_tv:
                Intent intent2 = new Intent(LoginActivity.this, ProviteActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
