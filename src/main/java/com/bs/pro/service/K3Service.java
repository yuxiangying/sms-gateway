package com.bs.pro.service;

import com.bs.pro.bean.SCapacityAnalysis;
import com.bs.pro.bean.SCapacityPersonStatistics;

import java.util.List;
import java.util.Map;

public interface K3Service {
    Map<String,List> taskMonths(int statisticsMonth,String startDay,String endDay);

    Map<String,List> capacityCharts(SCapacityAnalysis sCapacityAnalysis,SCapacityPersonStatistics statistics,int statisticsCurrentMonth);

    Map<String,List> taskCharts(int statisticsMonth,String startDay,String endDay);
}
