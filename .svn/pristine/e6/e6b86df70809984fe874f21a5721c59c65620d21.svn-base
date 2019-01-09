package com.fendo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
	
	/*跳转页面到home_page*/
	@RequestMapping("/toHomePage")
	public ModelAndView toHomePage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home_page");
		return mv;
	}
	
	/*跳转页面到权限 管理页面*/
	@RequestMapping("/toAuthManger")
	public ModelAndView toAuthManger(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AuthorityManagement");
		return mv;
	}
}
