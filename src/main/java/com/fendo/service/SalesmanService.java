package com.fendo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.FinanceVo;
import com.fendo.entity.SalesMan;


import com.fendo.entity.SalesmanVo;


public interface SalesmanService {
	/*查询所有业务员*/
	public List<SalesMan> getSalesManList();
	/*查询业务员*/
	public List<SalesmanVo> getSalesManList(String name);
	/*查询业务员id*/
	public List<SalesMan> getSalesManListbyid(String id);
	/*增加业务员*/
	void toAddSaleMan(SalesMan salesman);
	/*修改业务员*/
	int toUpdateSaleMan(SalesMan salesman);
	/*修改锁状态*/
	int toUpdateSaleManstats(String id);
  
	public List<FinanceVo> getSalesVos(String time,long id);
	List<FinanceVo> getSalesVosone(String time, long id);
	
	public List<FinanceVo> getFinceVo(String time,String id);
	
	public List<FinanceVo> getFinceVoList(String time,String id);
	
	public List<FinanceVo> togetFinceVoList(String time,String id);
	
	/*通过姓名查询该业务员的信息*/
	List<SalesmanVo> findSaleByName(String name);
	/*通过工号查询该业务员的信息*/
	List<SalesMan> findSaleByjbnumber(String jbnumber);
	

	/*通过业务员id删除业务员*/
	int deleteSaleById(String id);
	/*通过id查找也业务*/
	SalesMan getSalesNameById(String id);
}
