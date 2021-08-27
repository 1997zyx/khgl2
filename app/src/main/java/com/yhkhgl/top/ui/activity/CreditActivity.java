package com.yhkhgl.top.ui.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.reflect.TypeToken;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.CityBean;
import com.yhkhgl.top.bean.CreditBean;
import com.yhkhgl.top.ui.adapter.CreditAdapter;
import com.yhkhgl.top.ui.presenters.CreditPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreditActivity extends BaseActivity <CreditPresenter> implements MainView , View.OnClickListener {
    LinearLayout city_ll,area_ll;
    List<CityBean> cityBeans;
    List<String> area_id=new ArrayList<>();
    List<String> area_name=new ArrayList<>();
   public String city_id="39",region_id="";//全部
    OptionsPickerView cityTypePickerView,areaTypePickerView;
    int page = 1;
    TextView tv_city,tv_qu;
    List<CreditBean> list=new ArrayList<>();
    CreditAdapter adapter;
    RecyclerView recycleview;
    SmartRefreshLayout smartrefresh;

    @Override
    protected CreditPresenter createPresenter() {
        return new CreditPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_credit;
    }

    @Override
    protected void initView() {
        ImageView iv_fanhui=findViewById(R.id.iv_fanhui);
        iv_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fixTitlePadding(findViewById(R.id.ll_title));
        initSelectPickerViews();
        TextView tv_bottom=findViewById(R.id.tv_bottom);
        tv_bottom.setSelected(true);
//        tv_list_title=findViewById(R.id.tv_list_title);
        city_ll=findViewById(R.id.city_ll);
        city_ll.setOnClickListener(this);
        area_ll=findViewById(R.id.area_ll);
        area_ll.setOnClickListener(this);
        tv_city=findViewById(R.id.tv_city);
        tv_qu=findViewById(R.id.tv_qu);
        adapter=new CreditAdapter(this,list,this);
        recycleview=findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(adapter);
        smartrefresh=findViewById(R.id.smartrefresh);
        smartrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                smartrefresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                adapter.remove();
                initData();
                smartrefresh.finishRefresh();
            }
        });
    }

    @Override
    protected void initData() {
//        if (region_id.equals("")){
//            tv_list_title.setVisibility(View.INVISIBLE);
//        }else {
//            tv_list_title.setVisibility(View.VISIBLE);
//            tv_list_title.setText(tv_qu.getText().toString());
//        }
        HashMap<String,String> map=new HashMap<>();
        map.put("longitude", App.getLongitude);
        map.put("latitude",App.getLatitude);
        map.put("page",page+"");
        map.put("page_size","10");
        map.put("name","");
        map.put("p_c_id",city_id);
        map.put("region_id",region_id);
        mPresenter.getList(map);
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
        if(area_name!=null){
            area_name.clear();
            area_id.clear();
        }
        if (i==0){
            try {
                JSONArray jsonArray=new JSONArray(gson.toJson(o.getData()));
                cityBeans =gson.fromJson(jsonArray.toString(),new TypeToken<List<CityBean>>(){
                }.getType());
                for (int j=0;j<cityBeans.size();j++){
                    area_name.add(cityBeans.get(j).getArea_name());
                    area_id.add(cityBeans.get(j).getArea_id());
                }
                cityTypePickerView.setPicker(area_name);
                cityTypePickerView.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (i == 1){
            JSONArray jsonArray= null;
            try {
                jsonArray = new JSONArray(gson.toJson(o.getData()));
                cityBeans =gson.fromJson(jsonArray.toString(),new TypeToken<List<CityBean>>(){
                }.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int j=0;j<cityBeans.size();j++){
                area_name.add(cityBeans.get(j).getArea_name());
                area_id.add(cityBeans.get(j).getArea_id());
            }
            areaTypePickerView.setPicker(area_name);
            areaTypePickerView.show();

        }
    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {

    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {

        try {
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            list =gson.fromJson(jsonArray.toString(),new TypeToken<List<CreditBean>>(){
            }.getType());
            if (jsonArray.length()<10){
                if (jsonArray.length()>0){
                    page=page+1;
                    adapter.updateList(list);
                }else {
                    showError("没有更多了！");
                }
            }else {
                page=page+1;
                adapter.updateList(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.city_ll:
                mPresenter.getCity();
                break;
            case R.id.area_ll:
                mPresenter.getQu(city_id);


                break;
        }
    }
    public void initSelectPickerViews() {
        if (cityTypePickerView == null) {
            cityTypePickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
                city_id=area_id.get(options1);
                tv_city.setText(area_name.get(options1));
                region_id="";
                tv_qu.setText("全部");
                adapter.remove();
                page = 1;
                initData();


            })//设置选择第一个
                    .setSubmitColor(getResources().getColor(R.color.bg_85736))//确定按钮文字颜色
                    .setCancelColor(getResources().getColor(R.color.text_959595))//取消按钮文字颜色
                    .setTitleBgColor(getResources().getColor(R.color.colorWhite))//标题背景颜色 Night mode
                    .setContentTextSize(15) // 滚轮文字大小
                    .setSubCalSize(14) // 确定取消按钮文字大小
                    .setTitleSize(17)  // 标题文字大小
                    .setTitleText("请选择") // 标题文字
                    .setLineSpacingMultiplier(2f)//设置间距倍数,但是只能在1.2-2.0f之间
                    .setTitleColor(getResources().getColor(R.color.text_1b1b1b)) // 标题文字颜色
                    .setLabels("", "", "")
                    .build();
        }
        if (areaTypePickerView == null) {
            areaTypePickerView = new OptionsPickerView.Builder(this, (int options1, int option2, int options3, View view) -> {
                region_id=area_id.get(options1);
                tv_qu.setText(area_name.get(options1));
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
                    .setTitleText("请选择") // 标题文字
                    .setLineSpacingMultiplier(2f)//设置间距倍数,但是只能在1.2-2.0f之间
                    .setTitleColor(getResources().getColor(R.color.text_1b1b1b)) // 标题文字颜色
                    .setLabels("", "", "")
                    .build();
        }
    }
}
