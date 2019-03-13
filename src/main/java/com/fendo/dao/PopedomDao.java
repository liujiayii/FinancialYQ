package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.Popedom;
import com.fendo.entity.UserPopedom;
import com.fendo.entity.t_user;



public interface PopedomDao {
	
	/**
	 * @Title: findPopedomByUserId
	 * @description 根据用户id查询所有权限
	 * @param @param id 用户id
	 * @return List<Popedom> 返回权限集合   
	 * @author ZhaoSong
	 * @createDate 2019年1月3日
	 */
	List<Popedom> findPopedomByUserId(Long id);
	
	/**
	  * @Title: deletePopedomById
	  * @description 根据用户Id删除权限
	  * @param @param id 用户Id
	  * @return Integer 受影响行数   
	  * @author ZhaoSong
	  * @createDate 2019年1月7日
	 */
	Integer deletePopedomById(Long id);

	/**
	  * @Title: addPopedomById
	  * @description 根据用户id添加权限
	  * @param @param userPopedom 用户权限对象
	  * @return Integer 受影响行数   
	  * @author ZhaoSong
	  * @createDate 2019年1月7日
	 */
	Integer addPopedomById(UserPopedom userPopedom);
	
	/**
	  * @Title: findByLimit
	  * @description 分页查询用户信息
	  * @param @param page 页数
	  * @param @param limit 条数
	  * @return List<User> 返回用户信息   
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	List<t_user> findByLimit(@Param("page")Integer page, @Param("limit")Integer limit);

	
	/**
	  * @Title: findAll
	  * @description 无条件查询有所用户信息
	  * @return List<User> 所有用户信息   
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	List<t_user> findAll();
	
	
	/**
	  * @Title: findUserByPhone
	  * @description 根据关键字分页查询用户信息
	  * @param @param key 关键字
	  * @param @param page 页数
	  * @param @param limit 条数
	  * @return List<User> 返回符合条件的用户信息   
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	List<t_user> findUserByPhone(@Param("key")String key, @Param("page")Integer page, @Param("limit")Integer limit);
	
	
	/**
	  * @Title: findPhoneCount
	  * @description 查询符合关键字条件的用户条数
	  * @param @param key 关键字
	  * @return Integer 符合条件的用户条数
	  * @author ZhaoSong
	  * @createDate 2019年1月9日
	 */
	Integer findPhoneCount(String phones);
	
}
