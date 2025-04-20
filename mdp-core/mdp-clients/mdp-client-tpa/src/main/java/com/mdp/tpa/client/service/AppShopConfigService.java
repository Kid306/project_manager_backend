package com.mdp.tpa.client.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.Tips;
import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.tpa.client.entity.AppShopConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 app 小模块 <br>
 * 实体 AppShopConfig 表 ADM.app_shop_config 当前主键(包括多主键): shop_id; 
 ***/
@Service("mdp.tpa.client.AppShopConfigService" )
public class AppShopConfigService  {
	
	/** 请在此类添加自定义函数 */
	@Autowired
	CallBizService callBizService;

	@Autowired
	CacheHKVService cacheHKVService;
	
	public AppShopConfig getShopConfig(String shopId){
		AppShopConfig config= this.getShopConfigFromRedis(shopId);
			if(config!=null) {
				return config;
			}else {
				Map<String,Object> p=new HashMap<>();
				p.put("shopId",shopId);
 				Map<String,Object> resultMap=callBizService.getForMap("/lcode/app/appShopConfig/list?shopId={shopId}",p);
				Tips tips=BaseUtils.mapToTips(resultMap);
				if(tips.isOk()){
					List<Map<String,Object>> data= (List<Map<String, Object>>) resultMap.get("data");
					if(data!=null && data.size()>0){
						config=BaseUtils.fromMap(data.get(0),AppShopConfig.class);
						this.putShopConfigToRedis(shopId, config);
					}else{//如果没有，构造一个默认值
						config= defaultConfig(shopId);
						this.putShopConfigToRedis(shopId, config);
					}
				}
			}
			return config;
	}
	public AppShopConfig getShopConfigByBranchId(String shopBranchId){
		AppShopConfig config= this.getShopConfigFromRedis(shopBranchId);
			if(config!=null) {
				return config;
			}else {
				Map<String,Object> p=new HashMap<>();
				p.put("shopBranchId",shopBranchId);
				Map<String,Object> resultMap=callBizService.getForMap("/lcode/app/appShopConfig/list?shopBranchId={shopBranchId}",p);
				Tips tips=BaseUtils.mapToTips(resultMap);
				if(tips.isOk()){
					List<Map<String,Object>> data= (List<Map<String, Object>>) resultMap.get("data");
					if(data!=null && data.size()>0){
						config=BaseUtils.fromMap(data.get(0),AppShopConfig.class);
						this.putShopConfigToRedis(shopBranchId, config);
					}else{//如果没有，构造一个默认值
						config= defaultConfig(shopBranchId);
						this.putShopConfigToRedis(shopBranchId, config);
					}
				}
			}
			return config;
	}

	public AppShopConfig defaultConfig(String shopBranchId){
		AppShopConfig config=new AppShopConfig();
		config.setShopBranchId(shopBranchId);
		config.setShopId(shopBranchId);
		config.setHeadLocationId(shopBranchId+"-01");
		config.setPlatBranchId("platform-branch-001");
		config.setSettleBranchId("platform-branch-001");
		config.setAcctPrjType("platform");
		config.setPaySetType("platform");
		config.setSetLevel("1");
		config.setIsJoinPlat("1");
		config.setIsShopSc("1");
		config.setIsPlatSc("1");
		return config;
	}
	AppShopConfig getShopConfigFromRedis(String shopId){
		return (AppShopConfig) cacheHKVService.get(getKey(), shopId);
	}
	void putShopConfigToRedis(String shopId,AppShopConfig config){
		this.cacheHKVService.put(getKey(), shopId, config);
	}

	public String  getCacheKey(){
		return AppShopConfig.class.getSimpleName();
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


}

