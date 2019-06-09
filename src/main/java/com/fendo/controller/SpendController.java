package com.fendo.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.BranchOffice;
import com.fendo.entity.Expend;
import com.fendo.entity.Spend;
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
		System.out.println("incomeList"+incomeList);
		return incomeList;
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
	 * 动态显示修改页面
	 */
	@RequestMapping("/toShowUpdateSpend")
	@ResponseBody
	public ModelAndView showUpdateSpend(Spend spend,Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		Spend spend1 = spendService.findById(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String times;
		if(spend1.getTimes() == null){
			times = "";
		}else{
			times = sdf.format(spend1.getTimes()); 
		}
		mv.addObject("spend", spend1);
		mv.addObject("times", times);
		mv.setViewName("financialSpendingUpdate");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}
	/**
	 * 保存修改
	 */
	@RequestMapping("/updateSpend")
	@ResponseBody
	public ResultInfo updateIncome(@RequestBody Spend spend){
		ResultInfo result = new ResultInfo();
		try {
			int rows = spendService.toUpdateSpend(spend);
		
			if (rows >= 1) {
			
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
		List<Expend> expends=spendService.findImgBySpendId(id);
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
		mv.addObject("expends",expends);
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
	
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		spend = spendService.findById(id);
	
		if (branchOfficeList != null) {

		}

	
		
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
	
	
	/**
	 * 查询支出图片
	 * @param id
	 * @return 
	 */
	@RequestMapping("/findImgBySpendId")
	public Map<String,Object> findImgBySpendId(Long id ){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Expend> expend=spendService.findImgBySpendId(id);
		if(expend.size()>0){
			map.put("list",expend);
			
			map.put("code", 1);
			map.put("msg","查询图片成功");
		}else{
			map.put("code", 1);
			map.put("msg","暂无图片");
		}
		return map;
	}
	
	
	
}
