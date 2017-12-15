package com.binwang.dao;

import com.binwang.bean.prize.PrizeModel;
import com.binwang.bean.prize.PrizeParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by owen on 17/8/17.
 */
@Repository
@Mapper
public interface IPrizeDao {
    @Select("select count(id) from f_user_prize where prize_id = 1 and relation_id = #{relationId}")
    int oneTotal(@Param("relationId") int relationId);

    @Select("select count(id) from f_user_prize where prize_id = 2 and relation_id = #{relationId}")
    int twoTotal(@Param("relationId") int relationId);

    @Select("select id,name,num,type from prize where type in (3,4) and relation_id = #{relationId}")
    List<PrizeModel> threeAndFourPrize(@Param("relationId") int relationId);

    List<PrizeModel> getNoLimitPrize(@Param("list") List<Integer> ids, @Param("relationId") int relationId);

    @Update("update prize set num = #{num},name=#{name},info=#{info},ratio=#{ratio},type=#{type},duijiang_time=#{duijiangTime},duijiang_loc=#{duijiangLoc} where id = #{id} and act_name = #{actName}")
    int updateNum(@Param("num") int num, @Param("id") int id,@Param("actName")String actName,@Param("name") String name,@Param("type") String type,@Param("ratio") int ratio,@Param("info") String info,@Param("duijiangTime") String duijiangTime,@Param("duijiangLoc") String duijiangLoc);

    @Delete("delete from prize where id=#{id}")
    int paramDelete(@Param("id") int id);
    List<PrizeModel>getList(@Param("type")String type,@Param("actName")String actName, @Param("start") int start, @Param("pageSum") int pageSum);

    int getListSum(@Param("type")String type,@Param("actName")String actName);
    @Select("select distinct act_name from prize ")
    List<String>getActName();

    @Select("select distinct type from prize")
    List<String>getType();

    int addPrizeParam(PrizeParam prizeParam);
}
