package com.fendo.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fendo.entity.ServiceMoney;
import com.fendo.service.ServiceMoneyService;
import com.fendo.utils.Switch;
import com.shove.security.Encrypt;

import net.sf.json.JSONObject;

/**
 * 
 * @author han
 *
 * @createDate 2018年8月23日-上午11:14:04
 */
@Component

@Lazy(false)
@Controller
public class ServiceMoneyController {
	@Autowired
	ServiceMoneyService servicemoneyService;

	/**
	 * 每天 23:56 定事项数据库导入服务费数据
	 */
	@Scheduled(cron = "59 59 23 * * ?")
	public void addServiceMoney() {
		
		String url = "www.ouyepuhui.com/query/costList";
	
		String jsonStr = loadJson(url);
		  String stt=Encrypt.decrypt3DES(jsonStr, "fbetb4rJnLUUPVSx");
		  if(stt.length()>10){
		
		String c = stt.substring(10, stt.length() - 2);
		
		if(c!=""&&c!=null){
			
		
		String[] atr = c.split("},");
		String s;
	
		for (int a = 0; a < atr.length; a++) {
			s = atr[a] + "}";
			if (a == atr.length - 1) {
				s = atr[a];
			}
		

			JSONObject jsonObject = JSONObject.fromObject(s);
			
			ServiceMoney servicemoneys = (ServiceMoney) JSONObject.toBean(jsonObject, ServiceMoney.class);
			
			int k = servicemoneyService.addServiceMoney(servicemoneys);
		
			
		}
		}
		  }
	}

	/**
	 * 获取json
	 * 
	 * @param url
	 * @return
	 */
	private String loadJson(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL urlObject = new URL(url);
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 跳转到服务费页面
	 */
	@RequestMapping("/toServiceMoney")
	public ModelAndView toServiceMoney() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("serviceMoney");
		return mv;
	}

	/**
	 * 查询服务费
	 */
	@RequestMapping("/financialServiceMoney")
	@ResponseBody
	public List<ServiceMoney> findAlls() {	
		List<ServiceMoney> servicemoney = servicemoneyService.findAlls();		
		return servicemoney;
	}

	/**
	 * @author zhoujiaxin 根据当前页数查询查询本页服务费数据并返回到打印服务费页面
	 */
	/**
	 * 根据当前页数查询查询本页服务费数据并返回到打印服务费页面
	 * 
	 * @param model
	 * @param currentpage
	 * @return
	 */
	@RequestMapping("/financialServiceMoneyLimitFive")
	public String financialServiceMoneyLimitFive(Model model, Integer currentpage) {
		
		List<ServiceMoney> servicemoney = servicemoneyService.findServiceMoneyLimitFive(currentpage);		
		BigDecimal moneys = new BigDecimal("0.00");
		String strDate = "";		
		for (int i = 0; i < servicemoney.size(); i++) {	
			moneys = moneys.add(servicemoney.get(i).getMoney());		
			SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
			strDate = sformat.format(servicemoney.get(i).getTime());
		}		
		//转换成大写		
		String s = Switch.number2CNMontrayUnit(moneys);		
		model.addAttribute("moneys", moneys);
		model.addAttribute("servicemoney", servicemoney);
		model.addAttribute("strDate", strDate);
		model.addAttribute("s", s.toString());
		return "PrintServiceMoney";
	}
	

	/**
	 * @description 
	 *
	 * @param id
	 * @return
	 * @author lichangchun
	 * @createDate
	 */
	@RequestMapping("/todeleteSpend")
	
	public String todeleteSpend(long id){
		System.out.println("id"+id);
		
		servicemoneyService.deletePasssr(id);
		
		return "financialSpending";
		
	}

	/**
	 * 服务费查看详情
	 * 
	 * @param servicemoney
	 * @param id
	 * @return
	 */
	@RequestMapping("/ShowServiceMoney")
	public ModelAndView ShowServiceMoney(ServiceMoney servicemoney, long id) {
		ModelAndView mv = new ModelAndView();
		servicemoney = servicemoneyService.findIds(id);		
		mv.addObject("servicemoney", servicemoney);	
		mv.setViewName("ShowServiceMoney");
		return mv;
	}
}
