package com.bs.pro.dto;

import lombok.Data;

@Data
public class DayTrend {
    private String fname;
    private Integer month;
    private Integer day;
    private Integer sum;
    private Integer task;
    private Double  rate;
}
