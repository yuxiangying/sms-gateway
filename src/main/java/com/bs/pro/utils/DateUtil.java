package com.bs.pro.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	public static String format(Long date,String format) {
		Instant instant = Instant.ofEpochMilli(date);
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime d = LocalDateTime.ofInstant(instant, zone);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return d.format(formatter);
	}
	
	//计算N秒前时间
	public static long beforeSecondToNowDate(int second) {
		Calendar calendar = Calendar.getInstance();
		/* HOUR_OF_DAY 指示一天中的小时 */
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - second);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("一个小时前的时间：" + df.format(calendar.getTime()));
//		System.out.println("当前的时间：" + df.format(new Date()));
		return calendar.getTime().getTime();
	}
	
	
	public static void main(String[] s) {
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date().getTime());
		System.out.println(beforeSecondToNowDate(10));
	}
	
	
}
