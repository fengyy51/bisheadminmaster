package com.binwang.dao;

import com.binwang.bean.analysis.UserCollect;
import com.binwang.bean.analysis.Userprize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
//yy
@Repository
@Mapper
public interface AnalysisDao {

    @Select("SELECT count(id) as num,add_time as addtime from f_user_prize GROUP BY Time(FROM_UNIXTIME(add_time))")
    public List<Userprize>listUserPrize();
    @Select("SELECT count(id) as num,add_time as addtime from f_collect GROUP BY Time(FROM_UNIXTIME(add_time))")
    public List<UserCollect>listUserCollect();
}
