package com.binwang.dao;

import com.binwang.bean.prize.PrizeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("update prize set num = #{num} where id = #{id} and relation_id = #{relationId}")
    int updateNum(@Param("num") int num, @Param("id") int id, @Param("relationId") int relationId);
}
