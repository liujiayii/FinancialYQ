package com.fendo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(实体类)
 * @author hm
 *
 */
public class t_user implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*用户id*/
	private String  id;
	/*角色权限类型*/
	private String role_type;
	/*电话*/
	private String phone;
	/*姓名*/
	private String name;
	/*登录时账号名*/
	private String username;
	/*密码*/
	private String password;
	/*锁定状态*/
	private int status;
	/*创建时间*/
	private Date create_time;
	/*更新时间*/
	private Date update_time;
	
	
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public t_user() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole_type() {
		return role_type;
	}
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "t_user [id=" + id + ", role_type=" + role_type + ", phone=" + phone + ", name=" + name + ", username="
				+ username + ", password=" + password + ", status=" + status + "]";
	}
	
	
	
	
}
