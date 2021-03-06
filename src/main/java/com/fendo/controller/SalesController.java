package com.fendo.controller;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fendo.entity.BranchOffice;
import com.fendo.entity.SalesMan;
import com.fendo.entity.SalesmanVo;
import com.fendo.entity.Staff;
import com.fendo.entity.t_user;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.SalesmanService;


@Controller
public class SalesController{
	@Autowired 
	SalesmanService salesmanService;
	
	@Autowired
	private BranchOfficeService branchofficeService;
	
	/**
	 * 跳转公司管理页面
	 * @param model
	 * @return ModelAndView
	 * 
	 *  @author cc
	 */
	@RequestMapping("/toCompanyManagement")
	@ResponseBody
	public ModelAndView getCarler(Model model,HttpSession session){
		t_user user = (t_user) session.getAttribute("currUser");
		ModelAndView mv = new ModelAndView();
		//如果当前用户是管理员则可以跳转到公司管理页面，否则不可
			
			List<BranchOffice> List =branchofficeService.getBranchOffice();
			
			mv.addObject("list", List);
			mv.setViewName("CompanyManagement");
			
		
		return mv;
	}
	/**
	 * 跳转公司管理修改页面
	 * @param model
	 * @return ModelAndView
	 * 
	 *  @author cc
	 */
	@RequestMapping("/toupdatesalesMan")
	@ResponseBody
	public ModelAndView toupdatesalesMan(Model model,String id){
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> List =branchofficeService.findArea();
	
		
		List<SalesMan> listId=salesmanService.getSalesManListbyid(id);
		List<BranchOffice> Listcode =branchofficeService.findArea2(listId.get(0).getDistrict_id());
		listId.get(0).setDistrict_id(Listcode.get(0).getArea());
		System.out.println(listId.get(0)+"******");
		mv.addObject("list", List);
		mv.addObject("listId", listId);
		mv.setViewName("ManagementUpdate");
		return mv;
	}
	
	/**
	 * 公司管理修改页面
	 * @param model
	 * @return ModelAndView
	 * 
	 *  @author cc
	 */
	@RequestMapping(value = "/toupdatesalesman",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toupdatesalesman(@RequestBody SalesMan salesman,HttpSession session){
		ResultInfo result = new ResultInfo();
	
		try {
			
			List<BranchOffice> Listcode =branchofficeService.findArea3(salesman.getDistrict_id());
		
			salesman.setDistrict_id(Listcode.get(0).getArea_code());
		
			int a=salesmanService.toUpdateSaleMan(salesman);
			
			result.code = 0;
			result.msg = "successful";
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			
		}
		return result;
	}
	
	/**
	 * 查询业务员
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @author cc
	 */
	@RequestMapping("/getlistsalesman")
	@ResponseBody
	public List<SalesmanVo> getlistsales(String area) throws UnsupportedEncodingException{
	
		//String name1 = new String(area.getBytes("iso-8859-1"),"utf-8");
		List<SalesmanVo> list=salesmanService.getSalesManList(area);
		System.out.println("aaaaaaa");
//		System.out.println(list.get(0).getId()+"==789");
		return list;
		
	}
	
	/**
	 * 跳转添加业务员页面
	 * @param 
	 * @return ModelAndView
	 * 
	 *  @author cc
	 * @throws Exception 
	 */
	@RequestMapping("/toManagementAdd")
	@ResponseBody
	public ModelAndView toManagementAdd(String area) throws Exception{
	
	
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> List =branchofficeService.getBranchOffice();
		
		mv.addObject("name",area);
		mv.addObject("list", List);
		mv.setViewName("ManagementAdd");
		return mv;
	}
	
	
	/**
	 * 增加业务员
	 * @param salesman 业务员
	 * @param session
	 * @return
	 * @author han
	 * @createDate 
	 */
	
	@RequestMapping(value = "/toAddsalesman",method=RequestMethod.POST)   
	@ResponseBody
	public ResultInfo toAddMan(@RequestBody SalesMan salesman,HttpSession session){
		ResultInfo result = new ResultInfo();
		System.out.println("salesman"+salesman);
		try {
			List<BranchOffice> Listcode =branchofficeService.findArea3(salesman.getDistrict_id());
			System.out.println("Listcode"+Listcode);
		
			salesman.setDistrict_id(Listcode.get(0).getArea_code());
			salesmanService.toAddSaleMan(salesman);
			result.code = 0;
			result.msg = "successful";
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			
		}
		return result;
	}
	/**
	 * 异步判断工号
	 * @Title StaffController.java
	 * @description TODO
	 * @time 2018年8月29日 上午9:45:01
	 * @author 
	 */
	@RequestMapping(value="/toFindByJobNums",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo findByJobNum(@RequestParam String job_number,HttpServletRequest request){
		ResultInfo result = new ResultInfo();
		try {
			String job_number1 = request.getParameter("job_number");
			
			List<SalesMan> salesman = salesmanService.findSaleByjbnumber(job_number1);
			System.out.println("salesman"+salesman);
			if(salesman!=null&&salesman.size()>0){
			
				result.code=-1;
				result.msg="该工号已存在";
			}else{
				
				result.code=0;
				result.msg="该工号已存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result"+result);
		
		return result;
		
	}
	/**
	 * 公司管理修改页面
	 * @param model
	 * @return ModelAndView
	 * 
	 *  @author cc
	 */
	@RequestMapping(value = "/toupdatebyId",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddbranchoffice(@RequestBody String id,HttpSession session){
		ResultInfo result = new ResultInfo();
		String ids=id.substring(id.indexOf("=")+1);
		
		try {
			
			int a=salesmanService.toUpdateSaleManstats(ids);
			
			result.code = 0;
			result.msg = "successful";
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			
		}
		return result;
	}
	/**
	 * 根据姓名查询该业务员信息
	 * @param name 业务员姓名
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 * @author liuhj
	 */
	@RequestMapping("/toFindSales")
	@ResponseBody
	public List<SalesmanVo> findSalesByName(String name) throws UnsupportedEncodingException{
		
	
		String name1 = new String(name.getBytes("iso-8859-1"),"utf-8");
//		System.out.println(name.substring(name.indexOf("=")+1));
//		String n=name.substring(name.indexOf("=")+1);
		//前端传到后台的是16进制,需转换为汉字字符
		//URLDecoder 和 URLEncoder 用于完成普通字符串 和 application/x-www-form-urlencoded MIME 字符串之间的相互转换
//		String name1=URLDecoder.decode(n, "UTF-8");
//		System.out.println(name1);
	
		List<SalesmanVo> salemanList = salesmanService.findSaleByName(name1);
		
		
		
		return salemanList;
		
	}
//	@RequestMapping("/toDeleteSale")
//	public String toDeleteSale(String id){
//		int rows = salesmanService.deleteSaleById(id);
//		if(rows>=1){
//			System.out.println(id+"controller中已删除");
//		}
//		return "CompanyManagement";
//	}
	
	@RequestMapping(value = "/toDeleteSale",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toDeleteSale(@RequestBody String id,HttpSession session){
		String ids=id.substring(id.indexOf("=")+1);
	
	
		ResultInfo result = new ResultInfo();
		try {
			int rows = salesmanService.deleteSaleById(ids);
			
			if(rows>=1){
				result.code = 0;
				result.msg = "successful";
				
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			
			return result;
		}
	}
}
