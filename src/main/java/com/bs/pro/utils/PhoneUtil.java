package com.bs.pro.utils;

public class PhoneUtil {
	public static String phoneCategroy(String phone){
		if(phone.indexOf(",")==-1){
			return subString(phone);
		}
		String[] phones = phone.split(",");
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<phones.length; i++){
			buffer.append(subString(phones[i]));
			buffer.append(",");
		}
		if(buffer.length()>0){
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}
	
	private static String subString(String str){
		return str.substring(0,3);
	}
	
	public static void main(String[] args) {
		//String phone = "15280993215";
//		String phone = "15280993215,15280993211,13480993214";
		String phone = "";
		System.out.println(PhoneUtil.phoneCategroy(phone));
	}
}
