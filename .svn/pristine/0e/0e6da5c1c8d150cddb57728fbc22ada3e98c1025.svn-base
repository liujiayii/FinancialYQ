
package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.UserDao;
import com.fendo.entity.t_user;
import com.fendo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	/* 角色查询 */

	@Override
	public List<t_user> getUser() {

		return userDao.getUser();
	}
	/*用户登录*/
	@Override
	public t_user login(t_user user) {
		t_user user1 = userDao.findUserByNameAndPwd(user);
		if (user1 != null) {
			System.out.println("找到了");
		}
		return user1;

	}
	/*添加角色*/
	@Override
	public int toAddUser(t_user user) {
		int rows = userDao.toAddUser(user);
		if(rows>=1){
			
			System.out.println("添加成功");
		}
		return rows;
	}
	/*显示要修改职员的信息*/
	@Override
	public t_user getUserbyId(String id) {
		
		t_user user = userDao.getUserbyId(id);
		if(user!=null){
			System.out.println(user+"查询到当前id的用户");
		}
		return user;
	}
	@Override
	public int updateUser(t_user user) {
		int rows = userDao.updateUser(user);
		if(rows>=1){
			System.out.println("impl+修改成功");
		}
		return rows;
	}
	@Override
	public t_user findByUsername(String username) {
		
		t_user users = userDao.findByUsername(username);
		if(users!=null){
			System.out.println("该用户已经被添加过");
		}
		return users;
	}
	@Override
	public int deleteUserById(String id) {
		int rows = userDao.deleteUserById(id);
		if(rows>=1){
			System.out.println("删除成功");
		}
		return rows;
	}
	@Override
	public int lockUser(String id) {
		int rows = userDao.lockUser(id);
		if(rows>=1){
			System.out.println("已锁定");
		}
		return rows;
	}
	@Override
	public t_user findByPass(String username,String password) {
		return userDao.findByPass(username,password);
		
	}
	@Override
	public t_user findUserByPhone(String phone) {
	
		return userDao.findUserByPhone(phone);
	}

}
