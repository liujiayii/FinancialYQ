package com.fendo.dao;

import java.util.List;

import com.fendo.entity.Person;

public interface PersonDao {
	/**
	 * 
	 * @param person
	 * @return
	 */
	int toAddPerson(Person person);
	
	List<Person> getPerson();
	
	int toUpdatePerson(Person person);
	
	int toDeletePerson(int id);
	
	List<Person> findByPhone(String phone);
}
