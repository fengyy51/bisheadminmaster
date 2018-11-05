package com.binwang.dao;

import com.binwang.bean.votedata.VoteListModel;
import com.binwang.bean.votedata.VoteParam;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface IVoteDataDao {
    @Insert("INSERT INTO vote_data_yiwu (username,act_id,content,img_url) VALUES(#{username},#{actId},#{content},#{voteImgsUrl} )")
    int addVoteData(@Param("username")String username,@Param("actId")long actId,@Param("content")String content,@Param("voteImgsUrl")String voteImgsUrl);

    List<VoteListModel> listVoteData(@Param("username")String username,@Param("content")String content,@Param("act_id")int act_id,@Param("start") int start, @Param("pageSum") int pageSum);

    int listVoteDataSum(@Param("username")String username,@Param("content")String content,@Param("act_id")int act_id);

    @Select("select img_url from vote_data_yiwu where id=#{id}")
    String getImgById(@Param("id")long id);

    @Select("select content from vote_data_yiwu where id=#{id}")
    String getContentById(@Param("id")long id);

    @Select("select act_id from vote_data_yiwu where id=#{id}")
    long getActIdById(@Param("id")long id);

    @Update("update vote_data_yiwu set act_id=#{actId},content=#{content},img_url=#{voteImgsUrl} where id=#{id}")
    int edit(@Param("id")long id,@Param("actId")long actId,@Param("content")String content,@Param("voteImgsUrl")String voteImgsUrl);

    @Delete("delete from vote_data_yiwu where id=#{id}")
    int delete(@Param("id")long id);
}
