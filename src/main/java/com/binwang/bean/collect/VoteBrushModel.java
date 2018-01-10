package com.binwang.bean.collect;

public class VoteBrushModel {
    private int id;
    private String ip;
    private String addtime;
    private String record;
    private String useragent;
    private int actId;
    private String openId;
    private String begin;
    private String end;
    private int num;
    public void VoteResultModel(){
    }
    public void VoteResultModel(int id,String ip,String addtime,String begin,String end,String record,String useragent,int actId,String openId,int num){
        this.id=id;
        this.ip=ip;
        this.addtime=addtime;
        this.record=record;
        this.useragent=useragent;
        this.actId=actId;
        this.openId=openId;
        this.begin=begin;
        this.end=end;
        this.num=num;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public int getActId() {
        return actId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAddtime() {
        return addtime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getBegin() {
        return begin;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
