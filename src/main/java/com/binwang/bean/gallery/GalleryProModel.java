package com.binwang.bean.gallery;

public class GalleryProModel {
    private int id;
    private int adminChecked;
    private String galleryId;
    private String productionId;
    private String name;
    private String image;
    private String authorId;
    public GalleryProModel(){}
    public GalleryProModel(int id,int adminChecked,String galleryId,String productionId,String name,String image,String authorId){
        this.id=id;
        this.adminChecked=adminChecked;
        this.galleryId=galleryId;
        this.productionId=productionId;
        this.name=name;
        this.image=image;
        this.authorId=authorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public int getAdminChecked() {
        return adminChecked;
    }

    public void setAdminChecked(int adminChecked) {
        this.adminChecked = adminChecked;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }
}
