package com.binwang.dao;

import com.binwang.bean.notice.NoticeDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yy on 17/5/3.
 */
@Repository
@Mapper
public interface INoticeDao {
    @Insert("INSERT INTO notice (content, pub_date,username,add_time, mod_time) VALUES(#{content}, #{pubDate},#{username}, unix_timestamp(), unix_timestamp())")
    int add(NoticeDO notice);

    @Select("SELECT id,content,pub_date as pubDate from notice where username=#{username} order by pub_date desc limit #{start},#{pageSum}")
    List<NoticeDO> list(@Param(value = "start") int start, @Param(value = "pageSum") int pageSum,@Param(value = "username")String username);

    @Select("SELECT count(id) from notice")
    int numOfNotice();

    @Delete("DELETE FROM notice where id = #{id}")
    int delete(@Param(value = "id") Long id);

    @Update("UPDATE notice set content = #{content},pub_date = #{pubDate},mod_time = unix_timestamp() where id = #{id}")
    int update(NoticeDO noticeDO);
}
