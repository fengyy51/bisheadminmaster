package com.binwang.util.sqlToExcel.dao;

import com.binwang.util.sqlToExcel.bean.VoteRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by owen on 17/8/23.
 */
@Repository
@Mapper
public interface IVoteRecordDao {
    @Select("select concat(date(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')),' '," +
            "hour(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')),':'," +
            "floor(minute(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s'))/5)*5) as period, " +
            "count(*) as sum from f_vote_record where record like '%,${id},%' and addtime " +
            "between 1503589800 and 1503590400 group by " +
            "date(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')), " +
            "hour(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')), " +
            "floor(minute(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s'))/5)*5")
    List<VoteRecord> getRecord(@Param("id") int id);


    @Select("select concat(date(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')),' '," +
            "hour(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')),':'," +
            "floor(minute(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s'))/5)*5) as period, " +
            "count(*) as sum from f_vote_record where record like '%,${id},%' and addtime " +
            "between #{start} and #{end} group by " +
            "date(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')), " +
            "hour(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s')), " +
            "floor(minute(from_unixtime(addtime,'%Y%-%c%-%e% %k%:%i%:%s'))/5)*5")
    List<VoteRecord> getRecordTask(@Param("id") int id, @Param("start") long start, @Param("end") long end);
}
