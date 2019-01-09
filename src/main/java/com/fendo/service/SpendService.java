package com.fendo.service;

import java.util.List;

import com.fendo.entity.Income;
import com.fendo.entity.IncomeVo;
import com.fendo.entity.Spend;

import com.fendo.entity.SpendVo;


/**
 * 
 * @author han
 *
 * @createDate 2018年8月2日-下午4:34:41
 */

public interface SpendService {
	/*列表显示所有支出信息*/
	List<Spend> findAllType();
	/*添加支出信息*/
	int toAddSpend(Spend spend);
	/*通过id查询*/
	Spend findById(long id);
	/*修改通过状态*/
	int updatePasszc(Spend spend);
	 /*根据时间查询列表信息*/
	 List<Spend> findAllTypebytime(String time); 
}
