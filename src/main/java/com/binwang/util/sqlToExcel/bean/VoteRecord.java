package com.binwang.util.sqlToExcel.bean;

/**
 * Created by owen on 17/8/23.
 */
public class VoteRecord {
    private String period;
    private int sum;

    public VoteRecord() {
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
