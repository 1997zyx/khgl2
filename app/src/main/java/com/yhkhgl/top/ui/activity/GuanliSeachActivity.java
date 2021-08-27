package com.yhkhgl.top.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.GuanliListBean;
import com.yhkhgl.top.bean.XinziBean;
import com.yhkhgl.top.bean.YongjinListBean;
import com.yhkhgl.top.ui.adapter.GuanliSeachAdapter;
import com.yhkhgl.top.ui.adapter.SalaryListAdapter;
import com.yhkhgl.top.ui.adapter.YongjinAdapter;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuanliSeachActivity extends BaseActivity<GuanLiPresent> implements MainView {
    EditText editview;
    List<GuanliListBean> listBeans=new ArrayList<>();
    GuanliSeachAdapter adapter;
    SmartRefreshLayout smartRefreshLayout;
    RecyclerView recycleview;
    public JSONObject jsonObject=null;
    LinearLayout ll_zhanwu;
    SalaryListAdapter adaptertwo;
    YongjinAdapter yjadapter;
    int type;
    String calculation_date;
    List<XinziBean> list = new ArrayList<>();
    List<YongjinListBean> yjlist = new ArrayList<>();

    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guanli_seach;
    }

    @Override
    protected void initView() {
        type=getIntent().getIntExtra("type",0);
        try {
            calculation_date = getIntent().getStringExtra("calculation_date");
        }catch (Exception e){
            e.printStackTrace();
        }

        TextView quxiao=findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_zhanwu=findViewById(R.id.ll_zhanwu);
        ImageView clear_iv=findViewById(R.id.clear_iv);
        clear_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0){
                    adapter.remove();
                }else if(type == 1){
                    adaptertwo.remove();
                }else if (type == 2){
                    yjadapter.remove();
                }
                editview.setText(null);
                ll_zhanwu.setVisibility(View.GONE);
            }
        });

        fixTitlePadding(findViewById(R.id.ll_title));
        editview=findViewById(R.id.editview);

        editview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (type == 0){
                    if (s.length()>0){
                        HashMap<String,String> map=new HashMap<>();
                        map.put("token", App.token);
                        map.put("key_word", String.valueOf(s));
                        mPresenter.getSeachList(map);
                    }
                }else{
                    if (s.length()>0){
                        HashMap<String,String> map=new HashMap<>();
                        map.put("token", App.token);
                        map.put("page","1");
                        map.put("page_size","100");
                        map.put("calculation_date",calculation_date);
                        map.put("key_word",String.valueOf(s));
                        if (type == 1){
                            mPresenter.getListTwo(map);
                        }else if(type == 2){
                            mPresenter.getDetailTwo(map);
                        }

                    }

                }

            }
        });
        recycleview = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);




        if (type == 0){
            adapter =new GuanliSeachAdapter(this,listBeans);
            recycleview.setAdapter(adapter);
        }else if(type == 1){
            adaptertwo = new SalaryListAdapter(this,list);
            recycleview.setAdapter(adaptertwo);
        }else if (type == 2){
            yjadapter = new YongjinAdapter(this,yjlist);
            recycleview.setAdapter(yjadapter);
        }

        jsonObject = App.jsonObject;
        smartRefreshLayout =findViewById(R.id.smartrefresh);
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String s=editview.getText().toString().trim();
                if (s == null||s.equals("")){
                    showError("请输入关键字快速搜索");
                }else {
                    if (type == 0){
                        HashMap<String,String> map=new HashMap<>();
                        map.put("token", App.token);
                        map.put("key_word",s);
                        mPresenter.getSeachList(map);
                    }else {
                        if (type == 1){
                            adaptertwo.remove();
                            HashMap<String,String> map=new HashMap<>();
                            map.put("token", App.token);
                            map.put("page","1");
                            map.put("page_size","100");
                            map.put("calculation_date",calculation_date);
                            map.put("key_word",s);
                            mPresenter.getListTwo(map);
                        }else if (type == 2){
                            yjadapter.remove();
                            HashMap<String,String> map=new HashMap<>();
                            map.put("token", App.token);
                            map.put("page","1");
                            map.put("page_size","100");
                            map.put("calculation_date",calculation_date);
                            map.put("key_word",s);
                            mPresenter.getDetailTwo(map);
                        }

                    }

                }
                smartRefreshLayout.finishRefresh();
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
        try {
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            if (type == 1){
                list = gson.fromJson(jsonArray.toString(), new TypeToken<List<XinziBean>>() {
                }.getType());
                adaptertwo.updateList(list);
                if (jsonArray.length()>0){
                    ll_zhanwu.setVisibility(View.GONE);
                    recycleview.setVisibility(View.VISIBLE);
                }else {
                    ll_zhanwu.setVisibility(View.VISIBLE);
                    recycleview.setVisibility(View.GONE);
                }
            }else {
                yjlist = gson.fromJson(jsonArray.toString(), new TypeToken<List<YongjinListBean>>() {
                }.getType());
                yjadapter.updateList(yjlist);
                if (jsonArray.length()>0){
                    ll_zhanwu.setVisibility(View.GONE);
                    recycleview.setVisibility(View.VISIBLE);
                }else {
                    ll_zhanwu.setVisibility(View.VISIBLE);
                    recycleview.setVisibility(View.GONE);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
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
        try {
            JSONArray jsonArray=new JSONArray(gson.toJson(o.getData()));
            listBeans = gson.fromJson(jsonArray.toString(), new TypeToken<List<GuanliListBean>>() {
            }.getType());
            adapter.updateList(listBeans);
            if (jsonArray.length()>0){
                ll_zhanwu.setVisibility(View.GONE);
                recycleview.setVisibility(View.VISIBLE);
            }else {
                ll_zhanwu.setVisibility(View.VISIBLE);
                recycleview.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
