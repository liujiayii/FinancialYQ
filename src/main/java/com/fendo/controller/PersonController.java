package com.fendo.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fendo.entity.Person;

import com.fendo.service.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/toAddPerson")
	@ResponseBody
	public ResultInfo toAddPerson(@RequestBody Person person){
		ResultInfo result = new ResultInfo();
		int rows = personService.toAddPerson(person);
		if(rows>=1){
			result.code = 0;
			result.msg = "successful";
		}
		return result;
	}
	
	@RequestMapping("/toCustPersInfo")
	@ResponseBody
	public List<Person> toShowPerson(){
		List<Person> personList = personService.getPerson();
		System.out.println(personList);
		
		if(!personList.isEmpty()){
			
		}
		
		return personList;
	}
	@RequestMapping("/toUpdatePerson")
	@ResponseBody
	public ResultInfo toUpdatePerson(@RequestBody Person person){
		System.out.println("!!!!!!!!!!!!");
		ResultInfo result = new ResultInfo();
		System.out.println(">>>>>>");
		try {
			int rows = personService.toUpdatePerson(person);
			System.out.println(rows);
			if(rows>=1){
				result.code = 0;
				result.msg = "successful";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code=-1;
			result.msg="error";
		}
		
		
		return result;
	}
	@RequestMapping("/toDeletePerson")
	@ResponseBody
	public ModelAndView toDeletePerson(int id){
		ResultInfo result = new ResultInfo();
		int rows = personService.toDeletePerson(id);
		if(rows>=1){
			result.code = 0;
			result.msg = "successful";
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("custPersInfo");
		mav.addObject("result", result);
		return mav;
	}
	@RequestMapping("/toFindPersonByPhone")
	@ResponseBody
	public List<Person> findByPhone(String phone){
		String phones = "%"+phone+"%"; 
		List<Person> list= personService.findByPhone(phones);
		System.out.println(list);
		if(!list.isEmpty()){
			System.out.println(list);
		}
		return list;
	}
}	
