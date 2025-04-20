package com.mdp.tpa.client.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.micro.client.CallBizService;
import com.mdp.tpa.client.cache.TpAuthRedisCacheService;
import com.mdp.tpa.client.entity.AppTpAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 app 小模块 <br>
 * 实体 AppTpAuth 表 ADMIN.app_tp_auth 当前主键(包括多主键): tp_appid; 
 ***/
@Service("mdp.tpa.client.AppTpAuthService" )
public class AppTpAuthService  {

	@Value(value = "${mdp.tpa.app-tp-auth-query-uri:http://lcode/app/appTpAuth/list}")
	String appTpAuthQueryUri;
	
	/** 请在此类添加自定义函数 */
	@Autowired
    TpAuthRedisCacheService tpAuthRedisCacheService;
	@Autowired
	CallBizService callBizService;
	
	
	public AppTpAuth getAppTpAuth(String authId){
		AppTpAuth appTpAuth=tpAuthRedisCacheService.getAppTpAuth(authId);
		if(appTpAuth==null) {
			appTpAuth=getAppTpAuthFromRemoteServer(appTpAuthQueryUri+"?authId={authId}",BaseUtils.map("authId",authId));
			 if(appTpAuth!=null) {
				 tpAuthRedisCacheService.putAppTpAuth(appTpAuth); 
			 }
			return appTpAuth;
		}else {

			return appTpAuth;
		}
		  
	} 
	/**
	 * 根据商户编号、门店编号、业务类型获取授权编号
	 * @param shopId
	 * @param locationId
	 * @param bizType wxpub/wxa/zfbpub
	 * @return
	 */
	public AppTpAuth getAppTpAuth(String shopId, String locationId, String bizType) {
		AppTpAuth appTpAuth=tpAuthRedisCacheService.getAppTpAuth(shopId,locationId,bizType);
		if(appTpAuth==null) {
			 Map<String,Object> p=new HashMap<>();
			 p.put("shopId", shopId);
			 p.put("locationId", locationId);
			 p.put("bizType", bizType);
			 appTpAuth= getAppTpAuthFromRemoteServer(appTpAuthQueryUri+"?shopId={shopId}&locationId={locationId}&bizType={bizType}",p);
			 if(appTpAuth!=null) {
				 tpAuthRedisCacheService.putAppTpAuth(appTpAuth); 
			 }
			 return appTpAuth;
		}else {

			return appTpAuth;
		}
		  

	}
	/**
	 * 根据机构编号、业务类型获取授权数据
	 * @param branchId
	 * @param bizType wxpub/wxa/zfbpub
	 * @return
	 */
	public List<AppTpAuth> getAppTpAuthByBranchId(String branchId, String bizType) {
		List<AppTpAuth> appTpAuths=tpAuthRedisCacheService.getAppTpAuthsByBranchId(branchId,bizType);
		if(appTpAuths==null) {
			Map<String,Object> p=new HashMap<>();
			p.put("branchId", branchId);
			p.put("bizType", bizType);
			appTpAuths= getAppTpAuthsFromRemoteServer(appTpAuthQueryUri+"?branchId={branchId}&bizType={bizType}",p);
			if(appTpAuths!=null && appTpAuths.size()>0) {
				tpAuthRedisCacheService.putAppTpAuthsByBranchId(branchId,bizType,appTpAuths);
			}
			return appTpAuths;
		}else {
			return appTpAuths;
		}


	}
	/**
	 * 从远程服务器加载List<AppTpAuth>
	 * @param params
	 * @return List<AppTpAuth>
	 */
	public List<AppTpAuth> getAppTpAuthsFromRemoteServer(String uri,Map<String,Object> params) {
		Map<String,Object> result=callBizService.getForMap(uri,params);
		List<Map<String,Object>> appTpAuths= (List<Map<String,Object>>) result.get("data");
		if(appTpAuths==null || appTpAuths.size()==0){
			return null;
		}else {
			List<AppTpAuth> auths=appTpAuths.stream().map(i->BaseUtils.fromMap(i,AppTpAuth.class)).collect(Collectors.toList());
			return auths;
		}
	}
	/**
	 * 从远程服务器加载AppTpAuth
	 * @param params
	 * @return AppTpAuth
	 */
	public AppTpAuth getAppTpAuthFromRemoteServer(String uri,Map<String,Object> params) {
 		Map<String,Object> result=callBizService.getForMap(uri,params);
		List<Map<String,Object>> appTpAuths= (List<Map<String,Object>>) result.get("data");
		if(appTpAuths==null || appTpAuths.size()==0){
			return null;
		}else {
			AppTpAuth appTpAuth= BaseUtils.fromMap(appTpAuths.get(0),AppTpAuth.class);
			return appTpAuth;
		}
	}
	
	public void putAppTpAuth(AppTpAuth appTpAuth) {
		if(appTpAuth==null) {
			return;
		}
		tpAuthRedisCacheService.putAppTpAuth(appTpAuth);
	}
	public void clearOne(String authId) {
		tpAuthRedisCacheService.clearOne(authId);
	}
	public void clearOneByAppid(String appid) {
		tpAuthRedisCacheService.clearOneByAppid(appid);
	}
	/**
	 * 根据第三方appid反查authId
	 * @param appid  
	 * @return
	 */
	public AppTpAuth getAppTpAuthByTpAppid(String appid) {
		AppTpAuth appTpAuth=tpAuthRedisCacheService.getAppTpAuthByAppid(appid);
		if(appTpAuth==null) {
			Map<String,Object> p=new HashMap<>(); 
			 p.put("appid", appid);
			 appTpAuth= getAppTpAuthFromRemoteServer(this.appTpAuthQueryUri+"?appid={appid}",p);;
			 tpAuthRedisCacheService.putAppTpAuth(appTpAuth);
			 return appTpAuth;
		}else {
			return appTpAuth;
		}
		 
	}
	
	
  

	/**
	 * 通过微信小程序授权编号获取对应公众号授权号
	 * @param wxaAuthId 小程序授权编号
	 * @return
	 */
	@Deprecated
	public String getWxpubAuthIdByWxaAuthId(String wxaAuthId) {
		AppTpAuth auth= this.getAppTpAuth(wxaAuthId);
		if(auth==null) {
			return null;
		}else {
			return auth.getPubAuthId();
		}
	}
	/**
	 * 通过微信小程序授权编号获取对应公众号授权号
	 * @param miniAppAuthId 小程序授权编号
	 * @return
	 */
	@Deprecated
	public String getPubAuthIdByMiniappAuthId(String miniAppAuthId) {
		AppTpAuth auth= this.getAppTpAuth(miniAppAuthId);
		if(auth==null) {
			return null;
		}else {
			return auth.getPubAuthId();
		}
	}
}

