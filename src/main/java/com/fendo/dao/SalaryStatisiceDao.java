package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.SalaryStatistics;

public interface SalaryStatisiceDao {
 /*查询当月所有人员工资表*/
	List<SalaryStatistics> findAllSalaryStatisiceByMonth(@Param("month")String month,@Param("year")String year);
	/**根据员工号加30罚款*/
	int penaltyMoneyAdd(@Param("staff_id")long staff_id,@Param("year")String year,@Param("month")String month);
}
