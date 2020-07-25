package com.bs.pro.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bs.pro.dto.*;
import com.bs.pro.dto.output.BmoutputSumResponse;
import com.bs.pro.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DN
 * @since 2019-05-28
 */
@Slf4j
@Service
public class BsWarehouseRateServiceImpl implements IBsWarehouseRateService {

    @Override
    public void createWarehouseRate() {

    }
    @Override
    public void createWarehouseRate2() {

    }


    //-----------------------------------------------------------------------------------------------------start
    @Override
    public List<OutDaySum> wmsGetMonthOutSumData(Integer type) {     //from k3
        List<OutDaySum> topList = new ArrayList<>();
        //当前日期
        LocalDate now = LocalDate.now();
        //本月第一天
        LocalDate monthFirstDay = now.with(TemporalAdjusters.firstDayOfMonth());

        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;

        //获取本月的出库/入库的总金额
        Double totalAmount = 1.1;//materialBoxMapper.getAmountByRange(monthFirstDay,now,type,null);
        if(null != totalAmount){
            //获取出库/入库的金额数据
            //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);

            List<BmoutputSumResponse> outlist= httpget_monthsum(monthFirstDay.toString());
            log.info("bmoutputsum:"+outlist.toString());
            //topList;
            if(null != topList && !topList.isEmpty()){
                for(OutDaySum dto : topList){
                    /*
                    if(totalAmount > 0.0){
                        BigDecimal bigDecimal = new BigDecimal((dto.getAmount()/totalAmount) * 100 );
                        dto.setAmountRate(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                        //获取当前物料上个月的出库/入库金额
                        Double oldAmount = materialBoxMapper.getAmountByRange(perMonthFirstDay,perMonthLastDay,type,dto.getMaterialId());
                        dto.setAmountTag(Tools.compareSize(dto.getAmount(),oldAmount));
                    }
                    */

                }
            }
        }
        return topList;
    }



    /**
     * 获取本月入库物料top4数据
     * @return
     */
    @Override
    public List<MaterialTopDto> wmsGetInOrOutputTop4(Integer type) {
        List<MaterialTopDto> topList = new ArrayList<>();
        List<MaterialTopDto> topList2 = new ArrayList<>();

        //当前日期
        LocalDate now = LocalDate.now();
        //本月第一天
        LocalDate monthFirstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        //上月第一天
        LocalDate perMonthFirstDay = monthFirstDay.plusMonths(-1);
        //上月最后一天
        LocalDate perMonthLastDay = perMonthFirstDay.with(TemporalAdjusters.lastDayOfMonth());
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;

        //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);
        List<BmoutputSumResponse> outlist= new ArrayList<>();
        List<BmoutputSumResponse> outlist2= new ArrayList<>();
        outlist2 = httpget_monthsum(monthFirstDay.toString());
        List<Bmout_monthtask> tasklist = new ArrayList<>();
        tasklist = httpget_bmouttask(year,month);
        // 重试10次 TODO 为啥会出现tasklist数组空的情况
        for(int i=0;i<10;i++) {
            while (CollectionUtils.isEmpty(tasklist)) {
                tasklist = httpget_bmouttask(year,month);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // 应该 log.error
                    log.info("wmsGetInOrOutputTop4 httpget_bmouttask Thread.sleep exception:{}", e);
                }
            }
            if(!CollectionUtils.isEmpty(tasklist)){
                break;
            }
        }
        log.info("wmsGetInOrOutputTop4 httpget_bmouttask data:{}", tasklist.toString());
        MaterialTopDto totaldto=new MaterialTopDto();
        totaldto.setMaterialName("总计");
        totaldto.setMaterialId(0);
        double msum=0;
        int mtask=0;
        int i;
        BmoutputSumResponse ds= new BmoutputSumResponse();
        ds.setFNAME("电商部");
        ds.setAMOUNT("0");
        outlist.add(ds);
        BmoutputSumResponse gw= new BmoutputSumResponse();
        gw.setFNAME("国外销售部");
        gw.setAMOUNT("0");
        outlist.add(gw);
        BmoutputSumResponse eb= new BmoutputSumResponse();
        eb.setFNAME("销售二部");
        eb.setAMOUNT("0");
        outlist.add(eb);
        BmoutputSumResponse yb= new BmoutputSumResponse();
        yb.setFNAME("销售一部");
        yb.setAMOUNT("0");
        outlist.add(yb);
        BmoutputSumResponse gc= new BmoutputSumResponse();
        gc.setFNAME("工程项目部");
        gc.setAMOUNT("0");
        outlist.add(gc);

        //
        for(BmoutputSumResponse  out : outlist2){
            if(out.getFNAME().equals("电商部")){
                if(out.getAMOUNT()!="")
                    outlist.get(0).setAMOUNT(out.getAMOUNT());
            }else if(out.getFNAME().equals("国外销售部")){
                if(out.getAMOUNT()!="")
                    outlist.get(1).setAMOUNT(out.getAMOUNT());
            }else if(out.getFNAME().equals("销售二部")){
                if(out.getAMOUNT()!="")
                    outlist.get(2).setAMOUNT(out.getAMOUNT());
            }else if(out.getFNAME().equals("销售一部")){
                if(out.getAMOUNT()!="")
                    outlist.get(3).setAMOUNT(out.getAMOUNT());
            }else if(out.getFNAME().equals("工程项目部")){
                if(out.getAMOUNT()!="")
                    outlist.get(4).setAMOUNT(out.getAMOUNT());
            }
        }

        for(BmoutputSumResponse  out : outlist){
           log.info("bmoutput:"+out.toString());
            if(out.getFNAME().equals("电商部"))
            {
                MaterialTopDto dto=new MaterialTopDto();
                dto.setMaterialName("电商部");
                dto.setMaterialId(1);
                dto.setAmount(Double.valueOf(out.getAMOUNT()));
                dto.setMonthtask(tasklist.get(0).getTask());
                msum = msum + Double.valueOf(out.getAMOUNT()) ;
                mtask = mtask + tasklist.get(0).getTask();

                BigDecimal bigDecimal = new BigDecimal((dto.getAmount()/dto.getMonthtask()) * 100 );
                dto.setAmountRate(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                topList.add(dto);
            }else if(out.getFNAME().equals("国外销售部")){
                MaterialTopDto dto=new MaterialTopDto();
                dto.setMaterialName("国外销售部");
                dto.setMaterialId(2);
                //janua
                //dto.setAmount(Double.valueOf(out.getAMOUNT())+924953.41);
                dto.setAmount(Double.valueOf(out.getAMOUNT()));
                dto.setMonthtask(tasklist.get(1).getTask());

                //msum = msum + Double.valueOf(out.getAMOUNT())+924953.41 ;
                msum = msum + Double.valueOf(out.getAMOUNT()) ;
                mtask = mtask + tasklist.get(1).getTask();
                BigDecimal bigDecimal = new BigDecimal((dto.getAmount()/dto.getMonthtask()) * 100 );
                dto.setAmountRate(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                topList.add(dto);
            }else if(out.getFNAME().equals("销售二部")){
                MaterialTopDto dto=new MaterialTopDto();
                dto.setMaterialName("销售二部");
                dto.setMaterialId(3);
                dto.setAmount(Double.valueOf(out.getAMOUNT()));
                dto.setMonthtask(tasklist.get(2).getTask());
                msum = msum + Double.valueOf(out.getAMOUNT()) ;
                mtask = mtask + tasklist.get(2).getTask();
                BigDecimal bigDecimal = new BigDecimal((dto.getAmount()/dto.getMonthtask()) * 100 );
                dto.setAmountRate(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                topList.add(dto);

            }else if(out.getFNAME().equals("销售一部")){

                MaterialTopDto dto=new MaterialTopDto();
                dto.setMaterialName("销售一部");
                dto.setMaterialId(4);
                dto.setAmount(Double.valueOf(out.getAMOUNT()));
                dto.setMonthtask(tasklist.get(3).getTask());

                msum = msum + Double.valueOf(out.getAMOUNT()) ;
                mtask = mtask + tasklist.get(3).getTask();
                BigDecimal bigDecimal = new BigDecimal((dto.getAmount()/dto.getMonthtask()) * 100 );
                dto.setAmountRate(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                topList.add(dto);
            }else if(out.getFNAME().equals("工程项目部")){
                MaterialTopDto dto=new MaterialTopDto();
                dto.setMaterialName("工程项目部");
                dto.setMaterialId(5);
                dto.setAmount(Double.valueOf(out.getAMOUNT()));
                dto.setMonthtask(tasklist.get(4).getTask());

                msum = msum + Double.valueOf(out.getAMOUNT()) ;
                mtask = mtask + tasklist.get(4).getTask();
                BigDecimal bigDecimal = new BigDecimal((dto.getAmount()/dto.getMonthtask()) * 100 );
                dto.setAmountRate(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
                topList.add(dto);
            }
            totaldto.setAmount(msum);
            totaldto.setMonthtask(mtask);
        }
        topList.add(totaldto);

        return topList;
    }


    @Override
    public List<RuchuRecordday> wmsGetRuchuday() {   //from k3
        List<RuchuRecordday> topList = new ArrayList<>();
        //当前日期
        LocalDate now = LocalDate.now();
        //本月第一天
        //LocalDate monthFirstDay = now.with(TemporalAdjusters.firstDayOfMonth());

        Double totalAmount = 1.1;
        if(null != totalAmount){
            //获取出库/入库的金额数据
            //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);

            topList= httpget_ruchuday(now.toString());
            if(topList != null)
            log.info("ruchuday:"+topList.size());

        }
        return topList;
    }

    @Override
    public OutDaySum wmsGetDaymonthSum(Integer type) {
        OutDaySum  topList = new OutDaySum();
        //当前日期
        LocalDate now = LocalDate.now();
        //本月第一天
        //LocalDate monthFirstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        Double totalAmount = 1.1;

        topList= httpget_daymonthsum(year,month);
        log.info("wmsGetDaymonthSum daymonthsum:"+topList.toString());
        return topList;
    }

    @Override
    public OutDaySum wmsGetDaymonthSumIn() {
        OutDaySum  topList = new OutDaySum();
        //当前日期
        LocalDate now = LocalDate.now();
        //本月第一天
        //LocalDate monthFirstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        Double totalAmount = 1.1;
        if(null != totalAmount){
            //获取出库/入库的金额数据
            //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);

            topList= httpget_daymonthsumIn(year,month);
            log.info("daymonthsum:"+topList.toString());

        }
        return topList;
    }


    @Override
    public List<Dayproduct> wmsdayproduct() {
        List<Dayproduct> topList = new ArrayList<>();        //当前日期
        LocalDate now = LocalDate.now();

        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        Double totalAmount = 1.1;
        if(null != totalAmount){
            //获取出库/入库的金额数据
            //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);
            topList= httpget_dayproduct(year,month);
            if(topList != null)
            log.info("dayproduct:"+topList.toString());

        }
        return topList;
    }

    @Override
    public List<DayTrend> wmsdaytrend() {
        List<DayTrend> topList = new ArrayList<>();        //当前日期
        LocalDate now = LocalDate.now();

        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        Double totalAmount = 1.1;
        if(null != totalAmount){
            //获取出库/入库的金额数据
            //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);

            topList= httpget_dayTrend(year,month);
            log.info("DayTrend:"+topList.toString());

        }
        return topList;
    }


    @Override
    public WmsOwnspercent wmspercent() {
        WmsOwnspercent topList = new WmsOwnspercent();        //当前日期
        //ocalDate now = LocalDate.now();


        Double totalAmount = 1.1;
        if(null != totalAmount){
            //获取出库/入库的金额数据
            //topList = materialBoxMapper.wmsgetoutputTop(monthFirstDay,now,type);

            topList.setOwned(20000);
            topList.setTotal(300000);//httpget_dayTrend(year,month);
            log.info("DayTrend:"+topList.toString());

        }
        return topList;
    }

    @Override
    public List<WmsProductWaterlevel> wmswarterlevel() {
        List<WmsProductWaterlevel> topList = new ArrayList<>();        //当前日期
        LocalDate now = LocalDate.now();

        Calendar cale = null;
        cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        Double totalAmount = 1.1;
        // 重试10次 TODO 为啥会出现topList数组空的情况
        for(int i=0;i<10;i++) {
            while (CollectionUtils.isEmpty(topList)) {
                topList = httpget_waterlevel(year, month);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // 应该 log.error
                    log.info("wmswarterlevel dayproduct Thread.sleep exception:{}", e);
                }
            }
            if(!CollectionUtils.isEmpty(topList)){
                break;
            }
        }
        log.info("wmswarterlevel dayproduct data:"+topList.toString());

        return topList;
    }


    public List<WmsProductWaterlevel> httpget_waterlevel(int year, int month){ //} throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("year", String.valueOf(year));
        params.put("month", String.valueOf(month));
        String url="http://192.168.10.26/dp/productwaterlevel.php";
        log.info("url:"+url);
        String ret =  HttpRequest.sendGet(url,params);
        log.info(ret);
        //JSONObject root = JSONObject.parseObject(ret);
        //String  DATA= root.getString("Data");

        List<WmsProductWaterlevel>  datalist = JSONArray.parseArray(ret,WmsProductWaterlevel.class);

        log.info("DayTrend:"+datalist.toString());
        return datalist;

    }

    public List<DayTrend> httpget_dayTrend(int year, int month){ //} throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("year", String.valueOf(year));
        params.put("month", String.valueOf(month));
        String url="http://192.168.10.26/dp/bmouttrend.php";
        log.info("url:"+url);
        String ret =  HttpRequest.sendGet(url,params);
        log.info(ret);
        List<DayTrend>  datalist = JSONArray.parseArray(ret,DayTrend.class);
        log.info("DayTrend:"+datalist.toString());
        return datalist;

    }



    public List<Dayproduct> httpget_dayproduct(int year, int month){ //} throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("year", String.valueOf(year));
        params.put("month", String.valueOf(month));
        String url="http://192.168.10.26/dp/dayoutproduct_fromk3.php";//?year=" +year+"&month="+month;
        log.info("url:"+url);
        String ret =  HttpRequest.sendGet(url,params);
        log.info(ret);
        //JSONObject root = JSONObject.parseObject(ret);
        //String  DATA= root.getString("Data");

        List<Dayproduct>  datalist = JSONArray.parseArray(ret,Dayproduct.class);
        if(datalist != null)
        log.info("Dayproduct:"+datalist.toString());
        return datalist;

    }


    public List<RuchuRecordday> httpget_ruchuday(String stime){ //} throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("time", stime);
        log.info("stime:"+stime);  //stime:2019-11-01
        String ret =  HttpRequest.sendGet("http://192.168.10.26/dp/ruchukuday_fromk3.php", params);
        //log.info(ret);
        //JSONObject root = JSONObject.parseObject(ret);
        //String  DATA= root.getJSONObject("Data").getString("DATA");

        List<RuchuRecordday>  datalist = JSONArray.parseArray(ret,RuchuRecordday.class);
        if(datalist!= null)
        log.info("ruchu:"+datalist.size()+","+datalist.toString());
        return datalist;
    }


    public List<BmoutputSumResponse> httpget_monthsum(String stime){ //} throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("date", stime);
        log.info("stime:"+stime);  //stime:2019-11-01
        String ret =  HttpRequest.sendGet("http://192.168.10.26/dp/bmout_monthsum.php", params);
        //log.info(ret);
        JSONObject root = JSONObject.parseObject(ret);
        String  DATA= root.getJSONObject("Data").getString("DATA");

        if(DATA==null)
            return null;
        List<BmoutputSumResponse>  datalist = JSONArray.parseArray(DATA,BmoutputSumResponse.class);
        log.info("bmtop:"+datalist.size()+","+datalist.toString());
        return datalist;
    }

    public List<Bmout_monthtask> httpget_bmouttask(int year, int month){ //} throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("year", String.valueOf(year));
        params.put("month", String.valueOf(month));
        String url="http://192.168.10.26/dp/bmouttask.php";//?year=" +year+"&month="+month;
        log.info("url:"+url);
        String ret =  HttpRequest.sendGet(url,params);
        log.info(ret);
        //JSONObject root = JSONObject.parseObject(ret);
        //String  DATA= root.getString("Data");

        List<Bmout_monthtask>  datalist = JSONArray.parseArray(ret,Bmout_monthtask.class);
        log.info("task:"+datalist.toString());
        return datalist;

    }


    public OutDaySum httpget_daymonthsum(int year, int month){ //} throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("year", String.valueOf(year));
        params.put("month", String.valueOf(month));
        String ret =  HttpRequest.sendGet("http://192.168.10.26/dp/bmout_daymonthsum.php", params);
        //log.info(ret);
        JSONObject root = JSONObject.parseObject(ret);
        //String  DATA= root.getJSONObject("Data").getString("DATA");
        String yearret =  HttpRequest.sendGet("http://192.168.10.26/dp/bmout_yearsum.php", params);
        JSONObject yearj = JSONObject.parseObject(yearret);

        String yeartask =  HttpRequest.sendGet("http://192.168.10.26/dp/bmouttaskyear.php", params);
        JSONObject yeart = JSONObject.parseObject(yeartask);
        //{"daytask":190.38461538462,"days":26,"yeartask":52000}
       // List<OutDaySum>  datalist = JSONArray.parseArray(DATA,BmoutputSumResponse.class);
        OutDaySum daysum = new OutDaySum();
        daysum.setDaysum(root.getInteger("daysum"));
        daysum.setDaytask(yeart.getInteger("daytask"));
        daysum.setMonthsum(root.getInteger("monthsum"));
        daysum.setMonthtask(root.getInteger("monthtask"));
        daysum.setYearsum(yearj.getInteger("yearsum"));
        daysum.setYeartask(yeart.getInteger("yeartask"));
        // 重试10次 TODO  yeartask怎么会出现0 ？
        for(int i=0;i<10;i++) {
            while (null==yeart.getInteger("yeartask")||0==yeart.getInteger("yeartask")) {
                yeartask =  HttpRequest.sendGet("http://192.168.10.26/dp/bmouttaskyear.php", params);
                yeart = JSONObject.parseObject(yeartask);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // 应该 log.error
                    log.info("wmswarterlevel httpget_daymonthsum Thread.sleep exception:{}", e);
                }
            }
            if(null!=yeart.getInteger("yeartask")&&0!=yeart.getInteger("yeartask")){
                daysum.setYeartask(yeart.getInteger("yeartask"));
                break;
            }
        }

        log.info("httpget_daymonthsum httpget_daymonthsum data:"+daysum.toString());
        return daysum;

    }


    public OutDaySum httpget_daymonthsumIn(int year, int month){ //} throws IOException {

        Map<String, String> params = new HashMap<>();
        params.put("year", String.valueOf(year));
        params.put("month", String.valueOf(month));
        String ret =  HttpRequest.sendGet("http://192.168.10.26/dp/bminbydaymonth.php", params);
        log.info(ret);
        JSONObject root = JSONObject.parseObject(ret);
        //String  DATA= root.getJSONObject("Data").getString("DATA");
        OutDaySum daysum = new OutDaySum();
        daysum.setDaysum(root.getInteger("daysum"));
        daysum.setDaytask(root.getInteger("daytask"));
        daysum.setMonthsum(root.getInteger("monthsum"));
        daysum.setMonthtask(root.getInteger("monthtask"));
        log.info("dayinsum:"+daysum.toString());
        return daysum;

    }









}









