package com.bs.pro.controller;

import com.bs.pro.constant.ResultCode;
import com.bs.pro.constant.ResultVO;
import com.bs.pro.constant.WfConstant;
import com.bs.pro.dto.*;
import com.bs.pro.service.IBsWarehouseRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class WarehouseController {

    @Autowired
    IBsWarehouseRateService warehouseRateService;

    @GetMapping("/warehouse")
    public ModelAndView warehouse() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("warehouse");
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date(System.currentTimeMillis());
//        modelAndView.addObject("date", formatter.format(date));
        return modelAndView;
    }

    @GetMapping("/wms/warehouse")
    public ModelAndView wmsWarehouse() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wmsWarehouse");
        return modelAndView;
    }

    @GetMapping(value = "/wmsMonthOutputSum")
    @ResponseBody
    public ResultVO wmsMonthOutputSum(){
        ResultVO<String> result = new ResultVO<>();
        try {
            OutDaySum list = warehouseRateService.wmsGetDaymonthSum(WfConstant.ORDER_TYPE_OUT);
            result.success(list);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            // 从此处获取服务器时间
            result.setMessage(formatter.format(date));
        }catch (Exception e){
            log.error("获取本日、月出库sum数据失败",e);
            result.fail(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/wmsMonthTop4")
    @ResponseBody
    public ResultVO wmsMonthTop4(){
        ResultVO<String> result = new ResultVO<>();
        try {
            List<MaterialTopDto> list = warehouseRateService.wmsGetInOrOutputTop4(WfConstant.ORDER_TYPE_OUT);
            result.success(list);
        }catch (Exception e){
            log.error("各部门获取本月出库outputtop5数据失败",e);
            result.fail(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/wmsRuchuday")
    @ResponseBody
    public ResultVO wmsRuchuday(){
        ResultVO<String> result = new ResultVO<>();
        try {
            List<RuchuRecordday> list = warehouseRateService.wmsGetRuchuday();
            result.success(list);
        }catch (Exception e){
            log.error("获取本日、月出库sum数据失败",e);
            result.fail(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/wmsdayproduct" )
    @ResponseBody
    public ResultVO wmsdayproduct(){
        ResultVO<String> result = new ResultVO<>();
        try {
            List<Dayproduct> list = warehouseRateService.wmsdayproduct();
            result.success(list);
        }catch (Exception e){
            log.error("wms获取本日产量数据失败",e);
            result.fail(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/wmswarterlevel" )
    @ResponseBody
    public ResultVO wmswarterlevel(){
        ResultVO<String> result = new ResultVO<>();
        try {
            List<WmsProductWaterlevel> list = warehouseRateService.wmswarterlevel();
            result.success(list);
        }catch (Exception e){
            log.error("wms产品水位图失败",e);
            result.fail(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/wmsdaytrend")
    @ResponseBody
    public ResultVO wmsdaytrend(){
        ResultVO<String> result = new ResultVO<>();
        try {
            List<DayTrend> list = warehouseRateService.wmsdaytrend();
            result.success(list);
        }catch (Exception e){
            log.error("wms获取每月达成率失败",e);
            result.fail(ResultCode.FAIL.getCode(),e.getMessage());
        }
        return result;
    }

}
