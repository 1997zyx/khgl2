package com.yhkhgl.top.ui.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.OptionsPickerView;

import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.YongjinListBean;
import com.yhkhgl.top.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.yhkhgl.top.ui.adapter.SalaryAdapter;
import com.yhkhgl.top.ui.presenters.SralaryPresenter;
import com.yhkhgl.top.utils.BottomDialogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SalaryActivity extends BaseActivity<SralaryPresenter> implements MainView, View.OnClickListener {
    HeaderAndFooterWrapper headerAndFooterWrapper;
    RecyclerView recyclerView;
    SalaryAdapter adapter;
    RelativeLayout rl_title,Rl_ticheng;
    SmartRefreshLayout smartRefreshLayout;
    int y = 0;
    String service_user_id = "",calculation_date = "";
    TextView tv_total,monthly_salary_tv,sb_tv,tv_qita,tv_name,tv_mobile,tv_dx,tv_sb,tv_jiangjin,tv_chidao
           ,tv_cdje,tc_tv,tv_qt,tv_cje,tv_dyyj,wenhao,fangan_tv;
    String base_salary;
    Dialog dialog;
    JSONArray jsonArray;
    List<YongjinListBean> list=new ArrayList<>();
    int page = 1;
    LinearLayout ll_zhanwu;
    TextView tv_date;
    OptionsPickerView timeTypePickerView;
    List<String> date = new ArrayList<>();
    @Override
    protected SralaryPresenter createPresenter() {
        return new SralaryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_galary;
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
        tv_date = findViewById(R.id.tv_date);
        tv_date.setOnClickListener(this);
        try {
            service_user_id = getIntent().getStringExtra("service_user_id");
            calculation_date = getIntent().getStringExtra("calculation_date");
            tv_date.setText(calculation_date);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (calculation_date == null||calculation_date.equals("")){
            Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
            int year = calendar.get(Calendar.YEAR);
//月
            int month = calendar.get(Calendar.MONTH)+1;

            tv_date.setText(year+"-"+month);
        }
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                adapter.remove();
                initData();
                smartRefreshLayout.finishRefresh();
            }
        });
        recyclerView=findViewById(R.id.recycleview);
        fixTitlePadding(findViewById(R.id.rl_title));
        adapter = new SalaryAdapter(this,list);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        View view = LayoutInflater.from(this).inflate(R.layout.salarl_header,null);
        headerView(view);
        rl_title = findViewById(R.id.rl_title);
        headerAndFooterWrapper.addHeaderView(view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(headerAndFooterWrapper);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                y += dy;
                if (y <= 0) {//未滑动
                    rl_title.setBackground(null);
                } else if (y > 0 && y <= rl_title.getHeight()) { //滑动过程中 并且在mHeight之内
                    rl_title.setVisibility(View.VISIBLE);
                    float scale = (float) y / rl_title.getHeight();
                    float alpha = (255 * scale);

                    rl_title.setBackgroundColor(Color.argb((int) alpha, 33, 93, 255));
                } else {//超过mHeight
                    rl_title.setVisibility(View.VISIBLE);
                    rl_title.setBackgroundColor(Color.argb((int) 255, 33, 93, 255));
                }
                Log.i("-------","y"+y);
            }
        });

    }
    private void headerView(View view){
        Rl_ticheng = view.findViewById(R.id.Rl_ticheng);
        Rl_ticheng.setOnClickListener(this);
        tv_total = view.findViewById(R.id.tv_total);
        monthly_salary_tv = view.findViewById(R.id.monthly_salary_tv);
        sb_tv = view.findViewById(R.id.sb_tv);
        tv_qita = view.findViewById(R.id.tv_qita);
        tv_name = view.findViewById(R.id.tv_name);
        tv_mobile = view.findViewById(R.id.tv_mobile);
        tv_dx = view.findViewById(R.id.tv_dx);
        tv_sb = view.findViewById(R.id.tv_sb);
        tv_jiangjin = view.findViewById(R.id.tv_jiangjin);
        tv_chidao = view.findViewById(R.id.tv_chidao);
        tv_cdje = view.findViewById(R.id.tv_cdje);
        tc_tv = view.findViewById(R.id.tc_tv);
        tv_qt = view.findViewById(R.id.tv_qt);
        tv_cje = view.findViewById(R.id.tv_cje);
        tv_dyyj = view.findViewById(R.id.tv_dyyj);
        wenhao = view.findViewById(R.id.wenhao);
        wenhao.setOnClickListener(this);
        ll_zhanwu = view.findViewById(R.id.ll_zhanwu);
        fangan_tv = view.findViewById(R.id.fangan_tv);
        mPresenter.getDetaildate(service_user_id);
        setTimeTypePickerView();
    }

    @Override
    protected void initData() {

        HashMap<String,String> map=new HashMap<>();
        map.put("token", App.token);
        map.put("service_user_id",service_user_id);
        map.put("calculation_date",calculation_date);
        map.put("page",page+"");
        map.put("page_size","5");
        mPresenter.getDetail(map);
        mPresenter.getListDetail(map);
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
        try {
            JSONObject jsonObject=new JSONObject(gson.toJson(o.getData()));
            tv_total.setText("￥"+jsonObject.optString("total_salary"));
            monthly_salary_tv.setText("￥"+jsonObject.optString("monthly_salary"));
            sb_tv.setText("￥"+jsonObject.optString("social_insurance_late_money"));
            tv_qita.setText("￥"+jsonObject.optString("correction_value"));
            tv_name.setText(jsonObject.optString("name"));
            tv_mobile.setText(jsonObject.optString("mobile"));
            tv_dx.setText(jsonObject.optString("base_salary_money")+"元");
            tv_sb.setText(jsonObject.optString("social_insurance_money")+"元");
            tv_jiangjin.setText(jsonObject.optString("bonus")+"元");
            tv_chidao.setText(jsonObject.optString("late_times")+"次");
            tv_cdje.setText(jsonObject.optString("late_amount")+"元");
            tc_tv.setText(jsonObject.optString("commission_amount")+"元");
            tv_qt.setText(jsonObject.optString("correction_value")+"元");
            tv_cje.setText(jsonObject.optString("total_transaction_amount")+"万");
            tv_dyyj.setText(jsonObject.optString("total_commission")+"元");
            base_salary = jsonObject.optString("commission");
            fangan_tv.setText(base_salary);
            jsonArray=jsonObject.optJSONArray("proportion");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSuccess(BaseModel<Object> o, int i) {
        try {
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            list = gson.fromJson(jsonArray.toString(), new TypeToken<List<YongjinListBean>>() {
            }.getType());
            if (jsonArray.length() <= 5) {
                if (jsonArray.length() > 0) {
                    page = page + 1;
                    adapter.updateList(list);
                    ll_zhanwu.setVisibility(View.GONE);
                } else {
                    adapter.updateList(list);
                    if (page == 1){
                        ll_zhanwu.setVisibility(View.VISIBLE);
                    }else {
                        showError(hint);
                    }
                }
            }
            headerAndFooterWrapper.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {
        try {
            JSONArray jsonArray=new JSONArray(gson.toJson(o.getData()));
            for (int i = jsonArray.length()-1; i >=0;i--){
                date.add(jsonArray.optString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wenhao:
            case R.id.Rl_ticheng:
                if (dialog == null){
                    PopView(jsonArray);
                }else {
                    dialog.show();
                }
                break;
            case R.id.tv_date:
                timeTypePickerView.setPicker(date);
                timeTypePickerView.show();
                break;

        }
    }
    private void PopView(JSONArray jsonArray){
        View view = LayoutInflater.from(this).inflate(R.layout.fangan_pop,null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        Button btn_true = view.findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        LinearLayout ll_content = view.findViewById(R.id.ll_content);
        tv_title.setText(base_salary);
        for (int i = 0;i<jsonArray.length();i++){
            View view1 = LayoutInflater.from(this).inflate(R.layout.fanganpop_item,null);
            TextView name = view1.findViewById(R.id.name);
            name.setText(jsonArray.optString(i));
            ll_content.addView(view1);
        }
        dialog = new Dialog(this,R.style.BottomDialogStyle);
        BottomDialogUtil.AllCenter(dialog,this,view);
    }
    public void setTimeTypePickerView() {
        if (timeTypePickerView == null) {
            timeTypePickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
                tv_date.setText(date.get(options1));
                calculation_date = date.get(options1);
                page = 1;
                adapter.remove();
                initData();
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
}