package com.fendo.service;

import java.util.List;

import com.fendo.entity.WithDrawMoney;

public interface WithDrawMoneyService {
	/*添加提现手续费*/
	int addWithDrawMoney(WithDrawMoney withdrawmoney);
	/*查询所有提现手续费*/
	List<WithDrawMoney> findAll();
	/*根据id查询*/
	WithDrawMoney findId(long id);
}
