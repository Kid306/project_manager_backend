package com.mdp.safe.client.cache;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.api.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public abstract class SafeRedisCacheService<T> implements CacheService<T> {
	
	@Autowired
	CacheHKVService<T> cacheHKVService;
	
	 Logger log= LoggerFactory.getLogger(this.getCacheKey());
	 

	@Override
	public   void put(String key, T o) {
		String okey=getKey();
		cacheHKVService.put(okey, key, o);  
		
	}

	@Override
	public   T get(String key) {
		String okey=getKey();
		return  (T) cacheHKVService.get(okey, key);
	}

	@Override
	public void remove(String key) {
		String okey=getKey();
		cacheHKVService.remove(okey, key);
	}

	@Override
	public boolean containsKey(String key) {
		String okey=getKey();
		return cacheHKVService.containsKey(okey, key);
	}

	@Override
	public void refresh() {

		cacheHKVService.refresh(getKey());
	}

	@Override
	public boolean containsValue(T value) {
		
		return cacheHKVService.containsValue(getCacheKey(),value);
	}
	
	public abstract  String getCacheKey() ;
	
	 
	@Override
	public boolean expire(long milliseconds) {
		return cacheHKVService.expire(getKey(), milliseconds, TimeUnit.MILLISECONDS);
	}


	String currDateKey="";
	public String getKey(){
		 Calendar currCal=Calendar.getInstance();
		 String dateKey=currCal.get(Calendar.YEAR)+"-"+currCal.get(Calendar.DAY_OF_YEAR)+"-"+Double.valueOf(Math.ceil(currCal.get(Calendar.HOUR_OF_DAY)/3)).intValue();
		 if(dateKey.equals(currDateKey)){
		 	return this.getCacheKey()+":"+dateKey;
		 }else {
		 	currDateKey=dateKey;
		 	this.cacheHKVService.expire(this.getCacheKey()+":"+dateKey,5,TimeUnit.HOURS);
		 	return this.getCacheKey()+":"+dateKey;
		 }
	}
	/**
	public  static void main(String[] args) {
		Calendar currCal=Calendar.getInstance();
		String dateKey=currCal.get(Calendar.YEAR)+"-"+currCal.get(Calendar.DAY_OF_YEAR)+"-"+Double.valueOf(Math.ceil(currCal.get(Calendar.HOUR_OF_DAY)/3)).intValue();
		String key=dateKey;
		System.out.println(key);
	}
	**/
}
