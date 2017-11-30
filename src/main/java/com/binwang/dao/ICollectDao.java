package com.binwang.dao;

import com.binwang.bean.collect.CDetailModel;
import com.binwang.bean.collect.CListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yy on 17/7/13.
 */
@Repository
@Mapper
public interface ICollectDao {
    List<CListModel> getList(@Param("collectId") long collectId, @Param("type") int type, @Param("start") int start, @Param("pageSum") int pageSum);

    int listSum(@Param("collectId") long collectId, @Param("type") int type);

    @Select("select count(id) from f_collect where collect_id = #{collectId} and is_ok = 1")
    int approveSum(@Param("collectId") long collectId);

    @Update("update f_collect set is_ok = #{isOk} where id = #{itemId} and collect_id = #{collectId}")
    int handleApprove(@Param("collectId") long collectId, @Param("itemId") long itemId, @Param("isOk") int isOk);

    @Select("select name,wechat_id as wechatId,mobile,brand_name as brandName,brand_img_url as brandImgUrl," +
            "product_img_urls as productImgUrls,intro,is_ok as isOk,rec_unit as recUnit from f_collect " +
            "where id = #{itemId} and collect_id = #{collectId}")
    CDetailModel getDetail(@Param("itemId") long itemId, @Param("collectId") long collectId);

    @Update("update f_collect set product_first = #{url} where id = #{id}")
    int firstImgUpdate(@Param("id") long id, @Param("url") String url);

    @Update("update f_collect set product_img_urls = #{urls} where id = #{id}")
    int detailImgUpdate(@Param("id") long id, @Param("urls") String urls);
}
