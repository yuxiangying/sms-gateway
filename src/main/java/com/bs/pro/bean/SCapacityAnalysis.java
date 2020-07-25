package com.bs.pro.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * (s_capacity_analysis)五大能力分析实体类
 *
 * @author yuxiangying
 * @since 2020-05-27 09:34:04
 */
@Data
public class SCapacityAnalysis implements Serializable {
    private static final long serialVersionUID = 4674818228462071399L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     *  分析时间（年月）
     */
    private Date capacityDate;
    /**
     *  统计开始时间（年月日）
     */
    private Date allocationStartDate;
    /**
     *  统计结束时间（年月日）
     */
    private Date allocationEndDate;
    /**
     *  6S平均达成率
     */
    private BigDecimal avgAchieveRate;
    /**
     *  6S平均达成率Str
     */
    private String avgAchieveRateStr;
    /**
     *  生产人员人数
     */
    private Integer productionPersonNum;
    /**
     *  全员人数
     */
    private Integer allPersonNum;
    /**
     *  总产值目标
     */
    private BigDecimal outputTarget;
    /**
     *  总产值目标Str
     */
    private String outputTargetStr;
    /**
     *  生产人均产值目标
     */
    private BigDecimal productionAvgOutputTarget;
    /**
     *  生产人均产值目标Str
     */
    private String productionAvgOutputTargetStr;
    /**
     *  全员人均产值目标
     */
    private BigDecimal allAvgOutputTarget;
    /**
     *  全员人均产值目标Str
     */
    private String allAvgOutputTargetStr;
    /**
     *  数据来源：entering录入，statistics统计
     */
    private String sourceType;
    /**
     *  statistics统计类型(achieve 6S达成,allocation人员配置,output产值目标，以'-'为分隔符)
     */
    private String statisticsType;
    /**
     * 是否删除：0否，1是
     */
    private String isDelete;
    /**
     * 创建人工号
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人工号
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private Date updateTime;
}
