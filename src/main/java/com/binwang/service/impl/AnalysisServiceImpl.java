package com.binwang.service.impl;


import com.binwang.bean.analysis.Userprize;
import com.binwang.dao.AnalysisDao;
import com.binwang.exception.UserException;
import com.binwang.service.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnalysisServiceImpl implements AnalysisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisServiceImpl.class);
    @Resource
    private AnalysisDao analysisDao;

    @Override
    @Transactional
    public List<Userprize>listUserPrize(){
        try {
            List<Userprize> res = analysisDao.listUserPrize();
            return res;
        } catch (Exception e) {
            throw new UserException("获取用户抽奖记录失败！");
        }
    }
}
