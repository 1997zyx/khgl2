package com.yhkhgl.top.bean;

import java.io.Serializable;

public class FangDaiBean implements Serializable {
    private String beanjin;
    private String lixi;
    private String qishu;
    private String yuegong;
    private String shengyubenjin;

    public String getShengyubenjin() {
        return shengyubenjin;
    }

    public void setShengyubenjin(String shengyubenjin) {
        this.shengyubenjin = shengyubenjin;
    }

    public String getYuegong() {
        return yuegong;
    }

    public void setYuegong(String yuegong) {
        this.yuegong = yuegong;
    }

    public String getBeanjin() {
        return beanjin;
    }

    public void setBeanjin(String beanjin) {
        this.beanjin = beanjin;
    }

    public String getLixi() {
        return lixi;
    }

    public void setLixi(String lixi) {
        this.lixi = lixi;
    }

    public String getQishu() {
        return qishu;
    }

    public void setQishu(String qishu) {
        this.qishu = qishu;
    }

}
