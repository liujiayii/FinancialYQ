package com.fendo.entity;
/**
 * 职位实体
 * @author liuhangjing
 *
 */
public class CompanyPost {
	/** 职位id */
	private long id;
	
	/** 职位名称 */
	private String post_name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	@Override
	public String toString() {
		return "CompanyPost [id=" + id + ", post_name=" + post_name + "]";
	}
	
	
}
