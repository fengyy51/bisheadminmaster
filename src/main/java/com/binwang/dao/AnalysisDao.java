package com.binwang.dao;

import com.binwang.bean.analysis.Userprize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnalysisDao {

    @Select("SELECT count(id) as num,Time(FROM_UNIXTIME(add_time)) as addtime from f_user_prize GROUP BY Time(FROM_UNIXTIME(add_time))")
    public List<Userprize>listUserPrize();
}
