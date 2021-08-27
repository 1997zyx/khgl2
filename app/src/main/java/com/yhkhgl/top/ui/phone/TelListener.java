package com.yhkhgl.top.ui.phone;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.yhkhgl.top.App;
import com.yhkhgl.top.ui.activity.GuanLiActivity;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static com.yhkhgl.top.App.getFilesAllName;
import static com.yhkhgl.top.App.guanLiActivity;


public class TelListener extends PhoneStateListener{
    private MediaRecorder mediaRecorder;
    private String recordPath;
    static boolean recordState = false;// 是否开启了服务
    private boolean isRecording = false;// 时候正在录音
    private boolean isOffhooked = false;// 是否已经接通
    //    private boolean isRinging = false;// 来电标识
    private Context context;
    private String number = "";
    private String createTime;
    private static TelListener telListener;
    private final Timer timer = new Timer();
    private TimerTask task;
    sendMseC msendMse;
    private static final long HEART_BEAT_RATE = 10 * 1000;//每隔15秒进行一次对长连接的心跳检测
    public TelListener(Context context) {
        msendMse = App.backService;
        this.context = context;
        task = new TimerTask() {
            @Override

            public void run() {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }

        };
        timer.schedule(task, 0, 10000);
    }
    public static TelListener getInstense(Context context) {
        if (telListener == null) {
            telListener = new TelListener(context);
        }

        return telListener;
    }

    public void setRecordState(boolean state) {
        recordState = state;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        if (recordState) {
            if (!TextUtils.isEmpty(incomingNumber)) {
                number = incomingNumber;
            }
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:// 空闲
                    getFilesAllName();
                    Log.e("TAG1", "===============CALL_STATE_IDLE空闲==============");
                    // 音频数据上传到服务器
                    if (isRecording) {
                        stopRecord(mediaRecorder);
                        isRecording = false;
                        if (!TextUtils.isEmpty(recordPath) && isOffhooked) {
                            isOffhooked = false;
                            //这里可以写一些录完音之后的一些后学操作
                            //也可以写一个回调
                        }
                        App.is_telphone = 0;
//                        HashMap<String,Object> map=new HashMap<>();
//                        map.put("uid","m_13524728008");
//                        map.put("cus_id","6");
//                        map.put("type","2");
//                        map.put("tab","");
//                        map.put("c_type","1");
//                        map.put("mobile","13524728006");
                        Gson gson = new Gson();
                        PhoneBean bena = GuanLiActivity.phoneBean;
                        bena.setType("2");
                        String s =gson.toJson(bena);
                        Log.e("TAG1", "==============="+s);
                        App.backService.sendMsg(s);
                    }
                    break;

                case TelephonyManager.CALL_STATE_RINGING:// 来电
                    Log.e("TAG2", "===============CALL_STATE_RINGING来电==============");
//                    isRinging = true;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:// 接通
                    Log.e("TAG3", "===============CALL_STATE_OFFHOOK接通=============="+number+state+incomingNumber);
                    if (!isRecording) {
                        startRecordAudio(number);
//                        number = "";
                        isRecording = true;
                        isOffhooked = true;
                    }
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 描述：开始录音
     */
    private void startRecordAudio(String number) {
        Log.e("TAGXX", number + "电话录音"+".amr");
        mediaRecorder = new MediaRecorder();
        // 存放的文件路径
        File soundFile = null;
        String path = Environment.getExternalStorageDirectory().getPath()+"/" +context.getPackageName();
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            boolean mkdirs = dirFile.mkdirs();
            if (!mkdirs) {
                Log.i("TAG", "创建：" + mkdirs);
            } else {
                Log.i("TAG", "创建成功");
            }
        }
        soundFile = new File(path);
        createTime = MessageFormat.format("{0,date,yyyy-MM-dd-HH-mm-ss}",
                new Object[]{new Date(System.currentTimeMillis())});

        File file = new File(soundFile, number + "电话录音" + createTime
                + ".amr");
        recordPath = file.getAbsolutePath();
        // 录音
        new MediaRecordUtil(context).StartMediaRecord(file, mediaRecorder);
        Log.e("TAGXX", number + "电话录音" + createTime + ".amr");
        Log.e("TAGXX", file.length() + "-------------------" + recordPath.length() + "-----------------" + soundFile.length());
        //把号码值空
        if (TextUtils.isEmpty(number)) {
            number = "";
        }
        if (!soundFile.exists()) {
            try {
                soundFile.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止录音
     *
     * @param mediaRecorder
     */
    private void stopRecord(MediaRecorder mediaRecorder) {
        if (mediaRecorder != null) {
            new MediaRecordUtil(context).StopmediaRecorder(mediaRecorder);

        }
    }
    /**
     * 挂断电话
     */
    /**
     * 挂断电话
     */
//自动挂断

    private long sendTime = 0L;
    // 发送心跳包
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                sendTime = System.currentTimeMillis();
                Log.e("TAG", "telsetvice------------*************-------------");
            }
            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//每隔一定的时间
        }

    };
}
