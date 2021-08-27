package com.yhkhgl.top.bean;

import java.io.Serializable;

/**
 * Created by ZhangPan on 2017/12/25
 */
public class BaseCallBackBean implements Serializable {
    public int code;
    public int status;
//    public int count;
    public Object data;
//    public Object datainfo;
    public String msg = "";


    @Override
    public String toString() {
        return "BaseCallBackBean{" +
                "code=" + code +
                ", status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
