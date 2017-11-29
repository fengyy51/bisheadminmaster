package com.binwang.dao;

import com.binwang.bean.notice.NoticeDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by owen on 17/5/3.
 */
@Repository
@Mapper
public interface INoticeDao {
    @Insert("INSERT INTO notice (content, pub_date,add_time, mod_time) VALUES(#{content}, #{pubDate}, unix_timestamp(), unix_timestamp())")
    int add(NoticeDO notice);

    @Select("SELECT id,content,pub_date as pubDate from notice order by pub_date desc limit #{start},#{pageSum}")
    List<NoticeDO> list(@Param(value = "start") int start, @Param(value = "pageSum") int pageSum);

    @Select("SELECT count(id) from notice")
    int numOfNotice();

    @Delete("DELETE FROM notice where id = #{id}")
    int delete(@Param(value = "id") Long id);

    @Update("UPDATE notice set content = #{content},pub_date = #{pubDate},mod_time = unix_timestamp() where id = #{id}")
    int update(NoticeDO noticeDO);
}
