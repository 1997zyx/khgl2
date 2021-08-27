package com.yhkhgl.top.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.yhkhgl.top.R;


public class PopWinDownUtil {
    private Context context;
    private View contentView;
    private View relayView;
    private View bottomview;
    private PopupWindow popupWindow;

    public PopWinDownUtil(Context context, View contentView, View relayView, View bottomView) {
        this.context = context;
        this.contentView = contentView;
        this.relayView = relayView;
        this.bottomview = bottomView;
        init();
    }

    public void init() {
        //内容，高度，宽度
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //动画效果
        popupWindow.setAnimationStyle(R.style.topDialogAnimation);
        //菜单背景色
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        //关闭事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (onDismissLisener != null) {
                    onDismissLisener.onDismiss();
                }
            }
        });
    }

    public void setBackgroundAlpha(boolean b) {
        if (b) {
            bottomview.setVisibility(View.VISIBLE);
        } else {
            bottomview.setVisibility(View.GONE);
        }
    }

    public void show() {
        //显示位置
        setBackgroundAlpha(true);
        popupWindow.showAsDropDown(relayView);
    }

    public void hide() {
        if (popupWindow != null && popupWindow.isShowing()) {
            setBackgroundAlpha(false);
            popupWindow.dismiss();
        }
    }

    private OnDismissLisener onDismissLisener;

    public void setOnDismissListener(OnDismissLisener onDismissLisener) {
        setBackgroundAlpha(false);
        this.onDismissLisener = onDismissLisener;
    }

    public interface OnDismissLisener {
        void onDismiss();
    }

    public boolean isShowing() {
        return popupWindow.isShowing();
    }

    public void dismiss() {
        if (popupWindow != null) {
            setBackgroundAlpha(false);
            popupWindow.dismiss();
        }

    }

    public void setView(View view) {
        this.contentView = view;
        init();
    }

}
