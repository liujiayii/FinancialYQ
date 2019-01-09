package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.RuleSalesVo;
import com.fendo.entity.RuleVo;
import com.fendo.entity.SalesMan;

/**
 * @author 
 * @return
 * @author cc
 */
public interface RuleVoDao {
	void toAddRuleVo(RuleVo rulevo);
	List<RuleVo> getRulevo();
	List<RuleSalesVo> getRulevoByTime(@Param("area")String area,@Param("time") String time);
	int toupdateRuleVo(@Param("t") RuleVo rulevo);
	int toupdateRuleVobyId(@Param("t") RuleVo rulevo);
	
	
}
