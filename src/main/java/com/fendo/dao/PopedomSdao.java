package com.fendo.dao;

import java.util.List;

import com.fendo.entity.Popedom;
import com.fendo.entity.UserPopedom;
import com.fendo.entity.t_user;

public interface PopedomSdao {
	/**
	  * @Title: findPopedomByUserId
	  * @description 根据用户id查询所有权限
	  * @param @param id 用户id
	  * @return List<Popedom> 返回所有权限   
	  * @author ZhaoSong
	  * @createDate 2019年1月7日
	 */
	String findPopedomByUserId(Long id);
	
	
	/**
	  * @Title: deletePopedomById
	  * @description 根据用户id删除权限
	  * @param @param id 用户id
	  * @return Integer 受影响行数   
	  * @author ZhaoSong
	  * @createDate 2019年1月7日
	 */
	Integer deletePopedomById(Long id);
	
	
	/**
	  * @Title: addPopedomById
	  * @description 添加权限
	  * @param @param userPopedom 用户权限对象
	  * @return Integer 受影响行数   
	  * @author ZhaoSong
	  * @createDate 2019年1月7日
	 */
	Integer addPopedomById(UserPopedom userPopedom);

	
	/**
	  * @Title: updatePopedomById
	  * @description 根据用户id修改权限
	  * @param @param id 用户Id
	  * @param @param popedoms 权限数组
	  * @return Integer 受影响行数   
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	Integer updatePopedomById(Long id, String popedoms);

	/**
	  * @Title: findByLimit
	  * @description 分页查询用户信息
	  * @param @param page 页数
	  * @param @param limit 条数
	  * @return List<User> 用户对象集合   
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	List<t_user> findByLimit(Integer page, Integer limit);
	
	/**
	  * @Title: findAll
	  * @description 无条件查询所有用户
	  * @return List<User> 返回所有用户集合   
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	List<t_user> findAll();

	/**
	  * @Title: findUserByPhone
	  * @description 根据关键字分页查询
	  * @param @param key 关键字
	  * @param @param page 页数
	  * @param @param limit 条数
	  * @return List<User> 返回所有符合条件的用户信息
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	List<t_user> findUserByPhone(String key, Integer page, Integer limit);

	
	/**
	  * @Title: findPhoneCount
	  * @description 符合关键字条件的用户条数
	  * @param @param key 关键字
	  * @return Integer 符合条件的条数    
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	Integer findPhoneCount(String phones);
}
