package com.binwang.service;

import com.binwang.bean.analysis.UserCollect;
import com.binwang.bean.analysis.Userprize;

import java.util.List;

public interface AnalysisService {
    public List<Userprize> listUserPrize();
    public List<UserCollect> listUserCollect();
}
