package com.bs.pro.utils.http;

import java.util.Map;

public class HttpBuilder {

    private HttpRequest request = new HttpRequest();

    public HttpBuilder setUrl(String url) {
        request.setUrl(url);
        return this;
    }

    public HttpBuilder setHeader(Map<String, String> header) throws Exception {
        request.setHeader(new HttpHeader(header));
        return this;
    }

    public HttpBuilder setMethod(String method) throws Exception {
        request.setMethod(new HttpMethod(method));
        return this;
    }

    public HttpBuilder setBody(String body) {
        request.setBody(body);
        return this;
    }

    public HttpRequest build() {
        return request;
    }
}
