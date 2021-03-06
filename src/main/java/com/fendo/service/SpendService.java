package com.fendo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.fendo.entity.Expend;
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
	Map<String,Object> toAddSpend(Spend spend);
	/*通过id查询*/
	Spend findById(long id);
	/*修改通过状态*/
	int updatePasszc(Spend spend);
	 /*根据时间查询列表信息*/
	 List<Spend> findAllTypebytime(String time); 
	 
	 /*根据地区查询列表信息*/
	 List<Spend> findAllTypebyarea(String area);
	 /*修改收入费用*/
	 int toUpdateSpend(Spend spend);
	 
	 
	Map<String,Object> toAddSpend(Long id, List<MultipartFile> files);
	
	
	List<Expend> findImgBySpendId(Long id);
}
