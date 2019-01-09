package com.fendo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * @return
 * @author cc
 */
public class Rule implements Serializable{
	/*id*/
	private String id;
	/*地区编号*/
	private String district_id;
	/*任务*/
	private int task;
	/*完成百分比*/
	private int completionrate;
	/*门店经理任务之内百分率*/
	private int rule_one;
	/*门店经理超出任务百分率*/
	private int rule_two;
	/*营业部经理最大提成*/
	private int salesManageNumber;
	/*业务员提成百分率*/
	private int rule_three;
	/*创建时间*/
	private Date time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public int getTask() {
		return task;
	}
	public void setTask(int task) {
		this.task = task;
	}
	public int getCompletionrate() {
		return completionrate;
	}
	public void setCompletionrate(int completionrate) {
		this.completionrate = completionrate;
	}
	public int getRule_one() {
		return rule_one;
	}
	public void setRule_one(int rule_one) {
		this.rule_one = rule_one;
	}
	public int getRule_two() {
		return rule_two;
	}
	public void setRule_two(int rule_two) {
		this.rule_two = rule_two;
	}
	public int getSalesManageNumber() {
		return salesManageNumber;
	}
	public void setSalesManageNumber(int salesManageNumber) {
		this.salesManageNumber = salesManageNumber;
	}
	public int getRule_three() {
		return rule_three;
	}
	public void setRule_three(int rule_three) {
		this.rule_three = rule_three;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Rule [id=" + id + ", district_id=" + district_id + ", task=" + task + ", completionrate="
				+ completionrate + ", rule_one=" + rule_one + ", rule_two=" + rule_two + ", salesManageNumber="
				+ salesManageNumber + ", rule_three=" + rule_three + ", time=" + time + "]";
	}
	
	
	
}
