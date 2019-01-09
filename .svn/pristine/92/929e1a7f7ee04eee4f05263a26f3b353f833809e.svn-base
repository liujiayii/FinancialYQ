package com.fendo.service;

import java.util.List;
import com.fendo.entity.DeductVo;
import com.fendo.entity.Rule;
import com.fendo.entity.RuleSalesVo;

/**
 * @author 
 * @return
 * @author
 */
public interface RuleService {
	/*增加*/
	void toAddRule(Rule rule);
	/*修改*/
	int toUpdateRule(Rule rule);
	/*查询*/
	List<Rule> getlistRule(String name);
	/*通过姓名查询提成*/
	List<RuleSalesVo> findDeductByName(String name);
	
	/*查询所有业务员的提成*/
	List<DeductVo> findAllDeduct();
	/*查询提成详情*/
	List<DeductVo> findSaleDetailById(String id,String time);

}
