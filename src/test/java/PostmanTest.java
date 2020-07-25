import com.bs.pro.utils.MD5;

import java.net.URLDecoder;
import java.net.URLEncoder;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

/**
 * Created by czy on 2019/4/1.
 */
public class PostmanTest {
    public static void main(String[] args) throws Exception {
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1);
//        String oneMonthAgo = formatter.format(cal.getTime());
//        System.out.println(oneMonthAgo);
        System.out.println(205929+38638975.58+1993976.8+6268373.78+676158);
    }

    private static void queryAliSend() {

    }

    private static void genSignature() throws Exception {
        String SubscriptionType = "Subscription";
        String Format = "XML";
        String SignatureMethod = "HMAC-SHA1";
        String Timestamp = "2019-07-20T10:33:00Z";
        String AccessKeyId = "LTAIuAgOipnBLWy5";
        String Action = "QueryAccountBalance";
        String SignatureNonce = UUID.randomUUID().toString();
        String Version = "2017-12-14";
        String SignatureVersion = "1.0";
        String BillingCycle = "2018-01";
        String Key = "cE0jiM8zFWKnR8OGP1F7I3ij6snQ4R" + "38";

        Map<String, Object> sortParas = new TreeMap<>();
        sortParas.put("SubscriptionType", SubscriptionType);
        sortParas.put("Format", Format);
        sortParas.put("SignatureMethod", SignatureMethod);
        sortParas.put("Timestamp", Timestamp);
        sortParas.put("AccessKeyId", AccessKeyId);
        sortParas.put("Action", Action);
        sortParas.put("SignatureNonce", SignatureNonce);
        sortParas.put("Version", Version);
        sortParas.put("SignatureVersion", SignatureVersion);
        sortParas.put("BillingCycle", BillingCycle);
        sortParas.put("Key", Key);

        StringBuffer strBuffer = new StringBuffer();
        java.util.Iterator<String> it = sortParas.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            strBuffer.append(key + "=" + sortParas.get(key) + "&");

        }
        String strToSign = strBuffer.toString().substring(0, strBuffer.length() - 1);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update(strToSign.getBytes());
        String Signature = new BASE64Encoder().encode(messageDigest.digest());
        System.out.println(SignatureNonce);
        System.out.println(Signature);
    }

    private static void oldmsg() throws Exception {
        String content = "{\"temail\":\"11@czy.com\",\"code\":\"666778\",\"url\":\"www.3.com\"}";

        String toAddress = "+8617800806487";
        String type = "88";
        String appid = "20160823";
        String appkey = "3raaaaaa8db2192dffwwdb9e0deefff6";
        String sendTime = String.valueOf(new Date().getTime());
        System.out.println(sendTime);
        String str = URLEncoder.encode(content +
                toAddress
                +
                type
                +
                appid
                +
                appkey
                +
                sendTime
                + "syswintoon", "utf-8");

        System.out.println(MD5.getMD5Str(str).toLowerCase());
    }

    private static void decodeYunpian() throws Exception {
        String data = "%5B%7B%22uid%22%3A%220ycnk92c-06756290-5fd5-476c-9bb0-239413be1d3d%22%2C%22user_receive_time%22%3A%222019-07-24+21%3A02%3A13%22%2C%22error_msg%22%3A%22DELIVRD%22%2C%22error_detail%22%3A%22%E6%8E%A5%E6%94%B6%E6%88%90%E5%8A%9F%22%2C%22mobile%22%3A%2217800806487%22%2C%22report_status%22%3A%22SUCCESS%22%2C%22sid%22%3A43825664762%7D%5D";
        String decodeData = URLDecoder.decode(data, "utf-8");
        System.out.println(decodeData);

    }

}

