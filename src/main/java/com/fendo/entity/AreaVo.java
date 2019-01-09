package com.fendo.entity;

public class AreaVo {
	private String area;
	private String salesman;
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
	@Override
	public String toString() {
		return "AreaVo [area=" + area + ", salesman=" + salesman + "]";
	}
	
	
}
