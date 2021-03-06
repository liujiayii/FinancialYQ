package com.fendo.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * json工具类
 *
 * @ClassName: JsonUtil
 * @description 
 *
 * @author lujinpeng
 * @createDate 2018年10月31日-下午2:26:00
 */
public class JsonUtil {

	private static Logger logger = Logger.getLogger(JsonUtil.class);
	
	public static String getResponseJson(Integer code, String msg, Integer count, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("count", count);
        if (count == null) {
        	count = 0;
        }
        if (obj == null) {
            obj = "null";
        }
        map.put("data", obj);
        String json = JSONObject.toJSONString(map);
        
        return json;
    }
	
	
	
	
}
