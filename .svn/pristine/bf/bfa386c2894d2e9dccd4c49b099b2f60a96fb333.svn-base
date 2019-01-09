package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fendo.dao.BranchOfficeDao;
import com.fendo.dao.SpendDao;
import com.fendo.entity.Income;
import com.fendo.entity.IncomeVo;
import com.fendo.entity.Spend;
import com.fendo.entity.SpendVo;
import com.fendo.service.SpendService;
/**
 * 
 * @author han
 *
 * @createDate 2018年8月2日-下午4:40:49
 */
@Service
public class SpendServiceImpl implements SpendService{
	@Autowired
	private SpendDao spendDao;
	@Autowired
	private BranchOfficeDao branchofficeDao;
	/*查询所有支出列表*/
	@Override
	public List<Spend> findAllType() {
		List<Spend> spendList = spendDao.findAllType();
		if (spendList != null) {
			System.out.println("已查询到所有的支出列表"+spendList);
		}
		return spendList;
	}
	/*添加支出信息*/
	@Override
	public int toAddSpend(Spend spend) {
		int rows = spendDao.toAddSpend(spend);
		if(rows>=1){
			System.out.println(rows+"实现类成功添加支出信息");
		}
		return rows;
	}
	
	/**
	 * 通过id查找
	 */
	@Override
	public Spend findById(long id) {
		Spend spend = spendDao.findById(id);
		if (spend != null) {
			System.out.println("id查询到"+spend.toString());
		}
		return spend;
	}
	/**
	 * 通过功能
	 */
	@Override
	public int updatePasszc(Spend spend) {
		int rows = spendDao.updatePasszc(spend);
		if (rows >= 1) {
			System.out.println("通过成功！！");
		}
		return rows;
	}

	@Override
	public List<Spend> findAllTypebytime(String time) {
		
		return spendDao.findAllTypebytime(time);
	}
}
