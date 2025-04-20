package com.mdp.tpa.client.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.tpa.client.cache.TpPayRedisCacheService;
import com.mdp.tpa.client.entity.AppTpPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj.mdp  顶级模块 safe 大模块 common 小模块 <br>
 * 实体 AppTpPay 表 ADM.app_tp_pay 当前主键(包括多主键): pay_auth_id; 
 ***/
@Service("mdp.tpa.client.AppTpPayService" )
public class AppTpPayService {

	@Value(value = "${mdp.tpa.app-tp-pay-query-uri:http://lcode/app/appTpPay/list}")
	String appTpPayQueryUri;

	/** 请在此类添加自定义函数 */
	@Autowired
    TpPayRedisCacheService tpPayRedisCacheService;

	@Autowired
	CallBizService callBizService;
	  
	public AppTpPay getAppTpPay(String payAuthId){
		AppTpPay appTpPay=tpPayRedisCacheService.getAppTpPay(payAuthId);
		if(appTpPay==null) {
			appTpPay= getAppTpPayFromRemoteServer(appTpPayQueryUri+"?payAuthId={payAuthId}",BaseUtils.map("payAuthId",payAuthId));
			 if(appTpPay!=null) {
				 tpPayRedisCacheService.putAppTpPay(appTpPay); 
			 }
			return appTpPay;
		}else {

			return appTpPay;
		}
		  
	} 
	/**
	 * 根据商户编号、门店编号、业务类型获取授权编号
	 * @param shopId
	 * @param locationId
	 * @param payType wxpub/wxa/zfbpub
	 * @return
	 */
	public AppTpPay getAppTpPay(String shopId, String locationId, String payType) {
		AppTpPay appTpPay=tpPayRedisCacheService.getAppTpPay(shopId,locationId,payType);
		if(appTpPay==null) {
			 Map<String,Object> p=new HashMap<>();
			 p.put("shopId", shopId);
			 p.put("locationId", locationId);
			 p.put("payType", payType);
			 appTpPay=  getAppTpPayFromRemoteServer(this.appTpPayQueryUri+"?shopId={shopId}&locationId={locationId}&payType={payType}",p);
			 if(appTpPay!=null) {
				 tpPayRedisCacheService.putAppTpPay(appTpPay); 
			 }
			 return appTpPay;
		}else {

			return appTpPay;
		}
		  

	}
	
	public void putAppTpPay(AppTpPay appTpPay) {
		if(appTpPay==null) {
			return;
		}
		tpPayRedisCacheService.putAppTpPay(appTpPay);
	}
	public void clearOne(String authId) {
		tpPayRedisCacheService.clearOne(authId);
	}
	public void clearOneByAppid(String appid) {
		tpPayRedisCacheService.clearOneByAppid(appid);
	}
	/**
	 * 根据第三方appid反查authId
	 * @param appid  
	 * @return
	 */
	public AppTpPay getAppTpPayByTpAppid(String appid) {
		AppTpPay appTpPay=tpPayRedisCacheService.getAppTpPayByAppid(appid);
		if(appTpPay==null) {
			Map<String,Object> p=new HashMap<>(); 
			 p.put("appid", appid);
			 appTpPay= getAppTpPayFromRemoteServer(this.appTpPayQueryUri+"?appid={appid}",p);
			 tpPayRedisCacheService.putAppTpPay(appTpPay);
			 return appTpPay;
		}else {
			return appTpPay;
		}
		 
	}
	/**
	 * 从远程服务器加载AppTpPay
	 * @param params
	 * @return AppTpPay
	 */
	public AppTpPay getAppTpPayFromRemoteServer(String uri,Map<String,Object> params) {
		Map<String,Object> result=callBizService.getForMap(uri,params);
		List<Map<String,Object>> appTpPays= (List<Map<String,Object>>) result.get("data");
		if(appTpPays==null || appTpPays.size()==0){
			return null;
		}else {
			AppTpPay appTpPay= BaseUtils.fromMap(appTpPays.get(0),AppTpPay.class);
			return appTpPay;
		}
	}

}

