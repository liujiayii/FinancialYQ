package com.fendo.utils;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import  com.fendo.utils.AuthHelper;
import  com.fendo.utils.Env;
import  com.fendo.utils.User;
import  com.fendo.utils.UserService;
import  com.fendo.utils.HttpHelper;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年9月28日 上午10:09:34
 */
public class UserServiceTest {

	private static final String GET_USER_URL="https://oapi.dingtalk.com/user/get?access_token=ACCESSTOKEN&userid=USERID";
	
	@Test
	public void testCreateUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		
		User user=new User();
		user.setName("王子明");
		List<Long> departmentList=new ArrayList<Long>();
		departmentList.add(new Long(1));
		user.setDepartment(departmentList);
		user.setMobile("18771419627");
		
		
		UserService us=new UserService();
		String userId=us.createUser(accessToken, user);
		System.out.println("userId"+userId);
		
	}
	
	@Test
	public void testGetUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String userId="115016302321486058";
		
		UserService us=new UserService();
		us.getUser(accessToken, userId);
		
	}
	@Test
	public void getAttendance() throws Exception {
		   String dingDingAttendance = "https://oapi.dingtalk.com/attendance/list?access_token=db8fa6e0dc913bfd86a72d9a9ddf7709";
		   String url=GET_USER_URL.replace("ACCESSTOKEN", "db8fa6e0dc913bfd86a72d9a9ddf7709").replace("USERID", "115016302321486058");
System.out.println(url+"url");
			//2.发起GET请求，获取返回结果
			JSONObject jsonObject=HttpHelper.httpGet(url);
			String  workDateFrom  = "2018-08-06 00:11:11";
			String  workDateTo    = "2018-08-06 23:11:11";
			jsonObject.put("workDateFrom", workDateFrom);
		    jsonObject.put("workDateTo", workDateTo);
		  
		 
		 doPost(dingDingAttendance,jsonObject);//获取考勤记录
		
		   
		}
	@Test
	public void testGetDepartmentUser() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String departmentId="32323180";
		
		UserService us=new UserService();
		
		us.getDepartmentUser(accessToken, departmentId);
		
	}
	
	@Test
	public void testGetDepartmentUserDetail() throws Exception {
		String accessToken=AuthHelper.getAccessToken(Env.CORP_ID, Env.CORP_SECRET);
		String departmentId="32323180";//17257901918
		
		UserService us=new UserService();
		us.getDepartmentUser(accessToken, departmentId);
		
		
	}
	
	
	public void doPost(String requestUrl,JSONObject json){
	    HttpClient client = new DefaultHttpClient();
	    HttpPost post = new HttpPost(requestUrl);
	    post.setHeader("Content-Type", "application/json");
	    post.addHeader("Authorization", "Basic YWRtaW46");
	    String result = "";
	    try {
	        StringEntity s = new StringEntity(json.toString(), "utf-8");
	        s.setContentEncoding(new BasicHeader("contentType",
	                   "application/json"));
	        post.setEntity(s);
	        // 发送请求
	        HttpResponse httpResponse = client.execute(post);
	        // 获取响应输入流
	        InputStream inStream = httpResponse.getEntity().getContent();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
	        StringBuilder strber = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null){
	             strber.append(line + "\n");}
	        inStream.close();
	        result = strber.toString();
	        System.out.println(result);
	        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

	           System.out.println("请求服务器成功，做相应处理");

	        } else {
	          System.out.println("请求服务端失败");
	        }
	    } catch (Exception e) {
	        System.out.println("请求异常");
	        throw new RuntimeException(e);
	    }
	   
	}
}
