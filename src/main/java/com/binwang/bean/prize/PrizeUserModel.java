package com.binwang.bean.prize;

public class PrizeUserModel {
    private int id;
    private String openId;
    private String code;
    private int isUse;
    private int prizeId;
    private String addtime;
    private String modtime;
    public PrizeUserModel() {
    }

    public PrizeUserModel(int id,String openId, String code, int isUse,int prizeId,String addtime,String modtime) {
        this.openId = openId;
        this.code = code;
        this.id=id;
        this.isUse=isUse;
        this.prizeId=prizeId;
        this.modtime=modtime;
        this.addtime=addtime;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getIsUse() {
        return isUse;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }

    public int getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(int prizeId) {
        this.prizeId = prizeId;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getModtime() {
        return modtime;
    }

    public void setModtime(String modtime) {
        this.modtime = modtime;
    }
}
