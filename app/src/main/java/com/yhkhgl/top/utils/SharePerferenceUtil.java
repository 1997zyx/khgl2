package com.yhkhgl.top.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/8/25.
 */
public class SharePerferenceUtil {

    //保存信息存储在本地文件
    public static void saveValue(Context context, String fileName, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString(key, value);
        editor.commit();//提交修改

    }

    //拿到用户信息
    public static String getStringValue(Context mContext, String fileName, String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE); //私有数据
        return sharedPreferences.getString(key, "");

    }

    public static void saveValue(Context context, String fileName, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putBoolean(key, value);
        editor.commit();//提交修改
    }

    /**
     * 特例 默认返回true
     *
     * @param mContext
     * @param fileName
     * @param key
     * @return
     */
    public static boolean getBolleanVaule(Context mContext, String fileName, String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE); //私有数据
        return sharedPreferences.getBoolean(key, true);
    }

    public static void saveValue(Context context, String fileName, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt(key, value);
        editor.commit();//提交修改
    }

    public static int getIntVaule(Context mContext, String fileName, String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE); //私有数据
        int intValue = sharedPreferences.getInt(key, 0);
        return intValue;
    }

}