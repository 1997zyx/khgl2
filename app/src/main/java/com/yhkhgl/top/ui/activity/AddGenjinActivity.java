package com.yhkhgl.top.ui.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.GualiDetailBean;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;
import com.yhkhgl.top.utils.TimeUtils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddGenjinActivity extends BaseActivity<GuanLiPresent> implements MainView, View.OnClickListener {
    List<String> yser_list = new ArrayList<>();
    List<List<String>> month_list = new ArrayList<>();
    List<List<List<String>>> data_list = new ArrayList<>();
    List<String> yser_list_two = new ArrayList<>();
    List<List<String>> month_list_two = new ArrayList<>();
    List<List<List<String>>> data_list_two = new ArrayList<>();
    OptionsPickerView timeTypePickerView;
    RelativeLayout rl_next, rl_up_house;
    TextView newt_tv, tv_uphouse, tv_date;
    int type, year, month, day;
    Button btn_true;
    String visit_date, next_follow_date;
    EditText et_view;
    GualiDetailBean bean;
    TextView tv_copy;
    TextView btn_copy;
    LinearLayout ll_title;
    RelativeLayout rl_copy;
    TextView iv_fanhuo;
    LinearLayout ll_genjin;
    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_genjin;
    }

    @Override
    protected void initView() {
        iv_fanhuo=findViewById(R.id.iv_fanhuo);
        ll_genjin=findViewById(R.id.ll_genjin);
        rl_copy=findViewById(R.id.rl_copy);
        ll_title=findViewById(R.id.ll_title);
        getTime();
        setTimeTypePickerView();
        fixTitlePadding(findViewById(R.id.rl_title));
        ImageView fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_next = findViewById(R.id.rl_next);
        rl_next.setOnClickListener(this);
        rl_up_house = findViewById(R.id.rl_uphouse);
        rl_up_house.setOnClickListener(this);
        newt_tv = findViewById(R.id.newt_tv);
        tv_uphouse = findViewById(R.id.tv_uphouse);
        tv_date = findViewById(R.id.tv_date);
        if (month < 10) {
            tv_date.setText(year + "-0" + month + "-" + day);
        } else {
            tv_date.setText(year + "-" + month + "-" + day);
        }
        btn_true = findViewById(R.id.btn_true);
        btn_true.setOnClickListener(this);
        et_view = findViewById(R.id.et_view);
        tv_copy = findViewById(R.id.tv_copy);
        btn_copy = findViewById(R.id.btn_copy);
        btn_copy.setOnClickListener(this);
        if (getIntent().getIntExtra("position", 0) != 0) {
            ll_title.setVisibility(View.GONE);
            ll_genjin.setVisibility(View.GONE);
        }else {
            ll_title.setVisibility(View.VISIBLE);
        }
        try {
            bean = (GualiDetailBean) getIntent().getSerializableExtra("bean");
            if (bean != null) {
                newt_tv.setText(bean.getNext_follow_date());
                next_follow_date = bean.getNext_follow_date();
                tv_uphouse.setText(bean.getVisit_date());
                visit_date = bean.getVisit_date();
                et_view.setText(bean.getContent());
                et_view.setSelection(bean.getContent().length());
                iv_fanhuo.setText("编辑跟进");
            }else {
                iv_fanhuo.setText("添加跟进");
            }
                String copy=getIntent().getStringExtra("copy");
            if (copy!=null&&!copy.equals("")){
                tv_copy.setText(getIntent().getStringExtra("copy"));
            }else {
                ll_genjin.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initData() {

    }

    public void getTime() {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        year = calendar.get(Calendar.YEAR);
//月  int month = calendar.get(Calendar.MONTH) + 1;
        month = calendar.get(Calendar.MONTH) + 1;
//日
        day = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = year + 50; i >= 1970; i--) {
            yser_list.add(i + "");
        }
        for (int i = 0; i < yser_list.size(); i++) {
            List<String> one = new ArrayList<>();
            List<List<String>> three = new ArrayList<>();
            for (int m = 1; m <= 12; m++) {
                one.add(m + "");
                List<String> two = new ArrayList<>();
                int data = data(Integer.parseInt(yser_list.get(i)), m);
                for (int d = 1; d <= data; d++) {
                    if (d < 10) {
                        two.add("0" + d + "");
                    } else {
                        two.add(d + "");
                    }
                }
                three.add(two);
            }
            month_list.add(one);
            data_list.add(three);
        }
    }

    public void setTimeTypePickerView() {
        if (timeTypePickerView == null) {
            timeTypePickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
                if (type == 0) {

                    next_follow_date = yser_list_two.get(options1) + "-" + month_list_two.get(options1).get(option2) + "-" + data_list_two.get(options1).get(option2).get(options3);
                    newt_tv.setText(next_follow_date);
                } else {

                    visit_date = yser_list.get(options1) + "-" + month_list.get(options1).get(option2) + "-" + data_list.get(options1).get(option2).get(options3);
                    tv_uphouse.setText(visit_date);
                }
            })//设置选择第一个
                    .setSubmitColor(getResources().getColor(R.color.bg_85736))//确定按钮文字颜色
                    .setCancelColor(getResources().getColor(R.color.text_959595))//取消按钮文字颜色
                    .setTitleBgColor(getResources().getColor(R.color.colorWhite))//标题背景颜色 Night mode
                    .setContentTextSize(15) // 滚轮文字大小
                    .setSubCalSize(14) // 确定取消按钮文字大小
                    .setTitleSize(17)  // 标题文字大小
                    .setTitleText("时间") // 标题文字
                    .setLineSpacingMultiplier(2f)//设置间距倍数,但是只能在1.2-2.0f之间
                    .setTitleColor(getResources().getColor(R.color.text_1b1b1b)) // 标题文字颜色
                    .setLabels("", "", "")
                    .build();
        }
    }

    public int data(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (year % 4 == 0 && year % 100 != 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;

        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.rl_next:
                type = 0;
//                timeTypePickerView.setPicker(yser_list, month_list, data_list);
//                timeTypePickerView.setSelectOptions(50, month - 1, day);
                TimeUtils.getStartToEnd(year, year + 50, yser_list_two, month_list_two, data_list_two);
                timeTypePickerView.setPicker(yser_list_two, month_list_two, data_list_two);
                timeTypePickerView.setSelectOptions(0, 0, 0);
                timeTypePickerView.show();
                break;
            case R.id.rl_uphouse:
                type = 1;
                timeTypePickerView.setPicker(yser_list, month_list, data_list);
                timeTypePickerView.setSelectOptions(50, month - 1, day);
                timeTypePickerView.show();
                break;
            case R.id.btn_true:
                String content = et_view.getText().toString().trim();
                if (content == null || content.equals("")) {
                    showError("请输入跟进情况");
                    return;
                }
                setLoadingDialog("提交中");
                HashMap<String, String> map = new HashMap<>();
                if (getIntent().getStringExtra("id") != null) {
                    map.put("id", getIntent().getStringExtra("id"));
                }
                map.put("token", App.token);
                map.put("cus_id", getIntent().getStringExtra("cus_id"));
                if (next_follow_date != null) {
                    map.put("next_follow_date", next_follow_date);
                }
                map.put("content", content);
                mPresenter.addGenJin(map);
                break;
            case R.id.btn_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(tv_copy.getText().toString());
                showError("复制成功");
                break;
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

        GualiDetailBean gualiDetailBean = new GualiDetailBean();
        gualiDetailBean.setContent(et_view.getText().toString().trim());
        gualiDetailBean.setFollow_date(tv_date.getText().toString());
        gualiDetailBean.setVisit_date(visit_date);
        gualiDetailBean.setNext_follow_date(next_follow_date);
        Intent intent = new Intent();
        intent.putExtra("bean", gualiDetailBean);
        setResult(100, intent);
        showError("提交成功");
        finish();
    }
}
