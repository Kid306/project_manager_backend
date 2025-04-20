package com.mdp.tpa.client.cache;

import com.mdp.tpa.client.entity.AppTpAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public  class TpAuthRedisCacheService extends TpaRedisCacheService<AppTpAuth> {
	 
	 Logger log= LoggerFactory.getLogger(TpAuthRedisCacheService.class);

	 Map<String,String> keyMap=new HashMap<>();

	/**
	 * 根据商户编号、门店编号、业务类型获取授权编号
	 * @param shopId
	 * @param locationId
	 * @param bizType wxpub/wxa/zfbpub
	 * @return
	 */
	public AppTpAuth getAppTpAuth(String shopId,String locationId,String bizType){

		String authId=keyMap.get("slb_"+shopId+"_"+locationId+"_"+bizType);
		if(StringUtils.isEmpty(authId)) {
			return null;
		}else {
			return this.getAppTpAuth(authId);
		}

	}


	@Override
	public String getCacheKey() {
		
		return "mdp_safeAppAllTpAuth";
	}
	/**
	 * 根据authId push appTpAuth
	 * @param appTpAuth
	 * @return
	 */
	public void putAppTpAuth(AppTpAuth appTpAuth ){
 		this.put(appTpAuth.getAuthId(), appTpAuth);
 		keyMap.put("appid_"+appTpAuth.getAppid(),appTpAuth.getAuthId());
		keyMap.put("slb_"+appTpAuth.getShopId()+"_"+appTpAuth.getLocationId()+"_"+appTpAuth.getBizType(),appTpAuth.getAuthId());
	} 
	public AppTpAuth getAppTpAuth(String authId){
				return this.get(authId);
			
	} 
	public AppTpAuth getAppTpAuthByAppid(String appid){
		String authId=keyMap.get("appid_"+appid);
		if(authId==null){
			return null;
		}else {
			return this.get(authId);
		}

	}
  

	/**
	 * 通过微信小程序授权编号获取对应公众号授权号
	 * @param wxaAuthId 小程序授权编号
	 * @return
	 */
	public String getWxpubAuthIdByWxaAuthId(String wxaAuthId) {
		AppTpAuth auth= this.getAppTpAuth(wxaAuthId);
		if(auth==null) {
			return null;
		}else {
			return auth.getPubAuthId();
		}
	}
	public void clearOne(String authId) {
		super.put(authId,null);
		if(keyMap.containsValue(authId)){
			for (Map.Entry<String, String> kv : keyMap.entrySet()) {
				if( kv.getValue()!=null &&  kv.getValue().indexOf(authId)>=0 ){
					keyMap.remove(kv.getKey());
				}
			}
		}
		
	}  
	public void clearOneByAppid(String appid) {

			String authId=keyMap.get("appid_"+appid);
			if(StringUtils.hasText(authId)){
				this.put(authId,null);
				for (Map.Entry<String, String> kv : keyMap.entrySet()) {
					if(kv.getValue()!=null &&  kv.getValue().indexOf(authId)>=0){
						keyMap.remove(kv.getKey());
					}
				}
			}
	}

	public List<AppTpAuth> getAppTpAuthsByBranchId(String branchId, String bizType) {
		String authIds=keyMap.get("bb_"+branchId+"_"+bizType);
		List<AppTpAuth> appTpAuths=new ArrayList<>();
		if(StringUtils.hasText(authIds)){
			String[] authIdss=authIds.split(",");
			for (String authId : authIdss) {
				AppTpAuth appTpAuth=this.get(authId);
				if(appTpAuth==null){
					continue;
				}else {
					appTpAuths.add(appTpAuth);
				}
			}
			return appTpAuths.size()>0?appTpAuths:null;
		}else{
			return null;
		}
	}

	public void putAppTpAuthsByBranchId(String branchId, String bizType, List<AppTpAuth> appTpAuths) {
		String authIds=appTpAuths.stream().map(i->i.getAuthId()).collect(Collectors.joining(","));
		keyMap.put("bb_"+branchId+"_"+bizType,authIds);
		for (AppTpAuth appTpAuth : appTpAuths) {
			this.putAppTpAuth(appTpAuth);
		}
	}
}