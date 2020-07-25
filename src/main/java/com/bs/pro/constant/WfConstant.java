package com.bs.pro.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengning
 * 智慧工厂使用常量类
 */
public class WfConstant {

	public static final Map<String,String> weekMap = new HashMap<>();

	static {
		weekMap.put("MONDAY","星期一");
		weekMap.put("TUESDAY","星期二");
		weekMap.put("WEDNESDAY","星期三");
		weekMap.put("THURSDAY","星期四");
		weekMap.put("FRIDAY","星期五");
		weekMap.put("SATURDAY","星期六");
		weekMap.put("SUNDAY","星期天");
	}



	/**
	 * 周一
	 */
	public final static String MONDAY = "MONDAY";
	/**
	 * 周二
	 */
	public final static String TUESDAY = "TUESDAY";
	/**
	 * 周三
	 */
	public final static String WEDNESDAY = "WEDNESDAY";
	/**
	 * 周四
	 */
	public final static String THURSDAY = "THURSDAY";
	/**
	 * 周五
	 */
	public final static String FRIDAY = "FRIDAY";
	/**
	 * 周六
	 */
	public final static String SATURDAY = "SATURDAY";
	/**
	 * 周日
	 */
	public final static String SUNDAY = "SUNDAY";

	/**
	 * 休息类型 1-双休
	 */
	public final static Integer REST_DOUBLE = 1;
	/**
	 * 休息类型 2-单休
	 */
	public final static Integer REST_SINGLE = 2;
	/**
	 * 休息类型3-大小周
	 */
	public final static Integer REST_SINGLE_OR_DOUBLE = 3;
	/**
	 * 休息类型 4-自定义
	 */
	public final static Integer REST_CUSTOM = 4;
	/**
	 * 工作日
	 */
	public final static Integer WORK_DAY = 1;
	/**
	 * 休息日
	 */
	public final static Integer REST_DAY = 0;

	/**
	 * 本周开始
	 */
	public final static Integer CURRENT_WEEK = 1;

	/**
	 * 下周开始
	 */
	public final static Integer NEXT_WEEK = 2;


	/**
	 * 未授权
	 */
	public final static Integer NOT_AUTHORIZED = 0;
	/**
	 * 已经授权
	 */
	public final static Integer AUTHORIZED = 1;
	/**
	 * 未删除
	 */
	public final  static Integer NOT_DELETE = 0;

	/**
	 * 删除
	 */
	public final  static Integer DELETE = 1;

	/**
	 * 未完结
	 */
	public final  static Integer NOT_DONE = 0;

	/**
	 * 完结
	 */
	public final  static Integer DONE = 1;

	/**
	 * 状态可用
	 */
	public final  static String STATUS_USEABLE = "0";

	/**
	 * 状态不可用
	 */
	public final  static String STATUS_DISABLE = "1";


	/**
	 * 角色正常状态
	 */
	public final  static String ROLE_NORMAL = "1";

	/**
	 * 角色禁用状态
	 */
	public final  static String ROLE_FORBIDDEN = "2";


	/**
	 * 流程正常状态
	 */
	public final  static Integer WF_NORMAL = 1;

	/**
	 * 流程禁用状态
	 */
	public final  static Integer WF_FORBIDDEN = 2;

	/**
	 * 一级权限
	 */
	public final static Integer FIRST_PERMISSION = 1;
	/**
	 * 二级权限
	 */
	public final static Integer SECOND_PERMISSION = 2;
	/**
	 * 三级权限
	 */
	public final static Integer THIRD_PERMISSION = 3;


	/**
	 * 主要访客
	 */
	public final static String VISITOR_TYPE_MAIN = "1";

	/**
	 * 其他访客
	 */
	public final static String VISITOR_TYPE_OTHER = "2";

	/**
	 * 访客启用（默认）
	 */
	public final static int VISITOR_ENABLE = 0;
	/**
	 * 访客禁用
	 */
	public final static int VISITOR_DISENABLE = 1;

	/**
	 * 启用
	 */
	public final static int ENABLE = 1;
	/**
	 * 禁用
	 */
	public final static int DISENABLE = 2;

	/**
	 * 成功
	 */
	public final static Integer SUCCESS = 0;
	/**
	 * 失败
	 */
	public final static Integer FAIL = 1;

	/**
	 * 打卡成功状态
	 */
	public final static Integer CLOCK_SUCCESS_CODE = 0;
	/**
	 * 打卡成功描述
	 */
	public final static String CLOCK_SUCCESS_MSG  = "打卡成功";
	/**
	 * 打卡失败状态
	 */
	public final static Integer CLOCK_FAIL_CODE = 1;
	/**
	 * 打卡失败描述
	 */
	public final static String CLOCK_FAIL_MSG  = "打卡失败";

	/**
	 * 验证码未使用
	 */
	public final static String AUTHCODE_UNUSE = "1";
	/**
	 * 验证码已使用
	 */
	public final static String AUTHCODE_USED = "0";
	/**
	 * 访客预约类短信
	 */
	public final static String VISITOR_APPOINTMENT = "visitorAppointment";
	/**
	 * 预约成功，验证码通知短信
	 */
	public final static String VISITOR_NOTICE = "visitorNotice";

	/**
	 * 任务完成
	 */
	public final static Integer TASK_FINISH = 1;

	/**
	 * 任务未完成
	 */
	public final static Integer TASK_NOTFINISH = 0;

	/**
	 *  短信已发送
	 */
	public final static Integer SEND_FINISH = 1;
	/**
	 *  短信未发送
	 */
	public final static Integer SEND_NOTFINISH = 0;

	/**
	 * bs_workflow_node表序列名称
	 */
	public final static String WORKFLOW_NODE_SEQUENCE = "bs_workflow_node";

	/**
	 * 流程节点类型  单节点
	 */
	public final static String SINGLE_NODE = "1";

	/**
	 * 流程节点类型  分支节点
	 */
	public final static String BRANCH_NODE = "2";

	/**
	 * 阿里云短信请求成功
	 */
	public final static String ALI_YUN_MSG_SUCCESS = "OK";

	/**
	 * 流程节点类型  起始节点
	 */
	public final static Integer START_NODE = 0;

	/**
	 * 流程节点类型  条件节点
	 */
	public final static Integer CONDITION_NODE = 1;

	/**
	 * 流程节点类型  审核节点
	 */
	public final static Integer APPROVE_NODE = 2;

	/**
	 * 流程节点类型  路线节点
	 */
	public final static Integer ROUTE_NODE = 3;

	/**
	 * 流程默认版本号
	 */
	public final static String DEFAULT_VERSION = "1.0";

	/**
	 * 流程作废
	 */
	public final static Integer WF_CANCEL = 0;

	/**
	 * 流程待审批
	 */
	public final static Integer WF_PENDING = 1;

	/**
	 * 流程不通过
	 */
	public final static Integer WF_REJECT = 2;

	/**
	 * 流程通过
	 */
	public final static Integer WF_PASS = 3;

	/**
	 * 流程参与人
	 */
	public final static String PARTICIPANT = "pu";

	/**
	 * 流程抄送人
	 */
	public final static String CARBON_COPY = "ccu";

	/**
	 * 流程 同行人
	 */
	public final static String TRAVEL_PARTNER = "tpu";

	/**
	 * 流程 审批人
	 */
	public final static String APPROVER = "au";

	/**
	 * 流程 交接人
	 */
	public final static String HANDOVER = "hu";

	/**
	 * 流程审批人为主管
	 */
	public final static String APPROVER_USER_ZG = "zg";

	/**
	 * 流程审批人为新领导
	 */
	public final static String APPROVER_USER_NL = "nl";

	/**
	 * 流程审批人为原领导
	 */
	public final static String APPROVER_USER_OL = "ol";

	/**
	 * 请假
	 */
	public final static Integer WF_TYPE_LEAVE = 1;
	/**
	 * 加班
	 */
	public final static Integer WF_TYPE_OVERTIME = 2;
	/**
	 * 出差
	 */
	public final static Integer WF_TYPE_TRIP = 3;
	/**
	 * 其他
	 */
	public final static Integer WF_TYPE_OTHER = 4;

	/**
	 * 离职
	 */
	public final static Integer WF_TYPE_DEPARTURE = 5;

	/**
	 * 人员调动
	 */
	public final static Integer WF_TYPE_TRANSFER = 6;

	/**
	 * 因公外出
	 */
	public final static Integer WF_TYPE_BUSINESS_OUT = 7;

	/**
	 * 事假
	 */
	public final static Integer WF_SUB_TYPE_PAL = 101;

	/**
	 * 年假
	 */
	public final static Integer WF_SUB_TYPE_AL = 102;

	/**
	 * 病假
	 */
	public final static Integer WF_SUB_TYPE_SL = 103;

	/**
	 * 婚假
	 */
	public final static Integer WF_SUB_TYPE_ML = 104;

	/**
	 * 产假
	 */
	public final static Integer WF_SUB_TYPE_MAL = 105;

	/**
	 * 陪产假
	 */
	public final static Integer WF_SUB_TYPE_PL = 106;

	/**
	 * 丧假
	 */
	public final static Integer WF_SUB_TYPE_FL = 107;

	/**
	 * 工伤假
	 */
	public final static Integer WF_SUB_TYPE_LIL = 108;





	/**
	 * 仓位状态类型 1-空闲  2-占用  3-当前物料  4-禁用
	 */
	public final static Integer BOX_TYPE_FREE = 1;

	public final static Integer BOX_TYPE_OCCUPIED = 2;

	public final static Integer BOX_TYPE_CURRENT = 3;

	public final static Integer BOX_TYPE_DISABLE = 4;

	/**
	 * 类型 1 入库 2 出库  3 移库  4 盘点
	 */
	public final static Integer ORDER_TYPE_IN = 1;

	public final static Integer ORDER_TYPE_OUT = 2;

	public final static Integer ORDER_TYPE_MOVE = 3;

	public final static Integer ORDER_TYPE_CHECK = 4;

	/**
	 * 加号
	 */
	public final static String PLUS = "+";

	/**
	 * 减号
	 */
	public final static String MINUS = "-";





	/**
	 * 临时任务
	 */
	public final static Integer TEMPORARY_TASK = -1;

	/**
	 * 超级管理员用户id
	 */
	public final static Integer SUPER_ADMIN_USER_ID = 1;
	/**
	 * 超级管理员角色id
	 */
	public final static Integer SUPER_ADMIN_ROLE_ID = 1;

	/**
	 * 已激活
	 */
	public final static String ACTIVED = "1";

	/**
	 * 未激活
	 */
	public final static String NOT_ACTIVED = "0";

	/**
	 * 巡更计划周期
	 */
	public final static Integer PARTOL_CYCLE = 7;

	/**
	 * 异常
	 */
	public final static Integer IS_EXCEPTION = 0;

	/**
	 * 待巡逻
	 */
	public final static Integer IS_WILL_PATROL = 0;

	/**
	 * 已巡逻
	 */
	public final static Integer IS_PATROLED = 1;

	public final static String NORMAL = "正常";

	public final static Integer NORMAL_CODE = 0;

	public final static Integer PATROL_TIME_OUT_CODE = 1;

	public final static String PATROL_TIME_OUT = "超时打卡";

	public final static Integer PATROL_LEAK_CODE = 2;

	public final static String PATROL_LEAK = "漏卡";

	public final static String TYPE_VISITOR = "1";

	public final static Integer UPDATE_NORMAL_CODE = 3;

	public final static String UPDATE_NORMAL = "修改正常";

	public final static String WX_ACCESS_TOKENN_KEY = "wxAccessToken:";


	/**
	 * 我申请的
	 */
	public final static Integer SEARCH_BY_APPLY = 1;
	/**
	 * 待我审批的
	 */
	public final static Integer SEARCH_BY_APPROVE = 2;
	/**
	 * 我已审批的
	 */
	public final static Integer SEARCH_BY_APPROVE_DONE = 3;
	/**
	 * 抄送我的
	 */
	public final static Integer SEARCH_BY_COPY = 4;
	/**
	 * 已完结
	 */
	public final static Integer SEARCH_BY_DONE = 5;

	/**
	 * 时间参数  天
	 */
	public final static Integer DATE_TIME_DAY = 1;
	/**
	 * 时间参数  小时
	 */
	public final static Integer DATE_TIME_HOUR = 2;

	public final static Integer SUBMIT_SUCCESS = 1;

	public final static String YUFAN_SUBMIT_SUCCESS_CODE = "GS_SUS303";

	public final static String YUFAN_SUBMIT_SYNC_CODE = "GS_SUS318";

	public final static String YUFAN_SUBMIT_DEL_PICTURE = "GS_SUS601";

	public final static String YUFAN_REGISTER_FACE_IMG = "GS_SUS600";

	public final static String YUFAN_PUT_USER = "GS_SUS203";

	public final static String YUFAN_DEL_USER = "GS_SUS201";

	public final static String YUFAN_POST_USER = "GS_SUS200";

	public final static String YUFAN_DEVICE_CONTROL = "GS_SUS900";

	public final static String YUFAN_DEVICE_REVOKE = "GS_SUS205";

	public final static String YUFAN_DEVICE_AUTHORIZATION = "GS_SUS204";

	public final static String YU_FAN_IDENTIFY_DEVICE = "GS_SUS206";

	public final static String YUFAN_DEVICE_AUTHOR_PERSON = "GS_SUS327";

	public final static String YUFAN_AUTHOR_RELATION = "GS_SUS325";

	public final static String YUFN_REMOVE_RELATION = "GS_SUS330";

	public final static Integer PHOTO_NOT_SAVE = 0;

	public final static Integer PHOTO_SAVED = 1;

	public final static Integer GENERATE_PROPERTY_TASK = 0;

	public final static Integer NOT_GENERATE_PROPERTY_TASK = 1;

	public final static Integer SINGLE_PLAN = 0;


	/**
	 * 员工状态：1-在职，2-离职，3-退休
	 */
	public final static Integer ON_THE_JOB = 1;
	public final static Integer DEPARTURE = 2;
	public final static Integer RETIRED = 3;

	/**
	 * 能源设备抄表
	 */
	public final static String ENERGY_READ_METER = "readMeter";

	/**
	 * 每日考勤通知
	 */
	public final static String ATTEND_NOTICE = "attendNotice";

	/**
	 * 访客来访申请
	 */
	public final static String VISITOR_ACCESS_APPLY = "visitorApply";

	/**
	 * 访客取消来访申请
	 */
	public final static String VISITOR_ACCESS_CANCEL= "visitorCancel";

	/**
	 * 访客入园通知
	 */
	public final static String VISITOR_INNER_NOTICE = "visitorInnerNotice";

	/**
	 * 访客出园通知
	 */
	public final static String VISITOR_OUT_NOTICE = "visitorOutNotice";

	/**
	 * 访客人脸授权通过通知
	 */
	public final static String VISITOR_FACE_SUCCESS = "visitorFaceSuccess";

	/**
	 * 访客人脸授权失败通知
	 */
	public final static String VISITOR_FACE_FAILURE = "visitorFaceFailure";

	/**
	 * 访客申请驳回
	 */
	public final static String VISITOR_APPLY_REJECT = "visitorApplyReject";

	/**
	 * 审批通知
	 */
	public final static String WORKFLOW_APPROVER = "workflowApprover";


	/**
	 * 缺料单提醒
	 */
	public final static String SHORTAGE_LIST = "shortageList";
	/**
	 * 缺料单超时提醒
	 */
	public final static String SHORTAGE_LIST_TIMEOUT = "shortageListTimeout";
	/**
	 * 缺料单超时提醒已转领导
	 */
	public final static String SHORTAGE_LIST_TIMEOUT_LEADER = "shortageListTimeoutLeader";
	/**
	 * 缺料单审核短信
	 */
	public final static String SHORTAGE_LIST_CHECK = "shortageListCheck";
	/**
	 * 手机号码注册
	 */
	public final static String MOBILE_REGISTER = "mobileRegister";
	/**
	 * 绩效确认超时
	 */
	public final static String PERFORMANCE_RECOGNITION_TIMEOUT = "performanceRecognitionTimeout";
	/**
	 * 绩效确认
	 */
	public final static String PERFORMANCE_RECOGNITION = "performanceRecognition";
	/**
	 * 绩效考核完成
	 */
	public final static String PERFORMANCE_APPRAISAL_FINISH = "performanceAppraisalFinish";
	/**
	 * 绩效考核生成
	 */
	public final static String PERFORMANCE_APPRAISAL_GENERATION = "performanceAppraisalGeneration";
	/**
	 * 会议室预定
	 */
	public final static String MEETING_RESERVATION = "meetingReservation";
	/**
	 * 会议取消预定
	 */
	public final static String MEETING_CANCEL = "meetingCancel";
	/**
	 * 会议即将开始开始
	 */
	public final static String MEETING_WILL_START = "meetingWillStart";

	/**
	 * 保养工单
	 */
	public final static String UPHOLD_ORDER = "upholdOrder";
	/**
	 * 完成保养
	 */
	public final static String FINISH_UPHOLD = "finishUphold";
	/**
	 * 保养工单转派
	 */
	public final static String TRANSFER_ORDER = "transferOrder";
	/**
	 * 验收合格
	 */
	public final static String ACCEPTANCE = "acceptance";
	/**
	 * 验收不合格
	 */
	public final static String NOT_ACCEPTANCE = "notAcceptance";


	/**
	 * 设备告警
	 */
	public final static String DEVICE_WARN = "deviceWarn";

	/**
	 * 物业工单
	 */
	public final static String PROPERTY_ORDER = "propertyOrder";

	/**
	 * 访客申请通过
	 */
	public final static String VISITOR_APPLY_PASS = "visitorApplyPass";

	/**
	 * 巡更通知
	 */
	public final static String PATROL_NOTICE = "patrolNotice";

	/**
	 * 添加维修工单
	 */
	public final static String ADD_MAINTAIN_ORDER = "addMaintainOrder";

	/**
	 * 完结维修工单
	 */
	public final static String FINISH_MAINTAIN_ORDER = "finishMaintainOrder";
	/**
	 * 绩效待考核
	 */
	public final static String ASSESS_START = "ASSESS_START";
	/**
	 * 百胜签名
	 */
	public final static String SGIN_BAI_SHENG = "baiSheng";

	/**
	 * 上升
	 */
	public final static Integer RISING = 2;
	/**
	 * 平
	 */
	public final static Integer FLAT = 1;
	/**
	 * 下降
	 */
	public final static Integer FALLING = 0;

	public final static String INNER_GARDEN = "入园";
	public final static String INNER = "入";

	public final static String OUT_GARDEN = "出园";
	public final static String OUT = "出";

	/**
	 * 访客登记
	 */
	public final static String REGISTRATION = "Biz_VisitorRecordBiz_AddList";
	/**
	 * 访客签离
	 */
	public final static String LEAVE = "Biz_Vsitorrecord_Leave";

	public final static String MONITOR_SCREEN_KEY = "monitorScreen";
	public final static String PERSON_SCREEN_KEY = "personScreen";
	public final static String ATTENDANCE_SCREEN_KEY = "attendanceScreen";
	public final static String PRODUCE_CENSUS_SCREEN_KEY = "ProduceCensusScreen";
	public final static String WARE_HOUSE_SCREEN_KEY = "wareHouseScreen";
	public final static String IDENTIFY_TYPE = "identify";
	public final static String TMALL_ELVES = "tmallElves";
	public static final String BIGSCREEN_KEY="task-BigScreen2PushTask";



	public final static String SUCCESS_SUBMIT = "0";
	/**
	 * 海康ISC 事件信息
	 */

	/**
	 * 人脸抓事件
	 */
	public final static String EVENT_FACE = "event_face";
	/**
	 * 人脸比对事件
	 */
	public final static String EVENT_FACE_MATCH = "event_face_match";
	/**
	 * 重点人员识别事件
	 */
	public final static String EVENT_FACE_RECOGNITION = "event_face_recognition";
	//private final static String event_face_recognition = "event_face_recognition";

	/**
	 * 标识高频人员识别事件
	 */
	public final static String EVENT_HIGH_FREQUENCY_PERSONEL = "event_high_frequency_personel";

	public final static Integer STAY_IN_GARDEN = 10010;

	public final static Integer ENTERED_GARDEN = 10011;

	public final static Integer OUTED_GARDEN = 10012;

	public final static String ATTENDANCE_BIGSCREEN_KEY = "attendance:bigscreen:";

	/**
	 * 物料申购类型：1-生产缺料，2-bom不对，3-设备故障，4-其他
	 */
	public final static Integer MATER_PUR_TYPE_1 = 1;
	public final static Integer MATER_PUR_TYPE_2 = 2;
	public final static Integer MATER_PUR_TYPE_3 = 3;
	public final static Integer MATER_PUR_TYPE_4 = 4;
	/**
	 * 物料申购状态:1-待审核，2-协调中，3-待收货，4-待领料，5-已完成，6-已取消，7-已作废
	 */
	public final static Integer MATER_PUR_STATUS_1 = 1;
	public final static Integer MATER_PUR_STATUS_2 = 2;
	public final static Integer MATER_PUR_STATUS_3 = 3;
	public final static Integer MATER_PUR_STATUS_4 = 4;
	public final static Integer MATER_PUR_STATUS_5 = 5;
	public final static Integer MATER_PUR_STATUS_6 = 6;
	public final static Integer MATER_PUR_STATUS_7 = 7;
	/**
	 * 物料申购协调中 子状态：1-待核对，2-待同意，3-待协商，4-待执行
	 */
	public final static Integer MATER_PUR_STATUS_2_1 = 1;
	public final static Integer MATER_PUR_STATUS_2_2 = 2;
	public final static Integer MATER_PUR_STATUS_2_3 = 3;
	public final static Integer MATER_PUR_STATUS_2_4 = 4;
	/**
	 * 审核人员类型：审核人员类型:1-车间部长，2-采购，3-市场，4-总经办，5-申购人
	 */
	public final static Integer PUR_USER_TYPE_1 =1;
	public final static Integer PUR_USER_TYPE_2 =2;
	public final static Integer PUR_USER_TYPE_3 =3;
	public final static Integer PUR_USER_TYPE_4 =4;
	public final static Integer PUR_USER_TYPE_5 =5;
	/**
	 * 物料申购状态：1-待审核，2-待收货，3-待领料，4-待核对，5-待同意，6-待协商，7-待执行
	 * */
	public final static Integer PUR_STATUS_1 = 1;
	public final static Integer PUR_STATUS_2 = 2;
	public final static Integer PUR_STATUS_3 = 3;
	public final static Integer PUR_STATUS_4 = 4;
	public final static Integer PUR_STATUS_5 = 5;
	public final static Integer PUR_STATUS_6 = 6;
	public final static Integer PUR_STATUS_7 = 7;

	/**
	 * 同意
	 */
	public final static int IS_AGREE = 1;
	/**
	 * 不同意
	 */
	public final static int IS_NOT_AGREE = 2;

	public final static Integer ZERO = 0;
	public final static Integer ONE = 1;
	public final static Integer TWO = 2;

	public final static String BIG_SCREEN_CLIENT_KEY = "task-BigScreen2PushTask";
	public final static String BIG_SCREEN_2_PATROL = "patrol-BigScreen";

}
