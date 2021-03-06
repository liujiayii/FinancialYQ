package com.fendo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 支出费用（实体类）
 * @author han
 *
 * @createDate 2018年8月2日-下午3:23:29
 */
public class Spend implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/*主键id*/
	private long id;	
	/* 分公司id */
	private String district_id;
	/*创建时间*/
	private Date creat_time;
	/*员工姓名id*/
	private long staff_id;
	/*状态     0 未生效   1 已生效*/
	private int state;
	/* 标题 */
	private String title;
	/*合计*/
	private BigDecimal sum;
	/* （分公司）地区 */
	private String area;
	/* 姓名 */
	private String name;
	/*备注*/
	private String remark;
	/*收入集合*/
	private List<IncomeItem> IncomeItemList;
	private String spend_name;
	private String spend_name1;
	private String spend_name2;
	private String spend_name3;
	private String spend_name4;
	private String digest;
	private String digest1;
	private String digest2;
	private String digest3;
	private String digest4;
	private BigDecimal money;
	private BigDecimal money1;
	private BigDecimal money2;
	private BigDecimal money3;
	private BigDecimal money4;
	
	/*审核人*/
	private String auditor;
	/*审核人职业*/
	private String role;
	/*录入人*/
	private String entry_person;
	
	public String getEntry_person() {
		return entry_person;
	}
	public void setEntry_person(String entry_person) {
		this.entry_person = entry_person;
	}
	/*输入日期*/
	private Date times;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getTimes() {
		return times;
	}
	public void setTimes(Date times) {
		this.times = times;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public Date getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}
	public long getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
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
	public List<IncomeItem> getIncomeItemList() {
		return IncomeItemList;
	}
	public void setIncomeItemList(List<IncomeItem> incomeItemList) {
		IncomeItemList = incomeItemList;
	}
	public String getSpend_name() {
		return spend_name;
	}
	public void setSpend_name(String spend_name) {
		this.spend_name = spend_name;
	}
	public String getSpend_name1() {
		return spend_name1;
	}
	public void setSpend_name1(String spend_name1) {
		this.spend_name1 = spend_name1;
	}
	public String getSpend_name2() {
		return spend_name2;
	}
	public void setSpend_name2(String spend_name2) {
		this.spend_name2 = spend_name2;
	}
	public String getSpend_name3() {
		return spend_name3;
	}
	public void setSpend_name3(String spend_name3) {
		this.spend_name3 = spend_name3;
	}
	public String getSpend_name4() {
		return spend_name4;
	}
	public void setSpend_name4(String spend_name4) {
		this.spend_name4 = spend_name4;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getDigest1() {
		return digest1;
	}
	public void setDigest1(String digest1) {
		this.digest1 = digest1;
	}
	public String getDigest2() {
		return digest2;
	}
	public void setDigest2(String digest2) {
		this.digest2 = digest2;
	}
	public String getDigest3() {
		return digest3;
	}
	public void setDigest3(String digest3) {
		this.digest3 = digest3;
	}
	public String getDigest4() {
		return digest4;
	}
	public void setDigest4(String digest4) {
		this.digest4 = digest4;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getMoney1() {
		return money1;
	}
	public void setMoney1(BigDecimal money1) {
		this.money1 = money1;
	}
	public BigDecimal getMoney2() {
		return money2;
	}
	public void setMoney2(BigDecimal money2) {
		this.money2 = money2;
	}
	public BigDecimal getMoney3() {
		return money3;
	}
	public void setMoney3(BigDecimal money3) {
		this.money3 = money3;
	}
	public BigDecimal getMoney4() {
		return money4;
	}
	public void setMoney4(BigDecimal money4) {
		this.money4 = money4;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Spend [id=" + id + ", district_id=" + district_id + ", creat_time=" + creat_time + ", staff_id="
				+ staff_id + ", state=" + state + ", title=" + title + ", sum=" + sum + ", area=" + area + ", name="
				+ name + ", remark=" + remark + ", IncomeItemList=" + IncomeItemList + ", spend_name=" + spend_name
				+ ", spend_name1=" + spend_name1 + ", spend_name2=" + spend_name2 + ", spend_name3=" + spend_name3
				+ ", spend_name4=" + spend_name4 + ", digest=" + digest + ", digest1=" + digest1 + ", digest2="
				+ digest2 + ", digest3=" + digest3 + ", digest4=" + digest4 + ", money=" + money + ", money1=" + money1
				+ ", money2=" + money2 + ", money3=" + money3 + ", money4=" + money4 + ", auditor=" + auditor
				+ ", role=" + role + ", entry_person=" + entry_person + ", times=" + times + "]";
	}
	
	
}
