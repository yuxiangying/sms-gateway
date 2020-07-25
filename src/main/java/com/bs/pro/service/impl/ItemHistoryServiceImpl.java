package com.bs.pro.service.impl;

import com.bs.pro.bean.ProItem;
import com.bs.pro.bean.ProItemHistory;
import com.bs.pro.mapper.ProItemHistoryMapper;
import com.bs.pro.service.ItemHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemHistoryServiceImpl implements ItemHistoryService {
    @Autowired
    private ProItemHistoryMapper itemHistoryMapper;

    @Override
    public void insert(ProItemHistory item){
        itemHistoryMapper.insert(item);
    }

    @Override
    public List<ProItemHistory> selectByCode(String code){
        return  itemHistoryMapper.selectByCode(code);
    }

    @Override
    public List<ProItemHistory> selectByCodes(List<String> codesList){
        return itemHistoryMapper.selectByCodes(codesList);
    }

}
