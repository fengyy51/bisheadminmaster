package com.binwang.service;

import com.binwang.bean.shop.ShopListModel;
import com.binwang.bean.shop.ShopParam;

import java.util.List;

/**
 * Created by owen on 17/6/7.
 */
public interface ShopService {
    Boolean add(ShopParam shopParam);

    List<ShopListModel> list(int curPage, int pageSum, String name, int area, int floor);

    int listSum(String name, int area, int floor);

    ShopParam get(long id);

    Boolean edit(ShopParam shopParam);

    Boolean delete(long id);
}
