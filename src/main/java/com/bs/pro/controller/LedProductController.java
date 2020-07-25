package com.bs.pro.controller;

import com.alibaba.fastjson.JSON;
import com.bs.pro.bean.SCapacityAnalysis;
import com.bs.pro.bean.SCapacityPersonStatistics;
import com.bs.pro.constant.ProductConfig;
import com.bs.pro.constant.ResultCode;
import com.bs.pro.constant.ResultVO;
import com.bs.pro.service.K3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Slf4j
public class LedProductController {
    @Autowired
    private K3Service k3Service;
    @Autowired
    private ProductConfig productConfig;

    @RequestMapping("/board")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("led-product");
        return modelAndView;
    }
    @GetMapping("/productMessage")
    @ResponseBody
    public ResultVO getProductMessage(){
        ResultVO<String> result = new ResultVO<>();
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("url",productConfig.getUrl());
        dataMap.put("port",productConfig.getPort());
        result.success(dataMap);
        return result;
    }

    @GetMapping("/taskMonths")
    @ResponseBody
    public Map<String,List> taskMonths(int statisticsMonth,String startDay,String endDay){
        Map<String,List> dataMap = k3Service.taskMonths(statisticsMonth,startDay, endDay);
        return dataMap;
    }

    /**
     * desc:获取当前月五大能力数据
     * @param sCapacityAnalysisStr
     * @param statisticsStr
     * @return
     */
    @GetMapping("/currCapacityCharts")
    @ResponseBody
    public Map<String,List> currCapacityCharts(String sCapacityAnalysisStr, String statisticsStr,String statisticsCurrentMonthStr){
        Map<String,List> dataMap = new HashMap<>();
        System.out.println(sCapacityAnalysisStr);
        SCapacityAnalysis sCapacityAnalysis = new SCapacityAnalysis();
        int statisticsCurrentMonth = 0;
        if(StringUtils.isNotBlank(sCapacityAnalysisStr)){
            sCapacityAnalysis = JSON.parseObject(sCapacityAnalysisStr, SCapacityAnalysis.class);
        }
        SCapacityPersonStatistics statistics = new SCapacityPersonStatistics();
        if(StringUtils.isNotBlank(statisticsStr)){
            statistics = JSON.parseObject(statisticsStr, SCapacityPersonStatistics.class);
        }
        if(StringUtils.isNotBlank(statisticsCurrentMonthStr)){
            statisticsCurrentMonth = JSON.parseObject(statisticsCurrentMonthStr, Integer.class);
        }
        dataMap = k3Service.capacityCharts(sCapacityAnalysis,statistics,statisticsCurrentMonth);
        return dataMap;
    }

    @GetMapping("taskCharts")
    @ResponseBody
    public ResultVO taskCharts(int statisticsMonth,String startDay, String endDay) {
        ResultVO<String> result = new ResultVO<>();
        Map<String, List> orderByMonthsMap = k3Service.taskCharts(statisticsMonth,startDay, endDay);
        result.success(orderByMonthsMap);
        return result;
    }
}
