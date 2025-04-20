package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Menu;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RoleMenusRedisCacheService extends SafeRedisCacheService<Map<String, Menu>> {

	private Lock lock = new ReentrantLock();
	
	@Autowired
	UserResourceRemoteService commonService;

	Map<String,Map<String, Menu>> localCache=new HashMap<>(50);


	Logger log= LoggerFactory.getLogger(RoleMenusRedisCacheService.class);
	
	@Override
	public String getCacheKey() {
		return RoleMenusRedisCacheService.class.getName();
	}
	

	public Map<String, Menu> get(String roleid){
		Map<String, Menu> rr=this.localCache.get(roleid);
		if(rr!=null){
			return rr;
		}
		rr  = super.get( roleid);
		if (rr == null) {
			rr= this.rebuildOne(roleid);
		}
		return rr;
		
	}

	 
	public Map<String, Menu> rebuildOne(String roleid) {
		Map<String, Menu> roleMenus=commonService.getRoleMenusFromRemote(roleid);
			if(roleMenus==null){
				return null;
			}
			this.put(roleid,roleMenus);

			if(roleMenus!=null){
				this.localCache.put(roleid,roleMenus);
			}
			return roleMenus;
	}

	public void clearRoleMenus(String ...roleids){
		if(roleids!=null && roleids.length>0){
			for (String roleid : roleids) {
				this.remove(roleid);
				this.localCache.remove(roleid);
			}
		}
	}
	@Scheduled(cron = "0 0/5 * * * ?")
	public void autoClearLocalCache(){
		this.localCache.clear();
	}
}
