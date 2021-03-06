package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.Finance;
import com.fendo.entity.Income;
import com.fendo.entity.IncomeItem;
import com.fendo.entity.IncomeVo;
import com.fendo.entity.Receipt;
import com.fendo.entity.Staff;
/**
 * 收入
 * @author han
 *
 * @createDate 2018年8月2日-下午4:14:08
 */
public interface IncomeDao {
	
	/*列表显示收入所有信息*/
	List<Income> findAllType();
	/*添加收入信息*/
	int toAddIncome(Income income);
	/*通过id查询*/
	Income findById1(long id);
	/*修改通过状态*/
	int updatePasssr(Income income);
	/*删除收入信息*/
	 void deletePasssr(@Param("id")long id);
	 /*根据时间查询列表信息*/
	 List<Income> findAllTypebytime(@Param("time")String time);
	 
	 /*根据地区查询列表信息*/
	 List<Income> findAllTypebyarea(@Param("area")String area);
	 /*修改收入费用*/
	 int toUpdateIncome(Income income);
	 /* 通过条件查询*/
	 List<Income> findBySelective(Income income);
	 
	 List<Receipt> findIncomeImgById(Long id );
	 
	 
}
