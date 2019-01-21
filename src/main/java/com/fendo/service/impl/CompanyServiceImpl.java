package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.CompanyDao;
import com.fendo.entity.Company;
import com.fendo.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyDao companyDao;
	@Override
	public List<Company> findAll() {
		return companyDao.findAll();
	}
	@Override
	public int insertCompany(Company company) {
		int rows = companyDao.insertCompany(company);
		if(rows>=1){
			System.out.println("添加公司信息成功");
		}
		return rows;
	}
	@Override
	public int deleteCompany(int id) {
		int rows = companyDao.deleteCompany(id);
		if(rows>=1){
			System.out.println("删除公司信息成功");
		}
		return rows;
	}
	@Override
	public int updateCompany(Company company) {
		int rows = companyDao.updateCompany(company);
		if(rows>=1){
			System.out.println("修改公司信息成功");
		}
		return rows;
	}
	@Override
	public List<Company> findByPhone(String phone) {
		List<Company> list = companyDao.findByPhone(phone);
		return list;
	}
	
	
}
