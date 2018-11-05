package com.binwang.dao;

import com.binwang.bean.activity.ActListModel;
import com.binwang.bean.collect.CListModel;
import com.binwang.bean.gallery.GalleryListModel;
import com.binwang.bean.gallery.GalleryProModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface IGalleryDao {
    List<GalleryListModel> listGallery(@Param("username") String username,@Param("name") String name, @Param("begin") String begin, @Param("end") String end,
                                       @Param("start") int start, @Param("pageSum") int pageSum);

    int listGallerySum(@Param("username") String username,@Param("name") String name,@Param("begin") String begin, @Param("end") String end);

    List<GalleryProModel> getProList(@Param("galleryId") String galleryId, @Param("type") int type, @Param("name")String name, @Param("start") int start, @Param("pageSum") int pageSum);

    int listProSum(@Param("name")String name, @Param("type") int type,@Param("galleryId") String galleryId);

    //审核通过人数
    @Select("select count(id) from scratch_api_galleryproduction  where gallery_id = #{galleryId} and admin_checked = 1")
    int approveSum(@Param("galleryId") String galleryId);
    //改变审核状态
    @Update("update scratch_api_galleryproduction set admin_checked = #{type} where production_id= #{productionId}")
    int handleApprove(@Param("galleryId") String galleryId, @Param("productionId") String productionId, @Param("type") int type);

    //增加活动管理者
    @Insert("insert into gallery_admin(gallery_name,gallery_user) values(#{galleryName},#{galleryAdmin}) ")
    int addAdmin(@Param("galleryName")String galleryName, @Param("galleryAdmin")String galleryAdmin);

    //全部审核通过
    @Update("update scratch_api_galleryproduction set admin_checked =1 where gallery_id=#{galleryId}")
    int allPass(@Param("galleryId") String galleryId);

    //全部取消审核
    @Update("update scratch_api_galleryproduction set admin_checked =0 where gallery_id=#{galleryId}")
    int allReject(@Param("galleryId") String galleryId);



}
