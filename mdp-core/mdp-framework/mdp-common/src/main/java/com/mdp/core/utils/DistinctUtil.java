package com.mdp.core.utils;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 用于消息去重复判断
 * 微信发过来的消息去重
 * @author cyc
 * 20170223
 */
public class DistinctUtil {
	static int maximumSize = 10000;
	static LinkedHashMap<String, Object>  myMap= new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			@Override
	        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
	            return size() > maximumSize;
	        }
	    };
	    /**
	     * 根据key判断是否有重复消息
	     * @param key
	     * @return
	     */
	public static boolean isDistinct(String key){
		 if(myMap.containsKey(key)){
			 return false;
		 }else{
			 myMap.put(key, key);
			 return true;
		 }
	 }
	
	public static void remove(String key){
		myMap.remove(key);
	}
	 
}
