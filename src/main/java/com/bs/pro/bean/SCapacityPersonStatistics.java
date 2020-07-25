package com.bs.pro.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (s_capacity_person_statistics)五大能力-人员配置-统计实体类
 *
 * @author yuxiangying
 * @since 2020-06-05 09:34:04
 */
@Data
public class SCapacityPersonStatistics implements Serializable {
    private static final long serialVersionUID = 7781358019570143985L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     *  生产人员人数
     */
    private Integer productionPersonNum;
    /**
     *  全员人数
     */
    private Integer allPersonNum;
    /**
     *  统计开始日期
     */
    private String startDate;
    /**
     *  统计结束日期
     */
    private String endDate;
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
