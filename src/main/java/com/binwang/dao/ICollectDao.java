package com.binwang.dao;

import com.binwang.bean.activity.VoteParam;
import com.binwang.bean.collect.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yy
 */
@Repository
@Mapper
public interface ICollectDao {
    List<CListModel> getList(@Param("collectId") long collectId, @Param("type") int type,@Param("openId")String openId, @Param("start") int start, @Param("pageSum") int pageSum);

    int listSum(@Param("collectId") long collectId, @Param("type") int type,@Param("openId")String openId);

    //审核通过人数
    @Select("select count(id) from f_user_act where act_id = #{collectId} and is_ok = 1")
    int approveSum(@Param("collectId") long collectId);
//改变审核状态
    @Update("update f_user_act set is_ok = #{isOk} where id = #{itemId} and act_id = #{collectId}")
    int handleApprove(@Param("collectId") long collectId, @Param("itemId") long itemId, @Param("isOk") int isOk);

    @Select("select reg_item as regItem from f_user_act " +
            "where id = #{itemId} and act_id = #{collectId}")
    CDetailModel getDetail(@Param("itemId") long itemId, @Param("collectId") long collectId);

    @Update("update f_collect set product_first = #{url} where id = #{id}")
    int firstImgUpdate(@Param("id") long id, @Param("url") String url);

    @Update("update f_collect set product_img_urls = #{urls} where id = #{id}")
    int detailImgUpdate(@Param("id") long id, @Param("urls") String urls);
//    增加投票配置
    int addVoteParam(VoteParam voteParam);
    @Select("select count(id) from vote_params where act_id=#{actId}")
    int getNum(Long actId);

    @Update("UPDATE vote_params SET act_name=#{actName},begin=#{begin},end=#{end},pro_num=#{proNum},vote_num=#{voteNum},share_num=#{shareNum},vote_max_num=#{voteMaxNum},vote_decoration=#{voteDecoration} WHERE id = #{id}")
    int updateVoteParam(VoteParam voteParam);
    //投票活动列表

    List<VoteListModel> listVote(@Param("actName") String actName, @Param("username")String username, @Param("begin") String begin, @Param("end") String end,
                                  @Param("start") int start, @Param("pageSum") int pageSum);

    int listVoteSum(@Param("actName") String actName,@Param("username")String username, @Param("begin") String begin, @Param("end") String end);

    @Select("select act_name as actName,begin,end,pro_num as proNum,vote_num as voteNum,share_num as shareNum,vote_max_num as voteMaxNum,vote_decoration as voteDecoration from vote_params where id=#{id}")
    VoteParam VoteParamGet(@Param("id")long id);

    @Delete("delete from vote_params where id=#{id}")
    int PrizeParamDelete(int id);

    List<VoteResultModel> voteResult(@Param("itemId") long itemId,@Param("actId")long actId ,@Param("start") int start, @Param("pageSum") int pageSum);

    int voteResultSum(@Param("itemId") long itemId,@Param("actId")long actId );

    @Update("update f_vote set vote_num=#{voteNum} where id=#{id}")
    int voteResultEdit(VoteResultModel voteResultModel);
    @Select("select distinct act_name from vote_params where username=#{username} ")
    List<String>getActName(@Param("username")String username);
    List<VoteBrushModel> getBrushlist(@Param("id")int id, @Param("actName") String actName, @Param("begin")String begin, @Param("end")String end, @Param("num") int num);
    int getBrushlistSum(@Param("id")int id,@Param("actName") String actName,@Param("begin")String begin,@Param("end")String end,@Param("num") int num);
    List<Integer>getRecordIDS(@Param("actName")String actName);
    @Select("select id from vote_params where act_name=#{actName}")
    int getVoteId(@Param("actName") String actName);

}
