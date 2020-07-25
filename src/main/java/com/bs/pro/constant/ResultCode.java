package com.bs.pro.constant;

import java.util.Objects;

/**
 * @description: 返回状态
 * @author: zhl
 * @create: 2019-03-16 16:30:34
 **/
public enum ResultCode implements ResultEnum<Integer>{
	/**
	 * 0:执行成功
	 */
	SUCCESS(0, "操作成功"),

	/**
	 * -1:执行失败
	 */
	FAIL(-1, "操作失败"),
	
	/**
	 * 1000:业务异常
	 */
	BUSINESS_ERROR(1000, "业务异常"),
	
	/**
     * 1001:非法请求
     */
    UNLAW_REQUEST(1001, "非法请求"),
    
    /**
     * 1002:未登录或token失效
     */
    NOT_LOGIN(1002, "未登录或token失效"),
    
	/**
	 * 1003:图片上传失败
	 */
	UPLOAD_FAIL(1003, "图片上传失败"),
	
	/**
	 * 1004:用户调用次数超限
	 */
	USER_CALL_LIMITED(1004, "用户调用次数超限"),
	
	/**
	 * 1005:会话调用次数超限
	 */
	SESSION_CALL_LIMITED(1005, "会话调用次数超限"),
	
	/**
	 * 1006:应用调用次数超限
	 */
	APP_CALL_LIMITED(1006, "应用调用次数超限"),
	
	/**
	 * 1007:应用调用频率超限
	 */
	APP_CALL_EXCEEDS_LIMITED_FREQUENCY(1007, "应用调用频率超限"),
	
	/**
	 * 1008:服务不可用
	 */
	SERVICE_CURRENTLY_UNAVAILABLE(1008, "服务不可用"),
	
	/**
	 * 1009:远程服务出错
	 */
	REMOTE_SERVICE_ERROR(1009, "服务调用出错"),
	
	/**
	 * 1010:缺少方法名参数
	 */
	MISSING_METHOD(1010, "缺少方法名参数"),
	
	/**
	 * 1011:不存在的方法名
	 */
	INVALID_METHOD(1011, "不存在的方法名"),
	/**
	 * 1012:非法数据格式
	 */
	INVALID_FORMAT(1012, "非法数据格式"),
	/**
	 * 1013:缺少签名参数
	 */
	MISSING_SIGNATURE(1013, "缺少签名参数"),
	/**
	 * 1014:非法签名
	 */
	INVALID_SIGNATURE(1014, "非法签名"),
	/**
	 * 1015:缺少版本参数
	 */
	MISSING_VERSION(1015, "缺少版本参数"),
	/**
	 * 1016:非法的版本参数
	 */
    INVALID_VERSION(1016, "非法的版本参数"),
    /**
	 * 1017:不支持的版本号
	 */
    UNSUPPORTED_VERSION(1017, "不支持的版本号"),
    /**
	 * 1018:缺少必选参数
	 */
    MISSING_REQUIRED_ARGUMENTS(1018, "缺少必选参数"),
    /**
	 * 1019:非法的参数
	 */
    INVALID_ARGUMENTS(1019, "非法的参数"),
    /**
	 * 1020:请求被禁止
	 */
    FORBIDDEN_REQUEST(1020, "请求被禁止"),
    /**
	 * 1021:参数错误
	 */
    PARAMETER_ERROR(1021, "参数错误"),
	
	/**
	 * 1022:没有操作权限
	 */
	NOT_AUTHORITY(1022, "没有操作权限"),
	
    /**
     * 1023:请求无数据
     */
	DATA_NOT_EXIST(1023, "请求无数据"),
    
    /**
     * 1024:请求无数据
     */
    LOGIN_ERROR(1024, "登入失败，请检查用户或密码"),

	VISITOR_ACCESS_NOT_FIND(-1,"未找到来访信息"),

	NOT_ACCORD_BY_ROUTE(1025,"没有按照路线进行签到"),

	NOT_TO_SIGN_IN_TIME(1026,"未到巡更时间"),

	NOT_FIND_PATROL_TASK(1027,"目前没有巡更任务"),

	NOT_FIND_PATROL_POINT(1028,"没有找到巡更点信息"),

	NOT_FIND_PATROL_USER(1029,"没有找到巡更人信息"),

	LEAK_AGE_SIGN(1030,"漏签"),

	BLUE_TOOTH_NOT_FIND(1028,"蓝牙信标不存在"),

	NOT_DELETE_POINT(1029,"此巡更点已使用，无法删除"),

	AUTHCODE_ERROR(1030,"验证码错误"),

	NOT_FIND_SHAKE_RESULT_INFO(1031,"没有获取到摇一摇周边信息"),

	REMIND_CALL_EXCEEDS_LIMITED_FREQUENCY(1025, "催办调用频率超限"),

	NOT_DISPOSE_INITIAL_VALUE(1040, "未配置初始值，请先配置初始值！"),

	NOT_SET_UP_PRICE(1041, "未配置能耗单价，请先去系统设置配置！"),

	THIS_IS_LESS_THEN_LAST_TIME(1045,"本次抄表数不能小于上次！"),

	REGION_TYPE_ERROR(1040, "系统自带区域类型不可删除"),

	OLD_PASSWORD_ERROR(1050,"旧密码不正确"),

	TWICE_PASSWORD_ERROR(1051,"新密码两次输入不一致,请确认"),

	USER_NAME_IS_HAD(1045,"用户名已存在"),

	USER_NAMEORJOBNO_IS_HAD(1045,"用户名或员工工号已存在"),

	NAME_IS_HAD(1045,"名称已存在"),

	CLOCK_TIME_NOT_ON_ATTENDANCE(1046,"打卡时间不在考勤范围内"),

	FILE_SIZE_TOO_LARGE(1047,"上传文件大小超过50MB"),

	THIRD_INFO_NOT_FIND(1048,"第三方信息不存在"),

	USER_INFO_NIT_EXIST(1049,"用户名或密码错误"),

	VISITOR_ACCESS_NOT_EXIST(1052,"访问记录不存在"),

    CLOCK_NORMAL(10000,"正常"),

	CLOCK_LATE(10001,"迟到"),

    CLOCK_SERIOUS_LATE(10002,"严重迟到"),

    CLOCK_ABSENTEEISM_LATE(10003,"旷工"),

    LEAVE_EARLY(10004,"早退"),

    CLOCK_LEAVE(10005,"请假"),

    CLOCK_OVER_TIME(10006,"加班"),

    CLOCK_BUSINESS_TRIP(10007,"出差"),

    LACK_OF_CARD(10008,"缺卡"),

	CLOCK_REST(10009,"休息"),

	ATTENDANCE(10010,"出勤"),

	SYSTEM_REGION_CANNOT_CHANGE_NAME(1041,"系统自带区域类型不能修改名称"),

    REGION_ERROR(1041, "该区域类型还存在区域，请先删除从属区域");



	private Integer code;
	private String desc;

	ResultCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	/**
	 * 根据code值换取中文提示信息
	 * @param code 错误码
	 * @return
	 */
	public static String getDescByCode(Integer code){
		for (ResultCode resultCode : ResultCode.values()){
			if (resultCode.getCode().equals(code)){
				return resultCode.getDesc();
			}
		}
		return null;
	}

	public static ResultCode getEnum(Integer code){
		for(ResultCode resultCode : ResultCode.values()){
			if(Objects.equals(code,resultCode.getCode())){
				return resultCode;
			}
		}
		return null;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
