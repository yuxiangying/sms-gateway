package com.bs.pro.service;



import com.bs.pro.bean.ProItemHistory;

import java.util.List;

public interface ItemHistoryService {

    public void insert(ProItemHistory itemHistory);

    public List<ProItemHistory> selectByCode(String code);

    public List<ProItemHistory> selectByCodes(List<String> codesList);

}
