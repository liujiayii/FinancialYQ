package com.fendo.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 列表显示员工信息的vo实体类
 * @author Administrator
 *
 */

public class StaffVo {
	//select s.id,s.job_number,s.name,b.area,c.name,cd.name,s.phone,s.entry_date from t_staff s,t_branch_office b,t_company_post c,t_company_department cd 

	/** 序号 */
	private long id;
	
	/** 工号 */
	private String job_number;
	
	/** 姓名 */
	private String name;
	
	/** 分公司id */
	private String district_id;
	
	/** 部门id */
	private long branch_id;
	
	/** 职位id */
	private long post_id;
	
	/** 地区 */
	private String area;
	
	/** 部门名称 */
	private String dep_name;
	
	/** 岗位名称*/
	private String post_name;
	
	/** 电话 */
	private String phone;
	
	/** 实习工资 */
	private BigDecimal base_pay;
	
	/**转正工资*/
	private BigDecimal zbase_pay;
	
	/** 全勤 */
	private BigDecimal attendance_bouns;
	
	/** 餐补 */
	private BigDecimal meal_bouns;
	
	/** 工装补 */
	private BigDecimal forck_bouns;
	
	/** 入职日期 */
   
	private String entry_date;
	
	/** 创建时间  */
	private Date create_time;
	
	/** 更新时间 */
	private Date update_time;
	
	/**状态 锁定1   未锁定0*/
	private int type;
	/**上一级领导id**/
	private String leadersalesid;
	
	public String getLeadersalesid() {
		return leadersalesid;
	}

	public void setLeadersalesid(String leadersalesid) {
		this.leadersalesid = leadersalesid;
	}

	/**是否转正*/
	private int is_become;
	
	
	
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

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
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

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
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

	public BigDecimal getZbase_pay() {
		return zbase_pay;
	}

	public void setZbase_pay(BigDecimal zbase_pay) {
		this.zbase_pay = zbase_pay;
	}

	@Override
	public String toString() {
		return "StaffVo [id=" + id + ", job_number=" + job_number + ", name=" + name + ", district_id=" + district_id
				+ ", branch_id=" + branch_id + ", post_id=" + post_id + ", area=" + area + ", dep_name=" + dep_name
				+ ", post_name=" + post_name + ", phone=" + phone + ", base_pay=" + base_pay + ", zbase_pay="
				+ zbase_pay + ", attendance_bouns=" + attendance_bouns + ", meal_bouns=" + meal_bouns + ", forck_bouns="
				+ forck_bouns + ", entry_date=" + entry_date + ", create_time=" + create_time + ", update_time="
				+ update_time + ", type=" + type + ", leadersalesid=" + leadersalesid + ", is_become=" + is_become
				+ "]";
	}

	
}
