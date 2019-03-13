package com.fendo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fendo.entity.BranchOffice;
import com.fendo.entity.t_user;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.FinanceService;
import com.fendo.service.RuleVoService;
import com.fendo.service.SalesmanService;

/**
 * 
 *
 * @ClassName: BranceOfficeController

 * @description 分公司管理
 *
 * @author HanMeng
 * @createDate 2018年9月14日 上午9:40:25
 */

@Controller
public class BranceOfficeController {
	@Autowired
	private BranchOfficeService branchofficeService;

	@Autowired
	SalesmanService salesmanService;
	@Autowired
	RuleVoService ruleVoService;
	@Autowired
	FinanceService financeService;

	/**
	 * 跳转到公司管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/toCompany")
	public ModelAndView toCompany() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("financialCompany");
		return mv;
	}

	/**
	 * 分公司查询
	 * 
	 * @return
	 * @author han
	 */
	@RequestMapping("/branchofficeList")
	@ResponseBody
	public List<BranchOffice> getBranchOffice() {
		
		List<BranchOffice> list = new ArrayList<>();
		try {
			list = branchofficeService.getBranchOffice();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return list;
	}

	/**
	 * 前端动态显示分公司地区
	 * 
	 * @return mv
	 */
	@RequestMapping("/showArea")
	public ModelAndView findArea() {
		
		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			
		}
		// List<RuleService> rulelist=ruleService.getlistRule(name)
		mv.setViewName("demand");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 分公司增加
	 * 
	 * @param branchOffice
	 *            分公司地区
	 * @param session
	 * @return
	 * @author han
	 */

	@RequestMapping(value = "/toAddbranchoffice", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddBranch(@RequestBody BranchOffice branchOffice, HttpSession session) {
		System.out.println("----------");
		// 获取当前用户，(向下转型,返回的是一个对象)
		t_user user = (t_user) session.getAttribute("currUser");
		
		//if (user.getRole_type().equals("管理员")||user.getRole_type().equals("记账")||user.getRole_type().equals("财务总监")) {
			

			ResultInfo result = new ResultInfo();
		
			try {
				branchofficeService.addBranchOffice(branchOffice);

				result.code = 0;
				result.msg = "成功增加";
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				result.code = -1;
				result.msg = "增加分公司失败";

			}
			return result;
		//}
		//return null;

	}

	/**
	 * 分公司修改
	 * 
	 * @param branchOffice
	 *            分公司地区（）
	 * @param session
	 * @return
	 * @author han
	 */
	@RequestMapping(value = "/toUpdatebranchoffice", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toUpdateBranch(@RequestBody BranchOffice branchOffice, HttpSession session) {
		t_user user = (t_user) session.getAttribute("currUser");
	
		//if (user.getRole_type().equals("管理员")) {
			
			ResultInfo result = new ResultInfo();
			try {
				int rows = branchofficeService.updateBranchOffice(branchOffice);
				if (rows >= 1) {
					System.out.println("分公司修改成功");
					result.code = 0;
					result.msg = "successfull";
				}
				return result;
			} catch (Exception e) {
				result.code = -1;
				result.msg = "error";
				return result;
			}
		//}
		//return null;
	}

}
