package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.BranchOfficeDao;

import com.fendo.entity.BranchOffice;
import com.fendo.service.BranchOfficeService;

@Service
public class BranchOfficeServiceImpl implements BranchOfficeService{
	
	@Autowired
	private BranchOfficeDao  branchofficeDao;
	/*分公司查询*/
	@Override
	public List<BranchOffice> getBranchOffice() {
		
		return branchofficeDao.getBranchOffice();
	}
	@Override
	public List<BranchOffice> findArea() {
		List<BranchOffice> branchOfficeList = branchofficeDao.findArea();
		if(branchOfficeList!=null){
			System.out.println("查询到的地区："+branchOfficeList);
		}
		return branchOfficeList;
	}
	
	@Override
	public List<BranchOffice> getSalesMan() {
		List<BranchOffice> list = branchofficeDao.getSalesMan();
		if (list!=null) {
			System.out.println(list + "查询到的业务员");
		}
		return list;
	}
	
	
	/*分公司增加*/
	@Override
	public void addBranchOffice(BranchOffice branchOffice) {
		branchofficeDao.addBranchOffice(branchOffice);
		
	}
	/*分公司修改*/
	@Override
	public int updateBranchOffice(BranchOffice branchOffice) {
		int rows = branchofficeDao.updateBranchOffice(branchOffice);
		if (rows>=1) {
			System.out.println(rows + "修改成功");
		}
		return rows;
	}
	@Override
	public List<BranchOffice> findArea1() {
		List<BranchOffice> branchOfficeList = branchofficeDao.findArea();
		if(branchOfficeList!=null){
			System.out.println("查询到的地区："+branchOfficeList);
		}
		return branchOfficeList;
	}
	@Override
	public List<BranchOffice> findArea2(String areacode) {
		
		return branchofficeDao.findArea2(areacode) ;
	}
	@Override
	public List<BranchOffice> findArea3(String area) {
		
		return branchofficeDao.findArea3(area);
	}
	
	

}
