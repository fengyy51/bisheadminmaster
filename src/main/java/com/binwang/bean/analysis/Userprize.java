package com.binwang.bean.analysis;

public class Userprize
{
    private Long num;
    private String addtime;
    public Userprize(){}
    public Userprize(Long num,String addtime){
        this.num=num;
        this.addtime=addtime;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getNum() {
        return num;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
