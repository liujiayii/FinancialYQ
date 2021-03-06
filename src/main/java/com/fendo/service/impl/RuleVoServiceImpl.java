package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.FinanceDao;
import com.fendo.dao.RuleVoDao;
import com.fendo.entity.RuleSalesVo;
import com.fendo.entity.RuleVo;
import com.fendo.service.RuleVoService;

/**
 * @author 
 * @return
 * @author
 */
@Service
public class RuleVoServiceImpl implements RuleVoService {

	
	@Autowired
	private RuleVoDao ruleVoDao;
	@Override
	public void toAddRulsVo(RuleVo rulevo) {
		
		ruleVoDao.toAddRuleVo(rulevo);
	}
	@Override
	public List<RuleVo> getlistRuleVo() {
	
		return ruleVoDao.getRulevo();
	}
	@Override
	public int toupdate(RuleVo rulevo) {
		
		return ruleVoDao.toupdateRuleVo(rulevo);
	}
	@Override
	public List<RuleSalesVo> getlistRuleVobyTime(String area, String time) {
		
		return ruleVoDao.getRulevoByTime(area, time);
	}
	/* (non-Javadoc)
	 * @see com.fendo.service.RuleVoService#toupdateRuleVobyId(com.fendo.entity.RuleVo, int)
	 */
	@Override
	public int toupdateRuleVobyId(RuleVo rulevo) {
		
		return ruleVoDao.toupdateRuleVobyId(rulevo);
	}

}
