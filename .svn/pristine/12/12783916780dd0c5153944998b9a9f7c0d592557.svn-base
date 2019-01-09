package com.fendo.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.SalaryDao;
import com.fendo.entity.SalaryStatistics;
import com.fendo.entity.Staff;
import com.fendo.entityVo.SalaryVo;
import com.fendo.pojo.ExcelBean;
import com.fendo.service.SalaryService;
import com.fendo.utils.ExcelUtil;

@Service
public class SalaryServiceImpl implements SalaryService{
	
	@Autowired
	private SalaryDao salaryDao;
	


	@Override
	public List<SalaryVo> findSalaryByName(String name,String year,String month) {
		 return salaryDao.findSalaryByName(name,year,month);
	}

	@Override
	public SalaryVo findSalaryById(long id,String year,String month) {
		System.out.println("salaryDao.findSalaryById(id,year,month)"+salaryDao.findSalaryById(id,year,month));
		
		return salaryDao.findSalaryById(id,year,month);
	}
	
	@Override
	public XSSFWorkbook exportExcelInfo(String area,String year,String month) {
		XSSFWorkbook xssfWorkbook = null;
		try {
			//根据id查找
//			String[] list = idList.split(",");
			List<SalaryVo> salaryVoList = salaryDao.findSalary(area, year, month);
			System.out.println("自黑"+salaryVoList);
            List<ExcelBean> excel=new ArrayList<>();
            Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
            excel.add(new ExcelBean("序号","id",0));
            excel.add(new ExcelBean("职务","post_name",0));
            excel.add(new ExcelBean("姓名","name",0));
            excel.add(new ExcelBean("转正出勤","real_attendance",0));
            excel.add(new ExcelBean("实习出勤","real_attendance",0));
            excel.add(new ExcelBean("实习工资","base_pay",0));
            excel.add(new ExcelBean("转正工资","zbase_pay",0));
            excel.add(new ExcelBean("业务工资","business_pay",0));
            excel.add(new ExcelBean("原始股东分红","original_dividend",0));
            excel.add(new ExcelBean("全勤奖","attendance_bouns",0));
            excel.add(new ExcelBean("工装补助","forck_bouns",0));
            excel.add(new ExcelBean("餐补","meal_bouns",0));
            excel.add(new ExcelBean("保密金","confidential_gold",0));
            excel.add(new ExcelBean("迟到次数","late",0));
            
            excel.add(new ExcelBean("事假","leaves",0));
            excel.add(new ExcelBean("病假","sick",0));
            excel.add(new ExcelBean("年假","annualleave",0));
            excel.add(new ExcelBean("产假","maternity",0));
            excel.add(new ExcelBean("陪产假","pmaternity",0));
            excel.add(new ExcelBean("婚假","marriage",0));
//            excel.add(new ExcelBean("小计","",0));
            
            excel.add(new ExcelBean("未打卡","no_clock",0));
            excel.add(new ExcelBean("扣保险","insurance_money",0));
        /*    excel.add(new ExcelBean("扣多发","multiple_money",0));
            excel.add(new ExcelBean("扣逾期","overdue_money",0));*/
//            
            excel.add(new ExcelBean("罚款","penalty_money",0));
//            excel.add(new ExcelBean("小计","",0));
            excel.add(new ExcelBean("实发工资","real_salary",0));
            excel.add(new ExcelBean("备注","remark",0));
            
            map.put(0, excel);
            String sheetName = "工资表";
            xssfWorkbook = ExcelUtil.createExcelFile(SalaryVo.class, salaryVoList, map, sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}


	

	@Override
	public int toAddSalary(SalaryStatistics s) {
		
		return salaryDao.toAddSalary(s);
	}

	@Override
	public List<Staff> findListSaray() {
		
		return salaryDao.findListSaray();
	}

	@Override
	public List<SalaryStatistics> toFindListSaray() {
		
		return salaryDao.toFindListSaray();
	}

	@Override
	public int updateSalary(long id, int year, int month) {
		
		return salaryDao.updateSalary(id, year, month);
	}

	@Override
	public int updateNumber(long id, int year, int month) {
		
		return salaryDao.updateNumber(id, year, month);
	}

	@Override
	public int updatetoSaray(SalaryVo s, long id, int year, int month) {
		
		return salaryDao.updatetoSalary(s, id, year, month);
	}

	@Override
	public List<SalaryVo> findSalary(String area, String year, String month) {
		
		return salaryDao.findSalary(area, year, month);
	}

	@Override
	public List<SalaryVo> findrealSalary() {
		// TODO Auto-generated method stub
		return  salaryDao.findrealSalary();
	}

	@Override
	public SalaryVo findSalesPush(long id,String time) {
		
		return salaryDao.findSalesPush(id,time);
	}

	@Override
	public int updateRemark(SalaryVo salary) {
		
		return salaryDao.updateRemark(salary);
	}

	/* (non-Javadoc)
	 * @see com.fendo.service.SalaryService#toupleave(long, int, int)
	 */
	@Override
	public int toupleave(long id, int year, int month,double leave) {
		
		return salaryDao.toupleave(id, year, month,leave);
	}

	
	@Override
	public SalaryStatistics getleave(long id, int year, int month) {
		
		return salaryDao.getleave(id, year, month);
	}

	/* (non-Javadoc)
	 * @see com.fendo.service.SalaryService#touplate(long, int, int)
	 */
	@Override
	public int touplate(long id, int year, int month) {
		
		return salaryDao.touplate(id, year, month);
	}

	
	@Override
	public int noupdateSalary(long id, int year, int month) {
		
		return salaryDao.updatenoSalary(id, year, month);
	}

	
	@Override
	public int toupsick(long id, int year, int month, double leave) {
		
		return salaryDao.toupsick(id, year, month, leave);
	}

	
	@Override
	public int toupannualleave(long id, int year, int month, double leave) {
		
		return salaryDao.toupannualleave(id, year, month, leave);
	}

	
	@Override
	public int toupmaternity(long id, int year, int month, double leave) {
		
		return salaryDao.toupmaternity(id, year, month, leave);
	}

	
	@Override
	public int touppmaternity(long id, int year, int month, double leave) {
		
		return salaryDao.touppmaternity(id, year, month, leave);
	}

	@Override
	public int toupmarriage(long id, int year, int month, double leave) {
	
		return salaryDao.toupmarriage(id, year, month, leave);
	}

	

}
