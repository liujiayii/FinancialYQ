package com.fendo.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.t_user;
import com.fendo.service.UserService;
import com.fendo.utils.DesUtil;
import com.fendo.utils.MD5Encrypt;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * 跳转到登录页面
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		return "Login";
	}

	/**
	 * 登录
	 * 
	 * @param session
	 * @param user    用户
	 * @param request 请求
	 * @return
	 * 
	 * @author lhj zhoujiaxin
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/login")
	@ResponseBody
	public ModelAndView login(HttpSession session, t_user user, HttpServletRequest request) throws Exception {
		String sstr = null;
		String result = null;
		String auth_code = null;
		//获取用户的登录信息
	    if(session.getAttribute("currUser")!=null){
	        sstr = (String) session.getAttribute("currUser").toString();
	    }
		// 获取用户输入的验证码
		auth_code = request.getParameter("auth_code").toString();
		// 获取系统生成的验证码
		result = (String) session.getAttribute("result").toString();
		ModelAndView modelAndView = new ModelAndView();
		//判断用户是否登录，如果没有登录则登录，如果登录完成则跳转到登录完成页面
		if (sstr == null) {
			// 再加密
			String encryptPwd = DesUtil.encode("cwguanli", user.getPassword());
			String md5Pwd = MD5Encrypt.MD5Encode(encryptPwd);
			user.setPassword(md5Pwd);
			t_user users = userService.login(user);
			if (users != null) {
				// 设置当前登录用户的角色
				session.setAttribute("roletype", users.getRole_type());
				// 设置当前用户的用户名
				session.setAttribute("username", users.getUsername());
				session.setAttribute("name", users.getName());
				// 设置当前用户
				session.setAttribute("currUser", users);
				if (result == null) {
					modelAndView.setViewName("hydl");

				} else if (result.equals(auth_code)) {

					if (users.getRole_type().equals("行政")) {
						System.out.println("*******************");
						modelAndView.setViewName("financialStaff");
					} else {
						modelAndView.setViewName("home_page");
					}

				} else {
					modelAndView.setViewName("Login");
				}

			} else {
				modelAndView.setViewName("Login");
			}
			return modelAndView;
		}			
	    //如果用户已经登录跳转到登录成功的页面你
		modelAndView.setViewName("hydl");
		return modelAndView;
	}

	@RequestMapping("/loginTwo")
	@ResponseBody
	public ResultInfo loginTwo(HttpSession session, t_user user, HttpServletRequest request) throws Exception {

		ResultInfo result = new ResultInfo();
		// 前端输入的值
		String auth_code = request.getParameter("auth_code").toString();
		System.out.println("auth_code" + auth_code);
		// 验证码获取的结果值
		String resultNum = (String) session.getAttribute("result").toString();

		if (user != null) {
			if (resultNum.equals(auth_code)) {
				result.code = 0;
				result.msg = "正确";
			} else {
				result.code = -1;
				result.msg = "错误";
			}
		}
		return result;

	}

	/**
	 * 后台画验证码 ，前端页面显示，并计算结果
	 * 
	 * @param session 把计算结果result存到session中
	 * @param resp
	 */
	@RequestMapping(value = "/showAuthCode")
	public void showAuthCode(HttpSession session, HttpServletResponse resp, Model model) {

		int width = 150; // 宽
		int height = 60; // 高
		int codeCount = 4; // 产生随机的数量
		int x = 0; //
		int fontHeight; // 字体高度
		int codeY;
		// 随机产生的字符
		char[] codeSequence = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] codenumber = { '+', '-' };

		// 显示是x轴的位置
		x = width / (codeCount + 1);
		// 显示字体的高度
		fontHeight = height - 2;
		// y轴的位置
		codeY = height - 4;
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.createGraphics();
		// 创建一个随机数生成器
		Random r = new Random();
		Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		g.setColor(c);
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小由图片的高度来定
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// 设置字体
		g.setFont(font);
		// 随即产生160条干扰线，使图像中的认证码不易被其它程序探测到
		int lines = 10;
		for (int i = 0; i < lines; i++) {
			// 设置干扰线颜色
			Color c1 = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			g.setColor(c1);
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}

		// randomCode 用于保存随机产生的验证码，以使用户登录后进行验证
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		// 随机产生codeCount数字的验证码
		// 前一个数字
		String strRnd = String.valueOf(codeSequence[r.nextInt(10)]);
		// 中间的符号
		String s = String.valueOf(codenumber[r.nextInt(2)]);
		// 后面的数字
		String strRndlast = String.valueOf(codeSequence[r.nextInt(10)]);

		String coderesult = "=";
		String strRndwhy = "?";
		if (Integer.parseInt(strRnd) < Integer.parseInt(strRndlast)) {
			String a = strRndlast;
			strRndlast = strRnd;
			strRnd = a;
		}
		// 产生随机的颜色分量来构造颜色值。这样输出的每位数字的颜色值都将不同
		red = r.nextInt(255);
		green = r.nextInt(255);
		blue = r.nextInt(255);
		// 用随机产生的颜色将验证码绘制到图像中
		g.setColor(new Color(red, green, blue));
		g.drawString(strRnd, 1 * x / 3, codeY);
		g.drawString(s, 1 * x + 1, codeY);
		g.drawString(strRndlast, 2 * x, codeY);
		g.drawString(coderesult, 3 * x, codeY);
		g.drawString(strRndwhy, 4 * x, codeY);
		// 将产生的四个随机数组合到一起
		randomCode.append(strRnd);
		randomCode.append(s);
		randomCode.append(strRndlast);

		int start = Integer.parseInt(randomCode.substring(0, 1));
		String cen = randomCode.substring(1, 2);
		int end = Integer.parseInt(randomCode.substring(2, 3));
		int result = 0;
		if (cen.equals("-")) {
			result = start - end;

		} else if (cen.equals("+")) {
			result = start + end;
		}
		// 不需要计算的
		randomCode.append(coderesult);
		randomCode.append(strRndwhy);

		session.setAttribute("validateCode", randomCode.toString());
		session.setAttribute("result", result);
		model.addAttribute("result", result);
		ServletOutputStream sos;
		try {
			sos = resp.getOutputStream();
			ImageIO.write(buffImg, "jpg", sos);
			sos.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		// Shiro登出
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		
			session.removeAttribute("currUser");
			session.removeAttribute("users");
			subject.logout();
		
		modelAndView.setViewName("Login");
		return modelAndView;
	}

}
