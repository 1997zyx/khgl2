package com.yhkhgl.top.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
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
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;
import com.yhkhgl.top.activity.MainBean;
import com.yhkhgl.top.activity.MainView;
import com.yhkhgl.top.base.BaseActivity;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.bean.BaseCallBackBean;
import com.yhkhgl.top.bean.GualiDetailBean;
import com.yhkhgl.top.bean.YongjinListBean;
import com.yhkhgl.top.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.yhkhgl.top.ui.adapter.GuanliDetailAdapter;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;
import com.yhkhgl.top.utils.BottomDialogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GuanliDetailActivity extends BaseActivity<GuanLiPresent> implements MainView, View.OnClickListener {
    GuanliDetailAdapter adapter;
    RecyclerView recycleview;
    List<GualiDetailBean> list = new ArrayList<>();
    HeaderAndFooterWrapper headerAndFooterWrapper;
    View view;
    SmartRefreshLayout smartrefresh;
    TextView name_tv, mobile, qudao_tv, dengji_tv, zhengxin_tv, time_tv, ywlx_tv, mobile_tv, tv_need, tv_shouru, tv_chengjiao, tv_hangye, tv_house, tv_cae, tv_vx, tv_card, tv_s_date, tv_visit_time, tv_next_date;
    LinearLayout ll_bianji, add_genjin;
    JSONObject jsonObject, json;
    int page = 1;
    ImageView iv_phone;
    Button hs_btn;
    LinearLayout ll_phone;
    String s_phone, s_content, content;
    Dialog dialog;
    LinearLayout ll_zhanwu;
    TextView follow_tv, tv_yongjin, tv_fangan;
    ImageView zhiding_iv;
    LinearLayout ll_chengjiao;
    List<YongjinListBean> yjlist = new ArrayList<>();
    int type;
    String belong_id;
    LinearLayout ll_cjjl, ll_gjjl;
    TextView tv_gj, tv_cj;
    View view_one, view_two;

    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guanli_detail;
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
        json = App.jsonObject;
//        String s = getIntent().getStringExtra("jsonObject");
//        try {
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        zhiding_iv = findViewById(R.id.zhiding_iv);
        zhiding_iv.setOnClickListener(this);
        ll_phone = findViewById(R.id.ll_phone);
        ll_phone.setOnClickListener(this);
        hs_btn = findViewById(R.id.hs_btn);
        hs_btn.setOnClickListener(this);
        fixTitlePadding(findViewById(R.id.rl_title));
        view = View.inflate(this, R.layout.guanli_detail_header, null);
        ll_cjjl = view.findViewById(R.id.ll_cjjl);
        ll_cjjl.setOnClickListener(this);
        ll_gjjl = view.findViewById(R.id.ll_gjjl);
        ll_gjjl.setOnClickListener(this);
        tv_gj = view.findViewById(R.id.tv_gj);
        tv_cj = view.findViewById(R.id.tv_cj);
        view_one = view.findViewById(R.id.view_one);
        view_two = view.findViewById(R.id.view_two);
        ll_chengjiao = view.findViewById(R.id.ll_chengjiao);
        tv_fangan = view.findViewById(R.id.tv_fangan);
        tv_yongjin = view.findViewById(R.id.tv_yongjin);
        follow_tv = view.findViewById(R.id.follow_tv);
        ll_zhanwu = view.findViewById(R.id.ll_zhanwu);
        iv_phone = view.findViewById(R.id.iv_phone);
        iv_phone.setOnClickListener(this);
        name_tv = view.findViewById(R.id.name_tv);
        mobile = view.findViewById(R.id.mobile);
        qudao_tv = view.findViewById(R.id.qudao_tv);
        dengji_tv = view.findViewById(R.id.dengji_tv);
        zhengxin_tv = view.findViewById(R.id.zhengxin_tv);
        time_tv = view.findViewById(R.id.time_tv);
        ywlx_tv = view.findViewById(R.id.ywlx_tv);
        mobile_tv = view.findViewById(R.id.mobile_tv);
        tv_need = view.findViewById(R.id.tv_need);
        tv_shouru = view.findViewById(R.id.tv_shouru);
        tv_chengjiao = view.findViewById(R.id.tv_chengjiao);
        tv_hangye = view.findViewById(R.id.tv_hangye);
        tv_house = view.findViewById(R.id.tv_house);
        tv_cae = view.findViewById(R.id.tv_cae);
        tv_vx = view.findViewById(R.id.tv_vx);
        tv_card = view.findViewById(R.id.tv_card);
        tv_s_date = view.findViewById(R.id.tv_s_date);
        tv_visit_time = view.findViewById(R.id.tv_visit_time);
        tv_next_date = view.findViewById(R.id.tv_next_date);
        ll_bianji = findViewById(R.id.ll_bianji);
        ll_bianji.setOnClickListener(this);
        add_genjin = findViewById(R.id.add_genjin);
        add_genjin.setOnClickListener(this);
        recycleview = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);
        adapter = new GuanliDetailAdapter(this, list,0);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addHeaderView(view);
        recycleview.setAdapter(headerAndFooterWrapper);
        smartrefresh = findViewById(R.id.smartrefresh);
        smartrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                smartrefresh.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.remove();
                page = 1;
                initData();
                smartrefresh.finishRefresh();
            }
        });
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("cus_id", getIntent().getStringExtra("id"));
        mPresenter.getDetail(map);
    }

    @Override
    protected void initData() {
        mPresenter.getHuaShu();
        if (type == 0) {
            HashMap<String, String> map = new HashMap<>();
            map.put("token", App.token);
            map.put("cus_id", getIntent().getStringExtra("id"));
            map.put("page", page + "");
            map.put("page_size", "5");
            mPresenter.getDetailList(map);
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("token", App.token);
            map.put("id", getIntent().getStringExtra("id"));
            map.put("page", page + "");
            map.put("page_size", "5");
            map.put("belong_id", belong_id);
            mPresenter.getDetailListTwo(map);
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
        content = s_content;
        showError("提交成功");
        dialog.dismiss();
    }

    @Override
    public void onTableListSuccess(BaseModel<Object> o) {
        try {
            if (type == 0) {
                JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
                list = gson.fromJson(jsonArray.toString(), new TypeToken<List<GualiDetailBean>>() {
                }.getType());
                if (jsonArray.length() < 5) {
                    if (jsonArray.length() > 0) {
                        ll_zhanwu.setVisibility(View.GONE);
                        page = page + 1;
                        adapter.updateList(list,0);
                    } else {
                        if (page == 1) {
                            ll_zhanwu.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    ll_zhanwu.setVisibility(View.GONE);
                    page = page + 1;
                    adapter.updateList(list,0);
                }
                headerAndFooterWrapper.notifyDataSetChanged();
            } else {
                JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
                list = gson.fromJson(jsonArray.toString(), new TypeToken<List<GualiDetailBean>>() {
                }.getType());
                if (jsonArray.length() < 5) {
                    if (jsonArray.length() > 0) {
                        ll_zhanwu.setVisibility(View.GONE);
                        page = page + 1;
                        adapter.updateList(list,1);
                    } else {
                        if (page == 1) {
                            ll_zhanwu.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    ll_zhanwu.setVisibility(View.GONE);
                    page = page + 1;
                    adapter.updateList(list,1);
                }
                headerAndFooterWrapper.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(BaseModel<Object> o, int i) {
        showError("删除成功");
        adapter.removeData(i);
        headerAndFooterWrapper.notifyItemRemoved(i + 1);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("cus_id", getIntent().getStringExtra("id"));
        map.put("page", adapter.num + "");
        map.put("belong_id", belong_id);
        map.put("page_size", "1");
        mPresenter.getDetailList(map);
//        adapter.notifyItemChanged(0);
    }

    @Override
    public void onRestrictionsSuccess(BaseModel<Object> o) {
        try {
            JSONObject jsonObject = new JSONObject(gson.toJson(o.getData()));
            content = jsonObject.optString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheShiSuccess(BaseModel<Object> o) {
        try {
            jsonObject = new JSONObject(gson.toJson(o.getData()));
            String follow_status = jsonObject.optString("follow_status");
            follow_tv.setText(follow_status);
            switch (follow_status) {
                case "成交":
                    ll_chengjiao.setVisibility(View.VISIBLE);
                    break;
                case "重点":
                case "办理中":
                    ll_chengjiao.setVisibility(View.GONE);
                    follow_tv.setTextColor(getResources().getColor(R.color.fe60012));
                    break;
                default:
                    ll_chengjiao.setVisibility(View.GONE);
                    follow_tv.setTextColor(getResources().getColor(R.color.text_1b1b1b));
                    break;
            }
            String surname = jsonObject.optString("surname");
            name_tv.setText(surname);
            s_phone = jsonObject.optString("mobile");
            mobile.setText(s_phone);
            belong_id = jsonObject.optString("belong_id");
            mobile_tv.setText(s_phone);
            String channel = jsonObject.optString("channel");
            qudao_tv.setText(channel);
            String key_categories = jsonObject.optString("key_categories");
            dengji_tv.setText(key_categories);
            String credit_inquiry = jsonObject.optString("credit_inquiry");
            zhengxin_tv.setText(credit_inquiry);
            String next_s_date = jsonObject.optString("next_s_date");
            if (next_s_date == null || next_s_date.equals("")) {
                time_tv.setText("下次跟进时间:暂无");
                tv_next_date.setText("--");
                time_tv.setTextColor(getResources().getColor(R.color.ff215dff));
            } else {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String str = formatter.format(curDate);
                int date = Integer.parseInt(next_s_date.replaceAll("-", ""));
                int jindate = Integer.parseInt(str.replaceAll("-", ""));
                if (date < jindate) {
                    time_tv.setTextColor(getResources().getColor(R.color.fe60012));
                    time_tv.setText("下次跟进时间:" + next_s_date);
                } else if (date == jindate) {
                    time_tv.setTextColor(getResources().getColor(R.color.fe60012));
                    time_tv.setText("下次跟进时间:今天");
                } else {
                    time_tv.setTextColor(getResources().getColor(R.color.ff215dff));
                    time_tv.setText("下次跟进时间:" + next_s_date);
                }
                tv_next_date.setText(next_s_date);
            }
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

            String s_date = jsonObject.optString("s_date");
            tv_s_date.setText(setText(s_date));
            String visit_time = jsonObject.optString("visit_time");
            tv_visit_time.setText(setText(visit_time));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_bianji:
                if (jsonObject != null) {
                    Intent intent = new Intent(this, AddGuanliActivity.class);
                    intent.putExtra("info", jsonObject.toString());
                    intent.putExtra("jsonObject", json.toString());
                    startActivityForResult(intent, 1001);
                }
                break;
            case R.id.add_genjin:
                Intent intent = new Intent(this, AddGenjinActivity.class);
                intent.putExtra("position", 0);
                if (adapter.copy != null) {
                    intent.putExtra("copy", adapter.copy);
                }
                intent.putExtra("cus_id", getIntent().getStringExtra("id"));
                startActivityForResult(intent, 1);
                break;
            case R.id.iv_phone:
            case R.id.ll_phone:
                if (s_phone == null || s_phone.equals("")) {
                    showError("没有电话或电话号码有误");
                } else {
                    //创建打电话的意图
                    Intent phone = new Intent();
                    //设置拨打电话的动作
                    phone.setAction(Intent.ACTION_DIAL);
                    //设置拨打电话的号码
                    phone.setData(Uri.parse("tel:" + s_phone));
                    //开启打电话的意图
                    startActivity(phone);
                }
                break;
            case R.id.hs_btn:
                if (content == null || content.equals("")) {
                    return;
                }
                View view = View.inflate(this, R.layout.huashu_dialog, null);
                final EditText et_huaxhu = view.findViewById(R.id.et_huaxhu);
                et_huaxhu.setText(content);
                et_huaxhu.setFocusable(false);
                et_huaxhu.setFocusableInTouchMode(false);
                if (dialog == null) {
                    dialog = new Dialog(this, R.style.BottomDialogStyle);
                }

                ImageView iv_close = view.findViewById(R.id.iv_close);
                iv_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Button btn_true = view.findViewById(R.id.btn_true);
                btn_true.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btn_true.getText().toString().trim().equals("编辑话术")) {
                            btn_true.setText("保存");
                            et_huaxhu.setFocusableInTouchMode(true);
                            et_huaxhu.setFocusable(true);
                            et_huaxhu.requestFocus();
                        } else {
                            String s = et_huaxhu.getText().toString().trim();
                            if (s == null || s.equals("")) {
                                showError("话术不能为空");
                                return;
                            }
                            s_content = s;
                            showLoadingDialog("提交中...");
                            HashMap<String, String> map = new HashMap<>();
                            map.put("token", App.token);
                            map.put("content", s);
                            mPresenter.setHuaShu(map);
                        }

                    }
                });
                BottomDialogUtil.Dia(dialog, this, view);
                break;
            case R.id.zhiding_iv:
                recycleview.smoothScrollToPosition(0);
                break;
            case R.id.ll_gjjl:
                tv_gj.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                tv_cj.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                view_one.setVisibility(View.VISIBLE);
                view_two.setVisibility(View.INVISIBLE);
                adapter.remove();
                type = 0;
                adapter.type = 1;
                page = 1;
                initData();
                break;
            case R.id.ll_cjjl:
                tv_gj.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                tv_cj.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                view_one.setVisibility(View.INVISIBLE);
                view_two.setVisibility(View.VISIBLE);
                adapter.remove();
                adapter.type = 1;
                type = 1;

                page = 1;
                initData();
                break;

        }
    }

    public void setDetail(String id, int i) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("id", id);
        mPresenter.setDetailcus(map, i);
    }

    public void gengxin(int i) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("cus_id", getIntent().getStringExtra("id"));
        map.put("page", i + "");
        map.put("page_size", "1");
        mPresenter.getDetailList(map);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        page = 1;
        adapter.remove();
        initData();
        if (requestCode == 1001 && resultCode == 1001) {
            HashMap<String, String> map = new HashMap<>();
            map.put("token", App.token);
            map.put("cus_id", getIntent().getStringExtra("id"));
            mPresenter.getDetail(map);
            page = 1;
            adapter.remove();
            initData();
        }
//        if (requestCode == 1 && resultCode == 100) {
//            adapter.remove();
//            page = 1;
//            initData();
//        }
//        if (requestCode == 2 && resultCode == 100) { adapter.remove();
//            page = 1;
//            initData();
//
////            adapter.gengxin((GualiDetailBean) data.getSerializableExtra("bean"));
////            headerAndFooterWrapper.notifyDataSetChanged();
//        }
    }
}
