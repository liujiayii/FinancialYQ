package com.fendo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.Company;
import com.fendo.entity.Person;
import com.fendo.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/toCustCompanyInfo")
	@ResponseBody
	public List<Company> toShowCompany(){
		List<Company> list = companyService.findAll();
		return list;
	}
	@RequestMapping("/toAddCompany")
	@ResponseBody
	public ResultInfo toAddCompany(@RequestBody Company company){
		ResultInfo result = new ResultInfo();
		int rows = companyService.insertCompany(company);
		if(rows>=1){
			result.code = 0;
			result.msg = "successful";
		}
		return result;
	}
	@RequestMapping("/toDeleteCompany")
	@ResponseBody
	public ModelAndView toDeleteCompany(int id){
		ResultInfo result = new ResultInfo();
		int rows = companyService.deleteCompany(id);
		if(rows>=1){
			result.code = 0;
			result.msg = "删除成功";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("custCompInfo");
		mav.addObject("result", result);
		return mav;
	}
	@RequestMapping("/toUpdateCompany")
	@ResponseBody
	public ResultInfo toUpdateCompany(@RequestBody Company company){
		System.out.println(company);
		ResultInfo result = new ResultInfo();
		try {
			System.out.println(111);
			int rows = companyService.updateCompany(company);
			System.out.println(rows);
			if(rows>=1){
				result.code = 0;
				result.msg = "successful";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code=-1;
			result.msg="error";
		}
		return result;
	}
	@RequestMapping("/toFindCompanyByPhone")
	@ResponseBody
	public List<Company> findByPhone(String phone){
		String phones = "%"+phone+"%"; 
		List<Company> list= companyService.findByPhone(phones);
		if(!list.isEmpty()){
			System.out.println(list);
		}
		return list;
	}
}
