package com.fendo.controller;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.BranchOffice;
import com.fendo.entity.CompanyDepartment;
import com.fendo.entity.CompanyPost;
import com.fendo.entity.SalaryStatistics;
import com.fendo.entity.Staff;
import com.fendo.entity.StaffVo;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.SalaryService;
import com.fendo.service.StaffService;

/**
 * 员工信息管理控制类
 * @author liuhangjing
 *
 */
@Controller
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	@Autowired
	SalaryService salaryService;
	
	@Autowired
	private BranchOfficeService branchofficeService;
	/**
	 * 带数据列表跳到列表显示员工的页面
	 * @return
	 * @author liuhj
	 */
	@RequestMapping("/toStaff")
	public ModelAndView toShowStaff(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("financialStaff");
		return mv;
	}
	
	/**
	 * 前端动态显示分公司地区
	 * @return mv
	 */
	@RequestMapping("/toShowArea")
	public ModelAndView findArea(){
		
		ModelAndView mv = new ModelAndView();
		
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if(branchOfficeList!=null){
		
		}
		List<CompanyDepartment> departmentList = staffService.findDepartment();
		if(departmentList!=null){
		
		}
		List<CompanyPost> postList = staffService.findPost();
		if(postList!=null){
			
		}
		mv.setViewName("financialStaffAdd");
		
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("departmentList", departmentList);
		mv.addObject("postList", postList);
		return mv;
	}
	/**
	 * 列表显示员工信息
	 * @return
	 */
	@RequestMapping("/toShowStaffInfo")
	@ResponseBody
	public List<StaffVo> toShowStaffInfo(){
		List<StaffVo> staffVoList = staffService.findStaffInfo();
		if(!staffVoList.isEmpty()){
			
		}
		return staffVoList;
	}
	/**
	 * 添加员工信息
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/toAddStaff",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddStaff(@RequestBody Staff staff){
		ResultInfo result = new ResultInfo();
		try {
			int rows = staffService.toAddStaff(staff);
			
			SalaryStatistics s = new SalaryStatistics();
			SalaryStatistics sone = new SalaryStatistics();
			SalaryStatistics stwo = new SalaryStatistics();
			SalaryStatistics sthree = new SalaryStatistics();
			Calendar cal = Calendar.getInstance();
			// 年
			String year = String.valueOf(cal.get(Calendar.YEAR));
			// 月
			String month;
			if ((cal.get(Calendar.MONTH) + 1) < 10) {
				month = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
			} else {
				month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			}

			List<Staff> list = salaryService.findListSaray();
			List<SalaryStatistics> list1 = salaryService.toFindListSaray();
			String[] str = new String[list1.size()];
			String[] str1 = new String[list1.size()];

			String[] str2 = new String[list1.size()];
			for (int b = 0; b < list1.size(); b++) {

				str[b] = String.valueOf(list1.get(b).getStaff_id());

				str1[b] = list1.get(b).getYear();
				str2[b] = list1.get(b).getMonth();

			}

			for (int a = 0; a < list.size(); a++) {
				if (useLoop(str, String.valueOf(list.get(a).getId())) && useLoop(str1, String.valueOf(year))
						&& useLoop(str2, String.valueOf(month))) {

				} else {
					// Staff staff = new Staff();
					// BigDecimal base_pay = staff.getBase_pay();

					// BigDecimal business_pay = staff.getBase_pay();
					BigDecimal business_pay = list.get(a).getBase_pay();
					s.setStaff_id(list.get(a).getId());

					s.setYear(year);
					s.setMonth(month);
					sone.setStaff_id(list.get(a).getId());
					if (Integer.parseInt(month) + 1 > 12) {
						sone.setYear((Integer.parseInt(year) + 1) + "");
						sone.setMonth(1 + "");
					} else {
						sone.setYear(year);
						sone.setMonth(String.valueOf(Integer.parseInt(month) + 1));
					}

					stwo.setStaff_id(list.get(a).getId());
					if (Integer.parseInt(month) + 2 > 12) {
						stwo.setYear((Integer.parseInt(year) + 1) + "");
						stwo.setMonth(Integer.parseInt(month) + 2 - 12 + "");
					} else {
						stwo.setYear(year);
						stwo.setMonth(String.valueOf(Integer.parseInt(month) + 1));
					}

					sthree.setStaff_id(list.get(a).getId());
					if (Integer.parseInt(month) + 3 > 12) {
						sthree.setYear((Integer.parseInt(year) + 1) + "");
						sthree.setMonth(Integer.parseInt(month) + 3 - 12 + "");
					} else {
						sthree.setYear(year);
						sthree.setMonth(String.valueOf(Integer.parseInt(month) + 3));
					}
					System.out.println(s);
					System.out.println(sone);
					System.out.println(stwo);
					System.out.println(sthree);
					int k = salaryService.toAddSalary(s);
					int kone = salaryService.toAddSalary(sone);
					salaryService.toAddSalary(stwo);
					int kthree = salaryService.toAddSalary(sthree);

				}
			}
			if(rows>=1){
				System.out.println("对象："+staff);
				result.code=0;
				result.msg="successfully!!";
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
	 * 锁定该员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toLockStaff",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo toLockStaff(@RequestParam long id){
		
		ResultInfo result = new ResultInfo();
		try {
			int rows = staffService.toLockStaff(id);
			if(rows>=1){
			
				result.code=0;
				result.msg="successfully!!";
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
	 * 通过id查询Vo实体并显示在修改页面上
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdateStaff")
	public ModelAndView toUpdateStaff(StaffVo staffVo,long id){
	
		ModelAndView mv = new ModelAndView();
		
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if(branchOfficeList!=null){
			
		}
		List<CompanyDepartment> departmentList = staffService.findDepartment();
		if(departmentList!=null){
			
		}
		List<CompanyPost> postList = staffService.findPost();
		if(postList!=null){
			
		}
		staffVo= staffService.findAllStaff(id);
		System.out.println("staffVo"+staffVo);
		mv.setViewName("financialStaffUpdate");
		
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("departmentList", departmentList);
		mv.addObject("postList", postList);
		mv.addObject("staffVo", staffVo);
		return mv;
	}
	/**
	 * 修改员工信息
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/updateStaff",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateDStaff(@RequestBody Staff staff){
		ResultInfo result = new ResultInfo();
		try {
			int rows = 	staffService.toUpdateStaff(staff);
			if(rows>=1){
				
				result.code=0;
				result.msg="successfully";
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code=-1;
			result.msg="error";
		}
		return result;
	}
	/**
	 * 通过手机号码查询员工
	 * @param phone
	 * @return
	 */
	@RequestMapping("/toFindStaffByPhone")
	@ResponseBody
	public List<StaffVo> findStaffByPhone(String phone){

	String  phones="%"+phone+"%";
		List<StaffVo> staffVoList = staffService.toFindStaffByPhone(phones);
	

	
		List<StaffVo> staffVoList1 = staffService.toFindStaffByPhone(phones);

		if(!staffVoList1.isEmpty()){
			
		}
		return staffVoList1;
	}
	/**
	 * 异步判断工号
	 * @Title StaffController.java
	 * @description TODO
	 * @time 2018年8月29日 上午9:45:01
	 * @author liuhj
	 */
	@RequestMapping(value="/toFindByJobNum",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo findByJobNum(@RequestParam String job_number,HttpServletRequest request){
		ResultInfo result = new ResultInfo();
		try {
			String job_number1 = request.getParameter("job_number");
			
			Staff staff = staffService.findByJobNum(job_number1);
			if(staff!=null){
			
				result.code=0;
				result.msg="该工号已存在";
			}else{
				
				result.code=-1;
				result.msg="该工号不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	/*@RequestMapping(value="/toFindStaffByPhone",method=RequestMethod.POST)
=======
	/**
	 * 异步判断电话是否存在
	 * @Title StaffController.java
	 * @description TODO
	 * @time 2018年9月4日 上午10:47:22
	 * @author liuhj
	 */
	@RequestMapping(value="/findStaffByPhone",method=RequestMethod.POST)

	@ResponseBody
	public ResultInfo findStaffByPhone(@RequestParam String phone,HttpServletRequest request){
		ResultInfo result = new ResultInfo();
		try {
			String phone1 = request.getParameter("phone");
			
			Staff staff = staffService.findStaffByPhone(phone1);
			if(staff!=null){
				result.code=0;
				result.msg="该手机号已存在";
			}else{
			
				result.code=-1;
				result.msg="该手机不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	public static boolean useLoop(String[] arr, String targetValue) {
		for (String s : arr) {
			if (s.equals(targetValue))
				return true;
		}
		return false;
	}
	
/*	*//**
	 * 测试导入excel表数据时的controller
	 * @param xls
	 * @param request
	 * @throws Exception
	 *//*
	@RequestMapping("/edit")
    public void edit(@RequestParam("file") MultipartFile xls, HttpServletRequest request) throws Exception {
        //获取流数据，方便存储
        FileItem fi = ((CommonsMultipartFile) xls).getFileItem();
        //pic.getOriginalFilename()获取文件名
        //上传文件到服务器路径中
        File file = new File(request.getSession().getServletContext().getRealPath("/")+xls.getOriginalFilename());
        fi.write(file);
        String path = file.getAbsolutePath();
        System.out.println("路径：" + path);
       
        System.out.println("file====================="+file.getAbsolutePath());
        ExcelReader excelReader = new ExcelReader(file.getAbsolutePath(),staffService);
        excelReader.readExcelData();
    }
	*//**
	 * 跳转到导入测试的页面
	 * @return
	 *//*
	@RequestMapping("/toTextIn")
	public String toImport(){
		return "textIn";
	}*/

}
