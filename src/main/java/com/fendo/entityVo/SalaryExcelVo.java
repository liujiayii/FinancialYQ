package com.fendo.entityVo;

/**
 * 工资表查询列表显示实体Vo
 * @author liuhangjing
 *
 */
public class SalaryExcelVo {
	/** 主键id */
	private long id;
	
	/** 工号 */
	private String job_number;
	
	/** 姓名 */
	private String 姓名;
	
	/** 职位名称 */
	private String 职位;
	
	public String get职位() {
		return 职位;
	}
	public void set职位(String 职位) {
		this.职位 = 职位;
	}
	
	/** 基本工资 */
	private Double 基本工资;

	/** 天数*/
	private Double 天数;
	/** 缺勤扣款*/
	private Double 缺勤扣款;
	/** 未打卡*/
	private Double 未打卡;
	
	/** 罚款*/
	private Double 罚款;
	/** 实发工资*/
	private Double 实发工资;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
//	public String get工号() {
//		return 工号;
//	}
//	public void set工号(String 工号) {
//		this.工号 = 工号;
//	}
	
	public String get姓名() {
		return 姓名;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public void set姓名(String 姓名) {
		this.姓名 = 姓名;
	}
	public Double get基本工资() {
		return 基本工资;
	}
	public void set基本工资(Double 基本工资) {
		this.基本工资 = 基本工资;
	}
	public Double get天数() {
		return 天数;
	}
	public void set天数(Double 天数) {
		this.天数 = 天数;
	}
	public Double get缺勤扣款() {
		return 缺勤扣款;
	}
	public void set缺勤扣款(Double 缺勤扣款) {
		this.缺勤扣款 = 缺勤扣款;
	}
	public Double get未打卡() {
		return 未打卡;
	}
	public void set未打卡(Double 未打卡) {
		this.未打卡 = 未打卡;
	}
	public Double get罚款() {
		return 罚款;
	}
	public void set罚款(Double 罚款) {
		this.罚款 = 罚款;
	}
	public Double get实发工资() {
		return 实发工资;
	}
	public void set实发工资(Double 实发工资) {
		this.实发工资 = 实发工资;
	}
	@Override
	public String toString() {
		return "SalaryExcelVo [id=" + id + ", job_number=" + job_number + ", 姓名=" + 姓名 + ", 职位=" + 职位 + ", 基本工资=" + 基本工资
				+ ", 天数=" + 天数 + ", 缺勤扣款=" + 缺勤扣款 + ", 未打卡=" + 未打卡 + ", 罚款=" + 罚款 + ", 实发工资=" + 实发工资 + "]";
	}
	

	
	
	
}
