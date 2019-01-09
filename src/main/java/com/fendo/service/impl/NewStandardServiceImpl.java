package com.fendo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.FinanceDao;
import com.fendo.dao.NewstandardDao;
import com.fendo.entity.Finance;

import com.fendo.service.NewStandardService;

/**
 * @author 
 * @return
 * @author
 */
@Service
public class NewStandardServiceImpl implements NewStandardService {

	
	@Autowired
	private NewstandardDao newstandardDao;
	@Override
	public int addNewstandard(Finance ard) {
		System.out.println(ard+"///");
		return newstandardDao.addNewstandard(ard);
		
	}

}
