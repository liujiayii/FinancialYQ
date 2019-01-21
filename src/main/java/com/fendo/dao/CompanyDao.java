package com.fendo.dao;

import java.util.List;

import com.fendo.entity.Company;

public interface CompanyDao {
	List<Company> findAll(); 
	int insertCompany(Company company);
	int deleteCompany(int id);
	int updateCompany(Company company);
	List<Company> findByPhone(String phone);
}
