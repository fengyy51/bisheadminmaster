package com.binwang.bean.activity;

public class RegItemListModel {
    private Long id;
    private String title;
    private String dtype;
    private String ifneed;
    private String options;
    private String username;
    public RegItemListModel(){
    }
    public RegItemListModel(Long id,String title,String dtype,String ifneed,String options,String username){
        this.id=id;
        this.title=title;
        this.dtype=dtype;
        this.ifneed=ifneed;
        this.username=username;
        this.options=options;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIfneed() {
        return ifneed;
    }

    public void setIfneed(String ifneed) {
        this.ifneed = ifneed;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }
    public void setUsername(String username){this.username=username;}

    public String getUsername() {
        return username;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}
