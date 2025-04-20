package com.xm.core.service.cache;

import com.mdp.core.api.CacheHKVService;
import com.mdp.mq.sp.Publish;
import com.xm.core.entity.XmProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class XmProductCacheService {
	
	@Autowired
    CacheHKVService cacheHKVService;

	Map<String,XmProduct> cache=new ConcurrentHashMap<>();

	@Autowired
	Publish publish;

	String currDateKey="";
	public String getKey(){
		Calendar currCal=Calendar.getInstance();
		String dateKey=currCal.get(Calendar.YEAR)+"-"+currCal.get(Calendar.DAY_OF_YEAR);
		if(dateKey.equals(currDateKey)){
			return this.getCacheKey()+":"+dateKey;
		}else {
			currDateKey=dateKey;
			this.cacheHKVService.expire(this.getCacheKey()+":"+dateKey,24,TimeUnit.HOURS);
			return this.getCacheKey()+":"+dateKey;
		}
	}
	String getCacheKey() {
 		return "xm_product";
	} 
	public void putProduct(String productId,XmProduct product){
		String key=this.getKey();
		String hashKey=productId;
		cacheHKVService.put(key, hashKey, product);
		if(product!=null){
			cache.put(key+hashKey,product);
		}else{
			cache.remove(key+hashKey);
		}
		publish.push("XM_PRODUCT_CACHE",productId);
	}
	
	public  XmProduct  getProduct(String productId){
		String key=this.getKey();
		XmProduct product=cache.get(key+productId);
		if(product==null){
			String hashKey=productId;
			product= (XmProduct) cacheHKVService.get(key, hashKey);
			if(product!=null){
				cache.put(key+hashKey,product);
			}
			return product;
		}else {
			return product;
		}
		
	}

	public void clear(String productId){
		String key=this.getKey();
		cache.remove(key+productId);
		cacheHKVService.delete(key,productId);

		publish.push("XM_PRODUCT_CACHE",productId);
	}

	public void clearLocalCache(String productId) {
		this.cache.remove(getKey()+productId);
	}

	@Scheduled(cron = "* */30 * * * *")
	public void timer() {
		String currPrdKey=this.getKey();
		for (String key : this.cache.keySet()) {
			if(!key.startsWith(currPrdKey)){
				this.cache.remove(key);
			}
		}
	}
}
