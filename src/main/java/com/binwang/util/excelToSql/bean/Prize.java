package com.binwang.util.excelToSql.bean;

/**
 * Created by owen on 17/8/7.
 */
public class Prize {
    private int id;
    private String actName;
    private String name;
    private String info;
    private int ratio;
    private int num;
    private String type;
    private String duijiang_time;
    private String duijiang_loc;

    public Prize() {
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActName() {
        return actName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuijiang_time() {
        return duijiang_time;
    }

    public void setDuijiang_time(String duijiang_time) {
        this.duijiang_time = duijiang_time;
    }

    public String getDuijiang_loc() {
        return duijiang_loc;
    }

    public void setDuijiang_loc(String duijiang_loc) {
        this.duijiang_loc = duijiang_loc;
    }
}
