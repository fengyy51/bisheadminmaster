package com.binwang.task.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by owen on 17/8/16.
 */
@Repository
@Mapper
public interface IPrizeOpenDao {
    @Update("update prize set ratio = 100000,num = 1 where id = 1")
    int changeOne();

    @Update("update prize set ratio = 100000,num = 1 where id = 2")
    int changeTwo();

}
