package com.fendo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fendo.dao.IncomeDao;
import com.fendo.entity.Income;
import com.fendo.service.IncomeService;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月2日-下午4:40:49
 */
@Service
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private IncomeDao incomeDao;

	/* 查询所有收入列表 */
	@Override
	public List<Income> findAllType() {
		List<Income> incomeList = incomeDao.findAllType();
		if (incomeList != null) {
			System.out.println("已查询到所有的收入列表" + incomeList);
		}
		return incomeList;
	}

	/* 添加收入信息 */
	@Override

	public int toAddIncome(Income income) {
		int rows = incomeDao.toAddIncome(income);
		if (rows >= 1) {
			System.out.println(rows + "成功添加收入信息");
		}
		return rows;
	}

	/**
	 * 通过id查找
	 */
	@Override
	public Income findById1(long id) {
		Income income = incomeDao.findById1(id);
		if (income != null) {
			System.out.println("通过id查询到" + income.toString());
		}
		return income;
	}

	/**
	 * 通过功能
	 */
	@Override
	public int updatePasssr(Income income) {
		int rows = incomeDao.updatePasssr(income);
		if (rows >= 1) {
			System.out.println("通过成功");
		}
		return rows;
	}

	
	@Override
	public void deletePasssr(long id) {
	
		incomeDao.deletePasssr(id);
	}

	
	@Override
	public List<Income> findAllTypebytime(String time) {
		
		return incomeDao.findAllTypebytime(time);
	}

	@Override
	public List<Income> findAllTypebyarea(String area) {
		
		return incomeDao.findAllTypebyarea(area);
	}
}