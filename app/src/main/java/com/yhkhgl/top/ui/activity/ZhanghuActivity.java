package com.yhkhgl.top.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BasePresenter;


public class ZhanghuActivity extends BaseActivity {
    TextView renzheng_tv;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhanghu;
    }

    @Override
    protected void initView() {
        TextView textView =findViewById(R.id.phone_num_tv);
        textView.setText(App.phone);
        fixTitlePadding(findViewById(R.id.rl_title));
        ImageView fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RelativeLayout password_rl=findViewById(R.id.password_rl);
        password_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhanghuActivity.this,SetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (App.id_card.equals("1")){//实名认证已通过
            renzheng_tv.setText("已通过");
            renzheng_tv.setTextColor(Color.parseColor("#1B1B1B"));
        }else if (App.id_card.equals("2")){//实名认证未通过
            renzheng_tv.setText("未通过");
            renzheng_tv.setTextColor(Color.parseColor("#FF00A0E9"));
        }else if (App.id_card.equals("3")){//未提交
            renzheng_tv.setText("去认证");
            renzheng_tv.setTextColor(Color.parseColor("#FF00A0E9"));
        }else if (App.id_card.equals("0")){//待审核
            renzheng_tv.setText("审核中");
            renzheng_tv.setTextColor(Color.parseColor("#1B1B1B"));
        }
    }
}
