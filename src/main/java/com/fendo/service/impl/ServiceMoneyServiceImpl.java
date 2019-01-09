package com.fendo.service.impl;


import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fendo.dao.ServiceMoneyDao;
import com.fendo.entity.ServiceMoney;
import com.fendo.service.ServiceMoneyService;
@Service
public class ServiceMoneyServiceImpl implements ServiceMoneyService  {
	@Autowired 
	private ServiceMoneyDao servicemoneydao;
	/**
	 * 添加服务费
	 */
	@Override
	public int addServiceMoney(ServiceMoney servicemoney) {
		
		return servicemoneydao.addServiceMoney(servicemoney);
	}
	/**
	 * 查询所有服务费
	 */
	@Override
	public List<ServiceMoney> findAlls() {
		List<ServiceMoney> serviceMoney = servicemoneydao.findAlls();
		// DecimalFormat df = new DecimalFormat("#.00");
		if (serviceMoney != null) {
			System.out.println("所有服务费"+serviceMoney);
		}
		return serviceMoney;
	}
	@Override
	public ServiceMoney findIds(long id) {
		ServiceMoney servicemoney = servicemoneydao.findIds(id);
		if (servicemoney != null) {
			System.out.println("通过id查询到"+servicemoney.toString());
		}
		return servicemoney;
	}
	@Override
	// 根据当前页数查询查询本页服务费数据并返回到打印页面
	public List<ServiceMoney> findServiceMoneyLimitFive(Integer currentpage) {
		currentpage = (currentpage-1)*5;
		List<ServiceMoney> serviceMoney = servicemoneydao.findServiceMoneyLimitFive(currentpage);
		if (serviceMoney != null) {
			System.out.println("服务费"+serviceMoney);
		}
		return serviceMoney;
	}
	/* (non-Javadoc)
	 * @see com.fendo.service.ServiceMoneyService#deletePasssr(long)
	 */
	@Override
	public void deletePasssr(long id) {
		
		servicemoneydao.deleteLimitFive(id);
	}
	


}
