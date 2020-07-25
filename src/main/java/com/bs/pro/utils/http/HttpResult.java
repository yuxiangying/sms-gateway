package com.bs.pro.utils.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 返回值 0=succcess 0=success 1=other success -1= error
 * 
 * @author 142975
 *
 */
public class HttpResult {
	public static String check(String input, Map<String, String> map)
			throws RuntimeException {
		String successFeature = map.get("0");
		if (successFeature == null) {
			throw new RuntimeException(
					"channel return config have not success value");
		}

		if (input.indexOf(successFeature) != -1) {
			// 找到成功标志
			return "success";
		}

		// 找其他成功标志
		for (Entry<String, String> entry : map.entrySet()) {
			String num = entry.getKey();
			try {
				// 所有是数字的都是判断标志位，不是数字的就是错误信息
				Integer.parseInt(num);
				if (!num.equals("0")) {
					if (input.indexOf(entry.getValue()) != -1) {
						return map.get("error:" + entry.getValue());
					}
				}
			} catch (Exception e) {
				// noting to do
			}
		}
		// 到这里就说明服务端返回的空白或者其他
		return "exception";
	}

	public static void main(String[] args) {
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("0", ":0");
//		map.put("1", ":13");
//		map.put("2", ":15");
//		map.put("3", ":8");
//		map.put("error::13", "目标号码在黑名单中");
//		map.put("error::15", "通道不支持该目标号码");
//		map.put("error::8", "流量超过限制");
//		// String input =
//		// "SUCCESS:%E6%8F%90%E4%BA%A4%E6%88%90%E5%8A%9F%EF%BC%8115280993215:59106110281545040389:0";
//		//String input = "ERROR:%E7%AD%BE%E5%90%8D%E9%AA%8C%E8%AF%81%E4%B8%8D%E9%80%9A%E8%BF%87%EF%BC%81";
//		String input = "黑名单";
//		String out = HttpResult.check(input, map);
//		System.out.println(out);
		
		//System.out.println(EncodeUtil.urldecode("黑名单"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "Success");
		map.put("1", "密码错了");
		map.put("2", "用户名错误");
		map.put("error:密码错了", "目标号码在黑名单中");
		map.put("error:用户名错误", "通道不支持该目标号码");
		
		String input1 = "returnstatus: \"Success\"";
		String input2 = "message: \"密码错了\"";
		String input3 = "message: \"用户名错误\"";
		String out1 = HttpResult.check(input1, map);
		String out2 = HttpResult.check(input2, map);
		String out3 = HttpResult.check(input3, map);
		System.out.println(out1);
		System.out.println(out2);
		System.out.println(out3);

	}

}
