package com.bs.pro.dto;

import lombok.Data;

@Data
public class WmsProductWaterlevel {
    private String pname;
    private  Integer min;
    private  Integer max;
    private  Integer now;
}
