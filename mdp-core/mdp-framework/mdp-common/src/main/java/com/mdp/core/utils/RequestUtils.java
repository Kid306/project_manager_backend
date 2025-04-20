package com.mdp.core.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class RequestUtils {
	
	public static Logger logger = LoggerFactory.getLogger(RequestUtils.class);
	
	public static ObjectMapper o=new ObjectMapper();
	
	public static boolean isAjaxRequest(HttpServletRequest request){
		boolean is= "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if(is==false) {
			HeaderContentNegotiationStrategy header=new HeaderContentNegotiationStrategy();
			try {
				List<MediaType> requestMediaTypes=header.resolveMediaTypes(new ServletWebRequest(request));
				for (MediaType requestMediaType : requestMediaTypes) {
					if(MediaType.APPLICATION_JSON.includes(requestMediaType)){
						return true;
					};
				}
				
			} catch (HttpMediaTypeNotAcceptableException e) {
				 return true;
			}
		}else {
			return true;
		}
		return false;
	}
	
	public static  HttpEntity<Map<String,Object>> jsonMapHttpEntity(Map<String,Object> request){
	   	 HttpHeaders headers = new HttpHeaders();
	        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
	        headers.setContentType(type); 
	        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request,headers);
			return entity;
	}
	
	public static void transformArray(Map<String,Object> params,String ...keys) {
		if(params==null || params.isEmpty()){
			return;
		}
		if(keys==null || keys.length==0){
			return;
		}
		if(keys!=null && keys.length==1 ){
			String key=keys[0];
			String newKey=key+"[]";
			if( params.containsKey(newKey)) {
				HttpServletRequest request=getRequest();
				if(request!=null){
					params.put(key, getRequest().getParameterMap().get(newKey));
				}else{
					String[] valus=new String[0];
					params.put(key,Arrays.asList(valus));
				}
				params.remove(newKey);
			}else if( params.containsKey(key)) {
				String value=(String) params.get(key);
				if(!StringUtils.hasText(value)) {
					return;
				}
				if(value.indexOf("[")==0) {
					try {
						String[] valus=o.readValue(value, String[].class);
						params.put(key, valus);
					} catch (JsonParseException e) {
						logger.error("",e);
					} catch (JsonMappingException e) {
						logger.error("",e);
					} catch (IOException e) {
						logger.error("",e);
					}

				}else {
					params.put(key, value.split(","));
				}

			}else{
				 List<Object> vl=new ArrayList<>();
				 Set<String> keySet=new HashSet<>();
				for (Map.Entry<String, Object> entry : params.entrySet()) {
					String k=entry.getKey();
					if(k.indexOf(key)==0){
						vl.add(entry.getValue());
						keySet.add(k);
					}
				}
				for (String k : keySet) {
					params.remove(k);
				}
				if(vl.size()>0){
					params.put(key,vl);
				}
			}
		}else if(keys.length>1){
			Map<String,List<Object>> keysMap=new HashMap<>();
			for (String k : keys) {
				keysMap.put(k,new ArrayList<>());
			}
			Set<String> keySet=new HashSet<>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String k=entry.getKey();
				int index=k.indexOf("[");
				String k2=k;
				if(index>0){
					k2=k.substring(0,index);
				}
				List<Object> vl=keysMap.get(k2);
				if(vl!=null){
					vl.add(entry.getValue());
					keySet.add(k);
				}
			}
			for (String k : keySet) {
				params.remove(k);
			}
			for (Map.Entry<String, List<Object>> kv : keysMap.entrySet()) {
				if(kv.getValue().size()>0){
					params.put(kv.getKey(),kv.getValue());
				}
			}

		}
	}

	/**
	 * 得到request对象
	 */
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes=RequestContextHolder.getRequestAttributes();

		if (requestAttributes!= null) {
			HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
			return request;
		}
		return null;

	}
	
	public static String getIpAddr(HttpServletRequest request) { 
	       String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       }
	        
	       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("HTTP_CLIENT_IP");
	       }
	       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	       }
	       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("X-Forwarded-For");
	       }

	       if(ip.contains(",")){
	    	   String[] ips=ip.split(",");
	    	   ip=ips[0];
	       }
	       return ip; 
	   } 
	
}