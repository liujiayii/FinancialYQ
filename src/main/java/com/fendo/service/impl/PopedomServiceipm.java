package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.PopedomSdao;
import com.fendo.entity.Popedom;
import com.fendo.entity.UserPopedom;
import com.fendo.entity.t_user;
import com.fendo.service.PopedomService;
@Service
public class PopedomServiceipm implements PopedomService{
	@Autowired
	private PopedomSdao  PopedomSdao;
	@Override
	public String  findPopedomByUserId(Long id) {
		
		return PopedomSdao.findPopedomByUserId(id);
	}

	@Override
	public Integer deletePopedomById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addPopedomById(UserPopedom userPopedom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updatePopedomById(Long id, String popedoms) {
		 
		int  fig=PopedomSdao.updatePopedomById(id,popedoms);
		if(fig>0) {
		
		}
		return fig;
	}

	@Override
	public List<t_user> findByLimit(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<t_user> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<t_user> findUserByPhone(String key, Integer page, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findPhoneCount(String phones) {
		// TODO Auto-generated method stub
		return null;
	}


}
