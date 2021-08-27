package com.yhkhgl.top.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yhkhgl.top.R;

public class SynExceptionLayout extends FrameLayout {
    /**
     * 一般的执行顺序是：网络访问前 loadShow(), 然后加载成功之后，successShow();
     * 如果加载出错的话，errorShow();
     * 如果加载数据为null的话，emptyShow();
     * 一般来说使用emptyShow(int res)，即自定义布局
     */
    private LinearLayout emptyLinearLayout;//数据位null时，应该显示的布局
    private LinearLayout errorLinearLayout;//数据加载出错时，应该显示的布局
    private LinearLayout loadLinearLayout;//正在加载时，应该显示的布局

    public static final int EMPTY_INT = 0;//回调标记：数据为null
    public static final int ERROR_INT = 1;//回调标记：数据出错
    public static final int LOAD_INT = 2;//回调标记：数据正在加载

    private OnSynExceptionListaner onSynExceptionListaner;

    public OnSynExceptionListaner getOnSynExceptionListaner() {
        return onSynExceptionListaner;
    }

    public void setOnSynExceptionListaner(OnSynExceptionListaner onSynExceptionListaner) {
        this.onSynExceptionListaner = onSynExceptionListaner;
    }

    public SynExceptionLayout(Context context) {
        super(context);
    }

    public SynExceptionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SynExceptionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 加载为null
     */
    public void emptyShow(){//默认加载
        empty(R.layout.activity_laypout);
    }
    public void emptyShow(int res){//指定加载布局
        empty(res);
    }
    public void empty(int res){
        if(emptyLinearLayout == null){
            emptyLinearLayout = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(res, this,false);
            emptyLinearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSynExceptionListaner.onSynxceptionListener(EMPTY_INT);
                }
            });
        }
        if(errorLinearLayout == null){
            errorLinearLayout.setVisibility(View.GONE);
        }
        if(loadLinearLayout == null){
            loadLinearLayout.setVisibility(View.GONE);
        }
        /**
         * indexOfChild(View view) 方法返回该view在frameLayout中的位置索引
         * 不在里面时，返回 -1；
         *
         * addView(View view) 将view添加到frameLayout中
         */
        if(indexOfChild(emptyLinearLayout) == -1){
            addView(emptyLinearLayout);
            emptyLinearLayout.setVisibility(View.VISIBLE);
        }else{
            emptyLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载出错
     */
    public void errorShow(){
        error(R.layout.activity_null);
    }
    public void errorShow(int res){
        error(res);

    }
    private void error(int res){
        if(errorLinearLayout == null){
            errorLinearLayout = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(res, this,false);
            errorLinearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSynExceptionListaner.onSynxceptionListener(ERROR_INT);
                }
            });
        }
        if(loadLinearLayout != null){
            loadLinearLayout.setVisibility(View.GONE);
        }
        if(emptyLinearLayout != null){
            emptyLinearLayout.setVisibility(View.GONE);
        }
        if(indexOfChild(errorLinearLayout) == -1){
            addView(errorLinearLayout);
            errorLinearLayout.setVisibility(VISIBLE);
        }else{
            errorLinearLayout.setVisibility(VISIBLE);
        }
    }

    /**
     * 正在加载
     */
    public void loadShow(){
        load(R.layout.activity_laypout);
    }
    public void loadShow(int res){
        load(res);
    }
    private void load(int res){
        if(loadLinearLayout == null){
            loadLinearLayout = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(res, this, false);
            loadLinearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSynExceptionListaner.onSynxceptionListener(LOAD_INT);
                }
            });
        }
        if(errorLinearLayout != null){
            errorLinearLayout.setVisibility(View.GONE);
        }
        if(emptyLinearLayout  != null){
            emptyLinearLayout.setVisibility(View.GONE);
        }
        if(indexOfChild(loadLinearLayout) == -1){
            addView(loadLinearLayout);
            loadLinearLayout.setVisibility(View.VISIBLE);
        }else{
            loadLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     *成功时，将三个布局都设置为GONE
     */
    public void successShow(){
        if(emptyLinearLayout != null){
            emptyLinearLayout.setVisibility(View.GONE);
        }
        if(errorLinearLayout != null){
            errorLinearLayout.setVisibility(View.GONE);
        }
        if(loadLinearLayout != null){
            loadLinearLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 回调接口
     */
    public interface OnSynExceptionListaner{
        void onSynxceptionListener(int flug);
    }

}
