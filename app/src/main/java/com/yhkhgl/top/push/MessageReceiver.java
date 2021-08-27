package com.yhkhgl.top.push;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;
import com.yhkhgl.top.App;

import java.util.List;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class MessageReceiver extends JPushMessageReceiver {
    private static final String TAG = "极光";

    /**
     * tag 增删查改的操作会在此方法中回调结果。
     *
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
//        EventBus.getDefault().post(jPushMessage);
    }

    /**
     * 查询某个 tag 与当前用户的绑定状态的操作会在此方法中回调结果。
     *
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
//        EventBus.getDefault().post(jPushMessage);
    }


    /**
     * alias 相关的操作会在此方法中回调结果。
     *
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
//        EventBus.getDefault().post(jPushMessage);
    }


    /**
     * 设置手机号码会在此方法中回调结果。
     *
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
//        EventBus.getDefault().post(jPushMessage);
    }


    /**
     * 注册成功回调
     *
     * @param context
     * @param s
     */
    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
        LogUtils.e(TAG, "自带广播注册成功回调");
    }

    /**
     * 长连接状态回调
     *
     * @param context
     * @param b
     */
    @Override
    public void onConnected(Context context, boolean b) {
//        super.onConnected(context, b);
        LogUtils.e(TAG, "自带广播长连接状态回调");
    }

    /**
     * 注册失败回调
     *
     * @param context
     * @param cmdMessage
     */
    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        super.onCommandResult(context, cmdMessage);
        LogUtils.e(TAG, "自带广播注册失败回调");
    }

    /**
     * 通知的MultiAction回调
     *
     * @param context
     * @param intent
     */
    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
//        super.onMultiActionClicked(context, intent);
    }

    /**
     * 收到通知回调
     *
     * @param context
     * @param notificationMessage
     */
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
//        super.onNotifyMessageArrived(context, notificationMessage);
//        LogUtils.e(TAG,"自带广播收到通知回调"+ "\n" + notificationMessage.toString());
        NotificationsUtils.showNotification(notificationMessage.inAppMsgTitle);
    }

    /**
     * 点击通知回调
     *
     * @param context
     * @param notificationMessage
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
//        super.onNotifyMessageOpened(context, notificationMessage);
        isBackground(App.getContext());
//        Intent launchIntent = App.getAppContext().getPackageManager().
//                getLaunchIntentForPackage("com.yhkhgl.top");
//        launchIntent.setFlags(
//                Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//        Bundle args = new Bundle();
//        startActivity(launchIntent);
//        LogUtils.e(TAG,"自带广播点击通知回调");
//        NotificationsUtils.showNotification(notificationMessage.inAppMsgTitle);

    }

    /**
     * 清除通知回调
     *
     * @param context
     * @param notificationMessage
     */
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageDismiss(context, notificationMessage);
//        LogUtils.e(TAG,"自带广播清除通知回调");
        NotificationsUtils.showNotification(notificationMessage.inAppMsgTitle);
    }

    /**
     * 收到自定义消息回调
     *
     * @param context
     * @param customMessage
     */
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {

        super.onMessage(context, customMessage);
//        LogUtils.e(TAG,"自带广播收到自定义消息回调");
        NotificationsUtils.showNotification(customMessage.title);
//        NotificationsUtils.startNotif(customMessage.notificationTitle,customMessage.notificationContent);

    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                BACKGROUND=400 EMPTY=500 FOREGROUND=100
                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
                 */
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {

//                    ComponentName component = new ComponentName("com.yhkhgl.top", "com.yhkhgl.top.MsgDetailActivity");
//                    Intent intent = new Intent();
//                    intent.setComponent(component);
//                    intent.putExtra("type","1");
//                    intent.putExtra("title","11");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                    Intent launchIntent = App.getAppContext().getPackageManager().
                            getLaunchIntentForPackage("com.yhkhgl.top");
                    launchIntent.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                    Bundle args = new Bundle();
                    startActivity(launchIntent);
                    Log.i(context.getPackageName(), "处于后台"
                            + appProcess.processName);
                    return true;
                } else {
//                    Intent launchIntent = new Intent(App.getContext(), MsgDetailActivity.class);
//                    launchIntent.putExtra("type","1");
//                    launchIntent.putExtra("title","系统消息");
//                    startActivity(launchIntent);
//                    Log.i(context.getPackageName(), "处于前台"
//                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }
}
