package com.yhkhgl.top.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.ui.presenters.LoginPresenter;

import java.util.HashMap;
import java.util.List;

public class FindPasswordActivity extends BaseActivity<LoginPresenter> implements MainView {
    EditText et_phone,et_password,et_password_two,et_code;
    CheckBox checkbox_password,checkbox_password_two;
    TextView send_msg,tv_register;
    Button btn_login;
    ImageView fanhui;
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void initView() {
        TextView xieyi_tv=findViewById(R.id.xieyi_tv);
        xieyi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindPasswordActivity.this,ProviteActivity.class);
                startActivity(intent);
            }
        });
        fanhui=findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    fixTitlePadding(findViewById(R.id.rl_title));
        tv_register=findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_code=findViewById(R.id.et_code);
        btn_login=findViewById(R.id.btn_login);
        send_msg=findViewById(R.id.send_msg);
        et_phone=findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_password_two = findViewById(R.id.et_password_two);
        checkbox_password=findViewById(R.id.checkbox_password);
        checkbox_password_two=findViewById(R.id.checkbox_password_two);
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
        send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=et_phone.getText().toString().trim();
                if (mobile==null||mobile.equals("")){
                    showError("请输入手机号码");
                }else {
                    if (mobile.length()<11){
                        showError("请输入正确的手机号码");
                    }else {
                        countDownTime();
                        HashMap<String,String> map=new HashMap<>();
                        map.put("type","3");
                        map.put("mobile",mobile);
                        mPresenter.SendSms(map);
                    }
                }

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=et_phone.getText().toString().trim();
                String onepassword=et_password.getText().toString().trim();
                String twopassword=et_password_two.getText().toString().trim();
                String code=et_code.getText().toString().trim();
                if (mobile == null || mobile.equals("")){
                    showError("请输入手机号码");
                }else if (mobile.length()<11){
                    showError("请输入正确的手机号码");
                }else if (onepassword == null || onepassword.equals("")){
                    showError("请输入新密码");
                } else if (twopassword == null ||twopassword.equals("")) {
                    showError("请输入第二次新密码");
                }else if (!onepassword.equals(twopassword)){
                    showError("两次输入的密码不一致");
                }else if (code == null ||code.equals("")){
                    showError("请输入验证码");
                }else if (code.length()!=4){
                    showError("请输入正确的验证码");
                }
                else {
                    HashMap<String,String> map=new HashMap<>();
                    map.put("mobile",mobile);
                    map.put("new_password",onepassword);
                    map.put("two_password",twopassword);
                    map.put("sms_code",code);
                    map.put("from","khgl");
                    mPresenter.setpassword(map);
                }
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

    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {

    }
    private void countDownTime() {
        //用安卓自带的CountDownTimer实现
        CountDownTimer mTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i(TAG, "millisUntilFinished: " + millisUntilFinished);
                send_msg.setText(millisUntilFinished / 1000 + "秒后重发");
                send_msg.setTextColor(Color.parseColor("#AAAAAA"));
            }
            @Override
            public void onFinish() {
                send_msg.setEnabled(true);
                send_msg.setTextColor(getResources().getColor(R.color.bg_85736));
                send_msg.setText("发送验证码");
                cancel();
            }
        };
        mTimer.start();
        send_msg.setEnabled(false);
    }
}
