package com.yhkhgl.top.bean;

import java.io.Serializable;

public class XinziBean implements Serializable {
    private String service_user_id;
    private String name;
    private String avatar;
    private String calculation_date;
    private String total_salary;
    private String base_salary_money;
    private String social_insurance_money;
    private String social_insurance_late_money;
    private String total_transaction_amount;
    private String total_commission;
    private String commission_amount;
    private String bonus;
    private String correction_value;

    public String getService_user_id() {
        return service_user_id;
    }

    public void setService_user_id(String service_user_id) {
        this.service_user_id = service_user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCalculation_date() {
        return calculation_date;
    }

    public void setCalculation_date(String calculation_date) {
        this.calculation_date = calculation_date;
    }

    public String getTotal_salary() {
        return total_salary;
    }

    public void setTotal_salary(String total_salary) {
        this.total_salary = total_salary;
    }

    public String getBase_salary_money() {
        return base_salary_money;
    }

    public void setBase_salary_money(String base_salary_money) {
        this.base_salary_money = base_salary_money;
    }

    public String getSocial_insurance_money() {
        return social_insurance_money;
    }

    public void setSocial_insurance_money(String social_insurance_money) {
        this.social_insurance_money = social_insurance_money;
    }

    public String getSocial_insurance_late_money() {
        return social_insurance_late_money;
    }

    public void setSocial_insurance_late_money(String social_insurance_late_money) {
        this.social_insurance_late_money = social_insurance_late_money;
    }

    public String getTotal_transaction_amount() {
        return total_transaction_amount;
    }

    public void setTotal_transaction_amount(String total_transaction_amount) {
        this.total_transaction_amount = total_transaction_amount;
    }

    public String getTotal_commission() {
        return total_commission;
    }

    public void setTotal_commission(String total_commission) {
        this.total_commission = total_commission;
    }

    public String getCommission_amount() {
        return commission_amount;
    }

    public void setCommission_amount(String commission_amount) {
        this.commission_amount = commission_amount;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getCorrection_value() {
        return correction_value;
    }

    public void setCorrection_value(String correction_value) {
        this.correction_value = correction_value;
    }
}
