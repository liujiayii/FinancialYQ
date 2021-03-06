package com.fendo.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.ServiceMoney;

public interface ServiceMoneyDao {
	/*添加服务费*/
	int addServiceMoney(ServiceMoney servicemoney);
	/*查询所有服务费*/
	List<ServiceMoney> findAlls();
	/*根据id查询*/
	ServiceMoney findIds(long id);
	// 根据当前页数查询查询本页服务费数据并返回到打印页面
	List<ServiceMoney> findServiceMoneyLimitFive(Integer currentpage);
	
	 void deleteLimitFive(@Param("id")long id);
}
