package com.fendo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fendo.utils.AttendanceQvo;
import com.fendo.utils.User;
import com.fendo.utils.HttpHelper;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017年9月28日 上午9:53:51
 */
public class UserService {

	private static final String CREATE_USER_URL="https://oapi.dingtalk.com/user/create?access_token=ACCESSTOKEN";
	private static final String GET_USER_URL="https://oapi.dingtalk.com/user/get?access_token=ACCESSTOKEN&userid=USERID";
	private static final String GET_DEPARTMENTUSER_URL="https://oapi.dingtalk.com/user/simplelist?access_token=ACCESSTOKEN&department_id=DEPARTMENTID";
	private static final String GET_DEPARTMENTUSERDETAIL_URL="https://oapi.dingtalk.com/user/list?access_token=ACCESSTOKEN&department_id=DEPARTMENTID";
	private static final String GET_USERINFO_BYCODE_URL="https://oapi.dingtalk.com/user/getuserinfo?access_token=ACCESSTOKEN&code=CODE";

	/**1.创建用户
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param user
	 * @return
	 * @throws Exception String
	 */
	public String createUser(String accessToken,User user) throws Exception {
		//1.准备POST请求参数
		Object data=JSON.toJSON(user);
		System.out.println(data);

		//2.获取请求url
		String url=CREATE_USER_URL.replace("ACCESSTOKEN", accessToken);

		//3.发起POST请求，获取返回结果
		JSONObject jsonObject=HttpHelper.httpPost(url, data);
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.解析结果，获取UserId
		String userId="";
		if (null != jsonObject) {  
			userId=jsonObject.getString("userid");
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return userId;
	}


	/** 2.根据userid获取成员详情
	 * @desc ：获取成员详情
	 *   参考文档： https://open-doc.dingtalk.com/docs/doc.htm?spm=0.0.0.0.jjSfQQ&treeId=371&articleId=106816&docType=1#s0
	 * @param accessToken
	 * @param userId void
	 * @throws Exception 
	 */
	public JSONObject getUser(String accessToken, String userId) throws Exception {

		//1.获取请求url
		String url=GET_USER_URL.replace("ACCESSTOKEN", accessToken).replace("USERID", userId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.httpGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());
		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
			if (0==jsonObject.getInteger("errcode")) {
				return jsonObject;
			}
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return null;
	}

	/** 3.获取部门成员
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param departmentId
	 * @throws Exception void
	 */
	public JSONObject getDepartmentUser(String accessToken, String departmentId) throws Exception {

		//1.获取请求url
		String url=GET_DEPARTMENTUSER_URL.replace("ACCESSTOKEN", accessToken).replace("DEPARTMENTID", departmentId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.httpGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.解析结果，获取User
		if (null != jsonObject) {  

			//4.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}
		return jsonObject;   
	}

	/** 4.获取部门成员（详情）
	 * @desc ：
	 *  
	 * @param accessToken
	 * @param departmentId
	 * @throws Exception void
	 */
	public void getDepartmentUserDetail(String accessToken, String departmentId) throws Exception {

		//1.获取请求url
		String url=GET_DEPARTMENTUSERDETAIL_URL.replace("ACCESSTOKEN", accessToken).replace("DEPARTMENTID", departmentId);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.httpGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.解析结果，获取User
		if (null != jsonObject) {  

			//4.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   
	}

	/** 5.根据免登授权码Code查询免登用户userId
	 * @desc ：钉钉服务器返回的用户信息为：
	 * userid	员工在企业内的UserID
	 * deviceId	手机设备号,由钉钉在安装时随机产生
	 * is_sys	是否是管理员
	 * sys_level	级别，0：非管理员 1：超级管理员（主管理员） 2：普通管理员（子管理员） 100：老板
	 *  
	 * @param accessToken
	 * @param code
	 * @throws Exception void
	 */
	public JSONObject getUserInfo(String accessToken,String code) throws Exception {

		//1.获取请求url
		String url=GET_USERINFO_BYCODE_URL.replace("ACCESSTOKEN", accessToken).replace("CODE", code);

		//2.发起GET请求，获取返回结果
		JSONObject jsonObject=HttpHelper.httpGet(url);
		System.out.println("jsonObject:"+jsonObject.toString());

		//3.解析结果，获取User
		if (null != jsonObject) {  
			//4.请求成功,则返回jsonObject
			if (0==jsonObject.getInteger("errcode")) {
				return jsonObject;
			}
			//5.错误消息处理
			if (0 != jsonObject.getInteger("errcode")) {  
				int errCode = jsonObject.getInteger("errcode");
				String errMsg = jsonObject.getString("errmsg");
				throw new Exception("error code:"+errCode+", error message:"+errMsg); 
			}  
		}   

		return null;
	}

	
		public static String httpGetStringResult(String url,Map<String,Object> param){
		   String content = null;
		   CloseableHttpClient httpClient = HttpClients.createDefault();
		   if(param != null && !param.isEmpty()){
		      StringBuffer strparams = new StringBuffer();
		      for (Map.Entry<String, Object> map : param.entrySet()) {
		         strparams.append(map.getKey()).append("=").append(map.getValue().toString()).append("&");
		      }
		      strparams = strparams.deleteCharAt(strparams.length()-1);
		      url = url + "?" + strparams;
		   }
		   HttpGet httpGet = new HttpGet(url);
		   CloseableHttpResponse response = null;

		   try {

		      response = httpClient.execute(httpGet);
		      HttpEntity entity = response.getEntity();
		      content = EntityUtils.toString(entity,"UTF-8");
		   } catch (ClientProtocolException e) {
		      e.printStackTrace();
		      
		   } catch (IOException e) {
		      e.printStackTrace();
		      
		   }finally {
		         try {
		            if(null!=response){
		               response.close();
		            }
		         } catch (IOException e) {
		          
		            e.printStackTrace();
		         }
		      }
		     
		   return content;
		}
		public void getAttendances(List<AttendanceQvo> users, String workDateFrom, String workDateTo,String access_Token) {
		   Map<String,Object> map = new HashMap<>();
		   map.put("workDateFrom", workDateFrom);
		   map.put("workDateTo", workDateTo);
		   map.put("userIdList", "");
		   map.put("offset","");
		   map.put("limit","");
		   String attendanceStr = getAttendance(map, access_Token);
		   getAttendanceList(users, attendanceStr);//setAttendanceList
		}

		public String getAttendance(Map<String, Object> map ,String access_token_str) {
		   String dingDingAttendance = "https://oapi.dingtalk.com/attendance/list?access_token="+access_token_str;
		   JSONObject jsonObject = new JSONObject();
		   jsonObject.put("workDateFrom",map.get("workDateFrom"));
		   jsonObject.put("workDateTo",map.get("workDateTo"));
		   jsonObject.put("limit",map.get("limit"));
		   jsonObject.put("offset",map.get("offset"));
		   jsonObject.put("userIdList",map.get("userIdList"));
		   return doPost(dingDingAttendance,jsonObject);//获取考勤记录
		}
		/**
		 * post请求
		 * @param requestUrl
		 * @param json
		 * @return
		 */
		public static String doPost(String requestUrl,JSONObject json){
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
		        while ((line = reader.readLine()) != null)
		             strber.append(line + "\n");
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
		    return result;
		}
	
		public void getAttendanceList(List<AttendanceQvo> users, String json) {
		       JSONObject firstJson = JSONObject.parseObject(json);
		       JSONArray  recordFirst = firstJson.getJSONArray("recordresult");//当前部门下的userList
		       for(int j=0;j<recordFirst.size();j++) {
		           JSONObject record = recordFirst.getJSONObject(j);
		           AttendanceQvo attendanceQvo = new AttendanceQvo();
		           attendanceQvo.setCheckType(record.getString("checkType"));
		           attendanceQvo.setUserId(record.getString("userId"));
		           attendanceQvo.setUserCheckTime(record.getLong("userCheckTime"));
		           attendanceQvo.setWorkDate(record.getLong("workDate"));
		           users.add(attendanceQvo);
		       }
		   }
		

}
