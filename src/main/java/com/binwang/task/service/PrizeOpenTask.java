package com.binwang.task.service;

import com.binwang.task.dao.IPrizeOpenDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by owen on 17/8/16.
 */
@Component
public class PrizeOpenTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrizeOpenTask.class);

    @Resource
    private IPrizeOpenDao prizeOpenDao;

    @Scheduled(cron = "0 0 17 17-22 8 ?")
    public void doOneB() {
        prizeOpenDao.changeOne();
        LOGGER.info("执行一等奖放量");
    }

    @Scheduled(cron = "0 0 10,17 23-24 8 ?")
    public void doOneA() {
        prizeOpenDao.changeOne();
        LOGGER.info("执行一等奖放量");
    }

    @Scheduled(cron = "0 30 13-21 17-22 8 ?")
    public void doTwoB() {
        prizeOpenDao.changeTwo();
        LOGGER.info("执行二等奖放量");
    }

    @Scheduled(cron = "0 30 12-21 23-24 8 ?")
    public void doTwoA() {
        prizeOpenDao.changeTwo();
        LOGGER.info("执行二等奖放量");
    }

}
