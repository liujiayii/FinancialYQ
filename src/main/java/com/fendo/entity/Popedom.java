package com.fendo.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * 权限表POJO
 *
 */
public class Popedom implements Serializable{
	/*
	 * 序列化编号
	 */
	private static final long serialVersionUID = -3810891143481263339L;
	/*
	 * 主键id
	 */
	private Integer id;
	/*
	 * 权限名
	 */
	private String popedom_name;
	/*
	 * 功能id
	 */
	private Integer p_id;
	/*
	 * 功能路径
	 */
	private String popedom_url;
	/*
	 * 创建时间
	 */
	private Date create_time;
	/*
	 * 修改时间
	 */
	private Date update_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPopedom_name() {
		return popedom_name;
	}
	public void setPopedom_name(String popedom_name) {
		this.popedom_name = popedom_name;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public String getPopedom_url() {
		return popedom_url;
	}
	public void setPopedom_url(String popedom_url) {
		this.popedom_url = popedom_url;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	@Override
	public String toString() {
		return "Popedom [id=" + id + ", popedom_name=" + popedom_name + ", p_id=" + p_id + ", popedom_url="
				+ popedom_url + ", create_time=" + create_time + ", update_time=" + update_time + "]";
	}
	
	
	
	
}
