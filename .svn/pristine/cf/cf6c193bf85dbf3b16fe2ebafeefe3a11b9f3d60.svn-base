package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.CompanyDepartment;
import com.fendo.entity.CompanyPost;
import com.fendo.entity.Staff;
import com.fendo.entity.StaffVo;


public interface StaffDao {
	/** 修改时，显示所有信息*/
	StaffVo findAllStaff(@Param("id") long id);
	/** 查询部门显示 */
	List<CompanyDepartment> findDepartment();
	
	/** 查询职位显示 */
	List<CompanyPost> findPost();
	
	/**列表显示所有员工信息*/
	List<StaffVo> findStaffInfo();
	
	/** 添加员工信息  */
	int toAddStaff(Staff staff);
	
	/** 添加锁定状态 */
	int toLockStaff(@Param("id") long id);
	
	/** 修改信息*/
	int toUpdateStaff(Staff staff);
	
	/** 按电话查询 */
	List<StaffVo> toFindStaffByPhone(@Param("phone") String phone);

	/**根据员工姓名获取id*/
	List<Staff> getStaff(@Param("name") String name);
	/**异步判断标号*/
	Staff findByJobNum(@Param("job_number") String job_number);
	
	/**异步判断手机号*/
	Staff findStaffByPhone(@Param("phone") String phone);
	/**获取理财id*/
	int updateSalesId(@Param("s")Staff staff);
	

}
