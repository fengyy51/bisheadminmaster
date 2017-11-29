package com.binwang.bean.collect;

/**
 * Created by owen on 17/7/13.
 */
public class CListModel {
    private long id;
    private String name;
    private int isOk;
    private String recUnit;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecUnit() {
        return recUnit;
    }

    public void setRecUnit(String recUnit) {
        this.recUnit = recUnit;
    }
}
