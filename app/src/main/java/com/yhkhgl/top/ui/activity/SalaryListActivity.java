package com.yhkhgl.top.ui.activity;

import android.content.Intent;
import android.view.View;
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
import com.yhkhgl.top.bean.XinziBean;
import com.yhkhgl.top.bean.YongjinListBean;
import com.yhkhgl.top.ui.adapter.SalaryListAdapter;
import com.yhkhgl.top.ui.adapter.YongjinAdapter;
import com.yhkhgl.top.ui.presenters.SralaryPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SalaryListActivity extends BaseActivity<SralaryPresenter> implements MainView {
    SmartRefreshLayout smartrefresh;
    int page = 1;
    SalaryListAdapter adapter;
    YongjinAdapter yjadapter;
    RecyclerView recycleview;
    List<XinziBean> list = new ArrayList<>();
    List<YongjinListBean> yjlist = new ArrayList<>();
    OptionsPickerView timeTypePickerView;
    List<String> date = new ArrayList<>();
    LinearLayout ll_date;
    TextView tv_nian,tv_yue;
    String calculation_date;
    RelativeLayout ll_shousuo;
    LinearLayout ll_title_cj;
    int type;
    TextView tv_yjzj,tv_cjzj;
    @Override
    protected SralaryPresenter createPresenter() {
        return new SralaryPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_salary_list;
    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra("type",0);
        tv_yjzj = findViewById(R.id.tv_yjzj);
        tv_cjzj = findViewById(R.id.tv_cjzj);
        ll_title_cj = findViewById(R.id.ll_title_cj);
        if (type == 0){
            ll_title_cj.setVisibility(View.GONE);
        }else {
            ll_title_cj.setVisibility(View.VISIBLE);
        }
        ll_shousuo = findViewById(R.id.ll_shousuo);
        ll_shousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(SalaryListActivity.this,GuanliSeachActivity.class);
//                if (type == 0){
//                    intent.putExtra("type",1);
//                }else {
//                    intent.putExtra("type",2);
//                }
//                intent.putExtra("calculation_date",calculation_date);
//                startActivity(intent);
            }
        });
        setTimeTypePickerView();
        tv_nian = findViewById(R.id.tv_nian);
        tv_yue = findViewById(R.id.tv_yue);
        ll_date = findViewById(R.id.ll_date);
        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeTypePickerView.setPicker(date);
                timeTypePickerView.show();
            }
        });
        fixTitlePadding(findViewById(R.id.ll_title));
        ImageView iv_fanhui = findViewById(R.id.iv_fanhui);
        iv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        smartrefresh = findViewById(R.id.smartrefresh);
        smartrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                smartrefresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (type == 0){
                    adapter.remove();
                }else {
                    yjadapter.remove();
                }

                page = 1;
                initData();
                smartrefresh.finishRefresh();
            }
        }) ;
        recycleview = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);

        if (type == 0){
            adapter = new SalaryListAdapter(this,list);
            recycleview.setAdapter(adapter);
        }else {
            yjadapter = new YongjinAdapter(this,yjlist);
            recycleview.setAdapter(yjadapter);
        }


        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
//月  int month = calendar.get(Calendar.MONTH) + 1;
        int month = calendar.get(Calendar.MONTH) + 1;
        String m = null;
        if (month<10){
            m = "0"+month;
        }else {
            m = month+"";
        }
        calculation_date=year+"-"+m;
        String day=calculation_date.substring(calculation_date.length()-2,calculation_date.length());
        String date=calculation_date.substring(0,4);
        tv_nian.setText(date+"年");
        tv_yue.setText(day+"月");
        if (type == 0){
            mPresenter.getdate();
        }else {
            mPresenter.getyjdate();
        }
    }

    @Override
    protected void initData() {
        HashMap<String,String> map=new HashMap<>();
        map.put("token", App.token);
        map.put("page",page+"");
        map.put("page_size","10");
        map.put("calculation_date",calculation_date);
        map.put("key_word","");
        if (type == 0){
            mPresenter.getList(map);
        }else {
            mPresenter.getDetail(map);
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
        try {
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            if (type == 0){
                list = gson.fromJson(jsonArray.toString(), new TypeToken<List<XinziBean>>() {
                }.getType());
                if (jsonArray.length() <= 10) {
                    if (jsonArray.length() > 0) {
                        page = page + 1;
                        adapter.updateList(list);
                    } else {
                        adapter.updateList(list);
                        if (page == 1){
                            showError("暂无数据！");
                        }else {
                            showError(hint);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onSuccess(BaseModel<Object> o, int i) {
        try {
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            yjlist = gson.fromJson(jsonArray.toString(), new TypeToken<List<YongjinListBean>>() {
            }.getType());
            JSONObject jsonObject = new JSONObject(gson.toJson(o.getExtend()));
            tv_cjzj.setText("￥"+jsonObject.optString("transaction_amount"));
            tv_yjzj.setText("￥"+jsonObject.optString("commission"));
            if (jsonArray.length() <= 10) {
                if (jsonArray.length() > 0) {
                    page = page + 1;
                    yjadapter.updateList(yjlist);
                } else {
                    yjadapter.updateList(yjlist);
                    if (page == 1){
                        showError("暂无数据！");
                    }else {
                        showError(hint);
                    }
                }
            }

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
    public void setTimeTypePickerView() {
        if (timeTypePickerView == null) {
            timeTypePickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
                String s=date.get(options1);
                calculation_date = date.get(options1);
                String day=s.substring(s.length()-2,s.length());
                String date=s.substring(0,4);
                tv_nian.setText(date+"年");
                tv_yue.setText(day+"月");
                page = 1;
                if (type == 0){
                    adapter.remove();
                }else {
                    yjadapter.remove();
                }

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