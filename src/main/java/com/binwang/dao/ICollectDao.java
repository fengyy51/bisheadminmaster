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
 * Created by yy
 */
@Repository
@Mapper
public interface ICollectDao {
    List<CListModel> getList(@Param("collectId") long collectId, @Param("type") int type,@Param("openId")String openId, @Param("start") int start, @Param("pageSum") int pageSum);

    int listSum(@Param("collectId") long collectId, @Param("type") int type,@Param("openId")String openId);

    //审核通过人数
    @Select("select count(id) from f_user_act where act_id = #{collectId} and is_ok = 1")
    int approveSum(@Param("collectId") long collectId);
//改变审核状态
    @Update("update f_user_act set is_ok = #{isOk} where id = #{itemId} and act_id = #{collectId}")
    int handleApprove(@Param("collectId") long collectId, @Param("itemId") long itemId, @Param("isOk") int isOk);

    @Select("select reg_item as regItem from f_user_act " +
            "where id = #{itemId} and act_id = #{collectId}")
    CDetailModel getDetail(@Param("itemId") long itemId, @Param("collectId") long collectId);

    @Update("update f_collect set product_first = #{url} where id = #{id}")
    int firstImgUpdate(@Param("id") long id, @Param("url") String url);

    @Update("update f_collect set product_img_urls = #{urls} where id = #{id}")
    int detailImgUpdate(@Param("id") long id, @Param("urls") String urls);
}
