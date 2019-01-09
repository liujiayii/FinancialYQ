
package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.t_user;

public interface UserDao {
	
	/**
	 * 角色查询
	 * 
	 * @return
	 * @author han
	 * @createDate 2018.7.6
	 */
	List<t_user> getUser();
	/**
	 * 角色查询根据id
	 * 
	 * @return
	 * @author han
	 * @createDate 2018.7.6
	 */
	t_user getUserbyId(@Param("id")String id);
	/**
	 * 角色修改
	 * 
	 * @return
	 * @author han
	 * @createDate 2018.7.6
	 */
	int updateUser(t_user user);
	/**
	 * 增加用户
	 * @param user
	 */
	int toAddUser(t_user user);

	/**
	 * 通过用户名和密码查询用户
	 * @param user
	 * @return
	 * @createDate 2018.07.05
	 */
	

	public t_user findUserByNameAndPwd(t_user user);
	/**
	 * 后台添加时通过用户名查看该用户时候存在
	 * 登录时异步验证用户名是否存在
	 * @param user
	 * @return
	 */
	public t_user findByUsername(String username);
	/**
	 * 验证密码是否正确
	 * @time 2018年8月24日 下午3:37:00
	 * @author liuhj
	 */
	public t_user findByPass(String username,String password);
	/**
	 * 异步判断电话是否已存在
	 * @time 2018年8月29日 上午10:45:29
	 * @author liuhj
	 */
	public t_user findUserByPhone(String phone);
	/**
	 * 删除该权限职员
	 * @param id
	 * @return
	 */
	public int deleteUserById(String id);
	/**
	 * 锁定
	 * @param id
	 */
	public int lockUser(String id);
}

