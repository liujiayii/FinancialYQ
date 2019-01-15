package com.fendo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fendo.entity.BranchOffice;
import com.fendo.entityVo.SalaryVo;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.SalaryService;

@Controller
public class LoanController {
	
	@Autowired
	BranchOfficeService branchofficeService;
	
	@Autowired
	SalaryService salaryService;
	

	/**
	 * 工资表列表显示
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/showAttendance")
	@ResponseBody
	public List<SalaryVo> showAttendance(String year,String month) throws Exception{
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
		String area="石家庄";
		List<BranchOffice> Listcode =branchofficeService.findArea3(area);
			System.out.println("Listcode"+Listcode);
			System.out.println("month"+month);
		List<SalaryVo> salaryVoList = salaryService.findSalary(Listcode.get(0).getArea_code(), year, month);
        System.out.println("salaryVoList"+salaryVoList);
		return salaryVoList;
	}

}
