package com.yhkhgl.top.ui.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import com.yhkhgl.top.App;
import com.yhkhgl.top.ui.activity.GuanLiActivity;

/**
 * @author: Administrator
 * @date: 2021/8/13 0013
 */
public class WifiBroad extends BroadcastReceiver {
    /**
     * 获取连接类型
     *
     * @param type
     * @return
     */
    private String getConnectionType(int type) {
        String connType = "";
        if (type == ConnectivityManager.TYPE_MOBILE) {
            connType = "3G网络数据";
        } else if (type == ConnectivityManager.TYPE_WIFI) {
            connType = "WIFI网络";
        }
        return connType;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)) {
        } else if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
            NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (info.getState().equals(NetworkInfo.State.DISCONNECTED)) {// 如果断开连接
                System.out.println("wifi网络连接断开 ");
                App.guanLiActivity.onSuccess(false);
//                Toast.makeText(App.getContext(),"wifi网络连接断开",Toast.LENGTH_SHORT).show();
            }
            if (info.getState().equals(NetworkInfo.State.CONNECTING)) {
                System.out.println("连接到wifi网络");
//                Toast.makeText(App.getContext(),"连接到wifi网络",Toast.LENGTH_SHORT).show();
            }
        } else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            // WIFI开关
            int wifistate = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_DISABLED);
            if (wifistate == WifiManager.WIFI_STATE_DISABLED) {// 如果关闭
//                Toast.makeText(App.getContext(),"系统关闭wifi",Toast.LENGTH_SHORT).show();
                System.out.println("系统关闭wifi");
            }
            if (wifistate == WifiManager.WIFI_STATE_ENABLED) {
//                Toast.makeText(App.getContext(),"系统开启wifi",Toast.LENGTH_SHORT).show();
                System.out.println("系统开启wifi");
            }
        }

    }

}
