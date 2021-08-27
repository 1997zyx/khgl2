package com.yhkhgl.top.ui.phone;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.yhkhgl.top.App;
import com.yhkhgl.top.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.service.AlarmReceiver;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.yhkhgl.top.App.getContext;
import static com.yhkhgl.top.App.guanLiActivity;
import static com.yhkhgl.top.App.phoneBean;


/**
 * @author: Administrator
 * @date: 2021/8/2 0002
 */


public class BackService extends Service implements sendMseC {

    TelPhonelistener telPhonelistener;
    long time;
    int number = 0;
    private final Timer timer = new Timer();
    private TimerTask task;
    public int i = 0;

    private long sendTime = 0L;
    boolean aBoolean = true;

    /**
     * 心跳检测时间
     */
    private static final long HEART_BEAT_RATE = 5 * 1000;//每隔15秒进行一次对长连接的心跳检测
    private static final String WEBSOCKET_HOST_AND_PORT = "ws://apiapp.hrzp.top:8081";//可替换为自己的主机名和端口号
    private WebSocket mWebSocket;
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // TODO Auto-generated method stub
//        Intent notificationIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        Notification noti = new Notification.Builder(this)
//                .setContentTitle("Title")
//                .setContentText("Message")
//                .setSmallIcon(R.mipmap.logo)
//
//                .build();
//        startForeground(0,noti);
//        return Service.START_STICKY;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
        App.backService = this;
        telPhonelistener = App.guanLiActivity;
        new InitSocketThread().start();
        //application= BaseApplication.getApplication();//这个是application，需要在功能清单里面的--android:name=".main.app.TzApplication"
        //context=BaseApplication.getApplication();
        Log.e("TAG", "onCreate------------*************-------------");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "自动setvice销毁-------------");

//        if (mWebSocket != null) {
//            mWebSocket.close(1000, null);
//        }
//        if (timer !=null){
//            timer.cancel();
//            task.cancel();
//        }
    }

    public void out() {
        onDestroy();
        Log.e("TAG", "手动setvice销毁-------------");
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
        if (timer != null) {
            timer.cancel();
            task.cancel();
        }
    }

    @Override
    public void into(String map) {
        sendMsg(map);
    }

    public class InitSocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                initSocket();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 初始化socket
        private void initSocket() throws UnknownHostException, IOException {
            OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
            Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT).build();
            client.newWebSocket(request, new WebSocketListener() {
                @Override
                public void onOpen(WebSocket webSocket, Response response) {//开启长连接成功的回调
                    super.onOpen(webSocket, response);
                    mWebSocket = webSocket;
                    Log.e("TAG", "回调成功-------------");
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("uid", "m_" + App.phone);
                    Gson gson = new Gson();
                    String s = gson.toJson(map);
                    sendMsg(s);
//                    telPhonelistener.onSuccess(true);
                }

                @Override
                public void onMessage(WebSocket webSocket, String text) {//接收消息的回调
                    super.onMessage(webSocket, text);
                    //收到服务器端传过来的消息text
                    Log.e("TAG", "收到来自后台的信息text-------------" + text);
                    sendMsg(text);
                    try {
                        JSONObject jsonObject = new JSONObject(text);
                        phoneBean = new Gson().fromJson(text, PhoneBean.class);
                        if (phoneBean.getMobile() == null || phoneBean.getMobile().equals("")) {

                        } else {
                            if (phoneBean.getType() != null && phoneBean.getType().equals("1")) {
                                telPhonelistener.onMessage(text);//拨打电话
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onMessage(WebSocket webSocket, ByteString bytes) {
                    super.onMessage(webSocket, bytes);
                    Log.e("TAG", "收到来自后台的信息bytes-------------");

                }

                @Override
                public void onClosing(WebSocket webSocket, int code, String reason) {
                    super.onClosing(webSocket, code, reason);
                    Log.e("TAG", "远程端暗示没有数据交互时回调（即此时准备关闭，但连接还没有关闭）-------------");
                    mWebSocket.cancel();
                }

                @Override
                public void onClosed(WebSocket webSocket, int code, String reason) {
                    super.onClosed(webSocket, code, reason);
                    Log.e("TAG", "当连接已经释放的时候被回调-------------");
                    webSocket.close(code, reason);
                }

                @Override
                public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                    super.onFailure(webSocket, t, response);
                    Log.e("TAG", "回调掉失败-------------");

                }
            });
            client.dispatcher().executorService().shutdown();
//            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
            if (i == 0) {
                task = new TimerTask() {
                    @Override

                    public void run() {
                        Message message = new Message();
                        message.what = 0;
                        handler.sendMessage(message);
                    }

                };
                timer.schedule(task, 0, 10000);
            }
        }


    }

    public void sendMsg(String s) {
        try {
            Log.e("TAG", "发送的消息" + "-------------" + s);
            mWebSocket.send(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getinfo() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("uid", "m_" + App.phone);
        Gson gson = new Gson();
        String s = gson.toJson(map);
//        Log.e("TAG", "发送的消息" + "-------------" + s);
//        sendMsg(s);

        return s;
    }


    Handler handler = new Handler() {
        @Override

        public void handleMessage(Message msg) {
// TODO Auto-generated method stub
            i = 1;
// 要做的事情

            super.handleMessage(msg);
            boolean isSuccess = false;
            try {
                isSuccess = mWebSocket.send(getinfo());
                Log.e("TAG长链接发送的消息", "" + getinfo());
//            time=System.currentTimeMillis();
                if (!isSuccess) {//长连接已断开
                    Log.e("TAG", "长连接已断开-------------");
//                mHandler.removeCallbacks(heartBeatRunnable);
                    mWebSocket.cancel();//取消掉以前的长连接
                    new InitSocketThread().start();//创建一个新的连接
                } else {//长连接处于连接状态
                    Log.e("TAG", "发送心跳包-------------已连接");
                }
                Log.e("TAG", "-------------时间" + System.currentTimeMillis());

            } catch (Exception e) {
                e.printStackTrace();
//                timer.cancel();

            }

        }

    };


}
