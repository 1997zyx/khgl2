package com.yhkhgl.top.bean;

import java.io.Serializable;

public class YongjinListBean implements Serializable {
    private String id;
    private String surname;
    private String transaction_amount;
    private String commission;
    private String transaction_plan;
    private String success_time;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getTransaction_plan() {
        return transaction_plan;
    }

    public void setTransaction_plan(String transaction_plan) {
        this.transaction_plan = transaction_plan;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
