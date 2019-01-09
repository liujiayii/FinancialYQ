package com.fendo.entity;

import java.io.Serializable;

/**
 * 职务类型表(实体类)
 * @author lhj
 *
 */
public class DutyType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*职务类型id*/
	private String id;
	/*类型名称*/
	private int type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DutyType [id=" + id + ", type=" + type + "]";
	}
	
	
}
