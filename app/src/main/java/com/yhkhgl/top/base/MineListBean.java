package com.yhkhgl.top.base;

import java.io.Serializable;

public class MineListBean implements Serializable {
    private String id;
    private String user_name;
    private String user_avatar;
    private String b_outlets;
    private String b_audit_status;
    private String vip_type;
    private String own;
    private String is_follow;
    private String total_fans;
    private String is_vip;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getB_outlets() {
        return b_outlets;
    }

    public void setB_outlets(String b_outlets) {
        this.b_outlets = b_outlets;
    }

    public String getB_audit_status() {
        return b_audit_status;
    }

    public void setB_audit_status(String b_audit_status) {
        this.b_audit_status = b_audit_status;
    }

    public String getVip_type() {
        return vip_type;
    }

    public void setVip_type(String vip_type) {
        this.vip_type = vip_type;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(String is_follow) {
        this.is_follow = is_follow;
    }

    public String getTotal_fans() {
        return total_fans;
    }

    public void setTotal_fans(String total_fans) {
        this.total_fans = total_fans;
    }
}
