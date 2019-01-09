package com.fendo.entity;

import java.io.Serializable;
import java.sql.Date;


/**
 * 分公司地区表(实体)
 * @author lhj
 *
 */
public class BranchOffice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*地区编号*/
	private String id;
	
	/*地区名称*/
	private String area;
	
	/*区号*/
	private String area_code;
	
	/*创建时间*/
	private Date create_time;
	
	

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	@Override
	public String toString() {
		return "BranchOffice [id=" + id + ", area=" + area + ", area_code=" + area_code + ", create_time=" + create_time
				+ "]";
	}

	

	
	
}
