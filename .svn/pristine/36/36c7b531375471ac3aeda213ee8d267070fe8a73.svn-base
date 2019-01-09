package com.fendo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fendo.entity.DaNumber;
import com.fendo.entity.Finance;
/**
 * 风控
 * @author han
 *
 * @createDate 2018年9月3日-上午10:18:45
 */
public interface FinanceDao {

	/*添加*/
    int addFinance(Finance finance);    
    /*风控查询列表显示*/
	List<Finance> getFinance();
	/*财务查询列表显示*/
	List<Finance> getFinanceByCw();
	/*通过id查询*/
	Finance findById(long id);
	/*通过grade查询所有列表*/
	Finance findByGrade(@Param("grade")String grade);	
	/*查询所有的显示*/
	List<Finance> findAll();
	/*风控查询已审核*/
	List<Finance> findChecked();
	/*财务查询已审核*/
	List<Finance> findCheckedcw();
	/*查询未审核*/
	List<Finance> findUnchecked();
	/*查询未审核且已提交的风控表*/
	List<Finance> findUncheckSubmit();
	/*查询未审核且仅保存的风控表，供风控人员修改*/
	List<Finance> findUncheckSave();
	/*查询未审核财务自己填写的，风控提交的*/
	List<Finance> findCwUnchecked();
	/*查询已打回(风控)*/
	List<Finance> findBeenBack();
	/*查询已打回(财务)*/
	List<Finance> findBeenBackcw();
	/*财务查询已作废*/
	List<Finance> findBeenVoid();
	/*风控查询已作废*/
	List<Finance> findBeenVoidFk();
	/*修改*/
	int updateFinance(Finance finance);
	/*修改状态*/
	int updateFinanceStatus(Finance finance);
	/*打印*/
	int check(Finance finance);
	/*修改通过状态*/
	int updatePass(Finance finance);
	/*修改打回状态*/
	int updateBack(Finance finance);
	/*查询编号*/
	List<DaNumber> getNumber();
	/*更新编号*/
	int updateNumber(DaNumber danumber);
	/*修改作废状态*/
	int toNullify(Finance finance);
	/*查找标号，判断标号是否存在*/
	Finance findGrade(String grade);
	/*风控按标号查找*/
	List<Finance> findGrades(@Param("grade") String grade);
	/*财务按标号查找*/
	List<Finance> findGradescw(@Param("grade") String grade);
	/*风控通过标号搜索已审核列表*/
	List<Finance> findGradeByChecked(@Param("grade")String grade);
	/*财务通过标号搜索已审核列表*/
	List<Finance> findGradeByCheckedcw(@Param("grade")String grade);
	/*通过标号搜索未审核列表*/

	List<Finance> findGradeByUnChecked(@Param("grade")String grade);
	/*财务通过标号查询未审核列表*/
	List<Finance> findGradeByUnCheckedCw(@Param("grade")String grade);
	/*风控通过标号搜索已打回列表*/
	List<Finance> findGradeByBeenback(@Param("grade")String grade);
	/*财务通过标号搜索已打回列表*/
	List<Finance> findGradeByBeenBackCw(@Param("grade")String grade);
	/*风控通过标号搜索已作废列表*/
	List<Finance> findGradeByBeenvoidFk(@Param("grade")String grade);
	/*财务通过标号搜索已作废列表*/
	List<Finance> findGradeByBeenvoidCw(@Param("grade")String grade);
	
}