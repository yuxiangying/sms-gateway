package com.bs.pro.constant;

public class Constants {

    //成功失败的code值
    public final static String SUCCESS = "1";
    public final static String ERROR = "0";

    public final static String K3_DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //测试环境(内网)
//    public final static String K3_DBURL = "jdbc:sqlserver://192.168.10.2:1433";
    //生产环境（外网）
    public final static String K3_DBURL = "jdbc:sqlserver://223.83.254.181:2433";
    public final static String K3_DBUSER = "bs";
    public final static String K3_DBPASSWORD = "bs@123456";

    public static final String AES_KEYSEED = "sy3ddgs_dm_dfss_lsdwccs";
}
