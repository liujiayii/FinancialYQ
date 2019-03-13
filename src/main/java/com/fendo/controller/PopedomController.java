package com.fendo.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fendo.entity.Popedom;
import com.fendo.entity.t_user;
import com.fendo.service.PopedomService;
import com.fendo.utils.JsonUtil;


@Controller
@RequestMapping("/popedom")
public class PopedomController {

	@Autowired
	private PopedomService popedomService;
	/**
	 * @Title: updatePopedomById
	 * @description 根据用户id修改权限
	 * @param @param
	 *            id 用户id
	 * @param @param
	 *            popedoms 权限编号数组
	 * @return String JSON字符串
	 * @author ZhaoSong
	 * @createDate 2019年1月9日
	 */
	@RequestMapping(value="/updatePopedom",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updatePopedomById(Long id, Integer[] popedoms) {
		StringBuffer str5 = new StringBuffer();
		for(int i=0;i<popedoms.length;i++) {
			str5.append(popedoms[i]);
			str5.append(",");
			System.out.println(popedoms[i]);
		}
		System.out.println(Arrays.toString(popedoms));
		
		  String popedom=Arrays.toString(popedoms);
		System.out.println(id);
		Integer row = popedomService.updatePopedomById(id,str5.toString());
		if(popedoms!=null){
		
		}
		String msg;
		if (row >= 1) {
			msg = "修改权限成功";
			return JsonUtil.getResponseJson(row, msg, null, null);
		} else {
			msg = "修改权限失败";
			return JsonUtil.getResponseJson(row, msg, null, null);
		}
	}

	/**
	 * @Title: findPopedomByUserId
	 * @description 根据用户id查询所有权限
	 * @param @param
	 *            id 用户id
	 * @return String JSON 字符串
	 * @author ZhaoSong
	 * @createDate 2019年1月9日
	 */
	@RequestMapping(value="/findPopedomByUserId",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findPopedomByUserId(Long id,HttpServletResponse response) {
		Integer code = 1;
		String msg;
		
	    
		String popedoms = popedomService.findPopedomByUserId(id);
		System.out.println("id道德之-------------"+popedoms);
		
		
		
		if (popedoms != null) {
			
			String[] popedom = popedoms.split(",");
			System.out.println("id道德之-------------"+popedom);
			msg = "查询成功";
			return JsonUtil.getResponseJson(code, msg, null, popedom);
		} else {
			code = -1;
			msg = "查询失败";
			return JsonUtil.getResponseJson(null, msg, null, null);
		}
	}

//	/**
//	 * @Title: findByLimit
//	 * @description 分页查询用户信息
//	 * @param @param
//	 *            page 页数
//	 * @param @param
//	 *            limit 条数
//	 * @return String JSON字符串
//	 * @author ZhaoSong
//	 * @createDate 2019年1月9日
//	 */
//	@RequestMapping(value="/findByLimit",produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public String findByLimit(Integer page, Integer limit) {
//		List<t_user> users;
//		Integer code = 1;
//		String msg;
//		if (page == null || page <= 0) {
//			page = 1;
//		}
//		page = page - 1 * limit;
//		users = popedomService.findByLimit(page, limit);
//		try {
//			if (users.size() != 0) {
//				msg = "查询成功";
//				return JsonUtil.getResponseJson(code, msg, popedomService.findAll().size(), users);
//			} else {
//				msg = "无数据";
//				return JsonUtil.getResponseJson(code, msg, popedomService.findAll().size(), users);
//			}
//		} catch (Exception e) {
//			code = -1;
//			msg = "系统异常";
//			return JsonUtil.getResponseJson(code, msg, null, null);
//		}
//	}
//
//	/**
//	 * @Title: finaUserByPhone
//	 * @description 通过关键字查询用户
//	 * @param keyWord 关键字
//	 * @return String JSON字符串
//	 * @author ZhaoSong
//	 * @createDate 2019年1月9日
//	 */
//	@RequestMapping(value = "/findUserByPhone", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String finaUserByPhone(@Param("keyWord") String keyWord, @Param("page") Integer page,
//			@Param("limit") Integer limit) {
//		int code = 1;
//		String msg = null;
//		if (page <= 0) {
//			page = 1;
//		}
//		page = (page - 1) * limit;
//
//		if (keyWord.equals("") || keyWord == null) {
//			List<t_user> users = popedomService.findByLimit(page, limit);
//			if (users.size() <= 0) {
//				msg = "查询数据为空";
//			}
//			if (users != null) {
//				msg = "查询成功";
//			}
//			return JsonUtil.getResponseJson(code, msg, popedomService.findAll().size(), users);
//		} else {
//			String key = "%" + keyWord + "%";
//			List<t_user> users = popedomService.findUserByPhone(key, page, limit);
//			if (users.size() <= 0) {
//				msg = "查询数据为空";
//			}
//			if (users != null) {
//				msg = "查询成功";
//			}
//			return JsonUtil.getResponseJson(code, msg, popedomService.findPhoneCount(key), users);
//		}
//
//	}

}
