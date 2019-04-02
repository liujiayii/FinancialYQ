package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.Expend;
import com.fendo.entity.Income;
import com.fendo.entity.Spend;
import com.fendo.entity.SpendItem;
import com.fendo.entity.SpendVo;
import com.fendo.entity.Staff;
/**
 * 收入
 * @author han
 *
 * @createDate 2018年8月2日-下午4:14:08
 */
public interface SpendDao {
	
	/*列表显示支出所有信息*/
	List<Spend> findAllType();
	/*添加支出信息*/
	Integer toAddSpend(Spend spend);
	/*查询支出项目名称显示*/
	//List<SpendItem> findSpendItem();
	/*查询员工名称显示*/
	//List<Staff> findname(); 
	/*添加项目名称数据*/
	//int toAddSpendItem(Spend Spenditem);
	//List<SpendVo> sum();
	/*通过id查询*/
	Spend findById(long id);
	/*修改通过状态*/
	int updatePasszc(Spend spend);
	
	 /*根据时间查询列表信息*/
	 List<Spend> findAllTypebytime(@Param("time")String time); 
	 
	 /*根据地区查询列表信息*/
	 List<Spend> findAllTypebyarea(@Param("area")String area);
	 /*修改支出费用*/
	 int toUpdateSpend(Spend spend);

	 List<Expend> findImgBySpendId(Long id);
}
