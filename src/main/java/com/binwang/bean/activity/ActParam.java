package com.binwang.bean.activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owen on 17/5/11.
 */
public class ActParam {
    private String username;//系统的使用者
    private long id;
    private String name;
    private int faceObj;
    private int judgeCharge;
    private String begin;
    private String end;
    private String regDeadLine;
    private String locAbout;
    private int limitNum;
    private int cost;
    private String broadCastImg;
    private String content;
    private Boolean contentChange;
    private String reg;
    private String regItem;
    private String description;

    public ActParam() {
    }

    public ActParam(long id, String name, int faceObj, int judgeCharge, String begin, String end, String regDeadLine, String locAbout, int limitNum, int cost, String broadCastImg, String content, Boolean contentChange, String reg, String regItem, String description,String username) {
        this.id = id;
        this.name = name;
        this.faceObj = faceObj;
        this.judgeCharge = judgeCharge;
        this.begin = begin;
        this.end = end;
        this.regDeadLine = regDeadLine;
        this.locAbout = locAbout;
        this.limitNum = limitNum;
        this.cost = cost;
        this.broadCastImg = broadCastImg;
        this.content = content;
        this.contentChange = contentChange;
        this.reg = reg;
        this.regItem = regItem;
        this.description = description;
        this.username=username;
    }

    public Boolean getContentChange() {
        return contentChange;
    }

    public void setContentChange(Boolean contentChange) {
        this.contentChange = contentChange;
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

    public int getFaceObj() {
        return faceObj;
    }

    public void setFaceObj(int faceObj) {
        this.faceObj = faceObj;
    }

    public int getJudgeCharge() {
        return judgeCharge;
    }

    public void setJudgeCharge(int judgeCharge) {
        this.judgeCharge = judgeCharge;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRegDeadLine() {
        return regDeadLine;
    }

    public void setRegDeadLine(String regDeadLine) {
        this.regDeadLine = regDeadLine;
    }

    public String getLocAbout() {
        return locAbout;
    }

    public void setLocAbout(String locAbout) {
        this.locAbout = locAbout;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getBroadCastImg() {
        return broadCastImg;
    }

    public void setBroadCastImg(String broadCastImg) {
        this.broadCastImg = broadCastImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getReg() {
        return reg;
    }

    public void setRegItem(String regItem) {
        this.regItem = regItem;
    }

    public String getRegItem() {
        return regItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
