package com.binwang.bean.notice;

/**
 * Created by owen on 17/5/3.
 */
public class NoticeDO {
    private Long id;
    private String content;
    private String pubDate;

    public NoticeDO() {
    }

    public NoticeDO(String content, String pubDate) {
        this.content = content;
        this.pubDate = pubDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
}
