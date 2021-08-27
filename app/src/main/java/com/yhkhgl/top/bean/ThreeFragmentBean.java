package com.yhkhgl.top.bean;

import java.io.Serializable;

public class ThreeFragmentBean implements Serializable {
    private String id;
    private String user_id;
    private String picture;
    private String content;//内容
    private String location_address;
    private String tag_ids;
    private String like_num; //点赞数
    private String collect_num; //收藏数
    private String state;
    private String sort;
    private String create_time;
    private String user_name;   //昵称
    private String user_mobile;
    private String user_avatar;  //头像
    private String is_authentication;
    private String role;
    private String vip_type; //会员级别
    private String time_msg;// //时间文案
    private String total_article_evaluation;//评论数
    private String is_vip;//会员是否有效
    private String follow_status;//是否关注

    private String b_outlets;//认证银行名称
    private String b_audit_status;////是否认证
    private String[] pic_arr;//图片
    private String like;//1 已点赞 0否
    private String collect;//1 已收藏 0否
    private String own;//是否是自己  1自己

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getFollow_status() {
        return follow_status;
    }

    public void setFollow_status(String follow_status) {
        this.follow_status = follow_status;
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

    public String[] getPic_arr() {
        return pic_arr;
    }

    public void setPic_arr(String[] pic_arr) {
        this.pic_arr = pic_arr;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }

    public String getTag_ids() {
        return tag_ids;
    }

    public void setTag_ids(String tag_ids) {
        this.tag_ids = tag_ids;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getCollect_num() {
        return collect_num;
    }

    public void setCollect_num(String collect_num) {
        this.collect_num = collect_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getIs_authentication() {
        return is_authentication;
    }

    public void setIs_authentication(String is_authentication) {
        this.is_authentication = is_authentication;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVip_type() {
        return vip_type;
    }

    public void setVip_type(String vip_type) {
        this.vip_type = vip_type;
    }

    public String getTime_msg() {
        return time_msg;
    }

    public void setTime_msg(String time_msg) {
        this.time_msg = time_msg;
    }

    public String getTotal_article_evaluation() {
        return total_article_evaluation;
    }

    public void setTotal_article_evaluation(String total_article_evaluation) {
        this.total_article_evaluation = total_article_evaluation;
    }

    public String getIs_vip() {
        return is_vip;
    }

    public void setIs_vip(String is_vip) {
        this.is_vip = is_vip;
    }
}
