package com.bs.pro.mapper;

import com.bs.pro.bean.ProItem;
import com.bs.pro.bean.ProItemHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProItemHistoryMapper {

    public void insert(ProItemHistory itemHistory);

    public List<ProItemHistory> selectByCode(String code);

    public List<ProItemHistory> selectByCodes(List<String> codesList);

}
