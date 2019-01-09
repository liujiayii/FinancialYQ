package com.fendo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fendo.dao.BranchOfficeDao;
import com.fendo.dao.FinanceDao;
import com.fendo.entity.BranchOffice;
import com.fendo.entity.DaNumber;
import com.fendo.entity.Finance;
import com.fendo.service.FinanceService;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月1日-上午9:40:24
 */
@Service
public class FinanceServiceImpl implements FinanceService {

	@Autowired
	private FinanceDao financeDao;
	@Autowired
	private BranchOfficeDao branchofficeDao;

	/* 风控查询 */
	@Override
	public List<Finance> getFinance() {
		List<Finance> financeList = financeDao.getFinance();
		if (financeList != null) {
			System.out.println("已查询所有");
		}
		return financeList;
	}
	/*财务查询所有列表显示*/
	@Override
	public List<Finance> getFinanceByCw() {
		return financeDao.getFinanceByCw();
		
	}
	/* 查询所有 */
	@Override
	public List<Finance> findAll() {
		List<Finance> financeList = financeDao.findAll();
		if (financeList != null) {
			System.out.println("已查询所有");
		}
		return financeList;
	}

	/* 风控查询已审核 */
	@Override
	public List<Finance> findChecked() {
		System.out.println("已审核正在查询中，please waiting....");
		List<Finance> finList = financeDao.findChecked();
		if (finList != null) {
			System.out.println("已审核风控，查到了");
		}
		return finList;
	}
	/* 财务查询已审核 */
	@Override
	public List<Finance> findCheckedcw() {
		System.out.println("已审核正在查询中，please waiting....");
		List<Finance> finList = financeDao.findCheckedcw();
		if (finList != null) {
			System.out.println("已审核caiwu，查到了");
		}
		return finList;
	}
	/* 查询未审核 */
	@Override
	public List<Finance> findUnchecked() {

		System.out.println("未审核正在查询中，please waiting....");
		List<Finance> finList = financeDao.findUnchecked();
		if (finList != null) {
			System.out.println("未审核，查到了");
		}
		return finList;
	}

	/* 查询已打回(财务) */
	@Override
	public List<Finance> findBeenBackcw() {

		System.out.println("已打回正在查询中，please waiting....");
		List<Finance> finList = financeDao.findBeenBackcw();
		if (finList != null) {
			System.out.println("已打回财务写的，查到了");
		}
		return finList;
	}
	/* 查询已打回 （风控）*/
	@Override
	public List<Finance> findBeenBack() {

		System.out.println("已打回正在查询中，please waiting....");
		List<Finance> finList = financeDao.findBeenBack();
		if (finList != null) {
			System.out.println("已打回风控写的，查到了");
		}
		return finList;
	}
	/* 财务查询已作废 */
	@Override
	public List<Finance> findBeenVoid() {

		System.out.println("已作废正在查询中，please waiting....");
		List<Finance> finList = financeDao.findBeenVoid();
		if (finList != null) {
			System.out.println("已作废，查到了");
		}
		return finList;
	}
	/*风控查询已作废*/
	@Override
	public List<Finance> findBeenVoidFk() {
		return financeDao.findBeenVoidFk();
	}
	/* 风控添加 */
	@Override
	public int addFinance(Finance finance) {
		int rows = financeDao.addFinance(finance);

		if (rows >= 1) {
			System.out.println("插入" + rows + "个");
			System.out.println("添加成功" + finance);
		}
		return rows;
	}

	/* 通过id查询 */
	@Override
	public Finance findById(long id) {
		Finance finance = financeDao.findById(id);
		if (finance != null) {
			System.out.println("通过id查询到" + finance.toString());
		}
		return finance;
	}

	/* 风控修改 */
	@Override
	public int updateFinance(Finance finance) {

		int rows = financeDao.updateFinance(finance);
		if (rows >= 1) {
			System.out.println(rows + "修改成功@@@@");
		}
		return rows;

	}

	/* 修改是否逾期，是否提前还款 */
	@Override
	public int updateFinanceStatus(Finance finance) {
		int rows = financeDao.updateFinanceStatus(finance);
		if (rows >= 1) {
			System.out.println("修改成功！！");
		}
		return rows;
	}

	/* 打印 */
	
	@Override
	public List<BranchOffice> findArea() {
		List<BranchOffice> branchOfficeList = branchofficeDao.findArea();
		if (branchOfficeList != null) {
			System.out.println("查询到的地区：" + branchOfficeList);
		}
		return branchOfficeList;

	}

	@Override
	public List<BranchOffice> findArea1() {
		List<BranchOffice> branchOfficeList = branchofficeDao.findArea();
		if (branchOfficeList != null) {
			System.out.println("查询到的地区：" + branchOfficeList);
		}
		return branchOfficeList;
	}

	/* 审核 */
	@Override
	public int check(Finance finance) {
		int rows = financeDao.check(finance);
		if (rows >= 1) {
			System.out.println("审核成功！！");
		}
		return rows;
	}

	@Override
	public List<Finance> findUncheckSubmit() {
		List<Finance> finSubmitList = financeDao.findUncheckSubmit();
		if (!finSubmitList.isEmpty()) {
			System.out.println("未审核且提交的：" + finSubmitList);
		}
		return finSubmitList;
	}

	@Override
	public List<Finance> findUncheckSave() {
		List<Finance> finSaveList = financeDao.findUncheckSave();
		if (!finSaveList.isEmpty()) {
			System.out.println("未审核且仅保存了的：" + finSaveList);
		}
		return finSaveList;
	}

	/**
	 * 通过
	 * 
	 * @param finance
	 * @return
	 */
	@Override
	public int updatePass(Finance finance) {
		int rows = financeDao.updatePass(finance);
		if (rows >= 1) {
			System.out.println("通过成功！！");
		}
		return rows;

	}
	
	/**
	 * 作废
	 * 
	 * @param finance
	 * @return
	 */
	@Override
	public int toNullify(Finance finance) {
		int rows = financeDao.toNullify(finance);
		if (rows >= 1) {
			System.out.println("作废成功！！");
		}
		return rows;

	}
	
	/**
	 * 打回
	 * 
	 * @param finance
	 * @return
	 */
	@Override
	public int updateBack(Finance finance) {
		int rows = financeDao.updateBack(finance);
		if (rows >= 1) {
			System.out.println("dahui成功！！");
		}
		return rows;
	}

	@Override
	public List<DaNumber> getNumber() {

		return financeDao.getNumber();
	}

	@Override
	public int toupNumber(DaNumber number) {

		return financeDao.updateNumber(number);
	}

	@Override
	public Finance findGrade(String grade) {
		return financeDao.findGrade(grade);
		
	}

	@Override
	public Finance findByGrade(String grade) {
		
		return financeDao.findByGrade(grade);
	}
	/**
	 * 风控按标号查找
	 */
	
	@Override
	public List<Finance> findGrades(String grade) {
		
		return financeDao.findGrades(grade);
	}
	/**
	 * 财务按标号查找
	 */
	
	@Override
	public List<Finance> findGradescw(String grade) {
		
		return financeDao.findGradescw(grade);
	}
	/*风控标号搜索已审核*/
	@Override
	public List<Finance> findGradeByChecked(String grade) {
		return financeDao.findGradeByChecked(grade);
		
	}
	/*财务标号搜索已审核*/
	@Override
	public List<Finance> findGradeByCheckedcw(String grade) {
		return financeDao.findGradeByCheckedcw(grade);
		
	}
	/*标号搜索未审核*/
	@Override
	public List<Finance> findGradeByUnChecked(String grade) {
		
		return financeDao.findGradeByUnChecked(grade);
	}

	@Override
	public List<Finance> findGradeByBeenback(String grade) {
		
		return financeDao.findGradeByBeenback(grade);
	}

	@Override
	public List<Finance> findGradeByBeenvoidFk(String grade) {
		
		return financeDao.findGradeByBeenvoidFk(grade);
	}
	@Override
	public List<Finance> findCwUnchecked() {
		
		return financeDao.findCwUnchecked();
	}
	@Override
	public List<Finance> findGradeByBeenvoidCw(String grade) {
		
		return financeDao.findGradeByBeenvoidCw(grade);
	}
	@Override
	public List<Finance> findGradeByBeenBackCw(String grade) {
		
		return financeDao.findGradeByBeenBackCw(grade);
	}
	@Override
	public List<Finance> findGradeByUnCheckedCw(String grade) {
		
		return financeDao.findGradeByUnCheckedCw(grade);
	}
	

	

}
