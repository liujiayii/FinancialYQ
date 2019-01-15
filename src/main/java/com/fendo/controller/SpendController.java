package com.fendo.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.fendo.entity.DaNumber;
import com.fendo.entity.Finance;
import com.fendo.entity.Income;
import com.fendo.entity.IncomeVo;
import com.fendo.entity.SalesMan;
import com.fendo.entity.Spend;
import com.fendo.entity.SpendVo;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.SpendService;
import com.fendo.utils.Switch;

/**
 * 支出控制器
 * 
 * @author hanmeng
 *
 * @createDate 2018年8月2日-下午4:51:01
 */
@Controller
public class SpendController {

	@Autowired
	private SpendService spendService;
	@Autowired
	private BranchOfficeService branchofficeService;

	/**
	 * 带数据列表跳到支出费用的页面
	 * 
	 * @return
	 */
	@RequestMapping("/toSpend")
	public ModelAndView toSpend() {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.setViewName("financialSpending");
		return mv;
	}

	/**
	 * 查询所有支出列表
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/financialSpend")
	@ResponseBody
	public List<Spend> findAllType() {

		List<Spend> spendList = spendService.findAllType();
		if (spendList != null) {
			
		}
		return spendList;
	}
	
	@RequestMapping("/financialSpendbytime")
	@ResponseBody
     public List<Spend> financialSpendbytime(String time) {
		System.out.println("time");
		
		String timeone="%"+time+"%";

		List<Spend> incomeList = spendService.findAllTypebytime(timeone);
		if (incomeList != null) {
			
		}
		return incomeList;
	}
	
	
	@RequestMapping("/financialSpendbyarea")
	@ResponseBody
     public List<Spend> financialSpendbyarea(String area) {
		

		List<Spend> incomeList = spendService.findAllTypebyarea(area);
		if (incomeList != null) {
			
		}
		return incomeList;
	}

	/**
	 * 添加支出信息
	 * 
	 * @param spend
	 * @return
	 */
	@RequestMapping(value = "/toAddSpend", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddSpend(@RequestBody Spend spend) {

		ResultInfo result = new ResultInfo();
		try {
			int rows = spendService.toAddSpend(spend);

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
	@RequestMapping("/toShowAddSpend")
	public ModelAndView findArea() {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {

		}

		mv.setViewName("financialSpendingAdd");
		mv.addObject("branchOfficeList", branchOfficeList);
		// mv.addObject("spendItemList",spendItemList);
		// mv.addObject("nameList",nameList);
		return mv;

	}

	/**
	 * 支出详情
	 * 
	 * @param spend
	 * @param id
	 * @return
	 */
	@RequestMapping("/toShowfinancialSpend")
	public ModelAndView toShowfinancialSpend(Spend spend, long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			
		}

		spend = spendService.findById(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String times;
		if(spend.getTimes()==null){
			 times="";
		}else{
			 times = sdf.format(spend.getTimes());
		}
		
	
		
		mv.addObject("spend", spend);
        mv.addObject("times",times);
		mv.setViewName("ShowfinancialSpend");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 通过
	 * 
	 * @param spend
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updatePasszc", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toupdatePasszc(Spend spend, HttpSession session) {
		spend.setAuditor((String) session.getAttribute("name"));
		spend.setRole((String) session.getAttribute("roletype"));
		ResultInfo result = new ResultInfo();

		try {
			int rows = spendService.updatePasszc(spend);

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
	 * 支出打印
	 * 
	 * @param spend
	 * @param id
	 * @return
	 */

	@RequestMapping("/printSpend")
	public ModelAndView printSpend(Spend spend, long id) {
		System.out.println("*********");
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		spend = spendService.findById(id);
		System.out.println("apend"+spend);
		if (branchOfficeList != null) {

		}

	/*	// 时间格式转换
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
		String times;
		if(spend.getTimes()==null){
			 times="";
		}else{
			 times = sformat.format(spend.getTimes());
		}
		*/
		
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = sformat.format(spend.getCreat_time());
	//转大写
		BigDecimal moneys = spend.getSum();
		String s = Switch.number2CNMontrayUnit(moneys);
		List<BranchOffice> Listcode = branchofficeService.findArea2(spend.getDistrict_id());
		mv.addObject("area",Listcode.get(0).getArea());
		mv.setViewName("PrintSpend");
		mv.addObject("times", strDate);
		mv.addObject("spend", spend);
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("s", s.toString());

		return mv;
	}

}