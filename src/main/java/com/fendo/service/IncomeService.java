package com.fendo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.fendo.entity.Income;
import com.fendo.entity.IncomeItem;
import com.fendo.entity.IncomeVo;
import com.fendo.entity.Spend;
import com.fendo.entity.Staff;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月2日-下午4:34:41
 */

public interface IncomeService {
	/*列表显示所有收入信息*/
	List<Income> findAllType();
	/*添加收入信息*/
	//int toAddIncome(IncomeVo income);
	Map<String,Object> toAddSpend(Long id, List<MultipartFile> files);
	/*通过id查询*/
	Income findById1(long id);
	/*修改通过状态*/
	int updatePasssr(Income income);
	/*删除收入信息*/
	void deletePasssr(long id);
	 /*根据时间查询列表信息*/
	 List<Income> findAllTypebytime(String time); 
	 /*根据地区查询列表信息*/
	 List<Income> findAllTypebyarea(String area);
	 /*修改收入费用*/
	 int toUpdateIncome(Income income);
	 /*通过条件查询*/
	 List<Income> findBySelective(Income income);
	 Map<String,Object> toAddIncome(Income income);
}
