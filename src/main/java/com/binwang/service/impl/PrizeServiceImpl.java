package com.binwang.service.impl;

import com.binwang.bean.prize.PrizeModel;
import com.binwang.dao.IPrizeDao;
import com.binwang.service.PrizeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owen on 17/8/17.
 */
@Service
public class PrizeServiceImpl implements PrizeService {

    @Resource
    private IPrizeDao prizeDao;

    @Value("${binwang.prize.nolimit.ids}")
    private String IDS;

    private final int ONESUM = 10;
    private final int TWOSUM = 75;
    private final String ONENAME = "500元现金券";
    private final String TWONAME = "100元现金券";


    @Override
    public List<PrizeModel> getOneAndTwo(int collectId) {
        int oneNum = ONESUM - prizeDao.oneTotal(collectId);
        int twoNum = TWOSUM - prizeDao.twoTotal(collectId);
        List<PrizeModel> l = new ArrayList<>();
        PrizeModel p1 = new PrizeModel(1, 1, ONENAME, oneNum);
        PrizeModel p2 = new PrizeModel(2, 2, TWONAME, twoNum);
        l.add(p1);
        l.add(p2);
        return l;
    }

    @Override
    public List<PrizeModel> getThreeAndFour(int collectId) {
        return prizeDao.threeAndFourPrize(collectId);
    }

    @Override
    public List<PrizeModel> getNoLimit(int collectId) {
        List<Integer> lIds = new ArrayList<>();
        String[] idArray = IDS.split(",");
        for (int i = 0; i < idArray.length; i++) {
            lIds.add(Integer.parseInt(idArray[i]));
        }
        return prizeDao.getNoLimitPrize(lIds, collectId);
    }

    @Override
    @Transactional
    public Boolean changeNoLimitNum(int collectId, int num, int id) {
        String[] idArray = IDS.split(",");
        Boolean flag = false;
        for (int i = 0; i < idArray.length; i++) {
            if (Integer.parseInt(idArray[i]) == id)
                flag = true;
        }
        if (flag) {
            //id在不限量内可以修改
            if (prizeDao.updateNum(num, id, collectId) > 0)
                return true;
            else
                return false;
        } else
            return false;
    }
}
