package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.FinanceVo;
import com.fendo.entity.RuleVo;
import com.fendo.entity.SalesMan;

import com.fendo.entity.SalesVo;

import com.fendo.entity.SalesmanVo;


public interface SalesmanDao {
	/*查询所有公司的详情*/
	List<SalesMan> setALLlist();
	/*根据公司的地区名字查询每个公司的详情*/
	List<SalesmanVo> getSalesMan(@Param("n")String name);
	/*根据公司的地区id查询每个公司的详情*/
	List<SalesMan> getSalesManByid(@Param("id")String id);
	/*增加业务员*/
	void toAddSaleMan(SalesMan salesman);
	
	/*修改业务员*/
	int toUpdateSaleMan(@Param("t") SalesMan salesman);
	/*修改锁状态*/
	int toUpdateSaleManstats(@Param("id") String id);
    List<FinanceVo> getSalesVos(@Param("time") String time,@Param("ids") long id);
    List<FinanceVo> getSalesVosone(@Param("time") String time,@Param("ids") long id);
    
	List<FinanceVo> getFinanceVo(@Param("time") String time,@Param("d") String d);
	List<FinanceVo> getFinanceVoList(@Param("time") String time,@Param("ids") String d);
	List<FinanceVo> togetFinanceVoList(@Param("time") String time,@Param("id") String d);
	
	
	/*通过姓名查询业务员信息*/
	List<SalesmanVo> findSaleByName(String name);
	/*通过姓名查询业务员信息*/
	List<SalesMan> findSaleByjbnumber(@Param("jbnumber")String jbnumber);
	
	
	/*通过id删除业务员*/
	int deleteSaleById(@Param("id") String id);
	/*通过id查找也业务*/
	SalesMan getSalesNameById(@Param("id")String id);
}
