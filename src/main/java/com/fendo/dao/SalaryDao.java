package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.SalaryStatistics;
import com.fendo.entity.Staff;
import com.fendo.entityVo.SalaryVo;

public interface SalaryDao {
	
	/** 工资表查询按年月地区查询*/
	List<SalaryVo> findSalary(@Param("area")String area,@Param("year")String year,@Param("month")String month);
	/** 工资表查询列表显示*/
	List<SalaryVo> findrealSalary();
	/** 查询公司每个人的id,基本工资*/
	List<Staff> findListSaray();
	/**查询工资表中所有的staff_id,id,以及对应的年和月*/
	List<SalaryStatistics> toFindListSaray();
	/**插入工资列表*/
	int toAddSalary(SalaryStatistics s);
	/** 通过姓名查询工资显示*/
	List<SalaryVo> findSalaryByName(@Param("name")String name,@Param("year")String year,@Param("month")String month);
	
	/** 通过id查询详情 */
	SalaryVo findSalaryById(@Param("id")long id,@Param("year")String year,@Param("month")String month);
	
	/** 通过id修改备注*/
//	int updateRemark(@Param("remark")String remark,@Param("id")long id);
	int updateRemark(SalaryVo salary);
	/**事假天数*/
	
	int toupleave(@Param("id")long id,@Param("year")int year,@Param("month")int month,@Param("leave") double leave);
	/**病假天数*/
	int toupsick(@Param("id")long id,@Param("year")int year,@Param("month")int month,@Param("leave") double leave);
	/**年假天数*/
	
	int toupannualleave(@Param("id")long id,@Param("year")int year,@Param("month")int month,@Param("leave") double leave);
	/**产假天数*/
	
	int toupmaternity(@Param("id")long id,@Param("year")int year,@Param("month")int month,@Param("leave") double leave);
	/**陪产假天数*/
	
	int touppmaternity(@Param("id")long id,@Param("year")int year,@Param("month")int month,@Param("leave") double leave);
	/**婚假天数*/
	
	int toupmarriage(@Param("id")long id,@Param("year")int year,@Param("month")int month,@Param("leave") double leave);
	
	/**修改请假次数*/
	int touplate(@Param("id")long id,@Param("year")int year,@Param("month")int month);
	/**获取请假类型*/
	SalaryStatistics getleave(@Param("id")long id,@Param("year")int year,@Param("month")int month);
	/** 导出查询*/
	List<SalaryVo> findSalaryToExcel();
	/*根据id动态修改转正后考勤天数*/
	int updateSalary(@Param("id")long id,@Param("year")int year,@Param("month")int month);
	/*根据id动态修改未转正后考勤天数*/
	int updatenoSalary(@Param("id")long id,@Param("year")int year,@Param("month")int month);
	/*根据id获取未打卡次数*/
	int updateNumber(@Param("id")long id,@Param("year")int year,@Param("month")int month);
	/*实发工资*/
	int updatetoSalary(@Param("s")SalaryVo staff,@Param("id")long id,@Param("year")int year,@Param("month")int month);
	/*查询业务员工资表提成*/
	SalaryVo findSalesPush(@Param("id") long id,@Param("time") String time);
	//根据员工id查询上级工号
	String getLeaderIdByJobNumber(long staff_id);
	//根据工号查询员工id
	long getIdByJobNumber(String job_number);
}	
