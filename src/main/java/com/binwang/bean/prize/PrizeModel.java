package com.binwang.bean.prize;

/**
 * Created by yy on 17/8/17.
 */
public class PrizeModel {
    private int id;
    private String type;
    private String name;
    private int num;
    private int relationId;
    private String info;
    private int ratio;
    private String duijiangTime;
    private String duijiangLoc;

    public PrizeModel() {
    }

    public PrizeModel(int id, String type, String name, int num,int relationId,String info,int ratio,String duijiangLoc,String duijiangTime) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.num = num;
        this.duijiangLoc=duijiangLoc;
        this.duijiangTime=duijiangTime;
        this.ratio=ratio;
        this.relationId=relationId;
        this.info=info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getRelationId() {
        return relationId;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public String getDuijiangLoc() {
        return duijiangLoc;
    }

    public void setDuijiangLoc(String duijiangLoc) {
        this.duijiangLoc = duijiangLoc;
    }

    public String getDuijiangTime() {
        return duijiangTime;
    }

    public void setDuijiangTime(String duijiangTime) {
        this.duijiangTime = duijiangTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
