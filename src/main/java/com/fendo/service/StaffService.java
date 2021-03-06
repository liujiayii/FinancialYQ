package com.fendo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.CompanyDepartment;
import com.fendo.entity.CompanyPost;
import com.fendo.entity.Staff;
import com.fendo.entity.StaffVo;

public interface StaffService {
	
	/** 修改时，显示所有信息*/
	StaffVo findAllStaff(long id);
	/** 动态显示部门*/
	List<CompanyDepartment> findDepartment();
	
	/** 动态显示职位 */
	List<CompanyPost> findPost();
	
	/**列表显示所有员工信息*/
	List<StaffVo> findStaffInfo();
	
	/** 添加员工信息  */
	int toAddStaff(Staff staff);
	
	/** 添加锁定状态 */
	int toLockStaff(long id);
	
	/** 修改信息 */
	int toUpdateStaff(Staff staff);
	
	/** 按手机号查询 */
	List<StaffVo> toFindStaffByPhone(String phone);
	/** 按手机号查询 */
	List<Staff> getStaff(String name);
	
	/**异步判断标号*/
	Staff findByJobNum(String job_number);
	
	/**异步判断手机号*/
	Staff findStaffByPhone(String phone);
	/**获取理财id*/
	int toUpdateSalesId(Staff staff);
	
	  /**查询最大id*/
	Staff findlastId();
} 
