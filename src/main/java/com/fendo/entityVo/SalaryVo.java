package com.fendo.entityVo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 工资表查询列表显示实体Vo
 * @author liuhangjing
 *
 */
public class SalaryVo {
	/** 主键id */
	private long id;
	
	private long staff_id;
	/**理财id*/
	private long sales_id;
	
	/** 工号 */
	private String job_number;
	
	/** 姓名 */
	private String name;

	/** 分公司id */
	private String district_id;
	
	/** 部门id */
	private long branch_id;
	/** 部门名称 */
	private String dep_name;
	
	/** 职位id */
	private long post_id;
	
	/** 职位名称 */
	private String post_name;
	
	/** 电话 */
	private String phone;
	
	/** 实习工资 */
	private BigDecimal base_pay;
	
	/** 转正工资 */
	private BigDecimal zbase_pay;
	
	
	public BigDecimal getZbase_pay() {
		return zbase_pay;
	}
	public void setZbase_pay(BigDecimal zbase_pay) {
		this.zbase_pay = zbase_pay;
	}
	/** 全勤 */
	private BigDecimal attendance_bouns;
	
	/** 餐补 */
	private BigDecimal meal_bouns;
	
	/** 工装补 */
	private BigDecimal forck_bouns;
	
	/** 入职日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date entry_date;
    
    /** 创建时间  */
	private Date create_time;
	
	/** 更新时间 */
	private Date update_time;
    /**状态 锁定1   未锁定0*/
	private int type;
	
	/** 转正后出勤 */
	private Double real_attendance;
	
	/** 未转正后出勤 */
	private Double noreal_attendance;
	
	public Double getNoreal_attendance() {
		return noreal_attendance;
	}
	public void setNoreal_attendance(Double noreal_attendance) {
		this.noreal_attendance = noreal_attendance;
	}
	/** 天数*/
	private Double num_day;
	/** 业务工资*/
	private Double business_pay;
	/** 原始股东分红*/
	private BigDecimal original_dividend;
	/** 保密金*/
	private BigDecimal confidential_gold;
	/** 提成*/
	private Double push_money;
	/** 缺勤扣款*/
	private BigDecimal absence_money;
	/** 未打卡*/
	private Double no_clock;
	/** 扣保险*/
	private BigDecimal insurance_money;
	/** 扣多发*/
	private BigDecimal multiple_money;
	/** 扣逾期*/
	private BigDecimal overdue_money;
	/** 罚款*/
	private BigDecimal penalty_money;
	/** 实发工资*/
	private BigDecimal real_salary;
	/** 备注 */
	private String remark;
	/**是否转正*/
	private int is_become;
	
	private int year;
	
	private int month;
	/**事假天数*/
	private Double leaves;
	/**迟到次数*/
	private Double late;
	
	/**病假天数*/
	private Double sick;
	/**婚假天数*/
	private Double marriage;
	/**产假天数*/
	private Double maternity;
	/**陪产假天数*/
	private Double pmaternity;
	/**年假天数*/
	private Double annualleave;
	
	
	
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public Double getLate() {
		return late;
	}
	public void setLate(Double late) {
		this.late = late;
	}
	public Double getSick() {
		return sick;
	}
	public void setSick(Double sick) {
		this.sick = sick;
	}
	public Double getMarriage() {
		return marriage;
	}
	public void setMarriage(Double marriage) {
		this.marriage = marriage;
	}
	public Double getMaternity() {
		return maternity;
	}
	public void setMaternity(Double maternity) {
		this.maternity = maternity;
	}
	public Double getPmaternity() {
		return pmaternity;
	}
	public void setPmaternity(Double pmaternity) {
		this.pmaternity = pmaternity;
	}
	public Double getAnnualleave() {
		return annualleave;
	}
	public void setAnnualleave(Double annualleave) {
		this.annualleave = annualleave;
	}
	public int getIs_become() {
		return is_become;
	}
	public void setIs_become(int is_become) {
		this.is_become = is_become;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public long getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(long branch_id) {
		this.branch_id = branch_id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public String getPost_name() {
		return post_name;
	}
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BigDecimal getBase_pay() {
		return base_pay;
	}
	public void setBase_pay(BigDecimal base_pay) {
		this.base_pay = base_pay;
	}
	public BigDecimal getAttendance_bouns() {
		return attendance_bouns;
	}
	public void setAttendance_bouns(BigDecimal attendance_bouns) {
		this.attendance_bouns = attendance_bouns;
	}
	public BigDecimal getMeal_bouns() {
		return meal_bouns;
	}
	public void setMeal_bouns(BigDecimal meal_bouns) {
		this.meal_bouns = meal_bouns;
	}
	public BigDecimal getForck_bouns() {
		return forck_bouns;
	}
	public void setForck_bouns(BigDecimal forck_bouns) {
		this.forck_bouns = forck_bouns;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public Double getBusiness_pay() {
		return business_pay;
	}
	public void setBusiness_pay(Double business_pay) {
		this.business_pay = business_pay;
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
	public BigDecimal getMultiple_money() {
		return multiple_money;
	}
	public void setMultiple_money(BigDecimal multiple_money) {
		this.multiple_money = multiple_money;
	}
	public BigDecimal getOverdue_money() {
		return overdue_money;
	}
	public void setOverdue_money(BigDecimal overdue_money) {
		this.overdue_money = overdue_money;
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

	public Double getPush_money() {
		return push_money;
	}
	public void setPush_money(Double push_money) {
		this.push_money = push_money;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getSales_id() {
		return sales_id;
	}
	public void setSales_id(long sales_id) {
		this.sales_id = sales_id;
	}
	public long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}
	
	public Double getLeaves() {
		return leaves;
	}
	public void setLeaves(Double leaves) {
		this.leaves = leaves;
	}
	@Override
	public String toString() {
		return "SalaryVo [id=" + id + ", staff_id=" + staff_id + ", sales_id=" + sales_id + ", job_number=" + job_number
				+ ", name=" + name + ", district_id=" + district_id + ", branch_id=" + branch_id + ", dep_name="
				+ dep_name + ", post_id=" + post_id + ", post_name=" + post_name + ", phone=" + phone + ", base_pay="
				+ base_pay + ", zbase_pay=" + zbase_pay + ", attendance_bouns=" + attendance_bouns + ", meal_bouns="
				+ meal_bouns + ", forck_bouns=" + forck_bouns + ", entry_date=" + entry_date + ", create_time="
				+ create_time + ", update_time=" + update_time + ", type=" + type + ", real_attendance="
				+ real_attendance + ", noreal_attendance=" + noreal_attendance + ", num_day=" + num_day
				+ ", business_pay=" + business_pay + ", original_dividend=" + original_dividend + ", confidential_gold="
				+ confidential_gold + ", push_money=" + push_money + ", absence_money=" + absence_money + ", no_clock="
				+ no_clock + ", insurance_money=" + insurance_money + ", multiple_money=" + multiple_money
				+ ", overdue_money=" + overdue_money + ", penalty_money=" + penalty_money + ", real_salary="
				+ real_salary + ", remark=" + remark + ", is_become=" + is_become + ", year=" + year + ", month="
				+ month + ", leaves=" + leaves + ", late=" + late + ", sick=" + sick + ", marriage=" + marriage
				+ ", maternity=" + maternity + ", pmaternity=" + pmaternity + ", annualleave=" + annualleave + "]";
	}

	
}
