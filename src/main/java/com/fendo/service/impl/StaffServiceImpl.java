package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.StaffDao;
import com.fendo.entity.CompanyDepartment;
import com.fendo.entity.CompanyPost;
import com.fendo.entity.Staff;
import com.fendo.entity.StaffVo;
import com.fendo.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public List<CompanyDepartment> findDepartment() {
		List<CompanyDepartment> departmentList = staffDao.findDepartment();
		if(!departmentList.isEmpty()){
			System.out.println("查询到了:"+departmentList);
		}
		return departmentList;
	}

	@Override
	public int toAddStaff(Staff staff) {
		int rows = staffDao.toAddStaff(staff);
		if(rows>=1){
			System.out.println(rows+" 添加信息成功");
		}
		return rows;
	}

	@Override
	public List<CompanyPost> findPost() {
		List<CompanyPost> postList = staffDao.findPost();
		if(!postList.isEmpty()){
			System.out.println("查询到了:" + postList);
		}
		return postList;
	}

	@Override
	public List<StaffVo> findStaffInfo() {
		List<StaffVo> staffVoList = staffDao.findStaffInfo();
		return staffVoList;
	}

	@Override
	public int toLockStaff(long id) {
		int rows = staffDao.toLockStaff(id);
		if(rows>=1){
			System.out.println("锁定成功！！");
		}
		return rows;
	}

	@Override
	public StaffVo findAllStaff(long id) {
		StaffVo staffVo = staffDao.findAllStaff(id);
		if(staffVo!=null){
			
			System.out.println("获取到的数据："+staffVo);
		}else{
			System.out.println("获取失败");
		}
		return staffVo;
	}

	@Override
	public int toUpdateStaff(Staff staff) {
		int rows = staffDao.toUpdateStaff(staff);
		return rows;
	}

	@Override
	public List<StaffVo> toFindStaffByPhone(String phone) {
		List<StaffVo> staffListVo = staffDao.toFindStaffByPhone(phone);
		return staffListVo;
	}

	@Override
	public List<Staff> getStaff(String name) {
		System.out.println("staffDao.getStaff(name).size()"+staffDao.getStaff(name));
		if(staffDao.getStaff(name).size()==0){
			return null;
		}
		return staffDao.getStaff(name);
	}

	@Override
	public Staff findByJobNum(String job_number) {
		
		return staffDao.findByJobNum(job_number);
	}

	@Override
	public Staff findStaffByPhone(String phone) {
		
		return staffDao.findStaffByPhone(phone);
	}

	@Override
	public int toUpdateSalesId(Staff staff) {
	
		return staffDao.updateSalesId(staff);
	}

	@Override
	public Staff findlastId() {
		
		return staffDao.findlastId();
	}

}	
