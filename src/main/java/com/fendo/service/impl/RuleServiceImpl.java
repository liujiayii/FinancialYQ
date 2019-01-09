package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.RoleDao;
import com.fendo.entity.DeductVo;
import com.fendo.entity.Rule;
import com.fendo.entity.RuleSalesVo;
import com.fendo.service.RuleService;

/**
 * @author 
 * @return
 * @author
 */
@Service
public class RuleServiceImpl implements RuleService {
  
	@Autowired 
	private RoleDao roleDao;
	
	@Override
	public void toAddRule(Rule rule) {
		
		roleDao.toAddRole(rule);
	}

	@Override
	public int toUpdateRule(Rule rule) {
		
		return roleDao.toUpdateRole(rule);
	}

	@Override
	public List<Rule> getlistRule(String name) {
		
		return roleDao.getlistRule(name);
	}

	@Override
	public List<RuleSalesVo> findDeductByName(String name) {
		List<RuleSalesVo> deductList =	roleDao.findDeductByName(name);
		if(deductList.size()!=0){
			System.out.println("查到的是：" + deductList);
		}
		return deductList;
	}

	@Override
	public List<DeductVo> findAllDeduct() {
		List<DeductVo> deductList = roleDao.findAllDeduct();
		if(deductList.size()!=0){
			System.out.println("查到的是：" + deductList);
		}
		return deductList;
	}

	@Override
	public List<DeductVo> findSaleDetailById(String id,String time) {
		List<DeductVo> deList= roleDao.findSaleDetailById(id,time);
		if(deList.size()!=0){
			System.out.println("查到的是：" + deList);
		}
		return deList;
	}

}
