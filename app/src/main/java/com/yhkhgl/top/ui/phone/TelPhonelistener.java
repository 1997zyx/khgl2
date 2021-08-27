package com.yhkhgl.top.ui.phone;

public interface TelPhonelistener {
    void onSuccess(boolean s);
    void onMessage(String text);
    void onFailure();
    void isApp(String s);
}
