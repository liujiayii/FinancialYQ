package com.fendo.controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fendo.entity.BranchOffice;
import com.fendo.entity.Income;
import com.fendo.entity.Staff;
import com.fendo.entity.WithDrawMoney;
import com.fendo.entity.salesJON;
import com.fendo.service.StaffService;
import com.fendo.service.WithDrawMoneyService;
import com.fendo.utils.DesUtil;
import com.fendo.utils.Switch;
import com.shove.security.Encrypt;

import net.sf.json.JSONObject;
/**
 * 提现手续费
 * @author han
 *
 * @createDate 2018年9月12日-上午9:06:05
 */

@Component

@Lazy(false)
@Controller
public class WithDrawMoneyController {
	@Autowired
	WithDrawMoneyService withdrawmoneyService;
	
	@Autowired
	StaffService staffService;

	/**
	 * 定时向数据库导入提现手续费数据
	 *  
	 * 每个月1号凌晨0零点来执行
	 */
	@Scheduled(cron = "0 00 00 01 * ?")
	@RequestMapping("/addWithDrawMoney")
	@ResponseBody
	public void addWithDrawMoney() {
		String url = "www.ouyepuhui.com/query/withdrawMoney";
	
		String jsonStr = loadJson(url);
		  String stt=Encrypt.decrypt3DES(jsonStr, "fbetb4rJnLUUPVSx");
	
		String c = stt.substring(10, stt.length() - 2);
			if(c!=""&&c!=null){
		String[] atr = c.split("},");
		String s;
	
		for (int a = 0; a < atr.length; a++) {
			s = atr[a] + "}";
			if (a == atr.length - 1) {
				s = atr[a];
			}
		System.out.println("s"+s);
		
			JSONObject jsonObject = JSONObject.fromObject(s);
		
			WithDrawMoney withdrawmoney = (WithDrawMoney) JSONObject.toBean(jsonObject, WithDrawMoney.class);
		
			int money = withdrawmoneyService.addWithDrawMoney(withdrawmoney);
			
		}
		}
	}

	/**
	 * 获取json
	 * 
	 * @param url
	 * @return
	 */
	public  String loadJson(String url) {
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
	 * 跳提现手续费页面
	 * 
	 * @return
	 */
	@RequestMapping("/toWithDraw")
	public ModelAndView toWithDraw() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("withdrawMoney");
		return mv;
	}

	/**
	 * 查询提现手续费
	 * 
	 * @return
	 */
	@RequestMapping("/financialWithDraw")
	@ResponseBody
	public List<WithDrawMoney> findAll() {
	
		List<WithDrawMoney> withdrawList = withdrawmoneyService.findAll();
		if (withdrawList != null) {
		
		}
		return withdrawList;
	}

	/**
	 * 提现手续费查看详情
	 * 
	 * @param withdrawmoney
	 * @param id
	 * @return
	 */
	@RequestMapping("/ShowWithDrawMoney")
	public ModelAndView ShowWithDrawMoney(WithDrawMoney withdrawmoney, long id) {
		ModelAndView mv = new ModelAndView();
		WithDrawMoney withdrawmoneys = withdrawmoneyService.findId(id);
	

		mv.addObject("withdrawmoney", withdrawmoneys);
	
		mv.setViewName("ShowWithDrawMoney");

		return mv;
	}

	/**
	 * 打印提现手续费
	 * 
	 * @param withdrawmoney
	 * @param id
	 * @return
	 */
	@RequestMapping("/PrintWithDrawMoney")
	public ModelAndView PrintWithDrawMoney(WithDrawMoney withdrawmoney, long id) {
		ModelAndView mv = new ModelAndView();
		withdrawmoney = withdrawmoneyService.findId(id);
		
		// 时间格式转换
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = sformat.format(withdrawmoney.getTime());
		BigDecimal moneys = withdrawmoney.getMoney();

		//BigDecimal numberOfMoney = new BigDecimal(moneys);
		String s = Switch.number2CNMontrayUnit(moneys);
		
		mv.setViewName("PrintWithDrawMoney");
		mv.addObject("strDate", strDate);
		mv.addObject("withdrawmoney", withdrawmoney);
		mv.addObject("s", s.toString());

		return mv;

	}
	
	
	
	
	
	 @Scheduled(cron = "0 20 00 01 * ?")
	 @ResponseBody
	 public void toUpdateSalary() throws Exception  {
		
		 WithDrawMoneyController w=new WithDrawMoneyController();
			String str=w.loadJson("https://www.ouyepuhui.com/front/deduct/scanSales");
		
			System.out.println(str+"str");
            String stt=Encrypt.decrypt3DES(str, "fbetb4rJnLUUPVSx");
            		/*DesUtil.decrypt(str, "fbetb4rJnLUUPVSx");*/
            System.out.println("stt"+stt);
			String c = stt.substring(13, stt.length() - 2);
			System.out.println(c);
			String[] atr = c.split("},");
			String s;
		
			for (int a = 0; a < atr.length; a++) {
				s = atr[a] + "}";
				if (a == atr.length - 1) {
					s = atr[a];
				}
				
			
				JSONObject jsonObject = JSONObject.fromObject(s);
			
				salesJON string = (salesJON) JSONObject.toBean(jsonObject, salesJON.class);
			
				System.out.println("自信"+string);
				Staff s1=new Staff();
				
				s1.setSalesid(string.getId());
				s1.setName(string.getSale_name());
				s1.setPhone(string.getSale_mobile());
				
				int app=staffService.toUpdateSalesId(s1);
				
				
	 }
	 }	
	
}
