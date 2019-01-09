package com.fendo.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fendo.entity.DingJON;
import com.fendo.entity.Staff;
import com.fendo.service.StaffService;
import com.fendo.service.impl.StaffServiceImpl;
import com.fendo.utils.AuthHelper;
import com.fendo.utils.Env;
import com.fendo.utils.HttpRequestUtil;
import com.fendo.utils.UserService;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年9月27日 下午11:42:04
 */
public class AuthHelperTest {

	@Test
	public void testGetAccessToken()  {
		
		
		String accessToken;
		UserService us=new UserService();
		StaffService  staffService=new StaffServiceImpl();
		try {
			accessToken = AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
	
		
		// 2.0通过accessToken获取公司里各个部门的-部门名称（name）-部门ID（id）
		String getDepIdName = "https://oapi.dingtalk.com/department/list?access_token="+accessToken;
	
		net.sf.json.JSONObject jsonObjectDepList = HttpRequestUtil.doGet(getDepIdName);
		
		
		/*System.out.println(jsonObjectDepList);*/
		// 2.2通过department属性获取部门json对象数组
		net.sf.json.JSONArray DepArr = jsonObjectDepList.getJSONArray("department");
	
		Calendar cal = Calendar.getInstance();
		
	   	 int year = cal.get(Calendar.YEAR);
	   	  int month = cal.get(Calendar.MONTH)+1;
	   	  int day= cal.get(Calendar.DATE );
	   	String time;
	   	  for(int c=1;c<=day;c++){
	   		  if(c<10){
	   			time =year+"-"+month+"-0"+c+" "+"00:00:00";  
	   		  }else{
	   			 time=year+"-"+month+"-"+c+" "+"00:00:00";  
	   		  }
	   			String  workDateFrom  =time;
			String  workDateTo    =time;
			for(int a = 0; a < DepArr.size();a++){
				Map<String,Integer> map =   (Map<String, Integer>) DepArr.get(a);
				String value = map.get("id").toString();
				if(value.equals("1")){
					System.out.println();
				}else{
					String accessToken1=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
					
					JSONObject l=us.getDepartmentUser(accessToken1, value);
					com.alibaba.fastjson.JSONArray Dep = l.getJSONArray("userlist");
					System.out.println("尺寸"+Dep.size());
					for(int b=0;b<Dep.size();b++){
						@SuppressWarnings("unchecked")
						Map<String,String> map1 =   (Map<String, String>) Dep.get(b);
						String values = map1.get("userid");
						String v=map1.get("name");
						List<String>list=new ArrayList<String>();
							list.add(values);
							String recordUrl = "https://oapi.dingtalk.com/attendance/list?access_token="+accessToken;
						    Long offset = 0L;
						    Long limit = (long)1;
						    JSONObject jsonObject = new JSONObject();
						    jsonObject.put("userIdList", list);
						    jsonObject.put("workDateFrom", workDateFrom);
						    jsonObject.put("workDateTo", workDateTo);
						    jsonObject.put("offset", offset);
						    jsonObject.put("limit", limit);
						     net.sf.json.JSONObject jsonObject2 = HttpRequestUtil.doPost(recordUrl,jsonObject);
						    List<Staff>listStaff=staffService.getStaff(v);
						    if(listStaff.size()!=0){
							  
							  net.sf.json.JSONArray deps = jsonObject2.getJSONArray("recordresult");
							
							  List listmap=deps.toList(deps, DingJON.class);
							
							  if(listmap.size()!=0){
									 int p=listmap.get(0).toString().indexOf("locationResult=");
									 int p1=listmap.get(0).toString().indexOf("planId");
									 String state=listmap.get(0).toString().substring(p+15, p1-1);
									 if(state!="NotSigned"){
										 int num=staffService.toUpdateStaff(listStaff.get(0));
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
	
	@Test
	public void testGetJsapiTicket() throws Exception {
		
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		System.out.println("accessToken:"+accessToken);
		
		String ticket=AuthHelper.getJsapiTicket(accessToken);
		System.out.println("ticket:"+ticket);
	}
	
	
	@Test
	public void testLog4j() {

		
		
		
	}
	
}
