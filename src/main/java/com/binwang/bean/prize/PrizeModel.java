package com.binwang.bean.prize;

/**
 * Created by owen on 17/8/17.
 */
public class PrizeModel {
    private int id;
    private int type;
    private String name;
    private int num;

    public PrizeModel() {
    }

    public PrizeModel(int id, int type, String name, int num) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
