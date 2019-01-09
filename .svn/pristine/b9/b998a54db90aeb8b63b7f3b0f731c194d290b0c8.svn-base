package com.fendo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.SalaryDao;
import com.fendo.dao.SalaryStatisiceDao;
import com.fendo.entity.SalaryStatistics;
import com.fendo.service.TimerTaskService;
@Service
public class TimerTaskServiceImpl implements TimerTaskService{

	@Autowired
	private SalaryStatisiceDao salaryStatisiceDao;
	@Autowired
	private SalaryDao salaryDao;
	
	@Override
	public void penaltyMoney() {
		//获取当月月份
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy");
    	Date date = new Date();	
    	String nowMonth = simpleDateFormat.format(date);
    	String nowyear = simpleDateFormat2.format(date);
        //获取上一个月的月份
    	String lastMonth = (Integer.parseInt(nowyear)-1)+"";
    	
    	if(lastMonth.length()<2){
    		lastMonth ="0"+lastMonth;
    	}
    	//获取上一月的年份 如果是12月年份需要减 一
    	if(lastMonth.equals("12")){
    		nowyear = (Integer.parseInt(nowyear)-1)+"";
    	}
    	//查询上月所有人的工资表
    	List<SalaryStatistics> findAllSalaryStatisice = salaryStatisiceDao.findAllSalaryStatisiceByMonth(lastMonth, nowyear);
	      
    	//遍历查看请病假事假假天数年假
    	for (SalaryStatistics salaryStatistics : findAllSalaryStatisice) {
    		//病假天数
    		double sick = salaryStatistics.getSick();
    		//年假天数
    		double annualleave = salaryStatistics.getAnnualleave();
    		//事假天数
    		double leave =(double) salaryStatistics.getLeave();
    		
    		//扣上司款请假总数
    		double penaltyMoneyday =sick+annualleave+leave;
    		
    		//获取上月总天数
    		double monthday=0;
    		//1 3 5 7 8 10 12 31天

    		if("0103050708".contains(lastMonth)||"10".equals(lastMonth)||"12".equals(lastMonth)){

    			monthday = 31.0;
    		}
    		//2月
    		if("02".equals(lastMonth)){
    			//闰年
    			if(Integer.parseInt(nowyear)%4 == 0){
    				monthday = 29.0;
    			}else{
    				//平年
    				monthday = 28.0;
    			}
    		}
    		//剩余 30 天

    		if("040609".contains(lastMonth)||"11".equals(lastMonth)){

    			monthday = 30.0;
    		}
    				
    		//如果上班率不足93%罚上级30元
    		if((monthday-penaltyMoneyday)/monthday <0.93){
    			//员工id
    			long staff_id = salaryStatistics.getStaff_id();
    			//根据员工id查询上级工号
    			String leaderIdByJobNumber = salaryDao.getLeaderIdByJobNumber(staff_id);
    			//获取员工id
    			long id = salaryDao.getIdByJobNumber(leaderIdByJobNumber);
    			//通过员工id修改罚款金额
    			int penaltyMoneyAdd = salaryStatisiceDao.penaltyMoneyAdd(id, nowyear, lastMonth);
    			if (penaltyMoneyAdd >=1){
    				System.out.println("罚款成功");
    			}
    		}
		}
	}
	
	

}
