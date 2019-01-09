package com.fendo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.RuleSalesVo;
import com.fendo.entity.RuleVo;

/**
 * @author 
 * @return
 * @author
 */
public interface RuleVoService {
  public void toAddRulsVo(RuleVo rulevo);
  public List<RuleVo> getlistRuleVo();
  public int toupdate(RuleVo rulevo);
  public List<RuleSalesVo> getlistRuleVobyTime(String area,String time);
  
  int toupdateRuleVobyId(RuleVo rulevo);
}
