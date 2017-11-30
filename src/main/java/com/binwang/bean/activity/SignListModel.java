package com.binwang.bean.activity;

/**
 * Created by yy on 17/7/6.
 */
public class SignListModel {
    private String openId;
    private String code;
    private int sign;
    private int status;
    private String regItem;
    public SignListModel() {
    }

    public SignListModel(String openId, String code, int sign,int status,String regItem) {
        this.openId = openId;
        this.code = code;
        this.sign = sign;
        this.status=status;
        this.regItem=regItem;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getRegItem() {
        return regItem;
    }

    public void setRegItem(String regItem) {
        this.regItem = regItem;
    }
}
