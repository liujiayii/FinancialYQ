package com.fendo.service.impl;

import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.PersonDao;
import com.fendo.entity.Person;
import com.fendo.service.PersonService;
@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonDao personDao;
	@Override
	public int toAddPerson(Person person) {
		int rows = personDao.toAddPerson(person);
		if(rows>=1){
			System.out.println("添加个人信息成功");
		}
		return rows;
	}
	@Override
	public List<Person> getPerson() {
		return personDao.getPerson();
	}
	@Override
	public int toUpdatePerson(Person person) {
		int rows = personDao.toUpdatePerson(person);
		if(rows>=1){
			
		}
		return rows;
	}
	@Override
	public int toDeletePerson(int id) {
		int rows = personDao.toDeletePerson(id);
		if(rows>=1){
			
		}
		return rows;
	}
	@Override
	public List<Person> findByPhone(String phone) {
		List<Person> list = personDao.findByPhone(phone);
		return list;
	}
	
}
