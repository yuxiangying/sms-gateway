package com.bs.pro.mapper;

import com.bs.pro.bean.ProItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProItemMapper {

    public void insert(ProItem item);

    public List<ProItem> selectAll();

    public ProItem selectByCode(String code);

    public List<ProItem> searchByParam(Map<Object,Object> param);

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
     * 查找有效状态的ProItem
     */
    public List<ProItem> selectValidItems(String oneMonthAgo);

    public List<ProItem> selectValidStatisticItems();
}
