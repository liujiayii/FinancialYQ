package com.fendo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fendo.dao.SalesmanDao;
import com.fendo.entity.FinanceVo;
import com.fendo.entity.SalesMan;
import com.fendo.entity.SalesmanVo;

import com.fendo.service.SalesmanService;



/**
 * @author han
 *
 *@createDate 2018年7月6日-下午3:01:47
 */

@Service
public class SalesmanserviceImpl implements SalesmanService {
	@Autowired 
	private SalesmanDao salesmanDao;
	
	/*查询业务员*/
	@Override
	public List<SalesmanVo> getSalesManList(String name) {
		
		return salesmanDao.getSalesMan(name) ;
	}
	/*添加业务员*/
	@Override
	public void toAddSaleMan(SalesMan salesman) {
		salesmanDao.toAddSaleMan(salesman);
		
	}
	/*修改业务员*/
	@Override
	public int toUpdateSaleMan(SalesMan salesman) {
		int rows = salesmanDao.toUpdateSaleMan(salesman);
		if (rows>=1) {
			System.out.println(rows+"修改业务员成功");
		}
		return rows;
	}
	@Override
	public List<SalesMan> getSalesManListbyid(String id) {
		
		return salesmanDao.getSalesManByid(id);
	}
	@Override
	public int toUpdateSaleManstats(String id) {
		// TODO Auto-generated method stub
		return salesmanDao.toUpdateSaleManstats(id);
	}

	
	@Override
	public List<FinanceVo> getFinceVo(String time,String id) {
		
		return salesmanDao.getFinanceVo(time,id) ;
	}
	@Override
	public List<FinanceVo> getSalesVos(String time,long id) {
		System.out.println("salesmanDao.getSalesVos( time,id)"+salesmanDao.getSalesVos( time,id));
		
		return salesmanDao.getSalesVos( time,id);
	}

	@Override
	public List<SalesmanVo> findSaleByName(String name) {
	    List<SalesmanVo> salesmanList = salesmanDao.findSaleByName(name);
	    if(salesmanList !=null){
	    	System.out.println("查到了" + salesmanList);
	    }
		return salesmanList;
	}
	@Override
	public int deleteSaleById(String id) {
		int rows = salesmanDao.deleteSaleById(id);
		if(rows >= 1){
			System.out.println(id + "impl中删除成功！");
		}
		return rows;
	}
	@Override
	public List<SalesMan> getSalesManList() {
		
		return salesmanDao.setALLlist();
	}
	@Override
	public SalesMan getSalesNameById(String id) {
		SalesMan salesName = salesmanDao.getSalesNameById(id);
		if(salesName!=null){
			System.out.println("查到名字了"+salesName.getName());
		}
		return salesName;
	}
	@Override
	public List<FinanceVo> getFinceVoList(String time, String id) {
		
		return salesmanDao.getFinanceVoList(time, id);
	}
	
	@Override
	public List<FinanceVo> togetFinceVoList(String time, String id) {
	
		return salesmanDao.togetFinanceVoList(time, id);
	}
	@Override
	public List<SalesMan> findSaleByjbnumber(String jbnumber) {
		// TODO Auto-generated method stub
		return salesmanDao.findSaleByjbnumber(jbnumber);
	}
	@Override
	public List<FinanceVo> getSalesVosone(String time, long id) {
		
		return salesmanDao.getSalesVosone(time, id);
	}
	

	
	

}
