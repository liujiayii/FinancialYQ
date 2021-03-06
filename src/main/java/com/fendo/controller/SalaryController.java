package com.fendo.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.BranchOffice;
import com.fendo.entity.RuleSalesVo;
import com.fendo.entity.RuleVo;
import com.fendo.entity.SalaryStatistics;
import com.fendo.entity.Staff;
import com.fendo.entity.StaffVo;
import com.fendo.entity.financeJON;
import com.fendo.entity.salesJON;
import com.fendo.entityVo.SalaryVo;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.RuleVoService;
import com.fendo.service.SalaryService;
import com.fendo.service.StaffService;
import com.fendo.utils.WorkDay;

import net.sf.json.JSONObject;


@Controller
public class SalaryController {
	
	@Autowired
	BranchOfficeService branchofficeService;
	
	@Autowired
	SalaryService salaryService;
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	RuleVoService ruleVoService;
	
	/**
	 * 跳转到工资表列表显示页面
	 * @return
	 */
	@RequestMapping("/demandWage")
	public ModelAndView toDemandWage(){
		ModelAndView mv = new ModelAndView();
	
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		
		if(branchOfficeList!=null){
			System.out.println("成功获取到地址列表");
		}
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.setViewName("demandWage");
		return mv;
	} 
	
	/**
	 * 跳转到提成表列表显示页面
	 * @return
	 */
	@RequestMapping("/demandCommission")
	public ModelAndView toDemandCommission(HttpSession session){
		String sessions=(String) session.getAttribute("roletype");
		ModelAndView mv = new ModelAndView();
		
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if(branchOfficeList!=null){
			
		}
		
		mv.addObject("branchOfficeList", branchOfficeList);
		if(sessions.equals("行政")){
			mv.setViewName("demandWage");
		}else{
			mv.setViewName("demandCommission");
		}
		
		return mv;
	}
	
	/**
	 * 工资表列表显示
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/showSalary")
	@ResponseBody
	public List<SalaryVo> findSalary(String area,String year,String month) throws Exception{
		Calendar cal = Calendar.getInstance();
		int yearone = cal.get(Calendar.YEAR);
		System.out.println(yearone+"***");
		int monthone = cal.get(Calendar.MONTH)+1;
		List<SalaryVo> salaryVoList1=new ArrayList();
		/*if(Integer.parseInt(month)==monthone&&Integer.parseInt(year)==yearone){
			System.out.println(yearone+monthone+year+month);
			return salaryVoList1;
		}*/
		
		if(Integer.parseInt(month)<10){
			month="0"+month;
		}

		List<BranchOffice> Listcode =branchofficeService.findArea3(area);
			
		List<SalaryVo> salaryVoList = salaryService.findSalary(Listcode.get(0).getArea_code(), year, month);

		return salaryVoList;
	}
	/**
	 * 通过姓名查询显示某人的工资
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/showSalaryByName")
	@ResponseBody
	public List<SalaryVo> findSalaryByName(String name,String year,String month) throws UnsupportedEncodingException{
	
		
		List<SalaryVo> salaryVoList = salaryService.findSalaryByName(name,year,month);
		if(!salaryVoList.isEmpty()){
		
		}
		return salaryVoList;
	}
	/**
	 * 带数据查看详情
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toShowSalaryDetail")
	public ModelAndView findSalaryDetails(long id,String time) throws Exception{
		List<financeJON> list=new ArrayList<financeJON>();
		
		ModelAndView mv = new ModelAndView();
	
	String years=time.substring(0,time.indexOf("-"));
	String months=time.substring(time.indexOf("-")+1,time.length());
	
		//通过id查询某个详情
		SalaryVo salaryVo = salaryService.findSalaryById(id,years,months);
		
		StaffVo staffVo = staffService.findAllStaff(id);
		//判断员工是否转正
		//如果转正，基本工资手动修改，全勤，工装，餐补
		//如果未转正，全勤，工装，餐补为0，转正日期之后几天
		System.out.println("salaryVo"+salaryVo);
		if(salaryVo.getIs_become()==1){
			System.out.println("已转正");
			
			//判断当前月数有多少天
			Calendar cal = Calendar.getInstance(Locale.CHINA);
			int dayOfMonth = cal.getActualMaximum(Calendar.DATE);
			
			WorkDay workDay = new WorkDay();
			//获取年
			int year = salaryVo.getYear();
			//获取月
			int month = salaryVo.getMonth();
			//当月多少个工作日
			int workOfDay = workDay.countWorkDay(year, month);
			System.out.println("工作日天数："+workOfDay);
			//当月有几天周六日
			int week =  dayOfMonth-workOfDay;
			System.out.println("几天周六日"+week);
			//一个月多少天的餐补
			BigDecimal dayMeal = new BigDecimal(30);
			System.out.println("生气罢罢罢"+salaryVo.getMeal_bouns());
			//每天都有餐补   
			BigDecimal everyMeal = staffVo.getMeal_bouns().divide(dayMeal,2,RoundingMode.HALF_UP);
			System.out.println("猪"+everyMeal);
			if(salaryVo.getLeaves()==0.0&&salaryVo.getMarriage()==0.00&&salaryVo.getMaternity()==0.00&&salaryVo.getPmaternity()==0.00){
				//转正后没请假，有全勤即员工表信息
				//全月出勤，设置为200
				salaryVo.setAttendance_bouns(staffVo.getAttendance_bouns());
				salaryVo.setMeal_bouns(staffVo.getMeal_bouns());
			}else if(salaryVo.getReal_attendance()<workOfDay){
				
				//如果不是整月都在，餐补按天算
				//获取实际转正出勤天数
				double realDay= salaryVo.getReal_attendance();
				//转换实际天数
				BigDecimal realDays = new BigDecimal(realDay);
				//转换周六日
				BigDecimal realWeek = new BigDecimal(week);
				//每天的饭补乘以天数
				BigDecimal monthMeal = everyMeal.multiply(realDays);
				
				
				salaryVo.setMeal_bouns(monthMeal);
				//如果不是整月都在，全勤为0
				BigDecimal bouns= new BigDecimal("0.0");
				//没有全勤
				salaryVo.setAttendance_bouns(bouns);
				
			}
			//转正之后设置固定工装补
			salaryVo.setForck_bouns(salaryVo.getForck_bouns());
		}else if(salaryVo.getIs_become()==0){	
			System.out.println("没有转正");
			BigDecimal bouns= new BigDecimal("0.00");
			//没有全勤
			salaryVo.setAttendance_bouns(bouns);
			//没有工装补
			salaryVo.setForck_bouns(bouns);
			//没有餐补
			salaryVo.setMeal_bouns(bouns);
			
		}
		SalaryVo salaryVo1 = salaryService.findSalesPush(id,time);
			
		WithDrawMoneyController w=new WithDrawMoneyController();
		int j=0;
		try {
			String url="https://www.ouyepuhui.com/front/deduct/deductInfo?timeStr="+time+"&salesId="+salaryVo.getSales_id();
			 String str=w.loadJson(url);
			 System.out.println("str"+str);
			 String c = str.substring(15, str.length() - 2);
			 System.out.println(c);
				if(c!=""&&c!=null&&c.length()>0){
			 String[] atr = c.split("},");
			 String s;
			 for (int a = 0; a < atr.length; a++) {
				 s = atr[a] + "}";
				 if (a == atr.length - 1) {
					 s = atr[a]; 
				 }
				
				 JSONObject jsonObject = JSONObject.fromObject(s);
					
					financeJON string = (financeJON) JSONObject.toBean(jsonObject, financeJON.class);
					list.add(string);
					
				}
				for(int k=0;k<list.size();k++){
					j=j+list.get(k).getProfit();
				}
			
				mv.addObject("json", j);
				}
				 
		} catch (Exception e) {
			e.printStackTrace();
				
		} finally {
			
			mv.addObject("json", j);
			mv.addObject("salaryVo", salaryVo);
			mv.addObject("staffVo", staffVo);
			
			mv.addObject("salaryVo1", salaryVo1);
			mv.setViewName("demandWageDetails");
				
		}
		return mv;
				

		
	}

	@RequestMapping(value="/updateRemark",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateRemark(@RequestBody SalaryVo salaryVo) throws UnsupportedEncodingException{
	System.out.println("****"+salaryVo);
		 ResultInfo result=new ResultInfo();
		/*Staff staffVo =new Staff();
		staffVo.setId(salaryVo.getStaff_id());
		staffVo.setZbase_pay(salaryVo.getZbase_pay());
		staffVo.setBase_pay(salaryVo.getBase_pay());
		staffVo.setForck_bouns(salaryVo.getForck_bouns());
		staffVo.setMeal_bouns(salaryVo.getMeal_bouns());
		staffVo.setAttendance_bouns(salaryVo.getAttendance_bouns());
		System.out.println("staffVo"+staffVo);*/
		 RuleVo rulevo=new RuleVo();
		 Staff staf=staffService.findByJobNum(salaryVo.getJob_number());
		
		 
		int rows = salaryService.updateRemark(salaryVo);
		if(staf!=null){
		 rulevo.setExtract(salaryVo.getBusiness_pay());
		 rulevo.setTime(salaryVo.getYear()+"-"+salaryVo.getMonth());
		 
		 rulevo.setSalesId(staf.getId());
		 int rowtwo=ruleVoService.toupdateRuleVobyId(rulevo);
		}
		// int rowone=staffService.toUpdateStaff(staffVo);
		
	
		System.out.println("rowone");
		try {
			if(rows>=1){
				System.out.println("修改成功");
				result.code=0;
				result.msg="successfully!";
			}
			return result;
		} catch (Exception e) {
			result.code=-1;
			result.msg="error";
			return result;
		}
	}
	/**
	 * 导出
	 * @param response
	 * @param idList
	 * @throws Exception 
	 */
	@RequestMapping("/export")
	public void export(HttpServletResponse response,String area,String year,String month) throws Exception{
	
		
		
		List<BranchOffice> Listcode =branchofficeService.findArea3(area);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=salary.xlsx;charset=UTF-8");
        XSSFWorkbook workbook = salaryService.exportExcelInfo(Listcode.get(0).getArea_code(),year,month);
        try {
            OutputStream output  = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
