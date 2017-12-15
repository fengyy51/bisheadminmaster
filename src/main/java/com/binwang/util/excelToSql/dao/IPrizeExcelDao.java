package com.binwang.util.excelToSql.dao;

import com.binwang.util.excelToSql.bean.Prize;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by yy on 17/8/7.
 */
@Repository
@Mapper
public interface IPrizeExcelDao {
    @Insert("insert into prize(act_name,name,info,ratio,num,type,duijiang_time,duijiang_loc) values(#{actName},#{name},#{info}," +
            "#{ratio},#{num},#{type},#{duijiang_time},#{duijiang_loc})")
    int insertPrize(Prize prize);

    @Select("select count(id) from prize where act_name=#{actName} and name=#{name}")
    int getPrize(@Param("actName") String actName,@Param("name")String name);

    @Update("update prize set info = #{info},ratio=#{ratio},num=#{num},type=#{type},duijiang_time=#{duijiang_time},duijiang_loc=#{duijiang_loc} where act_name = #{actName} and name=#{name}")
    int updatePrize(Prize prize);
}
