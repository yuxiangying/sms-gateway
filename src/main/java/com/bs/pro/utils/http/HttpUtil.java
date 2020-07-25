package com.bs.pro.utils.http;


import org.apache.commons.lang3.StringUtils;

public class HttpUtil {

    public static String urlFix(String url) throws Exception {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("url is empty or null");
        }
        if (url.indexOf("http://") == -1 &&
                url.indexOf("https://") == -1) {
            url = "http://" + url;
        }
        return url;
    }

    public static void main(String[] args) throws Exception {
        //String url = "www.baidu.com";
        //String url = "http://www.baidu.com";
        //String url = "https://www.baidu.com";
        String url = null;
        System.out.println(urlFix(url));
    }
}
