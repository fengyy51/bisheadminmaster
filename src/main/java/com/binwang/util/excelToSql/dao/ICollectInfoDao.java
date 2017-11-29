package com.binwang.util.excelToSql.dao;

import com.binwang.util.excelToSql.bean.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by owen on 17/8/16.
 */
@Repository
@Mapper
public interface ICollectInfoDao {
    @Update("update f_collect set name = #{name},brand_name=#{brand_name}," +
            "intro = #{intro} where id = #{id}")
    int updateInfo(Collect collect);
}
