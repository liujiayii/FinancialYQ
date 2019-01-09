package com.fendo.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 工资表实体
 * @author lhj
 * 
 * @return
 * @author
 */
public class SalaryStatistics {
	/** id */
	private long id;
	
	
	/** 员工id*/
	private long staff_id;
	/** 实际出勤 */
	private Double real_attendance;
	/** 天数*/
	private Double num_day;
	/** 原始股东分红*/
	private BigDecimal original_dividend;
	/** 保密金*/
	private BigDecimal confidential_gold;
	/** 业务员提成*/
	private BigDecimal push_money;
	/**理财提成*/
	private BigDecimal financial_money;
	/** 缺勤扣款*/
	private BigDecimal absence_money;
	/** 未打卡*/
	private Double no_clock;
	/** 保险费用*/
	private BigDecimal insurance_money;
//	/** 多发费用*/
//	private BigDecimal multiple_money;
//	/** 逾期费用*/
//	private BigDecimal overdue_money;
	/** 罚款*/
	private BigDecimal penalty_money;
	/** 实发工资*/
	private BigDecimal real_salary;
	/** 备注*/
	private String remark;
	/** 年*/
	private String year;
	/** 月*/
	private String month;
	/** 日期*/
	private Date time;
	/**0代表没有事假有全勤，1代表有事假没有全勤 事假请假次数*/
	private Integer leave;
	/**迟到次数*/
	private double late;
	/**未转正出勤*/
	private double notreal_attendance;
	/**病假*/
	private double sick;
	/**婚假*/
	private double marriage;
	/**产假*/
	private double maternity;
	/**年假*/
	private double annualleave;
	/**陪产假*/
	private double pmaternity;
	/**业务员提成*/
	private double business_pay;
	public SalaryStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}
	public Double getReal_attendance() {
		return real_attendance;
	}
	public void setReal_attendance(Double real_attendance) {
		this.real_attendance = real_attendance;
	}
	public Double getNum_day() {
		return num_day;
	}
	public void setNum_day(Double num_day) {
		this.num_day = num_day;
	}
	public BigDecimal getOriginal_dividend() {
		return original_dividend;
	}
	public void setOriginal_dividend(BigDecimal original_dividend) {
		this.original_dividend = original_dividend;
	}
	public BigDecimal getConfidential_gold() {
		return confidential_gold;
	}
	public void setConfidential_gold(BigDecimal confidential_gold) {
		this.confidential_gold = confidential_gold;
	}
	public BigDecimal getPush_money() {
		return push_money;
	}
	public void setPush_money(BigDecimal push_money) {
		this.push_money = push_money;
	}
	public BigDecimal getFinancial_money() {
		return financial_money;
	}
	public void setFinancial_money(BigDecimal financial_money) {
		this.financial_money = financial_money;
	}
	public BigDecimal getAbsence_money() {
		return absence_money;
	}
	public void setAbsence_money(BigDecimal absence_money) {
		this.absence_money = absence_money;
	}
	public Double getNo_clock() {
		return no_clock;
	}
	public void setNo_clock(Double no_clock) {
		this.no_clock = no_clock;
	}
	public BigDecimal getInsurance_money() {
		return insurance_money;
	}
	public void setInsurance_money(BigDecimal insurance_money) {
		this.insurance_money = insurance_money;
	}
	public BigDecimal getPenalty_money() {
		return penalty_money;
	}
	public void setPenalty_money(BigDecimal penalty_money) {
		this.penalty_money = penalty_money;
	}
	public BigDecimal getReal_salary() {
		return real_salary;
	}
	public void setReal_salary(BigDecimal real_salary) {
		this.real_salary = real_salary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getLeave() {
		return leave;
	}
	public void setLeave(Integer leave) {
		this.leave = leave;
	}
	public double getLate() {
		return late;
	}
	public void setLate(double late) {
		this.late = late;
	}
	public double getNotreal_attendance() {
		return notreal_attendance;
	}
	public void setNotreal_attendance(double notreal_attendance) {
		this.notreal_attendance = notreal_attendance;
	}
	public double getSick() {
		return sick;
	}
	public void setSick(double sick) {
		this.sick = sick;
	}
	public double getMarriage() {
		return marriage;
	}
	public void setMarriage(double marriage) {
		this.marriage = marriage;
	}
	public double getMaternity() {
		return maternity;
	}
	public void setMaternity(double maternity) {
		this.maternity = maternity;
	}
	public double getAnnualleave() {
		return annualleave;
	}
	public void setAnnualleave(double annualleave) {
		this.annualleave = annualleave;
	}
	public double getPmaternity() {
		return pmaternity;
	}
	public void setPmaternity(double pmaternity) {
		this.pmaternity = pmaternity;
	}
	public double getBusiness_pay() {
		return business_pay;
	}
	public void setBusiness_pay(double business_pay) {
		this.business_pay = business_pay;
	}
	@Override
	public String toString() {
		return "SalaryStatistics [id=" + id + ", staff_id=" + staff_id + ", real_attendance=" + real_attendance
				+ ", num_day=" + num_day + ", original_dividend=" + original_dividend + ", confidential_gold="
				+ confidential_gold + ", push_money=" + push_money + ", financial_money=" + financial_money
				+ ", absence_money=" + absence_money + ", no_clock=" + no_clock + ", insurance_money=" + insurance_money
				+ ", penalty_money=" + penalty_money + ", real_salary=" + real_salary + ", remark=" + remark + ", year="
				+ year + ", month=" + month + ", time=" + time + ", leave=" + leave + ", late=" + late
				+ ", notreal_attendance=" + notreal_attendance + ", sick=" + sick + ", marriage=" + marriage
				+ ", maternity=" + maternity + ", annualleave=" + annualleave + ", pmaternity=" + pmaternity
				+ ", business_pay=" + business_pay + "]";
	}
	
	
}
