package com.binwang.util.excelToSql.dao;

import com.binwang.util.excelToSql.bean.Prize;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by owen on 17/8/7.
 */
@Repository
@Mapper
public interface IPrizeExcelDao {
    @Insert("insert into prize(id,name,info,ratio,num,type,duijiang_time,duijiang_loc) values(#{id},#{name},#{info}," +
            "#{ratio},#{num},#{type},#{duijiang_time},#{duijiang_loc})")
    int insertPrize(Prize prize);
}
