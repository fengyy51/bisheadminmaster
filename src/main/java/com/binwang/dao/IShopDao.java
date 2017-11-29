package com.binwang.dao;

import com.binwang.bean.shop.ShopListModel;
import com.binwang.bean.shop.ShopParam;
import com.binwang.bean.shop.ShopResource;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by owen on 17/6/7.
 */
@Repository
@Mapper
public interface IShopDao {
    int addShop(ShopParam shopParam);

    @Insert("INSERT INTO shop_resources (shop_id,img_url) VALUES (#{shopId}," +
            "#{imgUrl})")
    int addResource(ShopResource shopResource);


    List<ShopListModel> listShop(@Param("name") String name,@Param("area") int area,@Param("floor") int floor,
                                 @Param("start") int start, @Param("pageSum") int pageSum);

    int listShopSum(@Param("name") String name,@Param("area") int area,@Param("floor") int floor);

    @Select("SELECT id,name,area,floor,keeper_name,shop_about,loc_about,loc_about_relation,phone,qr_code_url " +
            "FROM shop where id = #{id}")
    ShopParam findById(@Param("id") long id);

    @Select("SELECT img_url FROM shop_resources where shop_id = #{shopId}")
    List<String> findResourceByShopId(@Param("shopId") long shopId);


    @Update("UPDATE shop set name = #{name},area = #{area},floor = #{floor},keeper_name = " +
            "#{keeperName},shop_about = #{shopAbout},loc_about = #{locAbout},loc_about_relation = " +
            "#{locAboutRelation},phone = #{phone},qr_code_url = #{qrCodeUrl},mod_time = unix_timestamp() where id = #{id}")
    int edit(ShopParam shopParam);

    @Delete("DELETE FROM shop_resources where shop_id = #{id}")
    int deleteResource(@Param("id") long id);


    @Delete("DELETE FROM shop where id = #{id}")
    int delete(@Param("id") long id);

}
