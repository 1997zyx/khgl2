package com.yhkhgl.top.ui.phone;

import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import static com.yhkhgl.top.App.storage;


public class MediaRecordUtil extends Activity {
    private Context mContext;

    public MediaRecordUtil(Context context) {
        mContext = context;
    }

    public void StartMediaRecord(File soundFile, MediaRecorder mediaRecorder) {
        // 先检测下是否含有SDCard
        //获取外部存储路径  api 29以下可以用这种方式在根目录 创建文件夹

         File dirFile = new File(storage);
         Log.d("dirFile", "" + dirFile);
         if (!dirFile.exists()) {
                 boolean mkdirs = dirFile.mkdirs();
                 if (!mkdirs) {
                         Log.e("TAG", "文件夹创建失败");
                     } else {
                        Log.e("TAG", "文件夹创建成功");
                     }
             }
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(mContext, "SD卡不存在，请插入SD卡", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        try {
            // 创建音频输出的文件路径soundFile
            // 设置录音的来源为麦克风
            //       华为，酷派可以
            //----------------------------------重要-------------------------------------------------
            //VOICE_CALL：语音呼叫,VOICE_COMMUNICATION：语音通信,MIC：麦克风，VOICE_RECOGNITION：语音识别。
            // VOICE_DOWNLINK：语音下行链路，VOICE_UPLINK：语音上行链路。
            //经过我的测试发现以下问题，测试手机：红米note4。
            //小米手机设置MediaRecorder.AudioSource设置调用的时候，
            // 调用MIC可以正常保存录音，VOICE_COMMUNICATION也可以，VOICE_RECOGNITION也可以，
            // 但是设置为VOICE_CALL就不行，VOICE_DOWNLINK也不行，VOICE_UPLINK也不行。
            //如果设置成VOICE_CALL，那么保存后的文件大小会为0b，就是为空。
            //例如：mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
            //------------------------------------------------------------------------------------------
            if ("xiaomi".equalsIgnoreCase(android.os.Build.BRAND)) {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            } else {
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            }
            // 设置录制的声音输出格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            // 设置声音的编码格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // 设置录音的输出文件路径
            mediaRecorder.setOutputFile(soundFile.getAbsolutePath());
            // 做预期准备
            mediaRecorder.prepare();
            // 开始录音
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止录音
     **/
    public void StopmediaRecorder(MediaRecorder mediaRecorder) {
        try {
            if (mediaRecorder != null) {
                mediaRecorder.stop();
                // 释放资源
                mediaRecorder.release();
                mediaRecorder = null;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除录音文件
     **/
    public void DeleteAudio(File soundFile) {
        if (soundFile.exists()) { // 判断文件是否存在
            if (soundFile.isFile()) { // 判断是否是文件
                soundFile.delete(); // delete()方法 你应该知道 是删除的意思;
            }
        }
    }

}
