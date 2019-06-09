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
			
		}
		return branchOfficeList;
	}
	
	@Override
	public List<BranchOffice> getSalesMan() {
		List<BranchOffice> list = branchofficeDao.getSalesMan();
		if (list!=null) {
			
		}
		return list;
	}
	
	
	/*分公司增加-zhoujiaxin*/
	@Override
	public int addBranchOffice(BranchOffice branchOffice) {
	    List<BranchOffice> branchOfficeList = branchofficeDao.getBranchOffice();
	    if (branchOfficeList.size()>0) {
            for (int i = 0; i < branchOfficeList.size(); i++) {
                if (branchOffice.getArea().equals(branchOfficeList.get(i).getArea())) {
                    return -1;
                }else if (branchOffice.getArea_code().equals(branchOfficeList.get(i).getArea_code())) {
                    return -1;
                }
            }
        }
		branchofficeDao.addBranchOffice(branchOffice);
		return 1;
		
	}
	/*分公司修改-zhoujiaxin*/
	@Override
	public int updateBranchOffice(BranchOffice branchOffice) {
	    List<BranchOffice> branchOfficeList = branchofficeDao.getBranchOffice();
        if (branchOfficeList.size()>0) {
            for (int i = 0; i < branchOfficeList.size(); i++) {
                if (branchOffice.getId().equals(branchOfficeList.get(i).getId())) {
                    continue;
                }
                if (branchOffice.getArea().equals(branchOfficeList.get(i).getArea())) {
                    return -1;
                }else if (branchOffice.getArea_code().equals(branchOfficeList.get(i).getArea_code())) {
                    return -1;
                }
            }
        }
		int rows = branchofficeDao.updateBranchOffice(branchOffice);
		if (rows>=1) {
			return 1;
		}else {
            return -1;
        }
	}
	
	@Override
	public List<BranchOffice> findArea1() {
		List<BranchOffice> branchOfficeList = branchofficeDao.findArea();
		if(branchOfficeList!=null){
			
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
