package com.yhkhgl.top.base.mvp;

import java.io.Serializable;

/**
 * File descripition:   mode基类
 *
 * @author lp
 * @date 2018/6/19
 */
public class BaseModel<T> implements Serializable {
    private String msg;
    private int code;
    private T data;
    private Object extend;

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    public BaseModel(String message, int code) {
        this.msg = message;
        this.code = code;
    }

    public int getErrcode() {
        return code;
    }

    public void setErrcode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return msg;
    }

    public void setErrmsg(String message) {
        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + data +
                '}';
    }
}
