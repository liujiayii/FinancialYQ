package com.fendo.entity;

import java.util.Date;

/**
 * 业务员信息显示Vo实体类
 * @author lhj
 *
 */
public class SalesmanVo {
	/*业务员id*/
	private String id;
	/*姓名*/
	private String name;
	/*地区*/
	private String area;
	/*职位*/
	private String duty;
	
	/*电话*/
	private String phone;
	/*创建时间*/
	private Date create_time;
	/*锁定和未锁定状态*/
	private String state;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	@Override
	public String toString() {
		return "SalesmanVo [id=" + id + ", name=" + name + ", area=" + area + ", duty=" + duty + ", phone=" + phone
				+ ", create_time=" + create_time + ", state=" + state + "]";
	}
	
	
	
}
