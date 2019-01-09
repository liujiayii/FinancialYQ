package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.DeductVo;
//import com.fendo.entity.DeductVo;
import com.fendo.entity.Rule;
import com.fendo.entity.RuleSalesVo;

/**
 * @author 
 * @return
 * @author
 */
public interface RoleDao {
	
	void toAddRole(Rule rule);
	int toUpdateRole(@Param("t")Rule rule);
	List<Rule>getlistRule(@Param("n")String name);
  	//通过姓名查询提成
	List<RuleSalesVo> findDeductByName(@Param("name")String name);
	
	//查询所有业务员的提成
	List<DeductVo> findAllDeduct();
	/*查询提成详情*/
	List<DeductVo> findSaleDetailById(@Param("id")String id,@Param("time")String time);
}
