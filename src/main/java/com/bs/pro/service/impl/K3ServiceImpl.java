package com.bs.pro.service.impl;

import com.bs.pro.bean.DBUtil;
import com.bs.pro.bean.ProductParamDTO;
import com.bs.pro.bean.SCapacityAnalysis;
import com.bs.pro.bean.SCapacityPersonStatistics;
import com.bs.pro.service.K3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Service
@Slf4j
public class K3ServiceImpl implements K3Service {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

    @Override
    public Map<String,List> taskMonths(int statisticsMonth,String startDay,String endDay){
        try {
            Map<String,List> resultMap = new HashMap<>();
            Calendar cal = Calendar.getInstance();
            String endDate = sdf.format(cal.getTime());
            cal.add(Calendar.MONTH, -(statisticsMonth-1));
            String startDate = sdf.format(cal.getTime());
            List<ProductParamDTO> amountProductList  = getAmountMonths(startDate, endDate, startDay, endDay);
//            Map<String, List> orderByMonthsMap = getWorkOrderByMonths(startDate, endDate,startDay,endDay);
            List<String> dateList = new ArrayList<>();
            List<BigDecimal> amountList = new ArrayList<>();
            List<Integer> finishList = new ArrayList<>();
            for(ProductParamDTO paramDTO: amountProductList){
                dateList.add(paramDTO.getDateStr());
                amountList.add(paramDTO.getAmount().divide(new BigDecimal(10000),2,BigDecimal.ROUND_DOWN ));
                finishList.add(paramDTO.getOrderNum());
            }
            /*resultMap.put("date",orderByMonthsMap.get("date"));
            resultMap.put("amount",amountList);
            resultMap.put("task",orderByMonthsMap.get("finishData"));*/
            resultMap.put("date",dateList);
            resultMap.put("amount",amountList);
            resultMap.put("task",finishList);
            //  时间条件
            return resultMap;
        } catch (Exception e) {
            log.error("K3ServiceImpl taskMonths exception:{}",e);
            return null;
        }
    }

    @Override
    public Map<String, List> capacityCharts(SCapacityAnalysis sCapacityAnalysis,SCapacityPersonStatistics statistics,int statisticsCurrentMonth) {
        Map<String,List> dataMap = new HashMap<>();
        List<Integer> data = new ArrayList<>();
        List<ProductParamDTO> finishProductList = new ArrayList<>();
        List<ProductParamDTO> unfinishProductList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        String startDay = "1";//查询开始日期
        String endDay = "31";//查询结束日期
        try {
            cal.add(Calendar.MONTH, statisticsCurrentMonth);
            String month = sdf.format(cal.getTime());
            String startDate = month;
            String endDate = month;
            Integer productionPersonNum = sCapacityAnalysis.getProductionPersonNum();
            Integer allPersonNum = sCapacityAnalysis.getAllPersonNum();
            //6S达成率
            data.add(sCapacityAnalysis.getAvgAchieveRate().intValue());
            //计算开始和结束日期
            if("statistics".equals(sCapacityAnalysis.getSourceType()) && null!=statistics && StringUtils.isNotEmpty(statistics.getStartDate())){
                startDay = statistics.getStartDate();
            }
            if("statistics".equals(sCapacityAnalysis.getSourceType()) && null!=statistics && StringUtils.isNotEmpty(statistics.getEndDate())){
                endDay = statistics.getEndDate();
            }
            //人员取值
            if("statistics".equals(sCapacityAnalysis.getSourceType()) && null!=statistics){//取统计数据
                productionPersonNum = statistics.getProductionPersonNum();
                allPersonNum = statistics.getAllPersonNum();
            }
            List<ProductParamDTO> amountProductList  = getAmountMonths(startDate, endDate, startDay, endDay);
            if(!amountProductList.isEmpty()){
                //生产人均产值达成率
                int proPresonOutputRate = amountProductList.get(0).getAmount().divide(new BigDecimal(productionPersonNum),2,BigDecimal.ROUND_DOWN ).divide(sCapacityAnalysis.getProductionAvgOutputTarget(),2,BigDecimal.ROUND_DOWN ).multiply(new BigDecimal(100)).intValue();
                data.add(proPresonOutputRate);
                //总产值达成率
                int allOutputRate =  amountProductList.get(0).getAmount().divide(sCapacityAnalysis.getOutputTarget(),2,BigDecimal.ROUND_DOWN ).multiply(new BigDecimal(100)).intValue();
                //暂时写死
                data.add(allOutputRate);
//                data.add(97);
                //全员人均产值达成率
                int allPresonOutputRate = amountProductList.get(0).getAmount().divide(new BigDecimal(allPersonNum),2,BigDecimal.ROUND_DOWN ).divide(sCapacityAnalysis.getAllAvgOutputTarget(),2,BigDecimal.ROUND_DOWN ).multiply(new BigDecimal(100)).intValue();
                data.add(allPresonOutputRate);
            }
            //任务单完成率
            Map<String, List> map = getWorkOrderByMonths(startDate, endDate,startDay,endDay);
            if(!map.isEmpty()){
                finishProductList = map.get("finish");
                unfinishProductList = map.get("unfinish");
                if(!finishProductList.isEmpty()){
                    BigDecimal finishOrderNum = new BigDecimal(finishProductList.get(0).getOrderNum());
                    BigDecimal unfinishOrderNum = new BigDecimal(unfinishProductList.get(0).getOrderNum());
                    int orderRate = finishOrderNum.divide(finishOrderNum.add(unfinishOrderNum),2,BigDecimal.ROUND_DOWN ).multiply(new BigDecimal(100)).intValue();
                    //暂时写死
//                    data.add(orderRate);
                    data.add(100);
                }
            }
            dataMap.put("data",data);
        }catch (Exception e) {
            log.error("K3ServiceImpl capacityCharts exception:{}",e);
            return null;
        }
        return dataMap;
    }

    @Override
    public Map<String, List> taskCharts(int statisticsMonth,String startDay,String endDay) {
        Map<String, List> orderByMonthsMap = new HashMap<>();
        try {
            Calendar cal = Calendar.getInstance();
            String endDate = sdf.format(cal.getTime());
            cal.add(Calendar.MONTH, -(statisticsMonth-1));
            String startDate = sdf.format(cal.getTime());
            orderByMonthsMap = getWorkOrderByMonths(startDate, endDate,startDay,endDay);
        } catch (Exception e) {
            log.error("K3ServiceImpl taskCharts exception:{}", e);
            return null;
        }
        return orderByMonthsMap;
    }


    /**
     * 求完成与未完成的任务单数
     * @param startDate 查询开始月
     * @param endDate 查询结束月
     * @return
     */
    private Map<String,List> getWorkOrderByMonths(String startDate,String endDate,String startDay,String endDay){
        Map<String,List> map = new HashMap<>();
        try {
            List<ProductParamDTO> finishProductList = new ArrayList<>();
            List<ProductParamDTO> unfinishProductList = new ArrayList<>();
            List<String> dateList = new ArrayList<>();
            List<Integer> finishList = new ArrayList<>();
            List<Integer> unfinishList = new ArrayList<>();
            StringBuffer timeLimit = new StringBuffer() ;
            StringBuffer finishQuerySql =  new StringBuffer();
            StringBuffer unfinishQuerySql =  new StringBuffer();
            timeLimit.append(" and CONVERT(varchar(6), a.fcommitdate, 112)>='"+startDate+"' and CONVERT(varchar(6), a.fcommitdate, 112)<='"+endDate+"'");
            timeLimit.append(" and Datename(day,a.FCommitDate)>="+startDay+" and Datename(day,a.FCommitDate)<="+endDay);
            finishQuerySql.append(getWorkOrderSQL("3",timeLimit.toString()));
            unfinishQuerySql.append(getWorkOrderSQL("1",timeLimit.toString()));
            /*ResultSet finishResultSet = DBUtil.executeQuery(finishQuerySql.toString());
            while(finishResultSet.next()){
                ProductParamDTO productParam = new ProductParamDTO();
                productParam.setDateStr(finishResultSet.getString(1));
                productParam.setOrderNum(finishResultSet.getInt(2));
                dateList.add(finishResultSet.getString(1));
                finishList.add(finishResultSet.getInt(2));
                finishProductList.add(productParam);
            }*/
            finishProductList  = getAmountMonths(startDate, endDate, startDay, endDay);
            for(ProductParamDTO paramDTO: finishProductList){
                dateList.add(paramDTO.getDateStr());
                finishList.add(paramDTO.getOrderNum());
            }
            ResultSet unfinishResultSet = DBUtil.executeQuery(unfinishQuerySql.toString());
            while(unfinishResultSet.next()){
                ProductParamDTO productParam = new ProductParamDTO();
                productParam.setDateStr(unfinishResultSet.getString(1));
                productParam.setOrderNum(unfinishResultSet.getInt(2));
                unfinishList.add(unfinishResultSet.getInt(2));
                unfinishProductList.add(productParam);
            }
            map.put("finish",finishProductList);
            map.put("unfinish",unfinishProductList);
            map.put("date",dateList);
            map.put("finishData",finishList);
            map.put("unfinishData",unfinishList);
            System.out.println(finishQuerySql.toString());
            System.out.println(unfinishQuerySql);
            return  map;
        } catch (Exception e) {
            log.error("K3ServiceImpl getWorkOrderByMonths exception:{}",e);
            return null;
        }
    }

    /**
     * 完成总金额
     * @param startDay 查询开始日期
     * @param endDay 查询结束日期
     * @return
     */
    private List getAmountMonths(String startDate,String endDate,String startDay,String endDay){
        try {
            List<ProductParamDTO> amountProductList = new ArrayList<>();
            StringBuffer amountQuerySql =  new StringBuffer();
            /*amountQuerySql.append("SELECT CONVERT(varchar(6), a.fdate, 112) as dd,SUM ( b.FQty ),SUM ( b.FAmount ) FROM ICStockBill a LEFT JOIN ICStockBillEntry b ON a.FInterID= b.FInterID  ");
            amountQuerySql.append(" LEFT JOIN t_Department c ON a.FDeptID= c.FItemID  LEFT JOIN t_icitem d ON d.FItemID= b.FItemID WHERE a.ftrantype= '2'");
            amountQuerySql.append(" AND CONVERT(varchar(6), a.fdate, 112)>= '"+startDate+"' AND CONVERT(varchar(6), a.fdate, 112)<= '"+endDate+"'");
            amountQuerySql.append(" AND Datename(day,a.fdate)>="+startDay+" AND Datename(day,a.fdate)<="+endDay+" ");
            amountQuerySql.append(" GROUP BY CONVERT(varchar(6), a.fdate, 112) ORDER BY dd;");*/
            amountQuerySql.append("SELECT CONVERT(varchar(6), a.fdate, 112) as dd,COUNT ( DISTINCT FBillNo ) ,SUM ( b.FConsignAmount ) FROM ICStockBill a LEFT JOIN ICStockBillEntry b ON a.FInterID= b.FInterID  ");
            amountQuerySql.append(" LEFT JOIN t_Department c ON a.FDeptID= c.FItemID  WHERE a.ftrantype= '21' AND a.FStatus = 1 ");
            amountQuerySql.append(" AND CONVERT(varchar(6), a.fdate, 112)>= '"+startDate+"' AND CONVERT(varchar(6), a.fdate, 112)<= '"+endDate+"'");
            amountQuerySql.append(" AND Datename(day,a.fdate)>="+startDay+" AND Datename(day,a.fdate)<="+endDay+" ");
            amountQuerySql.append(" GROUP BY CONVERT(varchar(6), a.fdate, 112) ORDER BY dd;");
            ResultSet amountResultSet = DBUtil.executeQuery(amountQuerySql.toString());
            while(amountResultSet.next()){
                ProductParamDTO productParam = new ProductParamDTO();
                productParam.setDateStr(amountResultSet.getString(1));
                productParam.setOrderNum(amountResultSet.getInt(2));
                productParam.setAmount(amountResultSet.getBigDecimal(3));
                amountProductList.add(productParam);
            }
            return  amountProductList;
        } catch (Exception e) {
            log.error("K3ServiceImpl getAmountMonths exception:{}",e);
            return null;
        }
    }

    private StringBuffer getWorkOrderSQL(String fStatus,String dateLimit){
        StringBuffer querySql =  new StringBuffer();
        querySql.append("select CONVERT(varchar(6), a.fcommitdate, 112) as dd,COUNT(*)  from ICMO a  where a.FStatus= "+fStatus);
        querySql.append(dateLimit);
        querySql.append(" group by CONVERT(varchar(6), a.fcommitdate, 112) ORDER BY dd;");
        return querySql;
    }

}
