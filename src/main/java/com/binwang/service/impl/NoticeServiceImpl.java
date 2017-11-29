package com.binwang.service.impl;

import com.binwang.bean.notice.NoticeDO;
import com.binwang.dao.INoticeDao;
import com.binwang.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by owen on 17/5/3.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Resource
    private INoticeDao noticeDao;

    @Override
    public Boolean add(String content, String pubDate) {
        NoticeDO notice = new NoticeDO(content, pubDate);
        if (noticeDao.add(notice) > 0)
            return true;
        else
            return false;
    }

    @Override
    public List<NoticeDO> list(int curPage, int pageSum) {
        try {
            return noticeDao.list((curPage - 1) * pageSum, pageSum);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int numOfNotice() {
        try {
            return noticeDao.numOfNotice();
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            int res = noticeDao.delete(id);
            if (res > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(NoticeDO noticeDO) {
        try {
            int res = noticeDao.update(noticeDO);
            if(res > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }
}
