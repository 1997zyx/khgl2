package com.yhkhgl.top;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.hjq.toast.ToastUtils;
import com.yhkhgl.top.base.BaseActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.yhkhgl.top.base.mvp.BaseModel;
import com.yhkhgl.top.ui.phone.BackService;
import com.yhkhgl.top.ui.phone.PhoneBean;
import com.yhkhgl.top.ui.activity.GuanLiActivity;
import com.yhkhgl.top.utils.SharePerferenceUtil;
import com.yhkhgl.top.utils.UiUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.jpush.android.api.JPushInterface;

/**
 * File descripition:   运用此项目时，修改地方如下
 * <p>
 * 1.BaseContent 中 baseUrl修改
 * 2.BaseContent 中 basecode修改
 * 3.BaseModel 中 各字段参数名 修改为接口对应
 * 备注：(接口如果复杂，自己修改不了，可以联系我)
 *
 * @author lp
 * @date 2018/10/11
 */

public class App extends Application {
    public static String storage;
    public static int ischanpin;
    public static Context mContext;
    public static String token;
    // 获得刘海适配的顶部距离高度
    public static int fixTitleBarHeight;
    //   public static List<Activity> activities = new ArrayList<Activity>();
    public static Vector<Object> activities = new Vector<>();
    public static Vector<Object> findactivities = new Vector<>();
    public static BaseModel<Object> info;
    public static String name;
    public static String phone;
    public static String touxiangurl;
    public static String  id_card="3";//实名认证状态 0待审核 1已实名通过 2实名未通过 3未提交实名
    public static String  is_read = "1";// 1已读 0未读
    public static String  getLatitude = "0";
    public static String getLongitude = "0";
    public static String province;
    public static String city;
    public static String district;
    public static boolean isclear=false;
    public static JSONObject jsonObject=null;//筛选相关数据
    public static App instance;
    //声明AMapLocationClientOption对象
    public static String  service_admin;//1 服务商管理员  0服务商员工
    //声明定位回调监听器
    public static String registrationID;
    public static int type = 1;
    public static int is_telphone=0;  //是否拨打电话
    public static GuanLiActivity guanLiActivity;
    public static BackService backService;
    public static PhoneBean phoneBean = new PhoneBean();
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        storage = Environment.getExternalStorageDirectory().getPath()+"/" +getPackageName();
        // 在 Application 中初始化
        ToastUtils.init(this);
        // 获得刘海适配的顶部距离高度
        mContext = this;
        instance =this;
        initJPush();
        token=SharePerferenceUtil.getStringValue(mContext,"userdata","token");
        phone=SharePerferenceUtil.getStringValue(mContext,"userdata","user_mobile");
//        token="DoHuoMwUo4QlvuzwrNYnyFAU8MkbUfR6";
        fixTitleBarHeight = UiUtils.getStatusBarHeight();

        initLogger();
        initSmartRefreshLayout();
//开始定位

    }
    /**
     * 初始化极光推送
     */
    private void initJPush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        //只需要在应用程序启动时调用一次该 API 即可
        JPushInterface.init(this);
      registrationID = JPushInterface.getRegistrationID(this);
      Log.i("----",registrationID+"");
    }
    public static Context getAppContext() {
        return instance;
    }
    private void initSmartRefreshLayout() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void findaddActivity(Activity activity) {
        findactivities.add(activity);
    }

    public static void findremoveActivity(Activity activity) {
        findactivities.remove(activity);
    }
    public static void finishAll() {
        for (Object activity : activities) {
            ((Activity) activity).finish();
        }
    }
    public static void finishFind() {
        for (Object activity : findactivities) {
            ((Activity) activity).finish();
        }

    }
    public static Context getContext() {
        return mContext;
    }



    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 是否显示线程信息 默认显示 上图Thread Infrom的位置
                .methodCount(0)         // 展示方法的行数 默认是2  上图Method的行数
                .methodOffset(7)        // 内部方法调用向上偏移的行数 默认是0
//                .logStrategy(customLog) // 改变log打印的策略一种是写本地，一种是logcat显示 默认是后者（当然也可以自己定义）
                .tag("mvp_network_tag")   // 自定义全局tag 默认：PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }

    public static Bundle getBundle(){
        Bundle b=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b =   ActivityOptions.makeSceneTransitionAnimation(BaseActivity.getCurrentActivity()).toBundle();
        }
        return b;
    }
    public static List<String> getFilesAllName() {
        File file = new File(storage);
        File[] files = file.listFiles();
        if (files == null) {
            Log.e("error", "空目录");
            return null;
        }

        List<String> s = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            s.add(files[i].getAbsolutePath());
        }
        return s;
    }
    //判断有没有使用wifi
    public static boolean isWifiActive(Context icontext) {
        Context context = icontext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info;
        if (connectivity != null) {
            info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
