package com.fendo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务员信息表(实体类)
 * @author lhj
 *
 */
public class SalesMan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*信息id*/
	private long id;
	/*姓名*/
	private String name;
	/*地区编号*/
	private String district_id;
	/*职务类型编号*/
	private String duty;
	/*联系电话*/
	private String phone;
	/*创建时间*/
	private Date create_time;
	/*是否锁定状态*/
	private String state;
	/**工号*/
	private String job_number;
	
	
	
	
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "SalesMan [id=" + id + ", name=" + name + ", district_id=" + district_id + ", duty=" + duty + ", phone="
				+ phone + ", create_time=" + create_time + ", state=" + state + ", job_number=" + job_number + "]";
	}
	
	
	
	
	
	
}
