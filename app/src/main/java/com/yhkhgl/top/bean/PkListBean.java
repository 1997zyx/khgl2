package com.yhkhgl.top.bean;

import java.io.Serializable;

public class PkListBean implements Serializable {
    private String id;
    private String product_id;
    private String name;
    private String quota;
    private String is_attestation;
    private boolean ischeck=false;

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getIs_attestation() {
        return is_attestation;
    }

    public void setIs_attestation(String is_attestation) {
        this.is_attestation = is_attestation;
    }
}
