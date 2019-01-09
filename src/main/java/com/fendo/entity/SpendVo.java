package com.fendo.entity;

import java.util.Date;
/**
 * 列表显示支出的vo实体类
 * @author han
 *
 * @createDate 2018年8月6日-上午9:57:05
 */
public class SpendVo {
	/*主键id*/
	private long id;
		
	/*支出项目标题*/
	private String title;
	/*创建时间*/
	private Date creat_time;
	/*状态     0 未生效   1 已生效*/
	private int state;
	/* （分公司）地区 */
	private String area;
	/* 姓名 */
	private String name;
	/*支出项目名称*/
	private String spend_name;
	/*支出项目金额*/
	private Double money;	
	/*总条数*/
	private int sumnumber;
	/*合计*/
	private Double sum;
	
	private long spend_cost_id;
	/*员工姓名*/
	private long staff_id ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpend_name() {
		return spend_name;
	}
	public void setSpend_name(String spend_name) {
		this.spend_name = spend_name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public int getSumnumber() {
		return sumnumber;
	}
	public void setSumnumber(int sumnumber) {
		this.sumnumber = sumnumber;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public long getSpend_cost_id() {
		return spend_cost_id;
	}
	public void setSpend_cost_id(long spend_cost_id) {
		this.spend_cost_id = spend_cost_id;
	}
	public long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}
	@Override
	public String toString() {
		return "SpendVo [id=" + id + ", title=" + title + ", creat_time=" + creat_time + ", state=" + state + ", area="
				+ area + ", name=" + name + ", spend_name=" + spend_name + ", money=" + money + ", sumnumber="
				+ sumnumber + ", sum=" + sum + ", spend_cost_id=" + spend_cost_id + ", staff_id=" + staff_id + "]";
	}
	
	
}

