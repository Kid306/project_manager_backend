package com.mdp.tpa.client.cache;

import com.mdp.tpa.client.entity.AppTpPay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
@Service
public  class TpPayRedisCacheService extends TpaRedisCacheService<AppTpPay> {
	
	private Lock lock = new ReentrantLock();

	  
	 Map<String, AppTpPay> authsMap=new HashMap<>();
	 
	 Map<String,String> locationPayAuthIdsMap=new HashMap<>();
	 
	 Map<String,AppTpPay> appidMap=new HashMap<>();
	 
	 Logger log= LoggerFactory.getLogger(TpPayRedisCacheService.class);
	 
	@Override
	public String getCacheKey() {
		
		return "mdp_safeAppAllTpPay";
	}
	/**
	 * 根据商户编号、门店编号、业务类型获取授权编号
	 * @param shopId
	 * @param locationId
	 * @param payType wxpub/wxa/zfbpub
	 * @return
	 */
	public AppTpPay getAppTpPay(String shopId,String locationId,String payType){
		   
		String payAuthId=locationPayAuthIdsMap.get(shopId+"_"+locationId+"_"+payType);
		if(StringUtils.isEmpty(payAuthId)) { 
			return null;
		}else {
			return this.getAppTpPay(payAuthId);
		} 
	
	} 
	/**
	 * 根据payAuthId push appTpPay
	 * @param appTpPay
	 * @return
	 */
	public void putAppTpPay(AppTpPay appTpPay ){
		   
 		locationPayAuthIdsMap.put(appTpPay.getShopId()+"_"+appTpPay.getLocationId()+"_"+appTpPay.getPayType(), appTpPay.getPayAuthId());
 		this.authsMap.put(appTpPay.getPayAuthId(), appTpPay);
 		this.appidMap.put(appTpPay.getAppid(), appTpPay);
 		this.put(appTpPay.getPayAuthId(), appTpPay); 
	
	} 
	public AppTpPay getAppTpPay(String payAuthId){
		   

				AppTpPay auth=authsMap.get(payAuthId);
				if(auth==null) {
					lock.lock();
					try {
						auth=this.get(payAuthId);
						if(auth==null) {
							 return null;
						}else {
							authsMap.put(payAuthId, auth);
							this.locationPayAuthIdsMap.put(auth.getShopId()+"_"+auth.getLocationId()+"_"+auth.getPayType(), auth.getPayAuthId());
							this.appidMap.put(auth.getAppid(), auth);
							return auth;
						} 
					} finally {
						lock.unlock();
					}
				} else {
					return auth;
				}
			
	} 
	public AppTpPay getAppTpPayByAppid(String appid){
		return this.appidMap.get(appid);
	}
  
 
	  
	public Map<String, AppTpPay> getAuthsMap() {
		return authsMap;
	}
	public void clearOne(String payAuthId) {
		AppTpPay auth=this.getAppTpPay(payAuthId);
		if(auth==null) {
			return;
		}
		this.locationPayAuthIdsMap.put(auth.getShopId()+"_"+auth.getLocationId()+"_"+auth.getPayType(),null);
		this.appidMap.put(auth.getAppid(), null);
		this.authsMap.put(auth.getPayAuthId(), null);
		this.put(auth.getPayAuthId(),null);
		
	}  
	public void clearOneByAppid(String appid) {
		AppTpPay auth=this.getAppTpPayByAppid(appid);
		if(auth==null) {
			return;
		}
		this.locationPayAuthIdsMap.put(auth.getShopId()+"_"+auth.getLocationId()+"_"+auth.getPayType(),null);
		this.appidMap.put(auth.getAppid(), null);
		this.authsMap.put(auth.getPayAuthId(), null);

		this.put(auth.getPayAuthId(), null);
		
	}  
 }