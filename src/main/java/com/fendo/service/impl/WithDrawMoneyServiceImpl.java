package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.WithDrawMoneyDao;
import com.fendo.entity.WithDrawMoney;
import com.fendo.service.WithDrawMoneyService;

@Service
public class WithDrawMoneyServiceImpl implements WithDrawMoneyService {
	@Autowired 
	private WithDrawMoneyDao withdrawmoneydao;
	/**
	 * 添加提现手续费
	 */
	@Override
	public int addWithDrawMoney(WithDrawMoney withdrawmoney) {
		
		return withdrawmoneydao.addWithDrawMoney(withdrawmoney);
	}
	/**
	 * 查询所有提现手续费
	 */
	@Override
	public List<WithDrawMoney> findAll() {
		List<WithDrawMoney> withdrawList = withdrawmoneydao.findAll();
		if (withdrawList != null) {
			System.out.println("所有提现手续费"+withdrawList);
		}
		return withdrawList;
	}
	/**
	 * 根据id查询
	 */
	@Override
	public WithDrawMoney findId(long id) {
		WithDrawMoney withdrawmoney = withdrawmoneydao.findId(id);
		if (withdrawmoney != null) {
			System.out.println("通过id查询到" + withdrawmoney.toString());
		}
		return withdrawmoney;
	
	}
	

}
