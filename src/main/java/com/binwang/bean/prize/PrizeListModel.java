package com.binwang.bean.prize;

public class PrizeListModel {
    private Long id;
    private String name;
    private String begin;
    private String end;
    public PrizeListModel() {
    }
    public PrizeListModel(Long id, String name, String begin, String end) {
        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
