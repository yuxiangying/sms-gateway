package com.bs.pro.utils.http;

import com.bs.pro.utils.https.SSLX509TrustManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@Log4j2
public class HttpRequest {

    private final static int CONN_TIME_OUT = 8000;
    private final static int READ_TIME_OUT = 5000;
    private String url = "";

    private HttpHeader header = null;
    private HttpMethod method = null;
    private String body = "";
    private boolean bHttps = false;

    public HttpRequest setUrl(String url) {
        this.url = url;
        if (url.startsWith("https")) {
            bHttps = true;
        }
        return this;
    }

    public HttpRequest setHeader(HttpHeader header) {
        this.header = header;
        return this;
    }

    public HttpRequest setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public String send() throws Exception {
        if (url == null || method == null) {
            throw new RuntimeException("http param:url or method is null!");
        }
        // 如果是https
        if (bHttps)
            return method.getMethod().equals("POST") ? postSSl() : getSSL();
        else
            return method.getMethod().equals("POST") ? post() : get();
    }

    private String post() throws Exception {

        URL requestUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestUrl
                .openConnection();
        connection.setRequestMethod(method.getMethod());
        connection.setConnectTimeout(CONN_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        // add header
        addHeaders(connection);
        // add content length
        addContentLength(connection);

        OutputStream os = null;
        try {
            os = connection.getOutputStream();
            os.write(body.getBytes("utf-8"));
            os.flush();
            String out = Result(connection);
            connection.disconnect();
            return out;
        } catch (Exception e) {
            throw new RuntimeException("can not send data:" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(os);
        }

    }

    private String get() throws Exception {
        URL requestUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestUrl
                .openConnection();
        connection.setRequestMethod(method.getMethod());
        connection.setConnectTimeout(CONN_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setDoInput(true);

        String out = Result(connection);
        connection.disconnect();
        return out;
    }

    private String postSSl() throws Exception {
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = {new SSLX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());

        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        URL requestUrl = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) requestUrl
                .openConnection();
        connection.setRequestMethod(method.getMethod());
        connection.setConnectTimeout(CONN_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setSSLSocketFactory(ssf);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        // add header
        addHeaders(connection);
        // add content length
        addContentLength(connection);

        OutputStream os = null;
        try {
            os = connection.getOutputStream();
            os.write(body.getBytes("utf-8"));
            os.flush();
            String out = Result(connection);
            connection.disconnect();
            return out;
        } catch (Exception e) {
            throw new RuntimeException("can not send data:" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(os);
        }

    }

    private String getSSL() throws Exception {
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = {new SSLX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());

        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        URL requestUrl = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) requestUrl
                .openConnection();
        connection.setRequestMethod(method.getMethod());
        connection.setSSLSocketFactory(ssf);
        connection.setUseCaches(false);
        connection.setConnectTimeout(CONN_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);
        connection.setDoInput(true);

        String out = Result(connection);
        connection.disconnect();
        return out;
    }

    private String Result(HttpURLConnection conn) throws Exception {
        InputStream is = null;
        try {
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                String out = IOUtils.toString(is);
                if (out.length() > 0) {
                    log.info("HttpRequest url:" + url + " data:" + body
                            + " response:" + conn.getResponseMessage());
                    return out;
                }
            }

            log.error("HttpRequest url:" + url + " data:" + body + " response:"
                    + conn.getResponseMessage());
            return "";
        } catch (Exception e) {
            log.error("HttpRequest url:" + url + " data:" + body + " response:"
                    + e.getMessage());
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }

    }

    private void addHeaders(HttpURLConnection conn) {
        if (header == null) {
            return;
        }
        Map<String, String> headers = header.getHeaders();
        for (Entry<String, String> item : headers.entrySet()) {
            if (StringUtils.isNotBlank(item.getKey())
                    && StringUtils.isNotBlank(item.getValue())) {
                conn.addRequestProperty(item.getKey(), item.getValue());
            }
        }
    }

    private void addContentLength(HttpURLConnection conn) {
        conn.setRequestProperty("Content-Length",
                String.valueOf(body.getBytes().length));
    }

    public static void main(String[] args) throws Exception {
//		HttpRequest request = new HttpBuilder()
//		.setUrl("https://dx.ipyy.net/sms.aspx")
//		.setMethod("post")
//		.setBody("action=send&userid=&account=AC00071&password=AC0007112&mobile=15280993215&content=【北京通】233221（验证码），北京通用户，您正在进行实名认证操作，温馨提示，请勿转发&sendTime=&extno=")
//		.build();
//		String result = request.send();
//		System.out.println(result);

        HttpRequest request = new HttpBuilder()
                .setUrl("https://www.baifggdu.com")
                .setMethod("get")
                //.setBody("action=send&userid=&account=AC00071&password=AC0007112&mobile=15280993215&content=【北京通】233221（验证码），北京通用户，您正在进行实名认证操作，温馨提示，请勿转发&sendTime=&extno=")
                .build();
        String result = request.send();
        System.out.println(result);
    }

}
