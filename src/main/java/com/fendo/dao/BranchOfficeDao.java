package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.BranchOffice;

public interface BranchOfficeDao {
	/* 查询所有分公司信息*/
	List<BranchOffice> getBranchOffice();
	
	/*查询地区名称*/
	List<BranchOffice> findArea();
	List<BranchOffice> findArea1();
	/*根据areacode查询*/
	List<BranchOffice> findArea2(@Param("areacode")String areacode);
	/*根据地区查询*/
	List<BranchOffice> findArea3(@Param("area")String area);
	/*查询业务员*/
	List<BranchOffice> getSalesMan();
	
	/*增加分公司*/
	void addBranchOffice(BranchOffice branchOffice);
	
	/*修改分公司*/
	int updateBranchOffice(BranchOffice branchOffice);
	
	
}
