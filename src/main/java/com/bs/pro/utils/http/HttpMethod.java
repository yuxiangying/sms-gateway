package com.bs.pro.utils.http;


import org.apache.commons.lang3.StringUtils;

public class HttpMethod {

    public final static String GET = "GET";
    public final static String POST = "POST";
    private String method = GET;

    public HttpMethod(String method) throws Exception {
        super();
        method = method.trim().toUpperCase();
        boolean ret = check(method);
        if (!ret) {
            throw new RuntimeException("HttpMethod invald method:" + method);
        }
        this.method = StringUtils.upperCase(method);
    }

    public void setMethod(String method) {
        this.method = StringUtils.upperCase(method);
    }

    public String getMethod() {
        return method;
    }

    private boolean check(String input) {
        if (input.equals("POST") || input.equals("GET")) {
            return true;
        }
        return false;
    }
}
