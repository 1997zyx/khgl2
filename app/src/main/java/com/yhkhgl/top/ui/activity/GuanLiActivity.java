package com.yhkhgl.top.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
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
import com.yhkhgl.top.bean.GuanliListBean;
import com.yhkhgl.top.bean.GuanliTitleBean;
import com.yhkhgl.top.ui.phone.BackService;
import com.yhkhgl.top.ui.phone.PhoneBean;
import com.yhkhgl.top.ui.adapter.GuanliAdapter;
import com.yhkhgl.top.ui.phone.TelListener;
import com.yhkhgl.top.ui.phone.WifiBroad;
import com.yhkhgl.top.ui.presenters.GuanLiPresent;
import com.yhkhgl.top.ui.phone.TelPhonelistener;
import com.yhkhgl.top.utils.BottomDialogUtil;
import com.yhkhgl.top.utils.Diputil;
import com.yhkhgl.top.utils.FlowLayout;
import com.yhkhgl.top.utils.PopWinDownUtil;
import com.yhkhgl.top.utils.SharePerferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;
import static java.lang.Thread.sleep;

public class GuanLiActivity extends BaseActivity<GuanLiPresent> implements MainView, View.OnClickListener, TelPhonelistener {
    //拨号请求码
    public static final int REQUEST_CALL_PERMISSION = 10111;
    LinearLayout dengji_ll, title, ll_zhuangtai, ll_leixing, ll_saixuan;
    PopWinDownUtil popWinDownUtil, popWinDownUtil2, popWinDownUtil3, popWinDownUtil4;
    DrawerLayout view;
    RelativeLayout ll_content;
    View viewbg, view_one, view_two, view_three;
    TextView textView1, textView2, textView3;
    ImageView iv1, iv2, iv3;
    int page = 1;
    int type = 0;
    private long mExitTime;
    RelativeLayout btn_saixuan;
    List<GuanliTitleBean> listKeHu = new ArrayList<>();
    List<GuanliTitleBean> listZhuangTai = new ArrayList<>();
    List<GuanliTitleBean> listType = new ArrayList<>();
    List<GuanliTitleBean> listQuDao = new ArrayList<>();
    List<String> keku_id = new ArrayList<>();
    List<String> zhuangtai_id = new ArrayList<>();
    List<String> type_id = new ArrayList<>();
    List<String> qudao_id = new ArrayList<>();
    List<CheckBox> checkBoxList = new ArrayList<>();
    HashMap<Integer, List> hashMap = new HashMap<>();
    Button btn_clear, btn_true;
    List<GuanliListBean> listBeans = new ArrayList<>();
    GuanliAdapter adapter;
    SmartRefreshLayout smartRefreshLayout;
    ImageView add_iv;
    public JSONObject jsonObject = null;
    TextView tv_quanbu, tv_daoqi, tv_chengjiao, tv_num, tv_lianjie;
    int content_type = 0;
    View view_bg, viewbgall;
    String user_avatar;
    ImageView iv_avatar, iv_avatar_two;
    RelativeLayout info_rl, zhanghu_rl, lxwm_rl, gywm_rl, clear_rl, rl_left, rl_seach, out_rl;
    RecyclerView recycleview;
    LinearLayout ll_lianjie;
    private AnimationDrawable anim;
    Intent bascintent, jtphone;
    boolean isOpenservice;
    private static final int MIN_CLICK_DELAY_TIME = 17 * 50;
    private static long lastClickTime;

    TelephonyManager manager;
    TelListener telListener;
    private final Timer timer = new Timer();
    private TimerTask task;
    public static PhoneBean phoneBean = new PhoneBean();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (isOpenservice == true) {
                    tv_lianjie.setTextSize(13);
                    tv_lianjie.setText("点击\n断开");
                    tv_lianjie.setTextColor(getResources().getColor(R.color.ff00adb6));
                } else {
                    tv_lianjie.setTextSize(13);
                    tv_lianjie.setText("点击\n连接");
                    tv_lianjie.setTextColor(getResources().getColor(R.color.ff2a70fd));

                }
            }

        }
    };

    @Override
    protected GuanLiPresent createPresenter() {
        return new GuanLiPresent(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guan_li;
    }

    @Override
    protected void initView() {
        WifiBroad wifiReceiver = new WifiBroad();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        this.registerReceiver(wifiReceiver, filter);


        App.guanLiActivity = this;
        tv_lianjie = findViewById(R.id.tv_lianjie);
        ll_lianjie = findViewById(R.id.ll_lianjie);
        ll_lianjie.setOnClickListener(this);
        out_rl = findViewById(R.id.out_rl);
        out_rl.setOnClickListener(this);
        rl_left = findViewById(R.id.rl_left);
        rl_left.setOnClickListener(this);
        info_rl = findViewById(R.id.info_rl);
        zhanghu_rl = findViewById(R.id.zhanghu_rl);
        lxwm_rl = findViewById(R.id.lxwm_rl);
        gywm_rl = findViewById(R.id.gywm_rl);
        clear_rl = findViewById(R.id.clear_rl);

        info_rl.setOnClickListener(this);
        zhanghu_rl.setOnClickListener(this);
        lxwm_rl.setOnClickListener(this);
        gywm_rl.setOnClickListener(this);
        clear_rl.setOnClickListener(this);
        rl_seach = findViewById(R.id.rl_seach);
        rl_seach.setOnClickListener(this);
        iv_avatar_two = findViewById(R.id.iv_avatar_two);
        iv_avatar_two.setOnClickListener(this);
        iv_avatar = findViewById(R.id.iv_avatar);
        viewbgall = findViewById(R.id.viewbgall);
        viewbgall.setOnClickListener(this);


        view_bg = findViewById(R.id.view_bg);
        tv_num = findViewById(R.id.tv_num);
        tv_chengjiao = findViewById(R.id.tv_chengjiao);
        tv_chengjiao.setOnClickListener(this);
        tv_daoqi = findViewById(R.id.tv_daoqi);
        tv_daoqi.setOnClickListener(this);
        tv_quanbu = findViewById(R.id.tv_quanbu);
        tv_quanbu.setOnClickListener(this);
        add_iv = findViewById(R.id.add_iv);
        add_iv.setOnClickListener(this);
        recycleview = findViewById(R.id.recycleview);
        btn_true = findViewById(R.id.btn_true);
        btn_true.setOnClickListener(this);
        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        view_one = findViewById(R.id.view_one);
        view_two = findViewById(R.id.view_two);
        view_three = findViewById(R.id.view_three);
        ll_content = findViewById(R.id.ll_content);
        view = findViewById(R.id.view);
        view.setOnClickListener(this);
        fixTitlePadding(findViewById(R.id.ll_title));
        dengji_ll = findViewById(R.id.dengji_ll);
        dengji_ll.setOnClickListener(this);
        title = findViewById(R.id.title);
        ll_zhuangtai = findViewById(R.id.ll_zhuangtai);
        ll_zhuangtai.setOnClickListener(this);
        viewbg = findViewById(R.id.viewbg);
        viewbg.setOnClickListener(this);
        viewbg.getBackground().setAlpha(100);
        ll_leixing = findViewById(R.id.ll_leixing);
        ll_leixing.setOnClickListener(this);
        ll_saixuan = findViewById(R.id.ll_saixuan);
        btn_saixuan = findViewById(R.id.btn_saixuan);
        btn_saixuan.setOnClickListener(this);
        adapter = new GuanliAdapter(this, listBeans);
        recycleview = findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(adapter);
        mPresenter.getSaixuan();
        smartRefreshLayout = findViewById(R.id.smartrefresh);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
                smartRefreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getSaixuan();
                adapter.remove();
                page = 1;
                initData();
                smartRefreshLayout.finishRefresh();
            }
        });

    }

    @Override
    protected void initData() {
        String kehu = String.valueOf(keku_id);
        String zhuangtai = String.valueOf(zhuangtai_id);
        String type = String.valueOf(type_id);
        String qudao = String.valueOf(qudao_id);
        String key_categories = remove_s(kehu);
        String follow_status = remove_s(zhuangtai);
        String cus_type = remove_s(type);
        String channel = remove_s(qudao);
        LogUtils.e(key_categories);
        LogUtils.e(follow_status);
        LogUtils.e(cus_type);
        LogUtils.e(channel);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", App.token);
        map.put("page", page + "");
        map.put("page_size", "10");
        map.put("type", content_type + "");
        map.put("key_categories", key_categories);//客户等级
        map.put("follow_status", follow_status);//状态
        map.put("cus_type", cus_type);//业务类型
        map.put("channel", channel);
        mPresenter.getList(map);
        mPresenter.getInfo();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.out_rl:
                final Dialog dialog = new Dialog(this, R.style.BottomDialogStyle);
                View diaview = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
                TextView title_tv2 = diaview.findViewById(R.id.title_tv);
                title_tv2.setText("您确定要退出登录吗？");
                TextView tv_false2 = diaview.findViewById(R.id.tv_false);
                tv_false2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                TextView tv_tru2e = diaview.findViewById(R.id.tv_true);
                tv_tru2e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        App.token = "";
                        SharePerferenceUtil.saveValue(App.getContext(), "userdata", "token", "");
                        App.finishAll();
                        Intent intent = new Intent(GuanLiActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                BottomDialogUtil.DiaCenter(dialog, this, diaview);

                break;
            case R.id.rl_left:
                break;
            case R.id.info_rl:
                startActivity(InfoDetailActivity.class);
                break;
            case R.id.zhanghu_rl:
                startActivity(ZhanghuActivity.class);
                break;
            case R.id.lxwm_rl:
                startActivity(SuggestActivity.class);
                break;
            case R.id.gywm_rl:
                startActivity(AboutAsActivity.class);
                break;
            case R.id.clear_rl:
                break;
            case R.id.ll_add:
                Intent intent = new Intent(this, AddGuanliActivity.class);
                intent.putExtra("jsonObject", String.valueOf(jsonObject));
                startActivity(intent);
                if (popWinDownUtil4 != null) {
                    popWinDownUtil4.dismiss();
                    viewbgall.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_yongjin:
                Intent intent1 = new Intent(GuanLiActivity.this, SalaryListActivity.class);
                intent1.putExtra("type", 2);
                startActivity(intent1);
                popWinDownUtil4.dismiss();
                break;
            case R.id.ll_xinzi:
                //1 服务商管理员  0服务商员工
                if (SharePerferenceUtil.getStringValue(this, "userdata", "service_admin").equals("0")) {
                    Intent xinzi = new Intent(GuanLiActivity.this, SalaryActivity.class);
                    xinzi.putExtra("service_user_id", "");
                    xinzi.putExtra("calculation_date", "");
                    startActivity(xinzi);
                    //                 startActivity(SalaryActivity.class);
                    popWinDownUtil4.dismiss();
                } else {
                    startActivity(SalaryListActivity.class);
                    popWinDownUtil4.dismiss();
                }

                break;
            case R.id.viewbgall:
                if (popWinDownUtil4 != null) {
                    popWinDownUtil4.dismiss();
                    viewbgall.setVisibility(View.GONE);
                }
                type = 0;
                setView();
                break;
            case R.id.dengji_ll:
                type = 1;
                if (popWinDownUtil == null) {
                    popWinDownUtil = new PopWinDownUtil(this, setDataView(), title, viewbg);
                } else {
                    if (popWinDownUtil.isShowing()) {
                        type = 0;
                    }
                }
                setView();
                break;
            case R.id.ll_zhuangtai:
                if (content_type == 2) {
                    showError("已是成交状态，不可选择");
                    return;
                }
                type = 2;
                if (popWinDownUtil2 == null) {
                    popWinDownUtil2 = new PopWinDownUtil(this, setDataView(), title, viewbg);
                } else {
                    if (popWinDownUtil2.isShowing()) {
                        type = 0;
                    }
                }
                setView();
                break;
            case R.id.ll_leixing:
                type = 3;
                if (popWinDownUtil3 == null) {
                    popWinDownUtil3 = new PopWinDownUtil(this, setDataView(), title, viewbg);
                } else {
                    if (popWinDownUtil3.isShowing()) {
                        type = 0;
                    }
                }
                setView();
                break;
            case R.id.btn_saixuan:
                type = 0;
                setSaiXuanView();
                setView();
                view.openDrawer(Gravity.RIGHT);

                break;
            case R.id.iv_avatar_two:
                type = 0;
//                setSaiXuanView();
                setView();
                view.openDrawer(Gravity.LEFT);
                break;
            case R.id.btn_clear:
                for (int i = 0; i < checkBoxList.size(); i++) {
                    checkBoxList.get(i).setChecked(false);
                }
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        keku_id.clear();
                        keku_id.addAll(hashMap.get(0));
                    }
                    if (i == 1) {
                        zhuangtai_id.clear();
                        zhuangtai_id.addAll(hashMap.get(1));
                    }
                    if (i == 2) {
                        type_id.clear();
                        type_id.addAll(hashMap.get(2));
                    }
                    if (i == 3) {
                        qudao_id.clear();
                        qudao_id.addAll(hashMap.get(3));
                    }
                }
                titleView();
                setView();
                adapter.remove();
                page = 1;
                initData();
                break;
            case R.id.btn_true:
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        keku_id.clear();
                        keku_id.addAll(hashMap.get(0));
                    }
                    if (i == 1) {
                        zhuangtai_id.clear();
                        zhuangtai_id.addAll(hashMap.get(1));
                    }
                    if (i == 2) {
                        type_id.clear();
                        type_id.addAll(hashMap.get(2));
                    }
                    if (i == 3) {
                        qudao_id.clear();
                        qudao_id.addAll(hashMap.get(3));
                    }
                }
                titleView();
                view.closeDrawers();
                setView();
                adapter.remove();
                page = 1;
                initData();
                break;
            case R.id.add_iv:
                pop();
                type = 0;
                setView();
//                Intent add_=new Intent(this,AddGuanliActivity.class);
//                add_.putExtra("jsonObject", String.valueOf(jsonObject));
//                startActivity(add_);
                break;
            case R.id.rl_seach:
                if (jsonObject != null) {
                    Intent seach = new Intent(this, GuanliSeachActivity.class);
                    seach.putExtra("jsonObject", jsonObject.toString());
                    startActivity(seach);
                }

                break;

            case R.id.tv_quanbu:
                if (content_type == 0) {
                    return;
                }
                view_bg.animate().translationX(0).setDuration(100);
                content_type = 0;
                tv_quanbu.setTextColor(getResources().getColor(R.color.colorWhite));
                tv_daoqi.setTextColor(getResources().getColor(R.color.ff215dff));
                tv_chengjiao.setTextColor(getResources().getColor(R.color.ff215dff));
                for (int i = 0; i < checkBoxList.size(); i++) {
                    checkBoxList.get(i).setChecked(false);
                }
                keku_id.clear();
                zhuangtai_id.clear();
                type_id.clear();
                qudao_id.clear();
                titleView();
                setView();
                adapter.remove();
                page = 1;
                initData();
                break;
            case R.id.tv_daoqi:
                if (content_type == 1) {
                    return;
                }
                int width = (getScreenWidth(this) - Diputil.dip2px(this, 14)) / 3;
                view_bg.animate().translationX(width).setDuration(100);
                content_type = 1;
                tv_daoqi.setTextColor(getResources().getColor(R.color.colorWhite));
                tv_quanbu.setTextColor(getResources().getColor(R.color.ff215dff));
                tv_chengjiao.setTextColor(getResources().getColor(R.color.ff215dff));
                for (int i = 0; i < checkBoxList.size(); i++) {
                    checkBoxList.get(i).setChecked(false);
                }
                keku_id.clear();
                zhuangtai_id.clear();
                type_id.clear();
                qudao_id.clear();
                titleView();
                setView();
                adapter.remove();
                page = 1;
                initData();
                break;
            case R.id.tv_chengjiao:
                if (content_type == 2) {
                    return;
                }
                int width1 = (getScreenWidth(this) - Diputil.dip2px(this, 14)) / 3;
                view_bg.animate().translationX(width1 * 2).setDuration(100);
                content_type = 2;
                tv_chengjiao.setTextColor(getResources().getColor(R.color.colorWhite));
                tv_quanbu.setTextColor(getResources().getColor(R.color.ff215dff));
                tv_daoqi.setTextColor(getResources().getColor(R.color.ff215dff));
                for (int i = 0; i < checkBoxList.size(); i++) {
                    checkBoxList.get(i).setChecked(false);
                }
                keku_id.clear();
                zhuangtai_id.clear();
                type_id.clear();
                qudao_id.clear();
                titleView();
                setView();
                adapter.remove();
                page = 1;
                initData();
                break;
            case R.id.ll_lianjie:
//                //创建消息处理器，送的消息进行处理，更新主界面元素（标签）的内容
//                handler = new Handler(){
//                    public void handleMessage(Message msg){
//                        super.handleMessage(msg);
//                        if(msg.what ==0x0001){
//                            tvTime.setText(sdf.format(new Date()));
//
//                        }
//                    }
//                };
                if (App.is_telphone == 1) {
                    showError("通话中，不可断开");
                    return;
                }
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //首先判断否获取了权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                        //让用户手动授权
                        showError("请授权");
                        Intent phone = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        phone.setData(uri);
                        startActivity(phone);
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                } else {
                    if (App.isWifiActive(this)) {
                        if (isFastClick()) {
                            setLianjie();
                        }
                    } else {
                        showError("请在wifi下使用");
                    }


                }


                break;
            default:
                type = 0;
                setView();
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
        JSONObject jsonObject = null;
        try {
            hideLoading();
            JSONObject extend = new JSONObject(gson.toJson(o.getExtend()));
            jsonObject = new JSONObject(gson.toJson(o.getData()));

            Log.e("----------", jsonObject + "");
            int service_id = jsonObject.optInt("service_id");
            user_avatar = jsonObject.optString("user_avatar");
            Glide.with(this).load(user_avatar).into(iv_avatar);
            Glide.with(this).load(user_avatar).into(iv_avatar_two);
            if (service_id != 0) {
                SharePerferenceUtil.saveValue(GuanLiActivity.this, "userdata", "service_admin", App.service_admin);
            } else {
                showError("请联系管理官开通权限！");
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onTableListSuccess(BaseModel<Object> o) {

        try {
            jsonObject = new JSONObject(gson.toJson(o.getData()));
            App.jsonObject = new JSONObject(gson.toJson(o.getData()));
            JSONArray key_categories = jsonObject.optJSONArray("key_categories");//客户管理
            listKeHu = gson.fromJson(key_categories.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            JSONArray follow_status = jsonObject.optJSONArray("follow_status");//跟进状态
            listZhuangTai = gson.fromJson(follow_status.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            JSONArray cus_type = jsonObject.optJSONArray("cus_type");//业务类型
            listType = gson.fromJson(cus_type.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            JSONArray channel = jsonObject.optJSONArray("channel");//业务类型
            listQuDao = gson.fromJson(channel.toString(), new TypeToken<List<GuanliTitleBean>>() {
            }.getType());
            setSaiXuanView();

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
            JSONObject extend = new JSONObject(gson.toJson(o.getExtend()));

            int expire_num = extend.optInt("expire_num");
            if (expire_num > 0) {
                tv_num.setText(expire_num + "");
                tv_num.setVisibility(View.VISIBLE);
            } else {
                tv_num.setVisibility(View.GONE);
            }
            JSONArray jsonArray = new JSONArray(gson.toJson(o.getData()));
            listBeans = gson.fromJson(jsonArray.toString(), new TypeToken<List<GuanliListBean>>() {
            }.getType());
            if (jsonArray.length() <= 10) {
                if (jsonArray.length() > 0) {
                    page = page + 1;
                    adapter.updateList(listBeans);
                } else {
                    adapter.updateList(listBeans);
                    if (page == 1) {
                        showError("暂无数据！");
                    } else {
                        showError(hint);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceType")
    public View setDataView() {
        List<String> idlist = new ArrayList<>();
        List<GuanliTitleBean> bean = null;
        if (type == 1) {
            bean = listKeHu;
            idlist.addAll(keku_id);
        } else if (type == 2) {
            bean = listZhuangTai;
            idlist.addAll(zhuangtai_id);
        } else if (type == 3) {
            bean = listType;
            idlist.addAll(type_id);
        }
        View view1 = LayoutInflater.from(GuanLiActivity.this).inflate(R.layout.pop_dengji, null);
        FlowLayout ll1 = view1.findViewById(R.id.ll_dengji_content);
        for (int i = 0; i < bean.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setWidth(getScreenWidth(this) / 3);
            checkBox.setPadding(5, 5, 5, 5);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                checkBox.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            if (idlist.contains(bean.get(i).getKey())) {
                checkBox.setChecked(true);
            }
            checkBox.setButtonDrawable(null);
            checkBox.setText(bean.get(i).getValue());
            checkBox.setTextColor(getResources().getColorStateList(R.drawable.guanli_text_color));
            int finalI = i;
            List<GuanliTitleBean> finalBean = bean;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        idlist.add(finalBean.get(finalI).getKey());

                    } else {
                        Iterator<String> iterator = idlist.iterator();
                        while (iterator.hasNext()) {
                            String value = iterator.next();
                            if (value.equals(finalBean.get(finalI).getKey())) {
                                iterator.remove();
                                checkBox.setChecked(false);
                            }
                        }
                    }
                }
            });
            ll1.addView(checkBox);
        }
        Button btn_clear = view1.findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < ll1.getChildCount(); i++) {
                    ((CheckBox) ll1.getChildAt(i)).setChecked(false);
                    idlist.clear();
                }
                if (type == 1) {
                    keku_id.clear();
                    keku_id.addAll(idlist);
                    //  type=0;
//                    setView();
                    //      popWinDownUtil.hide();
                    titleView();
                    iv1.setRotation(0);
                } else if (type == 2) {
                    //      type=0;
//                    setView();
                    zhuangtai_id.clear();
                    zhuangtai_id.addAll(idlist);
                    //      popWinDownUtil2.hide();
                    view_one.setVisibility(View.INVISIBLE);
                    titleView();
                    iv2.setRotation(0);
                } else if (type == 3) {
                    type_id.clear();
                    type_id.addAll(idlist);
//                    popWinDownUtil3.hide();
                    titleView();
                    iv3.setRotation(0);
                }
                adapter.remove();
                page = 1;
                initData();
            }
        });
        Button btn_true = view1.findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    keku_id.clear();
                    keku_id.addAll(idlist);
                    type = 0;
                    setView();
                    popWinDownUtil.hide();
                    titleView();
                    iv1.setRotation(0);
                } else if (type == 2) {
                    type = 0;
                    setView();
                    zhuangtai_id.clear();
                    zhuangtai_id.addAll(idlist);
                    popWinDownUtil2.hide();
                    view_one.setVisibility(View.INVISIBLE);
                    titleView();
                    iv2.setRotation(0);
                } else if (type == 3) {
                    type = 0;
                    setView();
                    type_id.clear();
                    type_id.addAll(idlist);
                    popWinDownUtil3.hide();
                    titleView();
                    iv3.setRotation(0);
                }
                adapter.remove();
                page = 1;
                initData();
            }

        });
        return view1;
    }

    private void titleView() {
        if (keku_id.size() > 0) {
            textView1.setTextColor(getResources().getColor(R.color.ff215dff));
            iv1.setImageResource(R.mipmap.list_but_more_under_two);
        } else {
            textView1.setTextColor(getResources().getColor(R.color.text_1b1b1b));
            iv1.setImageResource(R.mipmap.list_but_more_under);
        }
        if (zhuangtai_id.size() > 0) {
            textView2.setTextColor(getResources().getColor(R.color.ff215dff));
            iv2.setImageResource(R.mipmap.list_but_more_under_two);

        } else {
            textView2.setTextColor(getResources().getColor(R.color.text_1b1b1b));
            iv2.setImageResource(R.mipmap.list_but_more_under);
        }

        if (type_id.size() > 0) {
            textView3.setTextColor(getResources().getColor(R.color.ff215dff));
            iv3.setImageResource(R.mipmap.list_but_more_under_two);
        } else {
            textView3.setTextColor(getResources().getColor(R.color.text_1b1b1b));
            iv3.setImageResource(R.mipmap.list_but_more_under);
        }


    }

    public void setView() {
        switch (type) {
            case 1:
                if (popWinDownUtil2 != null) {
                    popWinDownUtil2.hide();
                    iv2.setRotation(0);
                    view_two.setVisibility(View.INVISIBLE);
                    ll_zhuangtai.setBackground(getResources().getDrawable(R.drawable.search));
                }
                if (popWinDownUtil3 != null) {
                    popWinDownUtil3.hide();
                    iv3.setRotation(0);
                    view_three.setVisibility(View.INVISIBLE);
                    ll_leixing.setBackground(getResources().getDrawable(R.drawable.search));
                }
                if (popWinDownUtil != null) {
                    popWinDownUtil.setView(setDataView());
                    popWinDownUtil.show();
                    dengji_ll.setBackground(getResources().getDrawable(R.drawable.search_two));
                    view_one.setVisibility(View.VISIBLE);
                    iv1.setRotation(180);
                }
                break;
            case 2:
                if (popWinDownUtil != null) {
                    popWinDownUtil.hide();
                    popWinDownUtil.hide();
                    iv1.setRotation(0);
                    dengji_ll.setBackground(getResources().getDrawable(R.drawable.search));
                    view_one.setVisibility(View.INVISIBLE);
                }

                if (popWinDownUtil3 != null) {
                    popWinDownUtil3.hide();
                    popWinDownUtil3.hide();
                    iv3.setRotation(0);
                    view_three.setVisibility(View.INVISIBLE);
                    ll_leixing.setBackground(getResources().getDrawable(R.drawable.search));
                }
                if (popWinDownUtil2 != null) {
                    popWinDownUtil2.setView(setDataView());
                    popWinDownUtil2.show();
                    iv2.setRotation(180);
                    view_two.setVisibility(View.VISIBLE);
                    ll_zhuangtai.setBackground(getResources().getDrawable(R.drawable.search_two));
                }
                break;
            case 3:
                if (popWinDownUtil != null) {
                    popWinDownUtil.hide();
                    popWinDownUtil.hide();
                    popWinDownUtil.hide();
                    iv1.setRotation(0);
                    dengji_ll.setBackground(getResources().getDrawable(R.drawable.search));
                    view_one.setVisibility(View.INVISIBLE);
                }
                if (popWinDownUtil2 != null) {
                    popWinDownUtil2.hide();
                    popWinDownUtil2.hide();
                    popWinDownUtil2.hide();
                    iv2.setRotation(0);
                    view_two.setVisibility(View.INVISIBLE);
                    ll_zhuangtai.setBackground(getResources().getDrawable(R.drawable.search));
                }
                if (popWinDownUtil3 != null) {
                    popWinDownUtil3.setView(setDataView());
                    popWinDownUtil3.show();
                    ll_leixing.setBackground(getResources().getDrawable(R.drawable.search_two));
                    view_three.setVisibility(View.VISIBLE);
                    iv3.setRotation(180);
                }
                break;
            default:
                if (popWinDownUtil != null) {
                    popWinDownUtil.hide();
                    iv1.setRotation(0);
                    dengji_ll.setBackground(getResources().getDrawable(R.drawable.search));
                    view_one.setVisibility(View.INVISIBLE);
                }
                if (popWinDownUtil2 != null) {
                    popWinDownUtil2.hide();
                    iv2.setRotation(0);
                    view_two.setVisibility(View.INVISIBLE);
                    ll_zhuangtai.setBackground(getResources().getDrawable(R.drawable.search));
                }
                if (popWinDownUtil3 != null) {
                    popWinDownUtil3.hide();
                    iv3.setRotation(0);
                    view_three.setVisibility(View.INVISIBLE);
                    ll_leixing.setBackground(getResources().getDrawable(R.drawable.search));
                }
                break;
        }

    }

    @SuppressLint("ResourceType")
    private void setSaiXuanView() {
        checkBoxList.clear();
        hashMap.clear();
        String[] name = {"客户等级", "跟进状态", "业务类型", "客户渠道"};
        if (ll_saixuan != null) {
            ll_saixuan.removeAllViews();
        }
        for (int i = 0; i < name.length; i++) {

            View view = LayoutInflater.from(this).inflate(R.layout.guanli_title, null);
            TextView nametv = view.findViewById(R.id.name);
            nametv.setText(name[i]);
            if (i == 1 && content_type == 2) {
                nametv.setTextColor(R.color.aeafb5ff);
            }
            FlowLayout flowLayout = view.findViewById(R.id.flowlayout);
            List<GuanliTitleBean> list = null;
            List<String> idlist = new ArrayList<>();
            if (i == 0) {
                list = listKeHu;
                idlist.addAll(keku_id);
                hashMap.put(i, idlist);
            } else if (i == 1) {
                list = listZhuangTai;
                idlist.addAll(zhuangtai_id);
                hashMap.put(i, idlist);

            } else if (i == 2) {
                list = listType;
                idlist.addAll(type_id);
                hashMap.put(i, idlist);

            } else if (i == 3) {
                list = listQuDao;
                idlist.addAll(qudao_id);
                hashMap.put(i, idlist);
            }

            for (int k = 0; k < list.size(); k++) {

                CheckBox checkBox = new CheckBox(GuanLiActivity.this);
                checkBox.setText(list.get(k).getValue());
                checkBox.setWidth((ll_saixuan.getWidth() - 10 - Diputil.dip2px(this, 60)) / 3);
                checkBox.setHeight(Diputil.dip2px(GuanLiActivity.this, 32));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    checkBox.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                checkBox.setBackground(getResources().getDrawable(R.drawable.guanli_check));
                checkBox.setButtonDrawable(null);
                checkBox.setTextSize(13);
                if (i == 1 && content_type == 2) {
                    checkBox.setTextColor(R.color.aeafb5ff);
                } else {
                    checkBox.setTextColor(getResources().getColorStateList(R.drawable.guanli_text_color));
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 0, Diputil.dip2px(this, 15), Diputil.dip2px(this, 10));//4个参数按顺序分别是左上右下
                checkBox.setLayoutParams(layoutParams);

                if (hashMap.get(i).contains(list.get(k).getKey())) {
                    checkBox.setChecked(true);
                }
                List<GuanliTitleBean> List = list;
                int finalI = i;
                int finalK = k;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (finalI == 1 && content_type == 2) {
                                showError("已是到期成交状态，不可选择");
                                checkBox.setChecked(false);
                                return;
                            }
                            hashMap.get(finalI).add(List.get(finalK).getKey());

                        } else {
                            Iterator<String> iterator = hashMap.get(finalI).iterator();
                            while (iterator.hasNext()) {
                                String value = iterator.next();
                                if (value.equals(List.get(finalK).getKey())) {
                                    iterator.remove();
                                    checkBox.setChecked(false);
                                }
                            }
                        }
                    }
                });

                checkBoxList.add(checkBox);
                flowLayout.addView(checkBox);
            }
            ll_saixuan.addView(view);
        }
    }

    private void pop() {
        View view = LayoutInflater.from(this).inflate(R.layout.guanli_pop_item, null);
        LinearLayout ll_add = view.findViewById(R.id.ll_add);
        ll_add.setOnClickListener(this);
        LinearLayout ll_yongjin = view.findViewById(R.id.ll_yongjin);
        ll_yongjin.setOnClickListener(this);
        LinearLayout ll_xinzi = view.findViewById(R.id.ll_xinzi);
        ll_xinzi.setOnClickListener(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWinDownUtil4.dismiss();
                type = 0;
                setView();
            }
        });
        if (popWinDownUtil4 == null) {
            popWinDownUtil4 = new PopWinDownUtil(this, view, add_iv, viewbgall);
        }
        popWinDownUtil4.show();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSuccess(boolean s) {//
        showError("wifi已断开，请重新连接！");
        //关闭连接
        isOpenservice = true;
        setLianjie();

    }

    @Override
    public void onMessage(String text) {
        phoneBean = new Gson().fromJson(text, PhoneBean.class);
        if (phoneBean != null) {
            App.is_telphone = 1;
            call("tel:" + phoneBean.getMobile());
            Log.e("tag----------", "拨打电话");

        }
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void isApp(String s) {

    }

    private void setLianjie() {

        if (isOpenservice == false) {
            ll_lianjie.setBackgroundResource(R.drawable.donghua_true);
            anim = (AnimationDrawable) ll_lianjie.getBackground();
            isOpenservice = true;
            anim.setOneShot(true);
            anim.start();
            start();
        } else {
            ll_lianjie.setBackgroundResource(R.drawable.donghua_false);
            anim = (AnimationDrawable) ll_lianjie.getBackground();
            anim.setOneShot(true);
            anim.start();
            stop();
            isOpenservice = false;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(50 * 17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();

    }

    private void start() {
        bascintent = new Intent(GuanLiActivity.this, BackService.class);
        startService(bascintent);
        if (manager == null) {
            manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        }
        if (telListener == null) {
            telListener = TelListener.getInstense(this);
        }
        telListener.setRecordState(true);
        manager.listen(telListener, PhoneStateListener.LISTEN_CALL_STATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.e("TAG", "start----");
    }

    private void stop() {
        if (telListener!=null){
            telListener.setRecordState(false);
        }
        if (bascintent!=null){
            stopService(bascintent);
        }
        if (App.backService!=null){
            App.backService.out();
            Log.e("TAG", "stop----");
        }


    }

    //申请到权限后打电话
    public void call(String telPhone) {
        if (checkReadPermission(Manifest.permission.CALL_PHONE, REQUEST_CALL_PERMISSION)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
            startActivityForResult(intent, 100);

        }
    }

    //打电话申请权限，
    public boolean checkReadPermission(String string_permission, int request_code) {
        boolean flag = false;
//已有权限verifyStoragePermissions
        if (ContextCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {
            flag = true;
        } else {
//申请权限
            ActivityCompat.requestPermissions(this, new String[]{string_permission}, request_code);
        }
        return flag;
    }


    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


}
