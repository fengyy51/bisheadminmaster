package com.binwang.service;

import com.binwang.bean.notice.NoticeDO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by owen on 17/5/3.
 */
public interface NoticeService {

    @Transactional
    Boolean add(String content, String pubDate);

    List<NoticeDO> list(int curPage, int pageSum,String username);

    int numOfNotice();

    @Transactional
    Boolean delete(Long id);

    @Transactional
    Boolean update(NoticeDO noticeDO);
}
