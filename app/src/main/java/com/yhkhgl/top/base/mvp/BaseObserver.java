package com.yhkhgl.top.base.mvp;

import android.content.Intent;

import com.google.gson.JsonParseException;

import com.yhkhgl.top.App;
import com.yhkhgl.top.base.BaseContent;
import com.yhkhgl.top.ui.activity.LoginActivity;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * File descripition:   数据处理基类
 *
 * @author lp
 * @date 2018/6/19
 */

public abstract class BaseObserver<T> extends DisposableObserver<BaseModel<T>> {
    protected BaseView view;
    /**
     * 网络连接失败  无网
     */
    public static final int NETWORK_ERROR = 100000;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1008;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1007;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1006;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1005;

    /**
     * 其他所有情况
     */
    public static final int NOT_TRUE_OVER = 1004;


    public BaseObserver(BaseView view) {
        this.view = view;
    }

    public BaseObserver() {
    }

    @Override
    protected void onStart() {
        if (view != null) {
//            view.showLoading();
        }
    }

    @Override
    public void onNext(BaseModel<T> o) {
//        T t = o.getData();
        try {
            if (view != null) {
                view.hideLoading();
            }
            if (o.getErrcode() == BaseContent.basecode || o.getErrcode() == 0) {
                onSuccess(o);
            } else {
                if (view != null) {
                    view.onErrorCode(o);
                }

                if (o.getErrmsg().contains("token")) {
                    if (o.getErrmsg().equals("token不能为空")) {
//                            onError(o.getErrmsg());
                        onError("身份已过期，请重新登录");
                        App.token = "";
                        App.finishAll();
                        Intent intent = new Intent();
                        intent.setClass(App.getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        App.getContext().startActivity(intent);
                    } else {
                        App.token = "";
                        onError("请登录");
                        App.finishAll();
                        Intent intent = new Intent();
                        intent.setClass(App.getContext(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        App.getContext().startActivity(intent);
                    }

                } else {
                    onException(PARSE_ERROR, o.getErrmsg());
                }
//                }else {
//                    onException(PARSE_ERROR, o.getErrmsg());
//                }
                //非  true的所有情况

            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.toString());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (view != null) {
            view.hideLoading();
        }
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(BAD_NETWORK, "");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(CONNECT_ERROR, "");
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(CONNECT_TIMEOUT, "");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(PARSE_ERROR, "");
            e.printStackTrace();
        } else {
            if (e != null) {
                onError(e.toString());
            } else {
                onError("未知错误");
            }
        }
    }


    private void onException(int unknownError, String message) {
        switch (unknownError) {
            case CONNECT_ERROR:
                onError("网络不佳");
//                onError("连接错误");
                break;
            case CONNECT_TIMEOUT:
                onError("网络不佳");
//                onError("连接超时");
                break;
            case BAD_NETWORK:
                onError("网络不佳");
//                onError("网络超时");
                break;
            case PARSE_ERROR:
                onError(message + "");
                break;
            //非true的所有情况
            case NOT_TRUE_OVER:
                onError(message);
                break;
            default:
                break;
        }
    }

    //消失写到这 有一定的延迟  对dialog显示有影响
    @Override
    public void onComplete() {
       /* if (view != null) {
            view.hideLoading();
        }*/
    }

    public abstract void onSuccess(BaseModel<T> o);

    public abstract void onError(String msg);
}
