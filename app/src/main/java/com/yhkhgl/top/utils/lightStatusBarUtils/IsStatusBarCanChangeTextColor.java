package com.yhkhgl.top.utils.lightStatusBarUtils;


import com.yhkhgl.top.base.BaseActivity;


/**
 * Created by ASUS on 2018/2/6.
 */

public class IsStatusBarCanChangeTextColor {

    public static void setStatusBarTextIsDark(Object activityInstance, boolean isDark) {
        switch (RomUtils.getLightStatausBarAvailableRomType()) {
            case RomUtils.AvailableRomType.MIUI:
                // 当前系统为MIUI6或以上 浅色状态栏可用
                break;

            case RomUtils.AvailableRomType.FLYME:
                // "当前系统为Flyme4或以上 浅色状态栏可用"
                break;

            case RomUtils.AvailableRomType.ANDROID_NATIVE:
                // "当前系统为Android M或以上 浅色状态栏可用"
                break;

            case RomUtils.AvailableRomType.NA:
                // "当前系统浅色状态栏不可用"
                return;
        }
        if (activityInstance instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) activityInstance;
            LightStatusBarUtils.setLightStatusBar(baseActivity, isDark);
        }
//        if (activityInstance instanceof BaseFragment) {
//            BaseFragment baseFragmentActivity = (BaseFragment) activityInstance;
//            LightStatusBarUtils.setLightStatusBar(baseFragmentActivity, isDark);
//        }
    }
}
