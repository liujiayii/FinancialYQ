package com.fendo.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.BranchOffice;
import com.fendo.entity.DeductVo;
import com.fendo.entity.FinanceVo;
import com.fendo.entity.Rule;
import com.fendo.entity.RuleSalesVo;

import com.fendo.entity.SalesMan;



import com.fendo.service.BranchOfficeService;
import com.fendo.service.RuleService;
import com.fendo.service.RuleVoService;
import com.fendo.service.SalesmanService;


/**
 * 提成规则控制器
 * @return
 * @author
 */
@Controller
public class RoleController {
	@Autowired
	private BranchOfficeService branchofficeService;
	
	@Autowired
	private RuleService ruleservice;
	@Autowired 
	RuleVoService ruleVoService;
	@Autowired
	SalesmanService salesmanService;
	/**
	 * 跳转业绩规则页面
	 * @param model
	 * @return ModelAndView
	 * 
	 *  @author cc
	 */
	@RequestMapping("/rules")
	@ResponseBody
	public ModelAndView getCarler(Model model){
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> List =branchofficeService.getBranchOffice();
		
	
		mv.addObject("list", List);
		mv.setViewName("results_rules");
		return mv;
	}
	/**
	 * 查询业绩规则
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @author cc
	 */
	@RequestMapping("/getlistrule")
	@ResponseBody
	public List<Rule> getlistsales(String area) throws UnsupportedEncodingException{
		
	/*	String name1 = new String(area.getBytes("iso-8859-1"),"utf-8");*/
	
		  List<BranchOffice> Listcode =branchofficeService.findArea3(area);
		
		List<Rule> list=ruleservice.getlistRule(Listcode.get(0).getArea_code());
		
		return list;
		
	}
	/**
	 * 增加业绩规则
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @author cc
	 */
	@RequestMapping(value = "/toAddRole",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddRole(@RequestBody Rule rule,HttpSession session){
		ResultInfo result = new ResultInfo();
		try {
			List<BranchOffice> Listcode =branchofficeService.findArea3(rule.getDistrict_id());
		
			rule.setDistrict_id(Listcode.get(0).getArea_code());
			ruleservice.toAddRule(rule);
			
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
	 * 修改业绩规则
	 * @param model
	 * @return ModelAndView
	 * 
	 *  @author cc
	 */
	@RequestMapping(value = "/toUpdateRole",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toupdateRole(@RequestBody Rule rule,HttpSession session){
		ResultInfo result = new ResultInfo();
		
		
		try {
			
			int a=ruleservice.toUpdateRule(rule);
		
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
	@RequestMapping("/showDeduct")
	@ResponseBody
	public List<RuleSalesVo> findDeductByName(String name) throws UnsupportedEncodingException{
		
		List<RuleSalesVo> deductList = ruleservice.findDeductByName(name);
		
		if(!deductList.isEmpty()){
		
		}
		return deductList;
	}
	
	@RequestMapping("/showAllDeduct")
	@ResponseBody
	public List<DeductVo> toShowAllDeduct() throws UnsupportedEncodingException{
		List<DeductVo> deductList = ruleservice.findAllDeduct();
		if(!deductList.isEmpty()){
			
		}
		return deductList;
	}
	/**
	 * 查询每个地区业务员每个月的提成详情
	 * @return
	 * @author cc
	 * @throws Exception 
	 * @throws Exception 
	 */
	@RequestMapping("/showDetail")
	public ModelAndView toShowDetail(String id,String time) throws Exception{
		
		
		String times="%"+time+"%";
	
		ModelAndView mv = new ModelAndView();
		List<FinanceVo> sales=new ArrayList<FinanceVo>();
		sales=salesmanService.getFinceVoList(times, id);
		
		if(sales.get(0)==null || sales.isEmpty()){
			mv.addObject("money",0);
		}else{
			
			int k=sales.get(0).getNumbers();
			String name = sales.get(0).getName();
			mv.addObject("money",k);
			mv.addObject("name",name);
		}
		
		mv.addObject("id", id);
	mv.addObject("time",time);
		mv.setViewName("For_details");
		
		return mv;
	}
	/**
	 * 查询业务员
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @author cc
	 */
	@RequestMapping("/getlistRole")
	@ResponseBody
	public List<DeductVo> getlistrole(String id,String time) throws UnsupportedEncodingException{
		
		String times="%"+time+"%";
		System.out.println(times+"***************");
		List<DeductVo> deductList = ruleservice.findSaleDetailById(id,times);
		System.out.println("集合"+deductList);
		List<SalesMan> listman=salesmanService.getSalesManListbyid(id);
		
		if(listman.size()==0){
			return deductList;
		}
		else{
			
		
		List<Rule> list=ruleservice.getlistRule(listman.get(0).getDistrict_id());
	
		double k;
		for(int a=0;a<deductList.size();a++){
			
			k=deductList.get(a).getMoney()*deductList.get(a).getInterest()*list.get(0).getRule_three()/10000;
		
			deductList.get(a).setDeduct(k);
		}
		
		return deductList;
		}
	
	}
	/**
	 * 查询各个地区各个时间段的提成
	 * @return
	 * @author cc
	 * @throws Exception 
	 */
	@RequestMapping("/getdemands")
	@ResponseBody
	public List<RuleSalesVo> getdemand(String area,String year,String month) throws Exception{
		System.out.println("area"+area+"year"+year+"month"+month);
		
		
		String y=year.substring(0,year.indexOf("年"));
		
		int m=Integer.parseInt(month.substring(0,month.indexOf("月")));
		String time;
		
		 if(m>9){
	    		time=y+"-"+m;
	    		
	    	  }else{
	    		  time=y+"-0"+m;
	    		 
	    	  }
		
		
		System.out.println("time"+time);
		
	
         List<BranchOffice> Listcode =branchofficeService.findArea3(area);
         System.out.println("Listcode"+Listcode);
		List<RuleSalesVo> list=ruleVoService.getlistRuleVobyTime(Listcode.get(0).getArea_code(), time);
	   System.out.println("list"+list);
		return list; 
	}
	
	/**
	 * 查询各个地区各个时间段的提成
	 * @return
	 * @author cc
	 * @throws Exception 
	 */
	@RequestMapping("/getdemandsum")
	@ResponseBody
	public List<FinanceVo> getdemandsum(String area,String year,String month) throws Exception{
		
	   
		String y=year.substring(0,year.indexOf("年"));
		
		int m=Integer.parseInt(month.substring(0,month.indexOf("月")));
		String time;
		
		 if(m>9){
	    		time="%"+y+"-"+m+"%";
	    		
	    	  }else{
	    		  time="%"+y+"-0"+m+"%";
	    		 
	    	  }
	
		List<BranchOffice> Listcode =branchofficeService.findArea3(area);
        List<FinanceVo> list =salesmanService.togetFinceVoList(time, Listcode.get(0).getArea_code());
		return list; 
	}
	
	
}
