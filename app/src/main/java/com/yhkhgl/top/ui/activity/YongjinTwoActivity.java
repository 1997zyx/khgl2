package com.yhkhgl.top.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class YongjinTwoActivity extends BaseActivity<GuanLiPresent> implements MainView {


    TextView name_tv, mobile, qudao_tv, dengji_tv, zhengxin_tv, ywlx_tv, tv_need, tv_shouru, tv_chengjiao, tv_hangye, tv_house, tv_cae, tv_vx, tv_card, tv_s_date, tv_visit_time, tv_next_date;
    JSONObject jsonObject;
    String s_phone;
    TextView follow_tv, tv_yongjin, tv_fangan,tv_date;
    ImageView fanhui;
    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_yongjin_two;
    }

    @Override
    protected void initView() {
        ImageView fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fanhui = findViewById(R.id.fanhui);
        fixTitlePadding(findViewById(R.id.rl_title));
        tv_fangan = findViewById(R.id.tv_fangan);
        tv_yongjin = findViewById(R.id.tv_yongjin);
        follow_tv = findViewById(R.id.follow_tv);
        name_tv = findViewById(R.id.name_tv);
        mobile = findViewById(R.id.mobile);
        qudao_tv = findViewById(R.id.qudao_tv);
        dengji_tv = findViewById(R.id.dengji_tv);
        zhengxin_tv = findViewById(R.id.zhengxin_tv);
        ywlx_tv = findViewById(R.id.ywlx_tv);
        tv_need = findViewById(R.id.tv_need);
        tv_shouru = findViewById(R.id.tv_shouru);
        tv_chengjiao = findViewById(R.id.tv_chengjiao);
        tv_hangye = findViewById(R.id.tv_hangye);
        tv_house = findViewById(R.id.tv_house);
        tv_cae = findViewById(R.id.tv_cae);
        tv_vx = findViewById(R.id.tv_vx);
        tv_card = findViewById(R.id.tv_card);
        tv_date = findViewById(R.id.tv_date);
//        tv_s_date = findViewById(R.id.tv_s_date);
//        tv_visit_time = findViewById(R.id.tv_visit_time);
//        tv_next_date = findViewById(R.id.tv_next_date);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("cus_id", getIntent().getStringExtra("id"));
        mPresenter.getDetail(map);
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
        try {
            jsonObject = new JSONObject(gson.toJson(o.getData()));
            String follow_status = jsonObject.optString("follow_status");
            tv_date.setText(jsonObject.optString("success_time"));
            follow_tv.setText(follow_status);
            String surname = jsonObject.optString("surname");
            name_tv.setText(surname);
            s_phone = jsonObject.optString("mobile");
            mobile.setText(s_phone);
            String channel = jsonObject.optString("channel");
            qudao_tv.setText(channel);
            String key_categories = jsonObject.optString("key_categories");
            dengji_tv.setText(key_categories);
            String credit_inquiry = jsonObject.optString("credit_inquiry");
            zhengxin_tv.setText(credit_inquiry);
//            String next_s_date = jsonObject.optString("next_s_date");
//            if (next_s_date == null || next_s_date.equals("")) {
//                time_tv.setText("下次跟进时间:暂无");
//                tv_next_date.setText("--");
//                time_tv.setTextColor(getResources().getColor(R.color.ff215dff));
//            } else {
//
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d");
//                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//                String str = formatter.format(curDate);
//                int date = Integer.parseInt(next_s_date.replaceAll("-", ""));
//                int jindate = Integer.parseInt(str.replaceAll("-", ""));
//                if (date < jindate) {
//                    time_tv.setTextColor(getResources().getColor(R.color.fe60012));
//                    time_tv.setText("下次跟进时间:" + next_s_date);
//                } else if (date == jindate) {
//                    time_tv.setTextColor(getResources().getColor(R.color.fe60012));
//                    time_tv.setText("下次跟进时间:今天");
//                } else {
//                    time_tv.setTextColor(getResources().getColor(R.color.ff215dff));
//                    time_tv.setText("下次跟进时间:" + next_s_date);
//                }
//                tv_next_date.setText(next_s_date);
//            }
            JSONArray jsonArray = jsonObject.optJSONArray("cus_type_arr");
            if (jsonArray.length() > 0) {
                String cus_type_arr = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (cus_type_arr == null) {
                        cus_type_arr = jsonArray.getString(i);
                    } else {
                        cus_type_arr += "," + jsonArray.getString(i);
                    }
                }
                ywlx_tv.setText(remove_s(cus_type_arr));
            } else {
                ywlx_tv.setText("--");
            }
            String need_money = jsonObject.optString("need_money");
            double need_d = jsonObject.optDouble("need_money");
            if (need_d > 0) {
                tv_need.setText(setText(need_money + "万"));
            } else {
                tv_need.setText("--");
            }
//            tv_need.setText(setText(need_money));
            String annual_income = jsonObject.optString("annual_income");

            double annual_d = jsonObject.optDouble("annual_income");
            if (annual_d > 0) {
                tv_shouru.setText(setText(annual_income + "万"));
            } else {
                tv_shouru.setText("--");
            }
            String transaction_amount = jsonObject.optString("transaction_amount");
            double transaction_d = jsonObject.optDouble("transaction_amount");
            if (transaction_d > 0) {
                tv_chengjiao.setText(setText(transaction_amount + "万"));
            } else {
                tv_chengjiao.setText("--");
            }

            String work = jsonObject.optString("work");
            tv_hangye.setText(setText(work));
            JSONArray house = jsonObject.optJSONArray("house_arr");
            if (house.length() > 0) {
                String s_house = null;
                for (int i = 0; i < house.length(); i++) {
                    if (s_house == null || s_house.equals("")) {
                        s_house = house.getString(i);
                    } else {
                        s_house += "," + house.getString(i);
                    }
                }
                tv_house.setText(s_house);
            } else {
                tv_house.setText("--");
            }
            JSONArray car = jsonObject.optJSONArray("cus_arr");
            if (car.length() > 0) {
                String s_car = null;
                for (int i = 0; i < car.length(); i++) {
                    if (s_car == null) {
                        s_car = car.getString(i);
                    } else {
                        s_car += "," + car.getString(i);
                    }
                }
                tv_cae.setText(s_car);
            } else {
                tv_cae.setText("--");
            }
            String wx_num = jsonObject.optString("wx_num");
            tv_vx.setText(setText(wx_num));
            String card_no = jsonObject.optString("card_no");
            if (card_no == null || card_no.equals("")) {
                tv_card.setText("--");
            } else {
                tv_card.setText(card_no);
            }
           tv_yongjin.setText(jsonObject.optString("commission") + "元");
            String fangan = jsonObject.optString("transaction_plan");
            if (fangan == null || fangan.equals("")) {
                tv_fangan.setText("--");
            } else {
                tv_fangan.setText(jsonObject.optString("transaction_plan"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String setText(String s) {
        if (s == null || s.equals("")) {
            s = "--";
        }
        return s;
    }
}