package com.yhkhgl.top.ui.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.ProductListBean;
import com.yhkhgl.top.ui.presenters.SearchPresent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresent> implements MainView {
    EditText editview;
    List<ProductListBean> list = new ArrayList<>();
//    ProductListAdapter adapter;//和产品列表共用一个adapter
    RecyclerView recycleview;
    ImageView clear_iv;
    TextView fanhui_tv;
    SmartRefreshLayout smartrefresh;
    @Override
    protected SearchPresent createPresenter() {
        return new SearchPresent(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        smartrefresh=findViewById(R.id.smartrefresh);
        smartrefresh.setEnableLoadMore(false);
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String s = editview.getText().toString();
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                if (s == null || s.equals("")) {
                    showError("请输入搜索内容");
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("token", App.token);
                    map.put("keyword",s);
                    mPresenter.SetSearch(map);
                }
                smartrefresh.finishRefresh();
            }
        });
        clear_iv=findViewById(R.id.clear_iv);
        fanhui_tv=findViewById(R.id.fanhui_tv);
        fixTitlePadding(findViewById(R.id.ll_title));
        editview = findViewById(R.id.editview);
        showSoftInputFromWindow(this,editview);

        editview.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String s = editview.getText().toString();
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                if (s == null || s.equals("")) {
                    showError("请输入搜索内容");
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("token", App.token);
                    map.put("keyword",s);
                    mPresenter.SetSearch(map);
                }
                return false;
            }
        });
        recycleview = findViewById(R.id.recycleview);
//        adapter = new ProductListAdapter(this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);
//        recycleview.setAdapter(adapter);

        clear_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editview.setText(null);
//                adapter.remove();
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
        });
        fanhui_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        try {
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            list = gson.fromJson(jsonArray.toString(), new TypeToken<List<ProductListBean>>() {
            }.getType());
            if (list.size()>0){
                recycleview.setVisibility(View.VISIBLE);
//                adapter.remove();
//                adapter.updateList(list, true);
            }else {
                recycleview.setVisibility(View.GONE);
                showError("没有匹配产品");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

}
