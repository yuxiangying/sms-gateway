package com.bs.pro.service;



import com.bs.pro.dto.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DN
 * @since 2019-05-28
 */
public interface IBsWarehouseRateService {

    /**
     * 生成当天的库存使用率数据
     */
    void createWarehouseRate();

    /**
     * 生成当天的各个库存金额占比数据
     */
    void createWarehouseRate2();

    /**
     * 获取仓库今日相关数据
     * @return
     */
    //WarehouseDataDto getWarehouseByDay();

    /**
     * 获取本月入库物料top数据
     * @return
     */
    //List<MaterialTopDto> getInOrOutboundTopData(Integer type);


     //day,month output
    List<OutDaySum> wmsGetMonthOutSumData(Integer type);
    //output top4
    //List<MaterialTopDto> wmsGetInOrOutputTop4(Integer type);
    List<MaterialTopDto> wmsGetInOrOutputTop4(Integer type);
    List<RuchuRecordday> wmsGetRuchuday();
    OutDaySum wmsGetDaymonthSum(Integer type);
    List<Dayproduct> wmsdayproduct();
    List<DayTrend> wmsdaytrend();
    OutDaySum wmsGetDaymonthSumIn();
    WmsOwnspercent wmspercent();
    List<WmsProductWaterlevel> wmswarterlevel();


    /**
     * 获取仓库本月相关数据
     * @return
     */
   // WarehouseDataDto getWarehouseByMonth();

    /**
     * 获取仓库当前情况数据
     * @return
     */
   // WarehouseAccountedDto getWarehouseSituation();

    /**
     * 获取本年库存占用率趋势图数据
     * @return
     */
    //WarehouseAccountedDto getAccLineChart();

    /**
     * 获取各仓库库存金额占比数据
     * @return
     */
    //List<InventoryAccDto> getAmountAcc();

    /**
     * 获取各仓库库存金额占比折线图数据
     * @return
     */
   // List<List<InventoryAccDto>> getAmountAccHistogram();

    /**
     * 获取去年各仓库库存金额占比折线图数据
     * @return
     */
    //List<List<InventoryAccDto>> getLastYearAmountAccHistogram();


}
