package com.fendo.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.fendo.entity.Income;
import com.fendo.entity.IncomeItem;
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
		if (incomeList != null) {
			
		}
		return incomeList;
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
	public ResultInfo toAddIncome(@RequestBody Income Income) {
	
		ResultInfo result = new ResultInfo();
		try {
			int rows = incomeService.toAddIncome(Income);
			// List<IncomeVo> a=incomeService.sum();
			// System.out.println(a+"a");

			//System.out.println(Income + "123");
			

			if (rows >= 1) {
			
				result.code = 0;
				result.msg = "successfully!!";
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
		}

		return result;
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
		if (branchOfficeList != null) {
			
		}
		
		income = incomeService.findById1(id);
		mv.addObject("income", income);
		
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
		if (branchOfficeList != null) {
			
		}
	
		// 时间格式转换
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = sformat.format(income.getCreat_time());
		// Double moneys = money1 + money2 + money3 + money4 + money;
		// System.out.println("WOSHI " + moneys);

		BigDecimal moneys = income.getSum();
		//BigDecimal numberOfMoney = new BigDecimal(moneys);
		String s = Switch.number2CNMontrayUnit(moneys);
		
		mv.setViewName("PrintIncome");
		mv.addObject("strDate", strDate);
		mv.addObject("income", income);
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("s", s.toString());

		return mv;
	}
}
