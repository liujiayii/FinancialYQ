package com.fendo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.t_user;
import com.fendo.service.UserService;
import com.fendo.utils.DesUtil;
import com.fendo.utils.MD5Encrypt;

/**
 * 
 * @author liuhj
 * 
 */

@Controller
public class UserController  {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/touserList")
	public String  togetUser(HttpSession session){
		t_user currUser = (t_user) session.getAttribute("currUser");
		if(currUser == null) {
		
			return "Login";
		}
		return "AuthorityManagement";
	}
	/**
	 * 查询角色
	 * @return List
	 * @author cc
	 */
	@RequestMapping("/userList")
	@ResponseBody
	public List<t_user> getUser(HttpSession session){
		t_user user = (t_user)session.getAttribute("currUser");
	
		//如果当前用户是管理员则可以查看角色权限列表
		
			
			List<t_user> list = new ArrayList<>();
			try {
				list = userService.getUser();
			
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return list;	
	
	}
	/**
	 * 跳转增加权限页面
	 * @return String
	 * @author cc
	 */
	@RequestMapping("/toAddList")
	public String toAddList(HttpSession session){
		t_user currUser = (t_user) session.getAttribute("currUser");
		if(currUser == null) {
		
				return "Login";
		}
		return "AuthorityAdd";
	}
	
	/**
	 * 添加用户
	 * @param user 用户
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/toAddUser",method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddUser(@RequestBody t_user user,HttpSession session ) throws Exception{
		ResultInfo result = new ResultInfo();
		//对密码先进行加密
		String pwd = user.getPassword();
	
		String encryptPwd =  DesUtil.encode("cwguanli",user.getPassword());
	
		String md5Pwd = MD5Encrypt.MD5Encode(encryptPwd);
		
		user.setPassword(md5Pwd);
		t_user users = userService.findByUsername(user.getUsername());
		
		if(users!=null){

			result.code = -1;
			result.msg = "该账号已存在请重新输入";
		
		}else{
			//用户不存在
			int rows = userService.toAddUser(user);
			if(rows>=1){
				result.code = 0;
				result.msg ="successful";
			
			}else{
				result.code = -1;
				result.msg = "error";
			}
		}
		return result;	
		
		
	}	
	/**
	 * 跳转权限修改页面
	 * @param 
	 * @return ModelAndView
	 * 
	 *  @author cc
	 * @throws Exception 
	 */
	@RequestMapping("/toupdateUserByid")
	@ResponseBody
	public ModelAndView toupdateUserByid(String id) throws Exception{
		
	
		ModelAndView mv = new ModelAndView();

		t_user user = userService.getUserbyId(id);
		

		mv.addObject("user",user);
		mv.setViewName("updateAuthorityAdd");
		return mv;
	}
	/**
	 * 修改权限人员信息
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateUser",method= RequestMethod.POST)
	@ResponseBody

	
	public ResultInfo updateUser(@RequestBody t_user user,HttpSession session) throws Exception{
	
		
		ResultInfo result = new ResultInfo();

				//对密码先进行加密
				String pwd = user.getPassword();
				if(pwd != null && pwd !=""){
					
					String encryptPwd =  DesUtil.encode("cwguanli",user.getPassword());
					
					String md5Pwd = MD5Encrypt.MD5Encode(encryptPwd);
				
					user.setPassword(md5Pwd);
				}
				

		try {
			int rows = userService.updateUser(user);
			if(rows>=1){
			
				result.code = 0;
				result.msg = "successfully!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg ="error";
		
		}
		return result;
		
	
	}
	
	/**
	 * 删除该权限职员
	 * @param id
	 * @return
	 */
	@RequestMapping("/toDeleteUser")
	public String toDeleteUserById(String id){
		int rows = userService.deleteUserById(id);
		if(rows>=1){
			
		}
		return "AuthorityManagement";
	}
	
	
	@RequestMapping("/toqxByid")
	public ModelAndView toqxByid(String id){
		System.out.println("id"+id);
		ModelAndView mv=new ModelAndView();
		
		mv.setViewName("qx");

		mv.addObject("id",id);
		return mv;
	}
	
	/**
	 * 锁定该权限职员
	 * @param id
	 * @return
	 */
	@RequestMapping("/toLockUser")
	@ResponseBody
	public ResultInfo toLockUser(@RequestBody String id){
		ResultInfo result = new ResultInfo();
	
		try {
			String ids = id.substring(id.indexOf("=")+1);
			
			int rows = userService.lockUser(ids);
			if(rows>=1){
			
			}
			result.code=0;
			result.msg="successfully!";
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code=-1;
			result.msg="error";
			return result;
		}
		
	}
	/**
	 * 异步判断用户名是否正确
	 * @author liuhj
	 * @return result
	 */
	@RequestMapping(value="/findByUsername",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo loginUser(@RequestParam String username,HttpServletRequest request){
		ResultInfo result = new ResultInfo();
		try {
			String userName = request.getParameter("username");
		
			t_user user = userService.findByUsername(userName);
			if(user!=null){
			
				result.code=0;
				result.msg="用户名正确";
			}else{
			
				result.code=-1;
				result.msg="用户名不正确";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 异步判断密码是否正确
	 * @author liuhj
	 * @return result
	 */
	@RequestMapping(value="/findByPass",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo loginPass(@RequestParam String username,@RequestParam String password,HttpServletRequest request){
		ResultInfo result = new ResultInfo();
		try {
			t_user user = userService.findByUsername(username);
			if(user!=null){
				
			    int  status=user.getStatus();
				String passWord = request.getParameter("password");
				String encryptPwd = DesUtil.encode("cwguanli",passWord); 
			
				//再MD5
				String md5Pwd = MD5Encrypt.MD5Encode(encryptPwd);
				
				String pass = user.getPassword();
			if(status==0) {
				if(pass.equals(md5Pwd)){
					
				
					result.code=0;
					result.msg="密码正确";
				}else{
				
					result.code=-1;
					result.msg="密码不正确";
					
				}
			}
			else {
				result.code=-1;
				result.msg="用户被锁定，请联系管理员";
			}
			}else{
			
				result.code=-1;
				result.msg="请输入正确的用户";
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		return result;
	}
	@RequestMapping(value="/findUserByPhone",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo findUserByPhone(@RequestParam String phone,HttpServletRequest request){
		ResultInfo result = new ResultInfo();
		try {
			String phone1= request.getParameter("phone");
			t_user user = userService.findUserByPhone(phone1);
			if(user!=null){
				result.code=0;
				result.msg="手机号已存在";
			}else{
				result.code=-1;
				result.msg="手机号不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} 
	
}
