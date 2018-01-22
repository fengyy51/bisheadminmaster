package com.binwang.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    @Insert("INSERT INTO user (username,password,addtime,modtime) VALUES(#{username},#{password},unix_timestamp(),unix_timestamp())")
    void register(@Param("username") String username, @Param("password") String password);

    @Select("select count(id) from user where username = #{username} limit 1")
    int isUserName(@Param("username") String username);

    @Select("select password from user where username = #{username}")
    String login(@Param("username") String username);

}
