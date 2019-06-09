package com.fendo.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.fendo.entity.BranchOffice;
import com.fendo.entity.Income;
import com.fendo.entity.IncomeItem;
import com.fendo.entity.Receipt;
import com.fendo.entity.Staff;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.IncomeService;
import com.fendo.utils.Switch;

/**
 * 收入控制器
 * 
 * @author hanmeng
 *
 * @createDate 2018年8月2日-下午4:51:01
 */
@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	@Autowired
	private BranchOfficeService branchofficeService;

	/**
	 * 带数据列表跳到收入费用的页面
	 * 
	 * @return
	 */
	@RequestMapping("/toIncome")
	public ModelAndView toIncome() {
		ModelAndView mv = new ModelAndView();
		
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.setViewName("financialIncome");
		return mv;
	}

	/**
	 * 查询所有收入列表
	 * 
	 * @return
	 */
	@RequestMapping("/financialIncome")
	@ResponseBody
	public List<Income> findAllType() {
		

		List<Income> incomeList = incomeService.findAllType();
		if (incomeList != null) {
			
		}
		return incomeList;
	}
	
	@RequestMapping("/financialIncomebytime")
	@ResponseBody
     public List<Income> financialIncomebytime(String time) {
		System.out.println("time");
		
		String timeone="%"+time+"%";

		List<Income> incomeList = incomeService.findAllTypebytime(timeone);
		System.out.println(incomeList);
		if (incomeList != null) {
			return incomeList;
		}else{
			return null;	
		}
		
	}
	
	
	@RequestMapping("/financialIncomebyarea")
	@ResponseBody
     public List<Income> financialIncomebyarea(String area) {
		

		List<Income> incomeList = incomeService.findAllTypebyarea(area);
		System.out.println(incomeList);
		if (incomeList != null) {
			return incomeList;
		}else{
			return null;	
		}
		
	}
	
	
	@RequestMapping("/todeleatefinancialIncome")
	
	public String todeleatefinancialIncome(long id){
		
		incomeService.deletePasssr(id);
		
		return "financialIncome";
		
	}

	/**
	 * 添加收入信息
	 * 
	 * @param income
	 * @return
	 */
	@RequestMapping(value = "/toAddIncome", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> toAddIncome (@RequestBody Income income,HttpSession session) {
		income.setEntry_person((String) session.getAttribute("name"));
		Map<String,Object> map  =  incomeService.toAddIncome(income);
		return map;
	}
	
	@RequestMapping(value="/toAddIncomeImg")
	@ResponseBody
	public Map<String,Object> toAddIncomeImg(HttpServletRequest request,Long id){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles("file");
		Map<String,Object> map = incomeService.toAddSpend(id, files);
		return map;
	}
	
	
	@RequestMapping("/findIncomeImgById")
	public Map<String,Object> findIncomeImgById(Long id ){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Receipt> receipt = incomeService.findIncomeImgById(id);
		if(receipt.size()>0){
			map.put("list",receipt);
			map.put("code", 1);
			map.put("msg","查询图片成功");
		}else{
			map.put("code", 1);
			map.put("msg","暂无图片");
		}
		return map;
	}
	
	
	/**
	 * 动态显示
	 */
	@RequestMapping("/toShowAddIncome")
	public ModelAndView findArea() {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("成功获取到地址列表(控)");
		}

		
		/*List<Staff> nameList = incomeService.findname();
		if (nameList != null) {
		
		}*/
		mv.setViewName("financialIncomeAdd");
		mv.addObject("branchOfficeList", branchOfficeList);
	
		// mv.addObject("nameList",nameList);
		return mv;

	}

	/**
	 * 收入详情
	 * 
	 * @param income
	 * @param id
	 * @return
	 */
	@RequestMapping("/toShowfinancialIncome")
	public ModelAndView toShowfinancialIncome(Income income, long id) {
		
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		List<Receipt> receipts = incomeService.findIncomeImgById(id);
		if (branchOfficeList != null) {
			
		}
		String times;
		
		income = incomeService.findById1(id);
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
	    System.out.println("income"+income);
		if(income.getTimes()==null){
			 times="";
		}else{
			 times = sformat.format(income.getTimes());
		}
		mv.addObject("receipts",receipts);
		mv.addObject("income", income);
		mv.addObject("times", times);
		System.out.println(times);
		mv.setViewName("ShowfinancialIncome");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 
	 * @param income
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updatePasssr", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toupdatePasssr(Income income, HttpSession session) {

		income.setAuditor((String) session.getAttribute("name"));
		income.setRole((String) session.getAttribute("roletype"));
		ResultInfo result = new ResultInfo();
		
		try {
			int rows = incomeService.updatePasssr(income);
		
			if (rows >= 1) {
				
			}
			result.code = 0;
			result.msg = "successfully!!";
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}

	}

	/**
	 * 收入打印页面
	 * 
	 * @param income
	 * @param id
	 * @return
	 */
	@RequestMapping("/printIncome")
	public ModelAndView printIncome(Income income, long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
	
		income = incomeService.findById1(id);
		System.out.println("income"+income);
		if (branchOfficeList != null) {
		}
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = sformat.format(income.getCreat_time());
		BigDecimal moneys = income.getSum();
		//BigDecimal numberOfMoney = new BigDecimal(moneys);
		String s = Switch.number2CNMontrayUnit(moneys);
		List<BranchOffice> Listcode = branchofficeService.findArea2(income.getDistrict_id());
		mv.addObject("area",Listcode.get(0).getArea());
		mv.setViewName("PrintIncome");
		mv.addObject("role",income.getRole());
		mv.addObject("auditor",income.getAuditor());
		mv.addObject("strDate", strDate);
		mv.addObject("income", income);
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("s", s.toString());

		return mv;
	}
	/**
	 * 
	  *显示添加页面
	 */
	@RequestMapping("/toUpdateFinancialIncome")
	@ResponseBody
	public ModelAndView toUpdateIncome(Income income, long id){
		ModelAndView mav = new ModelAndView();
		Income income1 = incomeService.findById1(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String times;
		if(income1.getTimes()==null){
			times = "";
		}else{
			times = sdf.format(income1.getTimes()); 
		}
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		mav.addObject("income", income1);
		mav.addObject("times", times);
		mav.addObject("branchOfficeList", branchOfficeList);
		mav.setViewName("financialIncomeUpdate");
		return mav;
	}
	/**
	 * 保存修改
	 */
	@RequestMapping("/updateIncome")
	@ResponseBody
	public ResultInfo updateIncome(@RequestBody Income income){
		ResultInfo result = new ResultInfo();
		try {
			int rows = incomeService.toUpdateIncome(income);
			if (rows >= 1) {
				System.out.println(2222);
				result.code = 0;
				result.msg = "successfully!!";
				return result;
			}else{
				result.code=-1;
				result.msg="失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
		}
		return result;	
	}
	
	/**
	 * 通过自选条件查询
	 * @param income
	 * @return
	 */
	@RequestMapping("/searchIncome")
	@ResponseBody
	public List<Income> searchIncome(@RequestBody Income income) {
		List<Income> incomeList = null;
		try {
			incomeList = incomeService.findBySelective(income);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return incomeList;
	} 
	
}
