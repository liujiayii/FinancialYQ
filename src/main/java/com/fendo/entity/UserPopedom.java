package com.fendo.entity;

import java.util.Date;

public class UserPopedom {
	/*
	 * 主键id
	 */
	private Long id;
	/*
	 * 功能id
	 */
	private Long p_id;
	/*
	 * 用户id
	 */
	private Long user_id;
	/*
	 * 修改时间
	 */
	private Date update_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "UserPopedom [id=" + id + ", p_id=" + p_id + ", user_id=" + user_id + ", update_time=" + update_time
				+ "]";
	}
}
