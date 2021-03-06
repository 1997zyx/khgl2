package com.yhkhgl.top.push;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

public class WReceiver extends BroadcastReceiver {
    private static final String TAG = "极光";

    private NotificationsUtils nm;

    @SuppressLint("ServiceCast")
    @Override
    public void onReceive(Context context, Intent intent) {
//        if (null == nm) {
//            nm = (NotificationsUtils) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        }

        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush 用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
