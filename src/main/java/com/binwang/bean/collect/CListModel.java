package com.binwang.bean.collect;

/**
 * Created by yy on 17/7/13.
 */
public class CListModel {
    private long id;
    private String regItem;
    private int isOk;
//    private String recUnit;

    public CListModel() {
    }

    public int getIsOk() {
        return isOk;
    }

    public void setIsOk(int isOk) {
        this.isOk = isOk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRegItem(String regItem) {
        this.regItem = regItem;
    }

    public String getRegItem() {
        return regItem;
    }
}
