package com.yhkhgl.top.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.reflect.TypeToken;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.GuanliTitleBean;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;
import com.yhkhgl.top.utils.BottomDialogUtil;
import com.yhkhgl.top.utils.EditViewUtil;
import com.yhkhgl.top.utils.TimeUtils;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.blankj.utilcode.util.ScreenUtils.getScreenHeight;


public class AddGuanliActivity extends BaseActivity<GuanLiPresent> implements MainView, View.OnClickListener {
    List<GuanliTitleBean> listKeHu = new ArrayList<>();
    List<GuanliTitleBean> listZhuangTai = new ArrayList<>();
    List<GuanliTitleBean> listType = new ArrayList<>();
    List<GuanliTitleBean> listQuDao = new ArrayList<>();
    List<GuanliTitleBean> peoplelist = new ArrayList<>();
    List<GuanliTitleBean> houselist = new ArrayList<>();
    List<GuanliTitleBean> carlist = new ArrayList<>();
    View view_kehu, view_zhuangtai, view_leixing, view_qudao, view_hangye;
    RelativeLayout rl_qudao, rl_dengji, rl_type, rl_zhuangtai, rl_house, rl_hangye, title_rl, rl_car;
    Dialog dialog;
    List<CheckBox> checkBoxList = new ArrayList<>();
    HashMap<Integer, String> hashMap = new HashMap<>();
    TextView tv_qudao, tv_dengji, tv_leixing, tv_zhuangtai, tv_hangye, tv_house, tv_car;
    List<String> cus_typelist = new ArrayList<>();//????????????
    List<String> house_id = new ArrayList<>();//??????
    List<String> car_id = new ArrayList<>();//??????
    String[] house = new String[]{"????????????", "???????????????", "????????????", "???????????????"};
    String[] car = new String[]{"??????", "?????????"};
    String[] people = new String[]{"?????????", "??????/????????????", "??????", "?????????", "??????", "??????"};
    View view;
    Button button;
    EditText et_name, et_phone, et_need, et_chengjiao, et_shouru, et_wx, et_card, et_view;
    RadioGroup rg;
    String credit_inquiry = "";
    String id;
    String channel = "", key_categories = "", follow_status = "", cus_type_str = "", house_str = "", car_str = "", work = "", wx_num = "", card_no = "",next_s_date = "",commission="",transaction_plan="";
    LinearLayout ll_time;
    List<String> yser_list = new ArrayList<>();
    List<List<String>> month_list = new ArrayList<>();
    List<List<List<String>>> data_list = new ArrayList<>();
    OptionsPickerView timeTypePickerView;
    TextView tv_time;
    EditText et_yongjin,et_cjfangan;
    RelativeLayout rl_yongjin,rl_cjfa,rl_cjje;
    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_guanli;
    }

    @Override
    protected void initView() {
        rl_cjje = findViewById(R.id.rl_cjje);
        rl_yongjin =findViewById(R.id.rl_yongjin);
        rl_cjfa = findViewById(R.id.rl_cjfa);
        et_yongjin = findViewById(R.id.et_yongjin);
        et_cjfangan = findViewById(R.id.et_cjfangan);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        credit_inquiry = "???";
                        break;
                    case R.id.rb2:
                        credit_inquiry = "???";
                        break;
                }
            }
        });
        setTimeTypePickerView();
        tv_time=findViewById(R.id.tv_time);
        ll_time = findViewById(R.id.ll_time);
        ll_time.setOnClickListener(this);
        et_view = findViewById(R.id.et_view);
        et_chengjiao = findViewById(R.id.et_chengjiao);
        et_card = findViewById(R.id.et_card);
        et_shouru = findViewById(R.id.et_shouru);
        et_need = findViewById(R.id.et_need);
        et_need.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtil.mangeEditextDigit(s, et_need, 4);//????????????mangeEditextDigit
            }

            @Override
            public void afterTextChanged(Editable edt) {
            }
        });
        et_yongjin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtil.mangeEditextDigit(s, et_yongjin, 4);//????????????mangeEditextDigit
            }

            @Override
            public void afterTextChanged(Editable edt) {
            }
        });

        et_chengjiao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtil.mangeEditextDigit(s, et_chengjiao, 4);//????????????mangeEditextDigit
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_shouru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditViewUtil.mangeEditextDigit(s, et_shouru, 4);//????????????mangeEditextDigit
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_wx = findViewById(R.id.et_wx);
        view = View.inflate(this, R.layout.guanli_bottom_dialog, null);
        title_rl = view.findViewById(R.id.title_rl);
        button = findViewById(R.id.btn_true);
        button.setOnClickListener(this);
        title_rl.setOnClickListener(this);
        rl_type = findViewById(R.id.rl_type);
        rl_type.setOnClickListener(this);
        rl_qudao = findViewById(R.id.rl_qudao);
        rl_qudao.setOnClickListener(this);
        rl_dengji = findViewById(R.id.rl_dengji);
        rl_dengji.setOnClickListener(this);
        rl_zhuangtai = findViewById(R.id.rl_zhuangtai);
        rl_zhuangtai.setOnClickListener(this);
        rl_house = findViewById(R.id.rl_house);
        rl_house.setOnClickListener(this);
        rl_hangye = findViewById(R.id.rl_hangye);
        rl_hangye.setOnClickListener(this);
        rl_car = findViewById(R.id.rl_car);
        rl_car.setOnClickListener(this);
        tv_house = findViewById(R.id.tv_house);
        tv_car = findViewById(R.id.tv_car);
        tv_qudao = findViewById(R.id.tv_qudao);
        tv_dengji = findViewById(R.id.tv_dengji);
        tv_leixing = findViewById(R.id.tv_leixing);
        tv_zhuangtai = findViewById(R.id.tv_zhuangtai);
        tv_hangye = findViewById(R.id.tv_hangye);
        fixTitlePadding(findViewById(R.id.rl_title));
        TextView iv_fanhuo = findViewById(R.id.iv_fanhuo);
        ImageView fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {

            String s = getIntent().getStringExtra("jsonObject");
            JSONObject jsonObject = new JSONObject(JSONTokener(s));
            LogUtils.e(jsonObject.toString() + "");
            JSONArray key_categories = jsonObject.optJSONArray("key_categories");//????????????
            listKeHu = gson.fromJson(key_categories.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            JSONArray follow_status = jsonObject.optJSONArray("follow_status");//????????????
            listZhuangTai = gson.fromJson(follow_status.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            JSONArray cus_type = jsonObject.optJSONArray("cus_type");//????????????
            listType = gson.fromJson(cus_type.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            JSONArray channel = jsonObject.optJSONArray("channel");//??????
            listQuDao = gson.fromJson(channel.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String info = getIntent().getStringExtra("info");
            JSONObject object = new JSONObject(JSONTokener(info));
            iv_fanhuo.setText("??????????????????");
            credit_inquiry = object.optString("credit_inquiry");
            if (credit_inquiry.equals("???")) {
                rg.check(R.id.rb1);
            } else {
                rg.check(R.id.rb2);
            }
            id = object.optString("id");
            et_name.setText(object.optString("surname"));
            et_phone.setText(object.optString("mobile"));
            et_need.setText(object.optString("need_money") + "");
            et_chengjiao.setText(object.optString("transaction_amount") + "");
            et_shouru.setText(object.optString("annual_income") + "");
            channel = object.optString("channel");//??????
            if (channel == null || channel.equals("")) {
                tv_qudao.setText("?????????");
                tv_qudao.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            } else {
                hashMap.put(1, channel);
                tv_qudao.setText(channel);
                tv_qudao.setTextColor(getResources().getColor(R.color.text_1b1b1b));
            }
            key_categories = object.optString("key_categories");
            if (key_categories == null || key_categories.equals("")) {
                tv_dengji.setText("?????????");
                tv_dengji.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            } else {
                for (int i = 0; i < listKeHu.size(); i++) {
                    if (listKeHu.get(i).getKey().equals(key_categories)) {
                        tv_dengji.setText(listKeHu.get(i).getValue());
                        tv_dengji.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                        hashMap.put(2, key_categories);
                    }
                }
            }
            follow_status = object.optString("follow_status");
            if (follow_status == null || follow_status.equals("")) {
                tv_zhuangtai.setText("?????????");
                tv_zhuangtai.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            } else {
                hashMap.put(3, follow_status);
                tv_zhuangtai.setText(follow_status);
                if (follow_status.equals("??????")){
                    rl_cjfa.setVisibility(View.VISIBLE);
                    rl_yongjin.setVisibility(View.VISIBLE);
                    rl_cjje.setVisibility(View.VISIBLE);
                }else {
                    rl_cjfa.setVisibility(View.GONE);
                    rl_yongjin.setVisibility(View.GONE);
                    rl_cjje.setVisibility(View.GONE);
                }
                tv_zhuangtai.setTextColor(getResources().getColor(R.color.text_1b1b1b));
            }

            JSONArray cus_type_id_arr = object.optJSONArray("cus_type_id_arr");
            JSONArray cus_type_strarr = object.optJSONArray("cus_type_arr");
            if (cus_type_strarr.length() > 0) {
                String cus_type_arr = null;
                for (int i = 0; i < cus_type_strarr.length(); i++) {
                    cus_typelist.add(cus_type_id_arr.getString(i));
                    if (cus_type_arr == null) {
                        cus_type_arr = cus_type_strarr.getString(i);
                    } else {
                        cus_type_arr += "," + cus_type_strarr.getString(i);
                    }
                }
                tv_leixing.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                tv_leixing.setText(cus_type_arr);
            } else {
                tv_leixing.setText("?????????");
                tv_leixing.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            }

            JSONArray housearr = object.optJSONArray("house_id_arr");
            JSONArray house_arr = object.optJSONArray("house_arr");
            if (house_arr.length() > 0) {
                String s_house = null;
                for (int i = 0; i < house_arr.length(); i++) {
                    house_id.add(housearr.getString(i));
                    if (s_house == null) {
                        s_house = house_arr.getString(i);
                    } else {
                        s_house += "," + house_arr.getString(i);
                    }
                }
                tv_house.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                tv_house.setText(s_house);
            } else {
                tv_house.setText("?????????");
                tv_house.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            }
            JSONArray cararr = object.optJSONArray("car_id_arr");
            JSONArray cus_arr = object.optJSONArray("cus_arr");
            if (cus_arr.length() > 0) {
                String s_car = null;
                for (int i = 0; i < cus_arr.length(); i++) {
                    car_id.add(cararr.getString(i));
                    if (s_car == null) {
                        s_car = cus_arr.getString(i);
                    } else {
                        s_car += "," + cus_arr.getString(i);
                    }
                }
                tv_car.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                tv_car.setText(s_car);
            } else {
                tv_car.setText("?????????");
                tv_car.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            }

            work = object.optString("work");
            if (work == null || work.equals("")) {
                tv_hangye.setText("?????????");
                tv_hangye.setTextColor(getResources().getColor(R.color.ffc9c9c9));
            } else {
                hashMap.put(4, work);
                tv_hangye.setText(work);
                tv_hangye.setTextColor(getResources().getColor(R.color.text_1b1b1b));
            }
            next_s_date = object.optString("next_s_date");
            if (next_s_date==null||next_s_date.equals("")){
                tv_time.setText("?????????");
            }else {
                tv_time.setText(next_s_date);
            }
            String cus_info=object.optString("cus_info");
            if (cus_info==null||cus_info.equals("")){
                et_view.setText("");
            }else {
                et_view.setText(cus_info);
            }
            wx_num = object.optString("wx_num");
            et_wx.setText(wx_num);
            card_no = object.optString("card_no");
            et_card.setText(card_no);
            commission =object.optString("commission");
            et_yongjin.setText(commission);
            transaction_plan = object.optString("transaction_plan");
            et_cjfangan.setText(transaction_plan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void initData() {
        if (peoplelist != null) {
            peoplelist.clear();
        }
        for (int i = 0; i < people.length; i++) {
            GuanliTitleBean c = new GuanliTitleBean();
            c.setKey(people[i]);
            c.setValue(people[i]);
            peoplelist.add(c);
        }
        if (houselist != null) {
            houselist.clear();
        }
        for (int i = 0; i < house.length; i++) {
            GuanliTitleBean c = new GuanliTitleBean();
            c.setKey(i + 1 + "");
            c.setValue(house[i]);
            houselist.add(c);
        }
        if (carlist != null) {
            carlist.clear();
        }
        for (int i = 0; i < car.length; i++) {
            GuanliTitleBean c = new GuanliTitleBean();
            c.setKey(i + 1 + "");
            c.setValue(car[i]);
            carlist.add(c);
        }
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
        showError(o.getErrmsg());
        Intent intent=new Intent();
        setResult(1001,intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_rl:
                if (dialog != null) {
                    dialog.dismiss();
                }
                break;
            case R.id.rl_qudao://??????
                SetDialogDanXuan(1, listQuDao, tv_qudao);
                break;
            case R.id.rl_dengji://????????????
                SetDialogDanXuan(2, listKeHu, tv_dengji);
                break;
            case R.id.rl_zhuangtai://??????????????????
                SetDialogDanXuan(3, listZhuangTai, tv_zhuangtai);
                break;
            case R.id.rl_hangye://????????????
                SetDialogDanXuan(4, peoplelist, tv_hangye);
                break;
            case R.id.rl_type://?????????????????????
                SetDialogDuoXuan(1, listType, tv_leixing, cus_typelist);
                break;
            case R.id.rl_house://???????????????
                SetDialogDuoXuan(2, houselist, tv_house, house_id);
                break;
            case R.id.rl_car:
                SetDialogDuoXuan(3, carlist, tv_car, car_id);
                break;
            case R.id.btn_true:
                String surname = et_name.getText().toString().trim();
                String mobile = et_phone.getText().toString().trim();
                String need = et_need.getText().toString().trim();
                String transaction_amount = et_chengjiao.getText().toString().trim();
                String annual_income = et_shouru.getText().toString().trim();
                String cus_info = et_view.getText().toString().trim();

                if (surname == null || surname.equals("")) {
                    showError("?????????????????????");
                    return;
                }
                if (mobile == null || mobile.equals("")) {
                    showError("?????????????????????");
                    return;
                }
                if (hashMap.get(1) == null || hashMap.get(1).equals("")) {
                    showError("?????????????????????");
                    return;
                } else {
                    channel = hashMap.get(1);
                }
                if (hashMap.get(2) == null || hashMap.get(2).equals("")) {
                    showError("?????????????????????");
                    return;
                } else {
                    key_categories = hashMap.get(2);
                }
                if (cus_typelist.size() < 1) {
//                    showError("?????????????????????");
//                    return;
                    cus_type_str="";
                } else {
                    cus_type_str = remove_s(String.valueOf(cus_typelist));
                }
                if (hashMap.get(3) == null || hashMap.get(3).equals("")) {
                    showError("?????????????????????");
                    return;
                } else {
                    follow_status = hashMap.get(3);
                }
                if (credit_inquiry == null || credit_inquiry.equals("")) {
                    showError("???????????????????????????");
                    return;
                }
                if (house_id.size() > 0) {
                    house_str = remove_s(String.valueOf(house_id));
                }
                if (car_id.size() > 0) {
                    car_str = remove_s(String.valueOf(car_id));
                }
                if (hashMap.get(4) != null) {
                    work = hashMap.get(4);
                }

                wx_num = et_wx.getText().toString().trim();
                card_no = et_card.getText().toString().trim();
                commission = et_yongjin.getText().toString().trim();
                transaction_plan = et_cjfangan.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                if (id != null) {
                    map.put("id", id);
                }
//                surname:???  //????????????
                map.put("token", App.token);
                map.put("commission",commission);
                //????????????
                map.put("transaction_plan",transaction_plan);
                //????????????
                map.put("surname", surname);
//                mobile:13524728006  //????????????
                map.put("mobile", mobile);
//                channel:??????  //??????
                map.put("channel", channel);
//                key_categories:A  //????????????
                map.put("key_categories", key_categories);
//                cus_type_str:1,2,3,4,5  //?????????????????????  1,2,3,4,5
                map.put("cus_type_str", cus_type_str);
//                follow_status:??????  //????????????
                map.put("follow_status", follow_status);
//                credit_inquiry:???  //????????????
                map.put("credit_inquiry", credit_inquiry);
//                need_money:50  //????????????
                map.put("need_money", need);
//                transaction_amount:50  //????????????
                map.put("transaction_amount", transaction_amount);
//                annual_income:100  //?????????
                map.put("annual_income", annual_income);
//                house_str:1,2,3  //???????????? 1,2,3
                map.put("house_str", house_str);
//                car_str:1,2  //???????????? 1,2
                map.put("car_str", car_str);
//                work:??????/????????????    //????????????
                map.put("work", work);
//                wx_num:8888888  //????????????
                map.put("wx_num", wx_num);
//                card_no:420922  //????????????
                map.put("card_no", card_no);
//                s_date:2021-06-08  //????????????
                map.put("next_s_date",next_s_date );
                map.put("cus_info", cus_info);
                LogUtils.e(map);
                mPresenter.add_info(map);

                break;
            case R.id.ll_time:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
//???  int month = calendar.get(Calendar.MONTH) + 1;
                int month = calendar.get(Calendar.MONTH) + 1;
//???
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                TimeUtils.getStartToEnd(year, year + 50, yser_list, month_list, data_list);
                timeTypePickerView.setPicker(yser_list, month_list, data_list);
                timeTypePickerView.setSelectOptions(0, 0, 0);
                timeTypePickerView.show();
                break;

        }
    }

    public void SetDialogDanXuan(int type, List<GuanliTitleBean> list, TextView text) {
        View v = view;
        if (dialog == null) {
            dialog = new Dialog(this, R.style.BottomDialogStyle);
        }
        Button btn_true = v.findViewById(R.id.btn_true);
        btn_true.setVisibility(View.GONE);
        TextView textView = v.findViewById(R.id.tv_name);
        switch (type) {
            case 1:
                textView.setText("??????");
                break;
            case 2:
                textView.setText("????????????");
                break;
            case 3:

                textView.setText("????????????");
                break;
            case 4:
                textView.setText("????????????");

                break;

        }
        LinearLayout ll_content = v.findViewById(R.id.ll_content);
        if (ll_content != null) {
            ll_content.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(AddGuanliActivity.this, R.layout.guanli_dialog_item, null);
            CheckBox checkBox = view.findViewById(R.id.checkbox);
            if (type == 2) {
                checkBox.setTextSize(12);
                if (i == 0) {
                    checkBox.setText(list.get(i).getValue() + "?????????????????????????????????");
                } else if (i == 1) {
                    checkBox.setText(list.get(i).getValue() + "????????????????????????????????????????????????\n????????????????????????????????????????????????");
                } else if (i == 2) {
                    checkBox.setText(list.get(i).getValue() + "????????????????????????????????????????????????");
                }
            } else {
                checkBox.setTextSize(15);
                checkBox.setText(list.get(i).getValue());
            }

            int finalI = i;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    hashMap.put(type, list.get(finalI).getKey());
                    text.setText(list.get(finalI).getValue());
                    text.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                    if (list.get(finalI).getValue().equals("??????")){
                        rl_cjfa.setVisibility(View.VISIBLE);
                        rl_yongjin.setVisibility(View.VISIBLE);
                        rl_cjje.setVisibility(View.VISIBLE);
                    }else {
                        rl_cjfa.setVisibility(View.GONE);
                        rl_yongjin.setVisibility(View.GONE);
                        rl_cjje.setVisibility(View.GONE);
                    }
                    dialog.dismiss();
                }
            });
            checkBoxList.add(checkBox);
            ll_content.addView(view);
        }
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (v.getHeight() < getScreenHeight() * 0.8) {

        } else {
            layoutParams.height = (int) (getScreenHeight() * 0.85);
            v.setLayoutParams(layoutParams);
        }
        BottomDialogUtil.Dia(dialog, AddGuanliActivity.this, v);
    }

    public void SetDialogDuoXuan(int type, List<GuanliTitleBean> list, TextView text, List list_id) {
        View v = view;
        if (dialog == null) {
            dialog = new Dialog(this, R.style.BottomDialogStyle);
        }
        List<String> id = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < list_id.size(); k++) {
                if (list.get(i).getKey().equals(list_id.get(k))) {
                    name.add(list.get(i).getValue());
                }
            }
        }
        Button btn_true = v.findViewById(R.id.btn_true);
        btn_true.setVisibility(View.VISIBLE);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_id != null) {
                    list_id.clear();
                }
                list_id.addAll(id);
                String sname = null;
                if (list_id.size() > 0) {
                    for (int i = 0; i < name.size(); i++) {
                        if (sname == null || sname.equals("")) {
                            sname = name.get(i);
                        } else {
                            sname += "," + name.get(i);
                        }
                    }
                    text.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                    text.setText(sname);
                } else {
                    text.setTextColor(getResources().getColor(R.color.ffc9c9c9));
                    text.setText("?????????");
                }


                dialog.dismiss();
            }
        });
        TextView textView = v.findViewById(R.id.tv_name);
        if (list_id != null) {
            id.addAll(list_id);
        }
        switch (type) {
            case 1:
                textView.setText("????????????");
                break;
            case 2:
                textView.setText("????????????");
                break;
            case 3:
                textView.setText("????????????");
                break;
        }
        LinearLayout ll_content = v.findViewById(R.id.ll_content);
        if (ll_content != null) {
            ll_content.removeAllViews();
        }
        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(AddGuanliActivity.this, R.layout.guanli_dialog_item, null);
            CheckBox checkBox = view.findViewById(R.id.checkbox);
            checkBox.setText(list.get(i).getValue());
            int finalI = i;
            if (id.contains(list.get(i).getKey())) {
                checkBox.setChecked(true);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        id.add(list.get(finalI).getKey());
                        name.add(list.get(finalI).getValue());
                    } else {
                        Iterator<String> iterator = id.iterator();
                        while (iterator.hasNext()) {
                            String value = iterator.next();
                            if (value.equals(list.get(finalI).getKey())) {
                                iterator.remove();
                            }
                        }
                        Iterator<String> names = name.iterator();
                        while (names.hasNext()) {
                            String value = names.next();
                            if (value.equals(list.get(finalI).getValue())) {
                                names.remove();
                            }
                        }
                    }
                    LogUtils.e();
                }
            });
            checkBoxList.add(checkBox);
            ll_content.addView(view);
        }
        BottomDialogUtil.Dia(dialog, AddGuanliActivity.this, v);
    }

    public void setTimeTypePickerView() {
        if (timeTypePickerView == null) {
            timeTypePickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
                tv_time.setText(yser_list.get(options1) + "-" + month_list.get(options1).get(option2) + "-" + data_list.get(options1).get(option2).get(options3));
                next_s_date = yser_list.get(options1) + "-" + month_list.get(options1).get(option2) + "-" + data_list.get(options1).get(option2).get(options3);
            })//?????????????????????
                    .setSubmitColor(getResources().getColor(R.color.bg_85736))//????????????????????????
                    .setCancelColor(getResources().getColor(R.color.text_959595))//????????????????????????
                    .setTitleBgColor(getResources().getColor(R.color.colorWhite))//?????????????????? Night mode
                    .setContentTextSize(15) // ??????????????????
                    .setSubCalSize(14) // ??????????????????????????????
                    .setTitleSize(17)  // ??????????????????
                    .setTitleText("??????") // ????????????
                    .setLineSpacingMultiplier(2f)//??????????????????,???????????????1.2-2.0f??????
                    .setTitleColor(getResources().getColor(R.color.text_1b1b1b)) // ??????????????????
                    .setLabels("", "", "")
                    .build();
        }
    }
    /*
     * ????????????????????????????????????????????????  ???addTextChangedListener????????????
	 * @param s  Editext  CharSequence
	 * @param et  Editext ??????
	 * @param digit  ??????????????????
	 */

}
