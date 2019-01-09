package com.fendo.entity;
/**
 * @author 
 * @return
 * @author
 */
public class SalesVo {
      /*投标状态*/
	private String type;
	/* 锁状态*/
	private String state;
	/*是否提前还款状态*/
	private String stateone;
	/*是否逾期*/
	private String statetwo;
	/*每单的金额*/
	private int money;
	/*每单的业务员的职务类型*/
	private String duty;
	/*每单的期限*/
	private int day;
	
	
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateone() {
		return stateone;
	}
	public void setStateone(String stateone) {
		this.stateone = stateone;
	}
	public String getStatetwo() {
		return statetwo;
	}
	public void setStatetwo(String statetwo) {
		this.statetwo = statetwo;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "SalesVo [type=" + type + ", state=" + state + ", stateone=" + stateone + ", statetwo=" + statetwo
				+ ", money=" + money + ", duty=" + duty + ", day=" + day + "]";
	}
	
	
}
