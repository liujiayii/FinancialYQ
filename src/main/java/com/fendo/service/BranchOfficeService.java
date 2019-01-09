package com.fendo.service;

import java.util.List;

import com.fendo.entity.BranchOffice;

public interface BranchOfficeService {
	/*分公司查询*/
	List<BranchOffice> getBranchOffice();
	/*查询地区*/
	List<BranchOffice> findArea();
	List<BranchOffice> findArea1();
	List<BranchOffice> findArea2(String areacode);
	List<BranchOffice> findArea3(String area);
	List<BranchOffice> getSalesMan();
	/*分公司增加*/
	public void addBranchOffice(BranchOffice branchOffice);
	/*分公司修改*/
	int updateBranchOffice(BranchOffice branchOffice);
	
	
}
