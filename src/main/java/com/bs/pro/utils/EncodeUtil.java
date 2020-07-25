package com.bs.pro.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncodeUtil {
	//url编码
	public static String urlencode(String data){
		return URLEncoder.encode(data);
	}
	
	public static String urldecode(String data){
		return URLDecoder.decode(data);
	}
	
	public static String md5hex(String data){
		return DigestUtils.md5Hex(data);
	}
	
	public static String base64(String data){
		return Base64.encodeBase64String(data.getBytes());
	}
	
//	public static String aliUrlEncode(String url) throws UnsupportedEncodingException{
//		//先用原始的进行编码
//		String buffer = URLEncoder.encode(url,ENCODING);
//		//（+）替换成%20、星号（*）替换成%2A、%7E 替换回波浪号（~）
//		buffer=buffer.replace("+", "%20");
//		buffer=buffer.replace("*", "%2A");
//		buffer=buffer.replace("%7E", "~");
//		return buffer;
//	}
//	
//	private static final String MAC_NAME = "HmacSHA1";
//	private static final String ENCODING = "UTF-8";
//
//	private static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
//			throws Exception {
//		byte[] data = encryptKey.getBytes(ENCODING);
//		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
//		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
//		// 生成一个指定 Mac 算法 的 Mac 对象
//		Mac mac = Mac.getInstance(MAC_NAME);
//		// 用给定密钥初始化 Mac 对象
//		mac.init(secretKey);
//
//		byte[] text = encryptText.getBytes(ENCODING);
//		// 完成 Mac 操作
//		return mac.doFinal(text);
//	}
//	
//	public static String hmacSha1(String content, String key) throws Exception{
//		String input = "POST&%2F&" + content;
//		byte[] arr = Base64.encodeBase64(HmacSHA1Encrypt(input, key));
//		String output = new String(arr);
//		return URLEncoder.encode(output);
//	}
	
	public static void main(String[] args) throws Exception {
//		String data ="您正在注册房通理财，验证码：123123【房通理财】";
//		System.out.println(EncodeUtil.urlencode(data));
//		System.out.println(EncodeUtil.urldecode("ERROR:%E7%AD%BE%E5%90%8D%E9%AA%8C%E8%AF%81%E4%B8%8D%E9%80%9A%E8%BF%87%EF%BC%81"));
//		
//		
//		System.out.println(EncodeUtil.md5hex("123456"));//e10adc3949ba59abbe56e057f20f883e
//		System.out.println(EncodeUtil.base64(data));//5oKo5q2j5Zyo5rOo5YaM5oi/6YCa55CG6LSi77yM6aqM6K+B56CB77yaMTIzMTIz44CQ5oi/6YCa55CG6LSi44CR
//		
		//阿里云短信接口编码测试
//		String site = "https://sms.aliyuncs.com/";
//		String content1 = "AccessKeyId=testid&Action=SingleSendSms&Format=XML&ParamString=%7B%22name%22%3A%22d%22%2C%22name1%22%3A%22d%22%7D&RecNum=13098765432&RegionId=cn-hangzhou&SignName=%E6%A0%87%E7%AD%BE%E6%B5%8B%E8%AF%95&SignatureMethod=HMAC-SHA1&SignatureNonce=9e030f6b-03a2-40f0-a6ba-157d44532fd0&SignatureVersion=1.0&TemplateCode=SMS_1650053&Timestamp=2016-10-20T05%3A37%3A52Z&Version=2016-09-27";
//		String content2 = "AccessKeyId%3Dtestid%26Action%3DSingleSendSms%26Format%3DXML%26ParamString%3D%257B%2522name%2522%253A%2522d%2522%252C%2522name1%2522%253A%2522d%2522%257D%26RecNum%3D13098765432%26RegionId%3Dcn-hangzhou%26SignName%3D%25E6%25A0%2587%25E7%25AD%25BE%25E6%25B5%258B%25E8%25AF%2595%26SignatureMethod%3DHMAC-SHA1%26SignatureNonce%3D9e030f6b-03a2-40f0-a6ba-157d44532fd0%26SignatureVersion%3D1.0%26TemplateCode%3DSMS_1650053%26Timestamp%3D2016-10-20T05%253A37%253A52Z%26Version%3D2016-09-27";
//
//		System.out.println(hmacSha1(content2,"testsecret&"));
//		
//		System.out.println(aliUrlEncode("{\"name\":\"d\",\"name1\":\"d\"}"));
//		System.out.println(aliUrlEncode("标签测试"));
//		System.out.println(aliUrlEncode("2016-10-20T05:37:52Z"));
//		System.out.println(aliUrlEncode(content1));
//		
//		System.out.println(content2);
		
	}
}
