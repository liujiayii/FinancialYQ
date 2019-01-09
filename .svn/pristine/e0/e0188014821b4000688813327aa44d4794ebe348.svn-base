package com.fendo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.fendo.entity.ServiceMoney;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class LoadJson {
	public static String loadJson (String url) {  
        StringBuilder json = new StringBuilder();  
        try {  
            URL urlObject = new URL(url);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
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
	public static void main(String[] args) {
		 String url = "http://192.168.1.230:8002/query/costList";   
		//String url = "http://192.168.1.230:8002/query/withdrawMoney";  
		    String jsonStr = loadJson(url);  
		    
		    System.out.println(jsonStr+"json");
		    String c=jsonStr.substring(1, jsonStr.length()-1);
		    System.out.println(c);
		    String []atr=c.split("},");
		    String s;
		    for(int a=0;a<atr.length;a++){
		    	 s=atr[a]+"}";
		    	if(a==atr.length-1){
		    		 s=atr[a];
		    	}
		   System.out.println(s+"----");
		  /*  JSONObject jsonObject = JSONObject.fromObject(s); 
		   ServiceMoney servicemoney= (ServiceMoney)JSONObject.toBean(jsonObject,ServiceMoney.class);*/
		   
		    
		    	JSONObject jsonObject = JSONObject.fromObject(s); 
		    	 System.out.println(jsonObject+"/////");
		        ServiceMoney servicemoneys= (ServiceMoney)JSONObject.toBean(jsonObject,ServiceMoney.class);
		        System.out.println(servicemoneys+"*****");
		    /*List<ServiceMoney> servicemoneys= (List<ServiceMoney>)JSONArray.toCollection(json,ServiceMoney.class);
		    String id[] = new String[json.size()];
			String incomeAccount[] = new String[json.size()];
			String remark[] = new String[json.size()];
			String money[] = new String[json.size()];
			String time[] = new String[json.size()];
			String strSQL = "";

			
		   System.out.println(servicemoneys+"111");
		   for (int i = 0; i < json.size(); i++) {
				 id[i] = json.getJSONObject(i).getString("id");
				 System.out.println(id[i]+"1111");
				 incomeAccount[i] = json.getJSONObject(i).getString("incomeAccount");
				 remark[i] = json.getJSONObject(i).getString("remark");
				 money[i] = json.getJSONObject(i).getString("money");
				 time[i] = json.getJSONObject(i).getString("time");
				
						 
						 strSQL = "INSERT into t_service_money (id,incomeAccount,remark,money,time) values("
									+ id[i]
									+ ",'"
									+  incomeAccount[i]
									+ "',"
									+  remark[i]
									+ ","
									+ money[i]
									+ ","
									+ time[i]
									
									+ "');";

			 }
		   System.out.println(strSQL);*/
		    }
		 
		  /* JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		   ServiceMoney servicemoney= (ServiceMoney)JSONObject.toBean(jsonObject,ServiceMoney.class);
		   System.out.println(servicemoney+"servicemoney");*/
	}
}
