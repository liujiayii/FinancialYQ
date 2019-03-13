
package com.fendo.controller;

import java.io.IOException;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthScrollBarUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.OSSClient;
import com.fendo.entity.BranchOffice;
import com.fendo.entity.DaNumber;
import com.fendo.entity.Finance;
import com.fendo.entity.Income;
import com.fendo.entity.SalesMan;
import com.fendo.entity.Spend;
import com.fendo.service.BranchOfficeService;
import com.fendo.service.FinanceService;
import com.fendo.service.NewStandardService;
import com.fendo.service.SalesmanService;
import com.fendo.service.SpendService;
import com.fendo.utils.Switch;
import com.fendo.utils.UploadFile;

@Controller
public class FinanceController {

	@Autowired
	private FinanceService financeService;
	@Autowired
	private BranchOfficeService branchofficeService;
	@Autowired
	private SalesmanService salesmanService;
	@Autowired
	private SpendService spendService;

	/**
	 * 风控查询所有列表
	 * 
	 * @return list
	 * 
	 */
	@RequestMapping("/financeList")
	@ResponseBody
	public List<Finance> getfinance(HttpServletRequest request) {

		List<Finance> list = financeService.getFinance();
		if (list != null) {
			for (int a = 0; a < list.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(list.get(a).getArea());
				list.get(a).setArea(Listcode.get(0).getArea());
			}

			return list;
		}
		request.setAttribute("list", list);
		return list;
	}

	/**
	 * 财务查询所有列表
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月11日 下午4:24:46
	 * @author liuhj
	 */
	@RequestMapping("/financeListByCw")
	@ResponseBody
	public List<Finance> getFinanceByCw(HttpServletRequest request) {
		List<Finance> list = financeService.getFinanceByCw();
		if (list != null) {
			for (int a = 0; a < list.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(list.get(a).getArea());
				list.get(a).setArea(Listcode.get(0).getArea());
			}

			return list;
		}
		request.setAttribute("list", list);
		return list;
	}

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping("/toShowAll")
	public String toAll() {
		return "home_page";
	}

	/**
	 * 风控续贷添加
	 * 
	 * @return
	 */
	@RequestMapping("/addFinance")
	public ModelAndView findArea() {

		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		mv.setViewName("Risk_control");

		mv.addObject("branchOfficeList", branchOfficeList);

		return mv;
	}

	/**
	 *
	 * @Title: findArea1
	 * 
	 * @description: 新标添加
	 *
	 * @param @return
	 * 
	 * @return ModelAndView
	 *
	 * 
	 * @author HanMeng
	 * @createDate 2018年9月14日-下午3:06:43
	 */
	@RequestMapping("/addFinances")
	public ModelAndView findArea1() {

		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println(" 新标成功获取到地址列表");
		}

		mv.setViewName("NewStandard");
		mv.addObject("branchOfficeList", branchOfficeList);

		return mv;
	}

	/**
	 * 
	 *
	 * @Title: findAll
	 * 
	 * @description: 查询所有列表
	 *
	 * @param request
	 * @param id
	 * @return
	 * 
	 * @return List<Finance>
	 *
	 * @author HanMeng
	 * @createDate 2018年9月14日-下午3:14:30
	 */
	@RequestMapping("/toFindAllType")
	@ResponseBody

	public List<Finance> findAll(HttpServletRequest request, String id) {

		List<Finance> financeList = financeService.findAll();

		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());

				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 跳转到显示已审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/toShowCheked")
	public String toChecked() {
		return "The_approved";
	}

	/**
	 * 风控显示已审核列表
	 * 
	 * @return
	 * 
	 */
	@RequestMapping("/toFindChecked")
	@ResponseBody
	public List<Finance> findChecked(HttpServletRequest request) {

		List<Finance> finList = financeService.findChecked();
		request.setAttribute("finList", finList);
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 财务显示已审核列表
	 * 
	 * @return
	 * 
	 */
	@RequestMapping("/toFindCheckedcw")
	@ResponseBody
	public List<Finance> findCheckedcw(HttpServletRequest request) {

		List<Finance> finList = financeService.findCheckedcw();
		request.setAttribute("finList", finList);
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 跳转到未审核页面
	 * 
	 * @return
	 */
	@RequestMapping("/toShowUnchecked")
	public String toUnchecked() {
		return "unaudited";
	}

	/**
	 * 显示未审核列表
	 * 
	 * @return
	 */
	@RequestMapping("/toFindUnchecked")
	@ResponseBody
	public List<Finance> findUnchecked(HttpServletRequest request) {
		List<Finance> finList = financeService.findUnchecked();

		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 查询未审核已提交的
	 * 
	 * @param session
	 * @param request
	 * @return
	 */

	@RequestMapping("/toFindUncheckSubmit")
	@ResponseBody
	public List<Finance> findUncheckSubmit(HttpSession session, HttpServletRequest request) {
		List<Finance> finList = financeService.findUncheckSubmit();
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}
		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 查询未审核仅保存
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/toFindUncheckSave")
	@ResponseBody
	public List<Finance> findUncheckSave(HttpSession session, HttpServletRequest request) {
		List<Finance> finList = financeService.findUncheckSave();

		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 财务填写显示未审核，风控提交未审核
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月11日 下午5:58:26
	 * @author liuhj
	 */
	@RequestMapping("/toFindCwUncheck")
	@ResponseBody
	public List<Finance> findCwUnchecked(HttpSession session, HttpServletRequest request) {
		List<Finance> finList = financeService.findCwUnchecked();

		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 跳转到已打回页面
	 * 
	 * @return
	 */
	@RequestMapping("/toShowBeenBack")
	public String toBeenBack() {
		return "been_back";
	}

	/**
	 * 显示已打回列表（风控）
	 * 
	 * @return
	 */
	@RequestMapping("/toFindBeenBack")
	@ResponseBody
	public List<Finance> findBeenBack(HttpServletRequest request) {
		List<Finance> finList = financeService.findBeenBack();
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 显示已打回列表（财务）
	 * 
	 * @return
	 */
	@RequestMapping("/toFindBeenBackcw")
	@ResponseBody
	public List<Finance> findBeenBackcw(HttpServletRequest request) {
		List<Finance> finList = financeService.findBeenBackcw();
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {

				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 跳转到已作废页面
	 * 
	 * @return
	 */
	@RequestMapping("/toShowBeenVoid")
	public String toBeenVoid() {
		return "invalid";
	}

	/**
	 * 财务显示已作废页面
	 * 
	 * @return
	 */
	@RequestMapping("/toFindBeenVoid")
	@ResponseBody
	public List<Finance> findBeenVoid(HttpServletRequest request) {
		List<Finance> finList = financeService.findBeenVoid();
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * 风控显示已作废列表
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月13日 下午3:01:52
	 * @author liuhj
	 */
	@RequestMapping("/toFindBeenVoidFk")
	@ResponseBody
	public List<Finance> findBeenVoidFk(HttpServletRequest request) {
		List<Finance> finList = financeService.findBeenVoidFk();
		if (finList != null) {
			for (int a = 0; a < finList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(finList.get(a).getArea());
				finList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("finList", finList);
		return finList;
	}

	/**
	 * @author han 风控添加 续贷
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toAddfinance", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toAddFinance(@RequestBody Finance finance, HttpSession session) {
		System.out.println("hehehehehe");
		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			// 风控填表设置状态为0
			finance.setState(0);
			int rows = financeService.addFinance(finance);

			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * @author han 风控添加 新标
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toaddNewstandard", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toaddNewstandard(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			// 风控添加状态为0
			finance.setState(0);
			int rows = financeService.addFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * @author han 续贷修改
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public ModelAndView toUpdate(Finance finance, Long id) {

		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println("续贷成功获取到地址列表");
		}
		finance = financeService.findById(id);

		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		mv.addObject("finance", finance);

		mv.setViewName("updateControl");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 新标修改
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdates")
	public ModelAndView toUpdates(Finance finance, Long id) {

		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println("新标成功获取到地址列表");
		}

		finance = financeService.findById(id);

		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		mv.addObject("finance", finance);

		mv.setViewName("updateControlX");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 风控修改
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toUpdateFinance", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toUpdateFinance(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());

		ResultInfo result = new ResultInfo();

		try {
			int rows = financeService.updateFinance(finance);

			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!!";
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}

	}

	@RequestMapping("/toFinanceManager")
	public String toUpdateStatus() {
		return "financial_management";
	}

	/**
	 * 修改是否逾期或提前还款
	 * 
	 * @param finance
	 * @return
	 */
	@RequestMapping(value = "/toUpdateFinanceStatus", method = RequestMethod.POST)

	@ResponseBody
	public ResultInfo toUpdateManager(@RequestBody Finance finance) {
		ResultInfo result = new ResultInfo();
		try {
			int rows = financeService.updateFinanceStatus(finance);
			if (rows >= 1) {
				System.out.println("修改成功");
			}
			result.code = 0;
			result.msg = "successfully!!";
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
		}
		return result;
	}

	/**
	 * 异步判断标号
	 * 
	 * @Title FinanceController.java
	 * @time 2018年8月22日 下午2:52:27
	 * @author liuhj
	 */
	@RequestMapping(value = "/findGrade", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toFindGrade(HttpServletRequest request, @RequestParam String grade) {
		ResultInfo result = new ResultInfo();

		try {
			String grade1 = request.getParameter("grade");
			Finance finance = financeService.findGrade(grade1);
			System.out.println(">>>>");
			if (finance != null) {

				result.code = 0;
				result.msg = "该标号存在";

			} else {

				result.code = -1;
				result.msg = "该标号不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * @author 续贷查看详情页面
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toShowDetails")
	public ModelAndView toShowDetails(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {

		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("finance", finance);

		mv.setViewName("showDetails");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * @author HAN 新标查看详情页面
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toShowDetails1")
	public ModelAndView toShowDetails1(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {

		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("finance", finance);

		mv.setViewName("showDetails1");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 续贷审核页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Risk_control_loansXD")
	public ModelAndView toCheckxd(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("XD成功获取到地址列表");
		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		if (finance.getMoney() != null) {
			BigDecimal money = finance.getMoney();
			finance.setMoney(money);
		}

		// 利息
		if (finance.getInterest() != null) {

			BigDecimal interest = finance.getInterest();
			// double interest = interestMoney.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setInterest(interest);
		}
		// 服务费
		if (finance.getServiceMoney() != null) {

			BigDecimal serviceMoney = finance.getServiceMoney();
			// double serviceMoney = service.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setServiceMoney(serviceMoney);
		}
		// 房产
		if (finance.getHouseProperty() != null) {

			BigDecimal houseProperty = finance.getHouseProperty();
			// double houseProperty = houses.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setHouseProperty(houseProperty);
		}
		// 档案管理
		if (finance.getArchivesMoney() != null) {

			BigDecimal archivesMoney = finance.getArchivesMoney();
			// double archivesMoney = archives.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setArchivesMoney(archivesMoney);
		}
		// 垫资费
		if (finance.getCapitalMoney() != null) {

			BigDecimal capitalMoney = finance.getCapitalMoney();
			// double capitalMoney = capital.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setCapitalMoney(capitalMoney);
		}
		// 过账费
		if (finance.getAccount() != null) {

			BigDecimal account = finance.getAccount();
			// double account = accountM.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setAccount(account);
		}
		// 实地费
		if (finance.getLandMoney() != null) {

			BigDecimal landMoney = finance.getLandMoney();
			// double landMoney = land.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setLandMoney(landMoney);
		}
		// GPS安装
		if (finance.getGpsMoney() != null) {

			BigDecimal gpsMoney = finance.getGpsMoney();
			// double gpsMoney = gps.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setGpsMoney(gpsMoney);
		}
		// 停车费
		if (finance.getStopMoney() != null) {

			BigDecimal stopMoney = finance.getStopMoney();
			// double stopMoney = stop.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setStopMoney(stopMoney);
		}
		// 进抵费
		if (finance.getJindiMoney() != null) {

			BigDecimal jindiMoney = finance.getJindiMoney();
			// double jindiMoney = jindi.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setJindiMoney(jindiMoney);
		}
		// 查档费
		if (finance.getFileMoney() != null) {

			BigDecimal fileMoney = finance.getFileMoney();
			// double fileMoney = files.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setFileMoney(fileMoney);
		}
		// 他项费
		if (finance.getElseMoney() != null) {

			BigDecimal elseMoney = finance.getElseMoney();
			// double elseMoney = others.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setElseMoney(elseMoney);
		}
		if (finance.getTime() == null) {
			System.out.println("没有获取到时间");
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("finance", finance);

		mv.setViewName("Risk_control_loansXD");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 新标审核页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Risk_control_loansXB")
	public ModelAndView toCheckxb(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println("XB成功获取到地址列表");
		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 金额
		// BigDecimal moneys = new BigDecimal("0.00");
		if (finance.getMoney() != null) {
			BigDecimal money = finance.getMoney();
			finance.setMoney(money);
		}

		// 利息
		if (finance.getInterest() != null) {

			BigDecimal interest = finance.getInterest();
			// double interest = interestMoney.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setInterest(interest);
		}
		// 服务费
		if (finance.getServiceMoney() != null) {

			BigDecimal serviceMoney = finance.getServiceMoney();
			// double serviceMoney = service.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setServiceMoney(serviceMoney);
		}
		// 房产
		if (finance.getHouseProperty() != null) {

			BigDecimal houseProperty = finance.getHouseProperty();
			// double houseProperty = houses.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setHouseProperty(houseProperty);
		}
		// 档案管理
		if (finance.getArchivesMoney() != null) {

			BigDecimal archivesMoney = finance.getArchivesMoney();
			// double archivesMoney = archives.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setArchivesMoney(archivesMoney);
		}
		// 垫资费
		if (finance.getCapitalMoney() != null) {

			BigDecimal capitalMoney = finance.getCapitalMoney();
			// double capitalMoney = capital.setScale(2,
			// BigDecimal.ROUND_DOWN).doubleValue();
			finance.setCapitalMoney(capitalMoney);
		}
		// 过账费
		if (finance.getAccount() != null) {

			BigDecimal account = finance.getAccount();
			finance.setAccount(account);
		}
		// 实地费
		if (finance.getLandMoney() != null) {
			BigDecimal landMoney = finance.getLandMoney();
			finance.setLandMoney(landMoney);
		}
		// GPS安装
		if (finance.getGpsMoney() != null) {

			BigDecimal gpsMoney = finance.getGpsMoney();
			finance.setGpsMoney(gpsMoney);
		}
		// 停车费
		if (finance.getStopMoney() != null) {

			BigDecimal stopMoney = finance.getStopMoney();
			finance.setStopMoney(stopMoney);
		}
		// 进抵费
		if (finance.getJindiMoney() != null) {

			BigDecimal jindiMoney = finance.getJindiMoney();
			finance.setJindiMoney(jindiMoney);
		}
		// 查档费
		if (finance.getFileMoney() != null) {

			BigDecimal fileMoney = finance.getFileMoney();
			finance.setFileMoney(fileMoney);
		}
		// 他项费
		if (finance.getElseMoney() != null) {
			BigDecimal elseMoney = finance.getElseMoney();
			finance.setElseMoney(elseMoney);
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("finance", finance);

		mv.setViewName("Risk_control_loansXB");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 新标打印页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/printXB")
	public ModelAndView printXB(Finance finance, Long id) {

		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());

		try {
			SalesMan salesName = salesmanService.getSalesNameById(finance.getS_id());

			if (salesName != null) {
				String saleName = salesName.getName();

				mv.addObject("saleName", saleName);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 时间格式转换
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = sformat.format(finance.getTime());

		finance.setArea(Listcode.get(0).getArea());

		// 停车费+（平台服务费+利息 ）+点位费+Gps安装费+档案管理费 小写转大写
		BigDecimal stopMoney = new BigDecimal("0.00");
		BigDecimal interest = new BigDecimal("0.00");
		BigDecimal serviceMoney = new BigDecimal("0.00");
		BigDecimal gpsMoney = new BigDecimal("0.00");
		BigDecimal archivesMoney = new BigDecimal("0.00");

		if (finance.getStopMoney() != null) {
			stopMoney = finance.getStopMoney();
		}
		if (finance.getInterest() != null) {
			interest = finance.getInterest();
		}
		if (finance.getServiceMoney() != null) {
			serviceMoney = finance.getServiceMoney();
		}
		if (finance.getGpsMoney() != null) {
			gpsMoney = finance.getGpsMoney();
		}
		if (finance.getArchivesMoney() != null) {
			archivesMoney = finance.getArchivesMoney();
		}
		BigDecimal money = stopMoney.add(interest).add(serviceMoney).add(gpsMoney).add(archivesMoney);
		System.out.println("WOSHI BigDecimal " + money);
		// BigDecimal numberOfMoney = 1.subtract(subtrahend);
		String s = Switch.number2CNMontrayUnit(money);

		if (finance.getServiceMoney() != null) {
			serviceMoney = finance.getServiceMoney();
		}

		String service = Switch.number2CNMontrayUnit(serviceMoney);

		List<DaNumber> list = financeService.getNumber();

		int n = list.get(0).getNumber();

		mv.setViewName("printXB");
		mv.addObject("strDate", strDate);
		mv.addObject("branchOfficeList", branchOfficeList);

		mv.addObject("number", n);
		mv.addObject("finance", finance);
		mv.addObject("s", s.toString());
		mv.addObject("service", service.toString());

		return mv;
	}

	/**
	 * 续贷打印
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/printXD")
	public ModelAndView printXD(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		try {
			SalesMan salesName = salesmanService.getSalesNameById(finance.getS_id());

			if (salesName != null) {
				String saleName = salesName.getName();

				mv.addObject("saleName", saleName);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BigDecimal interest = new BigDecimal("0.00");
		BigDecimal serviceMoney = new BigDecimal("0.00");
		BigDecimal archivesMoney = new BigDecimal("0.00");

		if (finance.getInterest() != null) {
			interest = finance.getInterest();
		}
		if (finance.getServiceMoney() != null) {
			serviceMoney = finance.getServiceMoney();
		}
		if (finance.getArchivesMoney() != null) {
			archivesMoney = finance.getArchivesMoney();
		}
		// （平台服务费+利息 ）+点位费+档案管理费 小写转大写
		BigDecimal money = interest.add(serviceMoney)
				.add(archivesMoney) /* + serviceMoney + archivesMoney */;

		// BigDecimal numberOfMoney = new BigDecimal(money);
		String two = Switch.number2CNMontrayUnit(money);

		List<DaNumber> list = financeService.getNumber();

		int n = list.get(0).getNumber();

		mv.addObject("number", n);
		mv.addObject("finance", finance);

		mv.setViewName("printXD");

		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("two", two.toString());
		return mv;
	}

	/**
	 * 新标风控审核
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toCheckXB", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toCheckXB(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());

		ResultInfo result = new ResultInfo();

		try {
			int rows = financeService.updateFinance(finance);

			if (rows == 0) {

				result.code = 0;
				result.msg = "successfully!!";
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}

	}

	/**
	 * 审核通过
	 * 
	 * @param f
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toCheckTG(@RequestBody Finance finance, HttpSession session, ServletResponse servletResponse)
			throws IOException {

		ResultInfo result = new ResultInfo();
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if ((String) session.getAttribute("names") == null) {
			response.sendRedirect("/toLogin.action");
		}
		finance.setAuditor((String) session.getAttribute("name"));
		finance.setRole((String) session.getAttribute("roletype"));
		try {
			System.out.println("finance" + finance);
			int rows = financeService.updatePass(finance);
			System.out.println("finance*********" + finance);
			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!!";
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}

	}

	/**
	 * 审核打回
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateBack", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toCheckDH(@RequestBody Finance finance, HttpSession session) {
		ResultInfo result = new ResultInfo();

		try {
			int rows = financeService.updateBack(finance);

			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!!";
				System.out.println("打回");
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}

	}

	/**
	 * 打印
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/togetNumber", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo togetNumber(@RequestBody DaNumber number, HttpSession session) {

		ResultInfo result = new ResultInfo();

		try {
			int rows = financeService.toupNumber(number);
			System.out.println("rows:" + rows);
			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!!";
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}

	}

	/**
	 * 审核作废
	 * 
	 * @param f
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/toNullify", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo toNullify(@RequestBody Finance finance, HttpSession session) {
		ResultInfo result = new ResultInfo();
		try {
			int rows = financeService.toNullify(finance);

			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!!";
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 根据标号搜索
	 * 
	 * @param grade
	 * @param request
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/toFindGrades", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public ResultInfo findGrades(@RequestParam String grade,
	 * HttpServletRequest request) { ResultInfo result = new ResultInfo(); try {
	 * String grades = request.getParameter("grade"); Finance finance =
	 * financeService.findGrade(grades); if (finance != null) { result.code = 0;
	 * result.msg = "标号已存在"; } else { result.code = -1; result.msg = "标号不存在"; }
	 * } catch (Exception e) { e.printStackTrace(); } return result;
	 * 
	 * }
	 */
	/**
	 * 风控根据标号搜索
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGrades")
	@ResponseBody
	public List<Finance> findGrades(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGrades(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 财务根据标号搜索
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradescw")
	@ResponseBody
	public List<Finance> findGradescw(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradescw(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 风控通过标号搜索已审核列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradeByChecked")
	@ResponseBody
	public List<Finance> findGradeByChecked(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByChecked(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 财务通过标号搜索已审核列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradeByCheckedcw")
	@ResponseBody
	public List<Finance> findGradeByCheckedcw(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByCheckedcw(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 风控通过标号搜索未审核列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradeByUnChecked")
	@ResponseBody
	public List<Finance> findGradeByUnChecked(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByUnChecked(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 财务通过标号搜索未审核列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradeByUnCheckedCw")
	@ResponseBody
	public List<Finance> findGradeByUnCheckedCw(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByUnCheckedCw(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 风控通过标号搜索已打回列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradeByBeenback")
	@ResponseBody
	public List<Finance> findGradeByBeenback(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByBeenback(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 财务通过标号搜索已打回列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/toFindGradeByBeenbackCw")
	@ResponseBody
	public List<Finance> findGradeByBeenbackCw(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByBeenBackCw(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 风控通过标号搜索已作废列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/tofindGradeByBeenvoidFk")
	@ResponseBody
	public List<Finance> findGradeByBeenvoidFk(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByBeenvoidFk(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 财务通过标号搜索已作废列表
	 * 
	 * @param grade
	 * @return
	 */
	@RequestMapping("/tofindGradeByBeenvoidCw")
	@ResponseBody
	public List<Finance> findGradeByBeenvoidCw(String grade, HttpServletRequest request) {
		String grades = "%" + grade + "%";
		List<Finance> financeList = financeService.findGradeByBeenvoidCw(grades);
		if (financeList != null) {
			for (int a = 0; a < financeList.size(); a++) {
				List<BranchOffice> Listcode = branchofficeService.findArea2(financeList.get(a).getArea());
				financeList.get(a).setArea(Listcode.get(0).getArea());
			}

		}
		request.setAttribute("financeList", financeList);
		return financeList;
	}

	/**
	 * 财务本金添加
	 * 
	 * @param finance
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/principalcw", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo principalcw(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();

		try {
			// 财务填写
			finance.setState(1);
			int rows = financeService.addFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 财务本金添加页面显示
	 * 
	 * @return
	 */
	@RequestMapping("/toPrincipalcw")
	public ModelAndView toPrincipalcw(HttpSession session) {

		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println(" 财务本金成功获取到地址列表");
		}

		mv.setViewName("principalcw");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("riskman", session.getAttribute("name"));

		return mv;
	}

	/**
	 * 风控本金添加
	 * 
	 * @param finance
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/principalfk", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo principalfk(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			// 风控添加
			finance.setState(0);
			int rows = financeService.addFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 风控本金添加
	 * 
	 * @return
	 */
	@RequestMapping("/toPrincipalfk")
	public ModelAndView toPrincipalfk() {

		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println(" 风控本金成功获取到地址列表");
		}

		mv.setViewName("principalfk");
		mv.addObject("branchOfficeList", branchOfficeList);

		return mv;
	}

	/**
	 * 跳转到风控本金修改页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdatefkbj")
	public ModelAndView toUpdatefkbj(Finance finance, Long id) {

		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println("风控本金成功获取到地址列表");
		}
		finance = financeService.findById(id);

		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		mv.addObject("finance", finance);

		mv.setViewName("updatefkbj");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 修改风控本金
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月11日 上午10:53:18
	 * @author liuhj
	 */
	@RequestMapping(value = "/updatefkbj", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updatefkbj(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());

		ResultInfo result = new ResultInfo();

		try {
			int rows = financeService.updateFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully!!";

			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 跳转到财务本金修改页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdatecwbj")
	public ModelAndView toUpdatecwbj(Finance finance, Long id) {

		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println("风控本金成功获取到地址列表");
		}
		finance = financeService.findById(id);

		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		mv.addObject("finance", finance);

		mv.setViewName("updatecwbj");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 修改财务本本金
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月11日 上午10:56:21
	 * @author liuhj
	 */
	@RequestMapping(value = "/updatecwbj", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updatecwbj(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());

		ResultInfo result = new ResultInfo();

		try {
			int rows = financeService.updateFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully!!";
			}
			return result;
		} catch (Exception e) {
			e.getStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 财务本金审核
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Checkcwbj")
	public ModelAndView Checkcwbj(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("Checkcwbj成功获取到地址列表");
		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		if (finance.getMaturityPlatform() != null) {
			BigDecimal maturityPlatform = finance.getMaturityPlatform();
			finance.setMaturityPlatform(maturityPlatform);
		}

		if (finance.getOverduePlatform() != null) {

			BigDecimal overduePlatform = finance.getOverduePlatform();

			finance.setOverduePlatform(overduePlatform);
		}
		if (finance.getPrepaymentPlatform() != null) {
			BigDecimal prepaymentPlatform = finance.getPrepaymentPlatform();
			finance.setPrepaymentPlatform(prepaymentPlatform);
		}
		if (finance.getRefinancePlatform() != null) {
			BigDecimal refinancePlatform = finance.getRefinancePlatform();
			finance.setRefinancePlatform(refinancePlatform);
		}
		if (finance.getTime() == null) {
			System.out.println("没有获取到时间");
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());
			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (finance.getReceiptType() == 1) {
			mv.addObject("receipttype", "续贷还平台");
		} else if (finance.getReceiptType() == 2) {
			mv.addObject("receipttype", "到期还平台");
		} else if (finance.getReceiptType() == 3) {
			mv.addObject("receipttype", "逾期还平台");
		} else if (finance.getReceiptType() == 4) {
			mv.addObject("receipttype", "提前还平台");
		}
		mv.addObject("finance", finance);
		mv.setViewName("Checkcwbj");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 风控本金审核
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Checkfkbj")
	public ModelAndView Checkfkbj(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("Checkfkbj成功获取到地址列表");
		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		if (finance.getMaturityPrincipal() != null) {
			BigDecimal maturityPrincipal = finance.getMaturityPrincipal();
			finance.setMaturityPrincipal(maturityPrincipal);
		}
		if (finance.getOverduePrincipal() != null) {

			BigDecimal overduePrincipal = finance.getOverduePrincipal();

			finance.setOverduePrincipal(overduePrincipal);
		}
		if (finance.getPrepaymentPrincipal() != null) {
			BigDecimal prepaymentPrincipal = finance.getPrepaymentPrincipal();
			finance.setPrepaymentPrincipal(prepaymentPrincipal);
		}
		if (finance.getRefinancePrincipal() != null) {
			BigDecimal refinancePrincipal = finance.getRefinancePrincipal();
			finance.setRefinancePrincipal(refinancePrincipal);
		}
		if (finance.getTime() == null) {
			System.out.println("没有获取到时间");
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());
			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("finance", finance);
		mv.setViewName("Checkfkbj");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 风控利息添加
	 * 
	 * @param finance
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/interestAddfk", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo interestAddfk(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			// 风控填写
			finance.setState(0);
			int rows = financeService.addFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 跳转风控利息添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toInterestAddfk")
	public ModelAndView toInterestAddfk() {

		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println(" 风控利息成功获取到地址列表");
		}

		mv.setViewName("interestAddfk");
		mv.addObject("branchOfficeList", branchOfficeList);

		return mv;
	}

	/**
	 * 财务利息添加
	 * 
	 * @param finance
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/interestAddcw", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo interestAddcw(@RequestBody Finance finance, HttpSession session) {

		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			// 财务填写
			finance.setState(1);
			int rows = financeService.addFinance(finance);
			if (rows >= 1) {
				result.code = 0;
				result.msg = "successfully";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
			return result;
		}
	}

	/**
	 * 跳转到财务利息添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toInterestAddcw")
	public ModelAndView toInterestAddcw() {
		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println(" 财务利息成功获取到地址列表");
		}
		mv.setViewName("interestAddcw");
		mv.addObject("branchOfficeList", branchOfficeList);

		return mv;
	}

	/**
	 * 跳转到修改财务利息页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdatecwlx")
	public ModelAndView toUpdatecwlx(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("财务利息成功获取到地址列表");
		}
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		mv.addObject("finance", finance);
		mv.setViewName("updatecwlx");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 修改财务利息
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月11日 上午11:02:24
	 * @author liuhj
	 */
	@RequestMapping(value = "/updatecwlx", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updatecwlx(@RequestBody Finance finance) {
		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			int rows = financeService.updateFinance(finance);
			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
		}
		return result;
	}

	/**
	 * 跳转修改风控利息页面
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/toUpdatefklx")
	public ModelAndView toUpdatefklx(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		if (branchOfficeList != null) {
			System.out.println("风控利息成功获取到地址列表");
		}
		finance = financeService.findById(id);
		System.out.println(id + "idqqqqqqqqqqqq");
		System.out.println(finance + "finance");
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		mv.addObject("finance", finance);
		mv.setViewName("updatefklx");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 修改风控利息
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月11日 上午11:03:08
	 * @author liuhj
	 */
	@RequestMapping(value = "/updatefklx", method = RequestMethod.POST)
	@ResponseBody
	public ResultInfo updatefklx(@RequestBody Finance finance) {
		List<BranchOffice> Listcode = branchofficeService.findArea3(finance.getArea());

		finance.setArea(Listcode.get(0).getArea_code());
		ResultInfo result = new ResultInfo();
		try {
			int rows = financeService.updateFinance(finance);
			if (rows >= 1) {

				result.code = 0;
				result.msg = "successfully!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.code = -1;
			result.msg = "error";
		}
		return result;
	}

	/**
	 * 审核风控利息
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Checkfklx")
	public ModelAndView Checkfklx(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("Checkfkbj成功获取到地址列表");
		}

		finance = financeService.findById(id);
		System.out.println(id + "idoooooooooooo");
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		if (finance.getInterest() != null) {
			BigDecimal interest = finance.getInterest();
			finance.setInterest(interest);
		}

		if (finance.getTime() == null) {
			System.out.println("没有获取到时间");
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());
			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("finance", finance);
		mv.setViewName("Checkfklx");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 审核财务利息
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Checkcwlx")
	public ModelAndView Checkcwlx(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		if (branchOfficeList != null) {
			System.out.println("Checkfkbj成功获取到地址列表");
		}

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		if (finance.getInterest() != null) {
			BigDecimal interest = finance.getInterest();
			finance.setInterest(interest);
		}

		if (finance.getTime() == null) {
			System.out.println("没有获取到时间");
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());
			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("finance", finance);
		mv.setViewName("Checkcwlx");
		mv.addObject("branchOfficeList", branchOfficeList);
		return mv;
	}

	/**
	 * 带数据跳到财务本金页面查看详情
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月7日 上午10:17:41
	 * @author liuhj
	 */
	@RequestMapping("/toShowPrincipalcw")
	public ModelAndView toShowPrincipalcw(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (finance.getReceiptType() == 1) {
			mv.addObject("receipttype", "续贷还平台");
		} else if (finance.getReceiptType() == 2) {
			mv.addObject("receipttype", "到期还平台");
		} else if (finance.getReceiptType() == 3) {
			mv.addObject("receipttype", "逾期还平台");
		} else if (finance.getReceiptType() == 4) {
			mv.addObject("receipttype", "提前还平台");
		}

		mv.setViewName("ShowPrincipalcw");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("finance", finance);
		return mv;

	}

	/**
	 * 带数据跳到风控本金页面查看详情
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月7日 上午10:17:41
	 * @author liuhj
	 */
	@RequestMapping("/toShowPrincipalfk")
	public ModelAndView toShowPrincipalfk(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("ShowPrincipalfk");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("finance", finance);
		return mv;

	}

	/**
	 * 带数据跳到风控利息页面查看详情
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月10日 下午1:59:21
	 * @author liuhj
	 */
	@RequestMapping("/toShowInterestfk")
	public ModelAndView toShowInterestfk(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("ShowInterestfk");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("finance", finance);
		return mv;

	}

	/**
	 * 带数据跳到财务利息页面查看详情
	 * 
	 * @Title FinanceController.java
	 * @time 2018年9月10日 下午1:59:11
	 * @author liuhj
	 */
	@RequestMapping("/toShowInterestcw")
	public ModelAndView toShowInterestcw(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();

		List<BranchOffice> branchOfficeList = branchofficeService.findArea();

		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("ShowInterestcw");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("finance", finance);
		return mv;

	}

	/**
	 * 财务本金打印
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Printcwbj")
	public ModelAndView Printcwbj(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());

		/*
		 * try { SalesMan salesName =
		 * salesmanService.getSalesNameById(finance.getS_id()); if (salesName !=
		 * null) { String saleName = salesName.getName();
		 * mv.addObject("saleName", saleName); } else { return null; } } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 本金
		BigDecimal refinancePrincipal = new BigDecimal("0.00");
		// 利息
		BigDecimal maturityPrincipal = new BigDecimal("0.00");

		if (finance.getRefinancePrincipal() != null) {
			refinancePrincipal = finance.getPrincipals();
		}
		if (finance.getMaturityPrincipal() != null) {
			maturityPrincipal = finance.getAccrual();
		}

		// 小写转大写
		BigDecimal money = refinancePrincipal.add(maturityPrincipal);

		String amount = Switch.number2CNMontrayUnit(money);
		List<DaNumber> list = financeService.getNumber();

		int n = list.get(0).getNumber();
		mv.addObject("area", Listcode.get(0).getArea());
		mv.addObject("number", n);
		mv.addObject("finance", finance);
		mv.setViewName("Printcwbj");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("amount", amount.toString());
		return mv;
	}

	/**
	 * 风控本金打印
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Printfkbj")
	public ModelAndView Printfkbj(Finance finance, Long id) {

		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());

		try {
			SalesMan salesName = salesmanService.getSalesNameById(finance.getS_id());

			if (salesName != null) {
				String saleName = salesName.getName();
				mv.addObject("saleName", saleName);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 续贷还平台
		BigDecimal refinancePlatform = new BigDecimal("0.00");
		// 到期还平台
		BigDecimal maturityPlatform = new BigDecimal("0.00");
		// 逾期还平台
		BigDecimal overduePlatform = new BigDecimal("0.00");
		// 提前还平台
		BigDecimal prepaymentPlatform = new BigDecimal("0.00");
		if (finance.getRefinancePlatform() != null) {
			refinancePlatform = finance.getRefinancePlatform();
		}
		if (finance.getMaturityPlatform() != null) {
			maturityPlatform = finance.getMaturityPlatform();
		}
		if (finance.getOverduePlatform() != null) {
			overduePlatform = finance.getOverduePlatform();
		}
		if (finance.getPrepaymentPlatform() != null) {
			prepaymentPlatform = finance.getPrepaymentPlatform();
		}
		// 小写转大写
		BigDecimal money = refinancePlatform.add(maturityPlatform).add(overduePlatform).add(prepaymentPlatform);

		String amount = Switch.number2CNMontrayUnit(money);
		List<DaNumber> list = financeService.getNumber();

		int n = list.get(0).getNumber();

		mv.addObject("number", n);
		mv.addObject("finance", finance);
		mv.setViewName("Printfkbj");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("amount", amount.toString());
		return mv;
	}

	/**
	 * 风控利息打印
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Printfklx")
	public ModelAndView Printfklx(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		try {
			SalesMan salesName = salesmanService.getSalesNameById(finance.getS_id());
			if (salesName != null) {
				String saleName = salesName.getName();
				mv.addObject("saleName", saleName);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 风控利息
		BigDecimal interest = new BigDecimal("0.00");
		if (finance.getInterest() != null) {
			interest = finance.getInterest();
		}
		// 小写转大写
		// BigDecimal money =
		// refinancePrincipal.add(maturityPrincipal).add(overduePrincipal).add(prepaymentPrincipal);
		String amount = Switch.number2CNMontrayUnit(interest);
		List<DaNumber> list = financeService.getNumber();

		int n = list.get(0).getNumber();

		mv.addObject("number", n);
		mv.addObject("finance", finance);
		mv.setViewName("Printfklx");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("amount", amount.toString());
		return mv;
	}

	/**
	 * 财务利息打印
	 * 
	 * @param finance
	 * @param id
	 * @return
	 */
	@RequestMapping("/Printcwlx")
	public ModelAndView Printcwlx(Finance finance, Long id) {
		ModelAndView mv = new ModelAndView();
		List<BranchOffice> branchOfficeList = branchofficeService.findArea();
		finance = financeService.findById(id);
		List<BranchOffice> Listcode = branchofficeService.findArea2(finance.getArea());
		finance.setArea(Listcode.get(0).getArea());
		try {
			SalesMan salesName = salesmanService.getSalesNameById(finance.getS_id());
			if (salesName != null) {
				String saleName = salesName.getName();
				mv.addObject("saleName", saleName);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 时间格式转换
		try {
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
			String strDate = sformat.format(finance.getTime());

			mv.addObject("strDate", strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 财务利息
		BigDecimal interests = new BigDecimal("0.00");
		if (finance.getInterests() != null) {
			interests = finance.getInterests();
			System.out.println("interests:" + interests);

		}
		// 小写转大写
		// BigDecimal money =
		// refinancePrincipal.add(maturityPrincipal).add(overduePrincipal).add(prepaymentPrincipal);
		String amount = Switch.number2CNMontrayUnit(interests);
		System.out.println("amount:" + amount);
		List<DaNumber> list = financeService.getNumber();

		int n = list.get(0).getNumber();

		mv.addObject("number", n);
		mv.addObject("finance", finance);
		mv.setViewName("Printcwlx");
		mv.addObject("branchOfficeList", branchOfficeList);
		mv.addObject("amount", amount.toString());
		return mv;
	}
	
	
	
	/**
	 * 添加支出信息
	 * 
	 * @param income
	 * @return
	 */
	
	@RequestMapping(value = "/toAddSpends", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> toAddIncome(@RequestBody Spend spend,HttpSession session) {
		System.out.println("----->>>"+spend.toString());
		//spend.setEntry_person(session.getAttribute("username").toString());
		Map<String,Object> map = spendService.toAddSpend(spend);
		return map;
	}
	
	@RequestMapping(value="/toAddSpendImg")
	@ResponseBody
	public Map<String,Object> toAddSpendImg(HttpServletRequest request,Long id){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> files = multipartRequest.getFiles("file");
		Map<String,Object> map = spendService.toAddSpend(id, files);
		return map;
	}
	
	
	
	
	
	
	
	
	
	

}