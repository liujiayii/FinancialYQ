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
