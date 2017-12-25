package com.binwang.bean.activity;

/**
 * Created by yy on 17/5/11.
 */
public class ActResource {
    private Long id;
    private Long activityId;
    private int type; // 1.活动详情
    private String content;

    public ActResource() {
    }

    public ActResource(Long activityId, int type, String content) {
        this.activityId = activityId;
        this.type = type;
        this.content = content;
    }

    public ActResource(Long id, Long activityId, int type, String content) {
        this.id = id;
        this.activityId = activityId;
        this.type = type;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
