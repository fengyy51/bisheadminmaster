package com.binwang.dao;

import com.binwang.bean.activity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by owen on 17/5/11.
 */
@Repository
@Mapper
public interface IActivityDao {
    int addAct(ActParam actParam);
    int regitemAdd(RegItemListModel regItemListModel);

    @Insert("INSERT INTO activity_resources (activity_id,type,content) VALUES (#{activityId}," +
            "#{type},#{content})")
    int addResource(ActResource actResource);


    List<ActListModel> listAct(@Param("name") String name,@Param("username")String username, @Param("begin") String begin, @Param("end") String end,
                               @Param("start") int start, @Param("pageSum") int pageSum);

    int listActSum(@Param("name") String name,@Param("username")String username, @Param("begin") String begin, @Param("end") String end);

    List<RegItemListModel> regitemList(@Param("start") int start, @Param("pageSum") int pageSum,@Param("username")String username);

    int regitemListSum(@Param("username")String username);
    List<RegItemListModel> actregitemList(@Param("username")String username);

    @Select("SELECT id,name,reg,reg_item as regItem,description,face_obj as faceObj,judge_charge as judgeCharge,begin,end,reg_dead_line as regDeadLine,loc_about as locAbout," +
            "limit_num as limitNum,cost," +
            "broad_cast_img as broadCastImg from activity where id = #{id}")
    ActParam findById(@Param("id") long id);


    @Select("SELECT type,content from activity_resources where activity_id = #{actId}")
    List<ActResource> findByActId(@Param("actId") long id);


    @Update("UPDATE activity set name = #{name},face_obj = #{faceObj},judge_charge = #{judgeCharge}," +
            "begin = #{begin},reg_dead_line = #{regDeadLine}," +
            "end = #{end},limit_num = #{limitNum},cost = #{cost},reg=#{reg},reg_item=#{regItem},description=#{description}," +
            "loc_about = #{locAbout},broad_cast_img = #{broadCastImg},mod_time = unix_timestamp() where id = #{id}")
    int edit(ActParam actParam);
    @Update("UPDATE reg_item set title = #{title},dtype = #{dtype},ifneed = #{ifneed},options=#{options} where id = #{id}")
    int regitemEdit(@Param("id") long id,@Param("title") String title,@Param("dtype") String dtype,@Param("ifneed")String ifneed,@Param("options")String options);

    int deleteResource(@Param("id") long id, @Param("type") int type);

    @Update("UPDATE activity_resources set content = #{content} where activity_id = #{id} and type = #{type}")
    int updateActRes(@Param("id") long id, @Param("type") int type, @Param("content") String content);

    @Delete("DELETE FROM activity where id =#{id}")
    int delete(@Param("id") long id);
    @Delete("DELETE FROM reg_item where id =#{id} and username=#{username}")
    int regitemDelete(@Param("id") long id,@Param("username")String username);

    List<SignListModel> signList(@Param("actId") long actId, @Param("code") String code, @Param("sign") int sign,
                                 @Param("start") int start, @Param("pageSum") int pageSum);

    int signListSum(@Param("actId") long actId, @Param("code") String code, @Param("sign") int sign);


    @Update("UPDATE f_user_act set is_sign = 1 where open_id = #{openId} and act_id = #{actId}")
    int doSign(@Param("actId") long actId, @Param("openId") String openId);

    @Update("UPDATE f_user_act set status = 0 where open_id = #{openId} and act_id = #{actId}")
    int doReg(@Param("actId") long actId, @Param("openId") String openId);
}
