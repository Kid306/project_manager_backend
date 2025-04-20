package com.mdp.sms.client.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.api.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public  class SmsCodeServerRedisCacheService implements CacheService<String> {

		@Autowired
		CacheHKVService cacheHKVService;
	  
	String getSmsCodeCacheKey() {
		 
		return "mdp_sms_ServerSmsCode";
	}
	 

	@Override
	public void put(String key, String smsCode) {
		cacheHKVService.setValue(getSmsCodeCacheKey()+":"+key, smsCode, 5, TimeUnit.MINUTES);
		
	}

	@Override
	public String get(String key) {
		 
		return (String) cacheHKVService.getValue(getSmsCodeCacheKey()+":"+key);
	}

	@Override
	public void remove(String key) {
		cacheHKVService.setValue(getSmsCodeCacheKey()+":"+key, null);
		
	}

	@Override
	public boolean containsKey(String key) {
		 
		return  cacheHKVService.getValue(getSmsCodeCacheKey()+":"+key)==null?false:true;
	}

	@Override
	public void refresh() {
		 
		
	}

	@Override
	public boolean containsValue(String value) {
		 
		return false;
	}

	@Override
	public boolean expire(long milliseconds) {
		 
		return false;
	}
 }