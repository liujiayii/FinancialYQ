package com.fendo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 风控表(实体)
 * @author lcc
 *
 */
public class Finance implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/*id*/
	private Long id;
   /*1 为新标  0 为续贷*/
	private Long audit_id;

	/*业务员id*/
	private String s_id;	
	/*标号*/
	private String grade;
	/*客户姓名*/
	private String name;		
	/*分公司*/
	private String area;	
	/*业务员姓名*/
	private String salesman;	
	/*金额*/
	private BigDecimal money;
	/*天数*/
	private String day;		
	/*还本金*/
	private BigDecimal principal;
	/*过账*/
	private BigDecimal account;
	/*房产服务费*/
	private BigDecimal houseProperty;
	/*利息 ==平台服务费+利息（风控）*/
	private BigDecimal interest;
	/*服务费*/
	private BigDecimal serviceMoney;
	/*征信查询费*/
	private BigDecimal creditInquiry;	
	/*垫资费*/
	private BigDecimal capitalMoney;	
	/*档案费*/
	private BigDecimal archivesMoney;		
	/*状态(未审核0  已审核1 ,已作废3，已打回/审核未通过2)*/
	private int type;
	/*合计*/
	private BigDecimal sum;
	/*状态(0保存1提交) */
	private int types;	
	/*建表时间*/
	private Date time;
	/*是否逾期*/
	private int is_overdue;
	/*是否提前还款*/
	private int is_payment;
	/*实地费*/
	private BigDecimal landMoney;
	/*GPS安装*/
	private BigDecimal gpsMoney;
	/*停车费*/
	private BigDecimal stopMoney;
	/*进抵费*/
	private BigDecimal jindiMoney;
	/*查档费*/
	private BigDecimal fileMoney;
	/*他项费*/
	private BigDecimal elseMoney;
	/*收据类型 （1 续贷还平台 ，2 到期还平台，3逾期还平台，4 提前还平台 5 续贷还本6  到期还本 ，7逾期还本 8提前还本））*/
	private int receiptType;
	/*业务类型 （0 惠房贷  1 惠车贷）*/
	private int businessType;
	/*旧标号*/
	private String formergrade;
	
	/*期数*/
	private String period;
	/*平台服务费+利息（财务）*/
	private BigDecimal interests;
	/*续贷还本*/
	private BigDecimal refinancePrincipal;
	/*到期还本*/
	private BigDecimal maturityPrincipal;
	/*提前还本*/
	private BigDecimal prepaymentPrincipal;
	/*逾期还本*/
	private BigDecimal overduePrincipal;
	/*续贷还平台*/
	private BigDecimal refinancePlatform;
	/*到期还平台*/
	private BigDecimal maturityPlatform;
	/*逾期还平台*/
	private BigDecimal overduePlatform;
	/*提前还平台*/
	private BigDecimal prepaymentPlatform;
	/*风控人员*/
	private String  riskman;
	/*0是风控写的    1是财务写的*/
	private int state;
	/*备注*/
	private String remark;
	/*本金*/
	private BigDecimal principals;
	/*利息*/
	private BigDecimal accrual;
	/*审核人*/
	private String auditor;
	/*审核人角色*/
	private String role;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRiskman() {
		return riskman;
	}
	public void setRiskman(String riskman) {
		this.riskman = riskman;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(Long audit_id) {
		this.audit_id = audit_id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public BigDecimal getHouseProperty() {
		return houseProperty;
	}
	public void setHouseProperty(BigDecimal houseProperty) {
		this.houseProperty = houseProperty;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public BigDecimal getServiceMoney() {
		return serviceMoney;
	}
	public void setServiceMoney(BigDecimal serviceMoney) {
		this.serviceMoney = serviceMoney;
	}
	public BigDecimal getCreditInquiry() {
		return creditInquiry;
	}
	public void setCreditInquiry(BigDecimal creditInquiry) {
		this.creditInquiry = creditInquiry;
	}
	public BigDecimal getCapitalMoney() {
		return capitalMoney;
	}
	public void setCapitalMoney(BigDecimal capitalMoney) {
		this.capitalMoney = capitalMoney;
	}
	public BigDecimal getArchivesMoney() {
		return archivesMoney;
	}
	public void setArchivesMoney(BigDecimal archivesMoney) {
		this.archivesMoney = archivesMoney;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	public int getTypes() {
		return types;
	}
	public void setTypes(int types) {
		this.types = types;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getIs_overdue() {
		return is_overdue;
	}
	public void setIs_overdue(int is_overdue) {
		this.is_overdue = is_overdue;
	}
	public int getIs_payment() {
		return is_payment;
	}
	public void setIs_payment(int is_payment) {
		this.is_payment = is_payment;
	}
	public BigDecimal getLandMoney() {
		return landMoney;
	}
	public void setLandMoney(BigDecimal landMoney) {
		this.landMoney = landMoney;
	}
	public BigDecimal getGpsMoney() {
		return gpsMoney;
	}
	public void setGpsMoney(BigDecimal gpsMoney) {
		this.gpsMoney = gpsMoney;
	}
	public BigDecimal getStopMoney() {
		return stopMoney;
	}
	public void setStopMoney(BigDecimal stopMoney) {
		this.stopMoney = stopMoney;
	}
	public BigDecimal getJindiMoney() {
		return jindiMoney;
	}
	public void setJindiMoney(BigDecimal jindiMoney) {
		this.jindiMoney = jindiMoney;
	}
	public BigDecimal getFileMoney() {
		return fileMoney;
	}
	public void setFileMoney(BigDecimal fileMoney) {
		this.fileMoney = fileMoney;
	}
	public BigDecimal getElseMoney() {
		return elseMoney;
	}
	public void setElseMoney(BigDecimal elseMoney) {
		this.elseMoney = elseMoney;
	}
	public int getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(int receiptType) {
		this.receiptType = receiptType;
	}
	public int getBusinessType() {
		return businessType;
	}
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}
	public String getFormergrade() {
		return formergrade;
	}
	public void setFormergrade(String formergrade) {
		this.formergrade = formergrade;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getInterests() {
		return interests;
	}
	public void setInterests(BigDecimal interests) {
		this.interests = interests;
	}
	public BigDecimal getRefinancePrincipal() {
		return refinancePrincipal;
	}
	public void setRefinancePrincipal(BigDecimal refinancePrincipal) {
		this.refinancePrincipal = refinancePrincipal;
	}
	public BigDecimal getMaturityPrincipal() {
		return maturityPrincipal;
	}
	public void setMaturityPrincipal(BigDecimal maturityPrincipal) {
		this.maturityPrincipal = maturityPrincipal;
	}
	public BigDecimal getPrepaymentPrincipal() {
		return prepaymentPrincipal;
	}
	public void setPrepaymentPrincipal(BigDecimal prepaymentPrincipal) {
		this.prepaymentPrincipal = prepaymentPrincipal;
	}
	public BigDecimal getOverduePrincipal() {
		return overduePrincipal;
	}
	public void setOverduePrincipal(BigDecimal overduePrincipal) {
		this.overduePrincipal = overduePrincipal;
	}
	public BigDecimal getRefinancePlatform() {
		return refinancePlatform;
	}
	public void setRefinancePlatform(BigDecimal refinancePlatform) {
		this.refinancePlatform = refinancePlatform;
	}
	public BigDecimal getMaturityPlatform() {
		return maturityPlatform;
	}
	public void setMaturityPlatform(BigDecimal maturityPlatform) {
		this.maturityPlatform = maturityPlatform;
	}
	public BigDecimal getOverduePlatform() {
		return overduePlatform;
	}
	public void setOverduePlatform(BigDecimal overduePlatform) {
		this.overduePlatform = overduePlatform;
	}
	public BigDecimal getPrepaymentPlatform() {
		return prepaymentPlatform;
	}
	public void setPrepaymentPlatform(BigDecimal prepaymentPlatform) {
		this.prepaymentPlatform = prepaymentPlatform;
	}
	
	public BigDecimal getPrincipals() {
		return principals;
	}
	public void setPrincipals(BigDecimal principals) {
		this.principals = principals;
	}
	public BigDecimal getAccrual() {
		return accrual;
	}
	public void setAccrual(BigDecimal accrual) {
		this.accrual = accrual;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Finance [id=" + id + ", audit_id=" + audit_id + ", s_id=" + s_id + ", grade=" + grade + ", name=" + name
				+ ", area=" + area + ", salesman=" + salesman + ", money=" + money + ", day=" + day + ", principal="
				+ principal + ", account=" + account + ", houseProperty=" + houseProperty + ", interest=" + interest
				+ ", serviceMoney=" + serviceMoney + ", creditInquiry=" + creditInquiry + ", capitalMoney="
				+ capitalMoney + ", archivesMoney=" + archivesMoney + ", type=" + type + ", sum=" + sum + ", types="
				+ types + ", time=" + time + ", is_overdue=" + is_overdue + ", is_payment=" + is_payment
				+ ", landMoney=" + landMoney + ", gpsMoney=" + gpsMoney + ", stopMoney=" + stopMoney + ", jindiMoney="
				+ jindiMoney + ", fileMoney=" + fileMoney + ", elseMoney=" + elseMoney + ", receiptType=" + receiptType
				+ ", businessType=" + businessType + ", formergrade=" + formergrade + ", period=" + period
				+ ", interests=" + interests + ", refinancePrincipal=" + refinancePrincipal + ", maturityPrincipal="
				+ maturityPrincipal + ", prepaymentPrincipal=" + prepaymentPrincipal + ", overduePrincipal="
				+ overduePrincipal + ", refinancePlatform=" + refinancePlatform + ", maturityPlatform="
				+ maturityPlatform + ", overduePlatform=" + overduePlatform + ", prepaymentPlatform="
				+ prepaymentPlatform + ", riskman=" + riskman + ", state=" + state + ", remark=" + remark
				+ ", principals=" + principals + ", accrual=" + accrual + ", auditor=" + auditor + ", role=" + role
				+ "]";
	}
	
	
	
}

