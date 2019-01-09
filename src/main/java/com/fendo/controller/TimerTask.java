
package com.fendo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fendo.entity.BranchOffice;
import com.fendo.entity.DingJON;
import com.fendo.entity.FinanceVo;
import com.fendo.entity.Rule;
import com.fendo.entity.RuleVo;
import com.fendo.entity.SalaryStatistics;
import com.fendo.entity.SalesMan;
import com.fendo.entity.SalesVo;
import com.fendo.entity.ServiceMoney;
import com.fendo.entity.Staff;
import com.fendo.entity.WithDrawMoney;
import com.fendo.entityVo.SalaryVo;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.FinanceService;
import com.fendo.service.RuleService;
import com.fendo.service.RuleVoService;
import com.fendo.service.SalaryService;
import com.fendo.service.SalesmanService;
import com.fendo.service.ServiceMoneyService;
import com.fendo.service.StaffService;
import com.fendo.service.TimerTaskService;
import com.fendo.utils.AuthHelper;
import com.fendo.utils.Env;
import com.fendo.utils.HttpRequestUtil;
import com.fendo.utils.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;

/**
 * @author
 * @return
 * @author
 */
@Component

@Lazy(false)
public class TimerTask {
	
    @Autowired
    private TimerTaskService timerTaskService;
	@Autowired
	private RuleService ruleService;
	@Autowired
	SalesmanService salesmanService;
	@Autowired
	RuleVoService ruleVoService;
	@Autowired
	FinanceService financeService;
	@Autowired
	StaffService staffService;
	@Autowired
	SalaryService salaryService;
	@Autowired
	ServiceMoneyService servicemoneyService;
	UserService us = new UserService();

	/*
	 * @Autowired private XXTZController xc;
	 * 
	 * 
	 * 每个月一号凌晨一点执行test1
	 */
	// 秒分时日 月周 获取提成
/*	@Scheduled(cron = "0 40 00 01 * ?")*/
	@Scheduled(cron = "0 33 14 * * ?")
	public List<RuleVo> test1() {
		System.out.println("4646");
		String time;
		String times;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		if (month > 9) {
			time = "%" + year + "-" + month + "%";
			times = year + "-" + month;
		} else {
			time = "%" + year + "-0" + month + "%";
			times = year + "-0" + month;
		}
		// 设一个空的所有的业务员提成集合
		List<RuleVo> list = ruleVoService.getlistRuleVo();

		// 每个地区的业务员基本情况
		List<SalesMan> listman = new ArrayList<SalesMan>();
		// 每个地区的业绩规则
		List<Rule> rule = new ArrayList<Rule>();
		// 每个地区每个月的总业绩和每个地区每个业业务员的总业绩
		List<FinanceVo> sales = new ArrayList<FinanceVo>();
		List<String> listint = new ArrayList<String>();
		double k = 0.00;// 提成
		// 获取业绩规则

		listman = salesmanService.getSalesManList();// 获取业务员基本信息

		for (int p = 0; p < list.size(); p++) {
			listint.add(list.get(p).getTime());

		}
		List<FinanceVo> saleslist = new ArrayList<FinanceVo>();
		List<FinanceVo> saleslistone = new ArrayList<FinanceVo>();

		for (int a = 0; a < listman.size(); a++) {
			 System.out.println("业务员1"+listman.get(a).getDistrict_id());
			/*sales = salesmanService.getFinceVo(time, listman.get(a).getDistrict_id());*/
			/*rule = ruleService.getlistRule(listman.get(a).getDistrict_id());*/
			RuleVo rulevo = new RuleVo();

			rulevo.setSalesId(listman.get(a).getId());
			rulevo.setTime(times);

			

			saleslist = salesmanService.getSalesVos(time, listman.get(a).getId());
			System.out.println("time"+time+"listman.get(a).getId()"+listman.get(a).getId());
			System.out.println("saleslist"+saleslist);
			saleslistone=salesmanService.getSalesVosone(time, listman.get(a).getId());

//			if (sales.get(0) == null || saleslist.isEmpty()) {
//				 System.out.println("业务员2");
//				k = 0;
//				list.get(a).setExtract(k);
//				/*if (listint.contains(rulevo.getTime())) {
//
//				} else {
//
//					ruleVoService.toAddRulsVo(rulevo);
//				}*/
//			} else {
				/*if (sales.get(0).getSum() > rule.get(0).getCompletionrate()) {*/
					if (listman.get(a).getDuty().equals("业务员")) {
                           System.out.println("业务员");
						if (saleslist.get(0) == null&&saleslistone.get(0) == null ) {
							System.out.println("业务员***");
							
							rulevo.setExtract(k);
						} else if(saleslist.get(0) == null&&saleslistone.get(0) != null){

							System.out.println("业务员---+++++"+saleslistone);
						
							k = new BigDecimal((float)saleslistone.get(0).getMoney()/2/10000).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							System.out.println("业务员k"+k);
							rulevo.setExtract(k);

						}else if(saleslist.get(0) != null&&saleslistone.get(0) == null){
							
							k =  new BigDecimal((float)saleslist.get(0).getMoney()/2/10000).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							System.out.println(k);
							rulevo.setExtract(k);
						}else{
							System.out.println("业务员---------****++++++++");
							k = (new BigDecimal((float)saleslist.get(0).getMoney()/2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())
									+new BigDecimal((float)saleslistone.get(0).getMoney()/2/10000).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									
							rulevo.setExtract(k);
						}
						System.out.println("rulevo"+rulevo);
						ruleVoService.toAddRulsVo(rulevo);
					}/* else if (listman.get(a).getDuty().equals("门店经理")) {
						if (sales.get(0).getSum() > 0) {
							if (sales.get(0).getSum() > rule.get(0).getTask()) {
								k = sales.get(0).getSum() * rule.get(0).getRule_one() / 10000
										* ((sales.get(0).getSum() / rule.get(0).getTask())
												+ (sales.get(0).getSum() - rule.get(0).getTask())
														* rule.get(0).getRule_two() / 10000);

								list.get(a).setExtract(k);

							} else {
								k = sales.get(0).getSum() * rule.get(0).getRule_one()
										* (sales.get(0).getSum() / rule.get(0).getTask());

								list.get(a).setExtract(k);
							}
						} else {
							k = 0;
							list.get(a).setExtract(k);
						}
					} *//*else {

						k = rule.get(0).getSalesManageNumber() * (sales.get(0).getSum() / rule.get(0).getTask());
						if (k > rule.get(0).getSalesManageNumber()) {
							k = rule.get(0).getSalesManageNumber();

						}

						list.get(a).setExtract(k);

					}*/
			/*	} else {
					list.get(a).setExtract(k);
				}*/

				/*if (listint.contains(rulevo.getTime())) {

				} else {

					ruleVoService.toAddRulsVo(rulevo);
				}*/
			/*}*/
		}

		return list;
	}
	/* 获取考勤天数 */

	@Scheduled(cron = "0 30 2 * * ?")
	public void testGetAccessToken() {

		List<String> listid = new ArrayList<String>();

		try {
			String accessToken;
			accessToken = AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);

			// 2.0通过accessToken获取公司里各个部门的-部门名称（name）-部门ID（id）
			String getDepIdName = "https://oapi.dingtalk.com/department/list?access_token=" + accessToken;

			net.sf.json.JSONObject jsonObjectDepList = HttpRequestUtil.doGet(getDepIdName);

			/* System.out.println(jsonObjectDepList); */
			// 2.2通过department属性获取部门json对象数组
			net.sf.json.JSONArray DepArr = jsonObjectDepList.getJSONArray("department");
			Calendar cal = Calendar.getInstance();
			// 年
			int year = cal.get(Calendar.YEAR);
			// 月
			int month = cal.get(Calendar.MONTH) + 1;

			// 日
			int day = cal.get(Calendar.DAY_OF_MONTH) - 1;
			String time = null;
			Date date = new Date();

			if (month < 10 && day < 10) {
				time = year + "-0" + month + "-0" + day + " 00:00:00";
			} else if (month < 10 && day >= 10) {
				time = year + "-0" + month + "-" + day + " 00:00:00";
			} else if (month > 10 && day < 10) {
				time = year + "-" + month + "-0" + day + " 00:00:00";
			} else {
				time = year + "-" + month + "-" + day + " 00:00:00";
			}
			// 日期格式:2018-08-20
			// 如果不是周六日
			/*
			 * System.out.println(); }else{
			 */
			String workDateFrom = "2018-11-09 00:00:00";
			String workDateTo = "2018-11-09 23:00:00";
			System.out.println(time + "time");
			for (int a = 0; a < DepArr.size(); a++) {

				Map<String, Integer> map = (Map<String, Integer>) DepArr.get(a);
				String value = map.get("id").toString();
				if (value.equals("1")) {
				} else {
					String accessToken1 = AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
					JSONObject l = us.getDepartmentUser(accessToken1, value);

					com.alibaba.fastjson.JSONArray Dep = l.getJSONArray("userlist");
					for (int b = 0; b < Dep.size(); b++) {
						@SuppressWarnings("unchecked")
						Map<String, String> map1 = (Map<String, String>) Dep.get(b);
						String values = map1.get("userid");
						JSONObject job = us.getUser(accessToken, values);
						String jobnumber = job.getString("jobnumber");
						// Map<String,String> map2 = (Map<String, String>)
						// job;

						String v = map1.get("name");
						List<String> list = new ArrayList<String>();
						list.add(values);
						listid.add(values);
						String recordUrl = "https://oapi.dingtalk.com/attendance/list?access_token=" + accessToken;

						Long offset = 0L;
						Long limit = (long) 2;
						JSONObject jsonObject = new JSONObject();

						jsonObject.put("userIdList", list);
						jsonObject.put("workDateFrom", workDateFrom);
						jsonObject.put("workDateTo", workDateTo);
						jsonObject.put("offset", offset);
						jsonObject.put("limit", limit);
						net.sf.json.JSONObject jsonObject2 = HttpRequestUtil.doPost(recordUrl, jsonObject);
						if (jsonObject2.size() != 0) {
							if (!JSONNull.getInstance().equals(jsonObject2.get("recordresult"))) {

								net.sf.json.JSONArray deps = jsonObject2.getJSONArray("recordresult");
								List listmap = deps.toList(deps, DingJON.class);
								System.out.println("listmap" + listmap);
								// System.out.println(listmap.size()+"长度"+listmap);
								if (!isWeekend(time)) {
									if (listmap != null && listmap.size() != 0) {
										int p = listmap.get(0).toString().indexOf("locationResult=");
										int p1 = listmap.get(0).toString().indexOf("planId");
										int p2 = listmap.get(1).toString().indexOf("locationResult=");
										int p3 = listmap.get(1).toString().indexOf("planId");
										String state = listmap.get(0).toString().substring(p + 15, p1 - 2);
										String state1 = listmap.get(1).toString().substring(p2 + 15, p3 - 2);

										if (state.equals("NotSigned")) {
											List<Staff> listStaffs = staffService.getStaff(jobnumber);
											if (listStaffs != null) {
												long id = listStaffs.get(0).getId();

												salaryService.updateNumber(id, year, month);
											}
										}

										else if (state.equals("Late")) {

											List<Staff> listStaff = staffService.getStaff(jobnumber);

											if (listStaff != null) {
												long id = listStaff.get(0).getId();

												salaryService.touplate(id, year, month);
												setupdateSalary(listStaff.get(0).getEntry_date(), year, month, day, id);
											}
										} else {

											List<Staff> listStaff = staffService.getStaff(jobnumber);
											System.out.println("listStaff" + listStaff);
											if (listStaff != null) {
												long id = listStaff.get(0).getId();
												setupdateSalary(listStaff.get(0).getEntry_date(), year, month, day, id);
											}
										}
										if (state1.equals("NotSigned")) {
											List<Staff> listStaffs = staffService.getStaff(jobnumber);
											if (listStaffs != null) {
												long id = listStaffs.get(0).getId();

												salaryService.updateNumber(id, year, month);
											}
										}

										else if (state1.equals("Late")) {
											List<Staff> listStaff = staffService.getStaff(jobnumber);

											if (listStaff != null) {
												long id = listStaff.get(0).getId();

												salaryService.touplate(id, year, month);
												setupdateSalary(listStaff.get(0).getEntry_date(), year, month, day, id);
											}

										}

										else {

											System.out.println("状态" + state1);
											List<Staff> listStaff = staffService.getStaff(jobnumber);
											if (listStaff != null) {
												long id = listStaff.get(0).getId();

												setupdateSalary(listStaff.get(0).getEntry_date(), year, month, day, id);

											}
										}
									}
								} else {
									List<Staff> listStaffs = staffService.getStaff(jobnumber);
									if (listStaffs != null) {
										long id = listStaffs.get(0).getId();
										System.out.println("listStaffs.get(0).getEntry_date()" + listStaffs);
										setupdateSalary(listStaffs.get(0).getEntry_date(), year, month, day, id);
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @description 判断这天是否转正
	 *
	 * @param date
	 * @param year
	 * @param month
	 * @param day
	 * @param id
	 * @author lichangchun
	 * @createDate
	 */
	public void setupdateSalary(String timeone, int year, int month, int day, Long id) {
	
		int yearone = Integer.parseInt(timeone.substring(0, 4));
		int monthon = Integer.parseInt(timeone.substring(5, 7));
		int dayone = Integer.parseInt(timeone.substring(8, 10));
		if (year > yearone || (month - 3 == monthon && day > dayone) || month - 3 > monthon) {
			
			int a = salaryService.updateSalary(id, year, month);
		
		} else {
			salaryService.noupdateSalary(id, year, month);
		}
	}

	/**
	 * 判断日期是否是周六日,返回false即不是周六日
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isWeekend(String date) throws ParseException {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date bdate = format1.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else
			return false;
	}

/*	
	*//** 定时插入工资 *//*
	@Scheduled(cron = "0  32  17  *  *  ?")
	public void toAddSalary() {
        System.out.println("***********");
		SalaryStatistics s = new SalaryStatistics();
		SalaryStatistics sone = new SalaryStatistics();
		SalaryStatistics stwo = new SalaryStatistics();
		SalaryStatistics sthree = new SalaryStatistics();
		Calendar cal = Calendar.getInstance();
		// 年
		String year = String.valueOf(cal.get(Calendar.YEAR));
		// 月
		String month;
		if ((cal.get(Calendar.MONTH) + 1) < 10) {
			month = "0" + String.valueOf(cal.get(Calendar.MONTH) + 1);
		} else {
			month = String.valueOf(cal.get(Calendar.MONTH) + 1);
		}

		List<Staff> list = salaryService.findListSaray();
		List<SalaryStatistics> list1 = salaryService.toFindListSaray();
		String[] str = new String[list1.size()];
		String[] str1 = new String[list1.size()];

		String[] str2 = new String[list1.size()];
		for (int b = 0; b < list1.size(); b++) {

			str[b] = String.valueOf(list1.get(b).getStaff_id());

			str1[b] = list1.get(b).getYear();
			str2[b] = list1.get(b).getMonth();

		}

		for (int a = 0; a < list.size(); a++) {
			if (useLoop(str, String.valueOf(list.get(a).getId())) && useLoop(str1, String.valueOf(year))
					&& useLoop(str2, String.valueOf(month))) {

			} else {
				// Staff staff = new Staff();
				// BigDecimal base_pay = staff.getBase_pay();

				// BigDecimal business_pay = staff.getBase_pay();
				BigDecimal business_pay = list.get(a).getBase_pay();
				s.setStaff_id(list.get(a).getId());

				s.setYear(year);
				s.setMonth(month);
				sone.setStaff_id(list.get(a).getId());
				if (Integer.parseInt(month) + 1 > 12) {
					sone.setYear((Integer.parseInt(year) + 1) + "");
					sone.setMonth(1 + "");
				} else {
					sone.setYear(year);
					sone.setMonth(String.valueOf(Integer.parseInt(month) + 1));
				}

				stwo.setStaff_id(list.get(a).getId());
				if (Integer.parseInt(month) + 2 > 12) {
					stwo.setYear((Integer.parseInt(year) + 1) + "");
					stwo.setMonth(Integer.parseInt(month) + 2 - 12 + "");
				} else {
					stwo.setYear(year);
					stwo.setMonth(String.valueOf(Integer.parseInt(month) + 1));
				}

				sthree.setStaff_id(list.get(a).getId());
				if (Integer.parseInt(month) + 3 > 12) {
					sthree.setYear((Integer.parseInt(year) + 1) + "");
					sthree.setMonth(Integer.parseInt(month) + 3 - 12 + "");
				} else {
					sthree.setYear(year);
					sthree.setMonth(String.valueOf(Integer.parseInt(month) + 3));
				}
				System.out.println(s);
				System.out.println(sone);
				System.out.println(stwo);
				System.out.println(sthree);
				int k = salaryService.toAddSalary(s);
				int kone = salaryService.toAddSalary(sone);
				salaryService.toAddSalary(stwo);
				int kthree = salaryService.toAddSalary(sthree);

			}

		}
	}

	
*/
	/**
	 * 获取每个人的请假天数
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 10 2 * * ?")
	public void toAddSalary1() throws Exception {

		Calendar cal = Calendar.getInstance();
		// 年
		int year = cal.get(Calendar.YEAR);
		// 月
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH) - 1;
		/* 获取当月天数 */
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		int maxDate = cal.get(Calendar.DATE);

		String accessToken;
		accessToken = AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);

		/** 获取审批实例所需要的唯一process_code */
		String process_code = "PROC-EF6YRO35P2-UYCJM3V5TLOJZBCFVBDQ1-2QD03M0J-BR";
		/** 获取审批实例的接口地址 */
		String recordUrl = "https://oapi.dingtalk.com/topapi/processinstance/listids?access_token=" + accessToken;
		JSONObject jsonObject = new JSONObject();
		Date date = new Date();
		long time = date.getTime() - 86400000;
		jsonObject.put("process_code", process_code);
		jsonObject.put("start_time", time);

		net.sf.json.JSONObject jsonObjectone = HttpRequestUtil.doPost(recordUrl, jsonObject);

		/** 获取json的属性值 */
		String values = jsonObjectone.getString("result");

		/** 字符串转json */
		JSONObject jsonObjecttwo = (JSONObject) JSON.parse(values);

		/** 获取json的属性值 */
		String v = jsonObjecttwo.getString("list");

		String sTemp = v.substring(1, v.length() - 1);
		String[] sArray = sTemp.split(",");
		for (int a = 0; a < sArray.length; a++) {
			String mmp = "https://oapi.dingtalk.com/topapi/processinstance/get?access_token=" + accessToken;
			JSONObject jsonObjectfive = new JSONObject();

			String process_instance_id = sArray[a].substring(1, sArray[a].length() - 1);
			jsonObjectfive.put("process_instance_id", process_instance_id);
			net.sf.json.JSONObject jsonObjectsix = HttpRequestUtil.doPost(mmp, jsonObjectfive);
			
			String process_instance = jsonObjectsix.getString("process_instance");

			JSONObject jsonObjectfour = (JSONObject) JSON.parse(process_instance);

			String form_component_values = jsonObjectfour.getString("form_component_values");
			String indexs = form_component_values.substring(form_component_values.lastIndexOf("午") + 3,
					form_component_values.indexOf("halfDay") - 2);
			double number = Double.parseDouble(indexs);
		
			String operation_records = jsonObjectfour.getString("operation_records");

			String strone;
			if (form_component_values.indexOf("事假") > -1 && operation_records.indexOf("AGREE") > -1) {
				strone = "事假";
			} else if (form_component_values.indexOf("病假") > -1 && operation_records.indexOf("AGREE") > -1) {
				strone = "病假";
			} else if (form_component_values.indexOf("婚假") > -1 && operation_records.indexOf("AGREE") > -1) {
				strone = "婚假";
			} else if (form_component_values.indexOf("产假") > -1 && operation_records.indexOf("AGREE") > -1) {
				strone = "产假";
			} else if (form_component_values.indexOf("陪产假") > -1 && operation_records.indexOf("AGREE") > -1) {
				strone = "陪产假";
			} else {
				strone = "年假";
			}
			String userid = jsonObjectfour.getString("originator_userid");
			JSONObject job = us.getUser(accessToken, userid);
			String jobnumber = job.getString("jobnumber");
			List<Staff> listStaff = staffService.getStaff(jobnumber);

			if (listStaff != null) {
				long id = listStaff.get(0).getId();

				Map<Long, String> mapjob = new HashMap();
				mapjob.put(id, strone);

				if (mapjob.get(id).equals("事假")) {
					if (maxDate - day >= number) {
						salaryService.toupleave(id, year, month, number);
					} else {
						salaryService.toupleave(id, year, month, maxDate - day);
						salaryService.toupleave(id, year, month + 1, number - (maxDate - day));
					}
				} else if (mapjob.get(id).equals("病假")) {

					if (maxDate - day >= number) {
						salaryService.toupsick(id, year, month, number);
					} else {
						salaryService.toupsick(id, year, month, maxDate - day);
						salaryService.toupsick(id, year, month + 1, number - (maxDate - day));
					}
				} else if (mapjob.get(id).equals("婚假")) {
					if (maxDate - day >= number) {
						salaryService.toupmarriage(id, year, month, number);
					} else {
						salaryService.toupmarriage(id, year, month, maxDate - day);
						salaryService.toupmarriage(id, year, month + 1, number - (maxDate - day));
					}
				} else if (mapjob.get(id).equals("产假")) {

					salaryService.toupmaternity(id, year, month, maxDate - day);
					salaryService.toupmaternity(id, year, month + 1, 30);
					salaryService.toupmaternity(id, year, month + 2, 30);
					salaryService.toupmaternity(id, year, month + 3, number - 60 - (maxDate - day));

				} else if (mapjob.get(id).equals("陪产假")) {
					if (maxDate - day >= number) {
						salaryService.touppmaternity(id, year, month, number);
					} else {
						salaryService.touppmaternity(id, year, month, maxDate - day);
						salaryService.touppmaternity(id, year, month + 1, number - (maxDate - day));
					}

				} else {
					if (maxDate - day >= number) {
						salaryService.toupannualleave(id, year, month, number);
					} else {
						salaryService.toupannualleave(id, year, month, maxDate - day);
						salaryService.toupannualleave(id, year, month + 1, number - (maxDate - day));
					}

				}
			}
		}
	}
	
	@Scheduled(cron = "1 0 2 1 * ?")
	public void penaltyMoney(){
		
    	
    	timerTaskService.penaltyMoney();
	}
}
