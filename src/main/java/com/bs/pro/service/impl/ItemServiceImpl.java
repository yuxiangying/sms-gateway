package com.bs.pro.service.impl;

import com.bs.pro.bean.ProItem;
import com.bs.pro.mapper.ProItemMapper;
import com.bs.pro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ProItemMapper itemMapper;

    @Override
    public List<ProItem> selectAll() {
        return itemMapper.selectAll();
    }

    @Override
    public void insert(ProItem item){
        itemMapper.insert(item);
    }

    @Override
    public ProItem selectByCode(String code){
        return itemMapper.selectByCode(code);
    }

    @Override
    public List<ProItem> searchByParam(Map<Object,Object> paramMap){
        return itemMapper.searchByParam(paramMap);
    }

    /*
     * 登记进度
     */
    @Override
    public void updateProgress(ProItem proItem){
        itemMapper.updateProgress(proItem);
    }

    /*
     * 项目变更
     */
    @Override
    public void modItem(ProItem proItem){
        itemMapper.modItem(proItem);
    }

    /*
     * 项目状态修改(暂停、取消、继续)
     */
    @Override
    public void updateStatus(Map paramMap){
        itemMapper.updateStatus(paramMap);
    }

    @Override
    public List<ProItem> selectValidItems(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        String oneMonthAgo = formatter.format(cal.getTime());
        return itemMapper.selectValidItems(oneMonthAgo);
    }

    @Override
    public List<ProItem> selectValidStatisticItems(){
        return itemMapper.selectValidStatisticItems();
    }

}
