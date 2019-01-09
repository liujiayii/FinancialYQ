package com.fendo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.BranchOffice;
import com.fendo.entity.DaNumber;
import com.fendo.entity.Finance;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月1日-上午9:39:43
 */
public interface FinanceService {

	/* 风控查询 */
	List<Finance> getFinance();

	/* 风控查询显示 */
	List<Finance> findAll();
	/*财务查询列表显示*/
	List<Finance> getFinanceByCw();
	
	List<BranchOffice> findArea();

	List<BranchOffice> findArea1();

	/* 通过id查询 */
	Finance findById(long id);
	/* 通过id查询 */
	Finance findByGrade(String grade);

	/*风控查询已审核*/
	List<Finance> findChecked();
	/*财务查询已审核*/
	List<Finance> findCheckedcw();

	/* 查询未审核 */
	List<Finance> findUnchecked();

	/* 查询未审核且已提交的风控表 */
	List<Finance> findUncheckSubmit();

	/* 查询未审核且仅保存的风控表，供风控人员修改 */
	List<Finance> findUncheckSave();

	/*查询未审核财务自己填写的，风控提交的*/
	List<Finance> findCwUnchecked();
	/*查询已打回(风控)*/
	List<Finance> findBeenBack();
	/*查询已打回(财务)*/
	List<Finance> findBeenBackcw();

	/* 财务查询已作废 */
	List<Finance> findBeenVoid();
	/*风控查询已作废*/
	List<Finance> findBeenVoidFk();
	/* 风控增加 */
	int addFinance(Finance finance);

	/* 风控修改 */
	int updateFinance(Finance finance);

	/*修改状态*/
	int updateFinanceStatus(Finance finance);
	/* 查询打印 */

	int check(Finance finance);

	/* 修改通过状态 */
	int updatePass(Finance finance);

	/* 修改打回状态 */
	int updateBack(Finance finance);

	/* 查询打印编号 */
	List<DaNumber> getNumber();
	/*更新编号*/
	int toupNumber(DaNumber number);
	/*修改作废状态*/
	int toNullify(Finance finance);
	
	/*查找标号，判断标号是否存在*/
	Finance findGrade(String grade);
	/*风控按标号查找*/
	List<Finance> findGrades(String grade);
	/*财务按标号查找*/
	List<Finance> findGradescw( String grade);
	/*风控通过标号搜索已审核列表*/
	List<Finance> findGradeByChecked(@Param("grade")String grade);
	/*财务通过标号搜索已审核列表*/
	List<Finance> findGradeByCheckedcw(@Param("grade")String grade);
	/*风控通过标号搜索未审核列表*/
	List<Finance> findGradeByUnChecked(String grade);
	/*财务通过标号查询未审核列表*/
	List<Finance> findGradeByUnCheckedCw(String grade);
	/*风控通过标号搜索已打回列表*/
	List<Finance> findGradeByBeenback(String grade);
	/*财务通过标号搜索已打回列表*/
	List<Finance> findGradeByBeenBackCw(String grade);
	/*风控通过标号搜索已作废列表*/
	List<Finance> findGradeByBeenvoidFk(String grade);
	/*财务通过标号搜索已作废列表*/
	List<Finance> findGradeByBeenvoidCw(String grade);
}
