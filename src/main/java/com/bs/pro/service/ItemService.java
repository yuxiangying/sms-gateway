package com.bs.pro.service;


import com.bs.pro.bean.ProItem;

import java.util.List;
import java.util.Map;

public interface ItemService {
    public List<ProItem> selectAll();

    public void insert(ProItem item);

    public ProItem selectByCode(String code);

    public List<ProItem> searchByParam(Map<Object,Object> paramMap);

    /*
     * 登记进度
     */
    public void updateProgress(ProItem proItem);

    /*
     * 项目变更
     */
    public void modItem(ProItem proItem);

    /*
     * 项目状态修改(暂停、取消、继续)
     */
    public void updateStatus(Map paramMap);

    /*
     * 查找有效状态的ProItem 一个月前结案的过滤掉
     */
    public List<ProItem> selectValidItems();

    public List<ProItem> selectValidStatisticItems();

}
