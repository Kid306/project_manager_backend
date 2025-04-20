package com.mdp.tpa.client.cache;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.api.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public abstract class TpaRedisCacheService<T> implements CacheService<T> {
	
	@Autowired
	CacheHKVService cacheHKVService;
	
	 Logger log= LoggerFactory.getLogger(this.getCacheKey());

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
		
		
	}

	@Override
	public boolean containsValue(T value) {
		
		return false;
	}
	
	public abstract  String getCacheKey() ;
	
	 
	@Override
	public boolean expire(long milliseconds) {
		
		return cacheHKVService.expire(getKey(), milliseconds, TimeUnit.MILLISECONDS);
	} 
	public boolean expire() {
		
		return cacheHKVService.expire(getKey(), 120000, TimeUnit.MILLISECONDS);
	}
}
