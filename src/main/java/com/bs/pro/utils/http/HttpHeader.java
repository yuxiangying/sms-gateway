package com.bs.pro.utils.http;

import java.util.HashMap;
import java.util.Map;


public class HttpHeader {

    private Map<String, String> headers = new HashMap<String, String>();

    public HttpHeader(Map<String, String> headers) throws Exception {
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

}
