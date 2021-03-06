package com.fendo.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fendo.entity.SalaryStatistics;
import com.fendo.entity.Staff;
import com.fendo.entityVo.SalaryVo;

public interface SalaryService {
	
	/** 工资表查询列表显示*/
	List<SalaryVo> findSalary(String area,String year,String month);
	/** 工资表查询列表显示*/
	List<SalaryVo> findrealSalary();
	/**插入工资列表*/
	int toAddSalary(SalaryStatistics s);
	
	/** 查询公司每个人的id,基本工资*/
	List<Staff> findListSaray();
	/**查询工资表中所有的staff_id,以及对应的年和月*/
	List<SalaryStatistics> toFindListSaray();
	/** 通过姓名查询工资显示*/
	List<SalaryVo> findSalaryByName(String name,String year,String month);
	
	/** 通过id查询详情 */
	SalaryVo findSalaryById(long id,String year,String month);
	
	/** 通过id修改备注*/
//	int updateRemark(String remark,long id);
	int updateRemark(SalaryVo salary);
 XSSFWorkbook exportExcelInfo(String area,String year,String month);
	/*查询转正后实际考勤天数*/
	int updateSalary(long id,int year,int month);
	
	/*查询未转正后实际考勤天数*/
	int noupdateSalary(long id,int year,int month);
	/*查询打卡次数*/
	int updateNumber(long id,int year,int month);
	/**查询实发工资*/
	int updatetoSaray(SalaryVo s,long id,int year,int month);
	/*查询业务员工资表提成*/
	SalaryVo findSalesPush(long id,String time);
/**事假天数*/
	
	int toupleave(long id,int year,int month, double leave);
	/**病假天数*/
	int toupsick(long id,int year,int month,double leave);
	/**年假天数*/
	
	int toupannualleave(long id,int year,int month, double leave);
	/**产假天数*/
	
	int toupmaternity(long id,int year,int month, double leave);
	/**陪产假天数*/
	
	int touppmaternity(long id,int year,int month, double leave);
	/**婚假天数*/
	
	int toupmarriage(long id,int year,int month, double leave);
	
	/**修改请假次数*/
	int touplate(long id,int year,int month);
	/**获取请假类型*/
	SalaryStatistics getleave(long id,int year,int month);
}
