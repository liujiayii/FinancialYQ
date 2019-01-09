
package com.fendo.service;

import java.util.List;

import com.fendo.entity.t_user;

/**
 * @author han
 *
 *@createDate 2018年7月6日-下午3:00:48
 */
public interface UserService {
	/*角色查询*/
	List<t_user> getUser();
	/*角色查询*/
	t_user getUserbyId(String id);
	/*角色增加*/
	public int toAddUser(t_user user);
	/*角色修改*/
	public int updateUser(t_user user);
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public t_user login(t_user user);
	/**
	 * 查看用户是否存在
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
	 * 异步判断手机号是否存在
	 * @time 2018年8月29日 上午10:46:33
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
	 * @return
	 */
	public int lockUser(String id);
}

