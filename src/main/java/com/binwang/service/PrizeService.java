package com.binwang.service;

import com.binwang.bean.prize.PrizeModel;

import java.util.List;

/**
 * Created by owen on 17/8/17.
 */
public interface PrizeService {
    List<PrizeModel> getOneAndTwo(int collectId);

    List<PrizeModel> getThreeAndFour(int collectId);

    List<PrizeModel> getNoLimit(int collectId);

    Boolean changeNoLimitNum(int collectId, int num, int id);
}
