package com.fendo.entity;
/**
 * @author 
 * @return
 * @author
 */
public class RuleSalesVo {
	 private String id;
	 private String duty;
	 private String name;
	 private String phone;
	 private String district_id;
	 private String time;
	 private double extract;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public double getExtract() {
		return extract;
	}
	public void setExtract(double extract) {
		this.extract = extract;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public String getTime() {
		return time;
	}
	@Override
	public String toString() {
		return "RuleVo [id=" + id + ", duty=" + duty + ", name=" + name + ", phone=" + phone + ", district_id="
				+ district_id + ", time=" + time + ", extract=" + extract + "]";
	}
	public void setTime(String time) {
		this.time = time;
	}




}
