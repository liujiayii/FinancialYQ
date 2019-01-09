package com.fendo.entity;

import java.io.Serializable;
/**
 * 支出项目实体类
 * @author han
 *
 * @createDate 2018年8月2日-下午3:50:48
 */
public class SpendItem implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*主键id*/
	private long id;
	/*收入项目名称*/
	private String spend_name;
	/*收入项目金额*/
	private Double money;
	/*外键*/
	private long spend_cost_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getSpend_cost_id() {
		return spend_cost_id;
	}
	public void setSpend_cost_id(long spend_cost_id) {
		this.spend_cost_id = spend_cost_id;
	}
	@Override
	public String toString() {
		return "spendItem [id=" + id + ", spend_name=" + spend_name + ", money=" + money + ", spend_cost_id="
				+ spend_cost_id + "]";
	}
	
}
