package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Qx;
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
public class RoleQxsRedisCacheService extends SafeRedisCacheService<Map<String, Qx>> {

	private Lock lock = new ReentrantLock();
	
	@Autowired
    UserResourceRemoteService commonService;
	Map<String, Map<String, Qx>> localCache=new HashMap<>(50);


	Logger log= LoggerFactory.getLogger(RoleQxsRedisCacheService.class);
	
	@Override
	public String getCacheKey() {
		return RoleQxsRedisCacheService.class.getName();
	}
	

	public Map<String, Qx> get(String roleid){
		Map<String, Qx> rr=this.localCache.get(roleid);
		if(rr!=null){
			return rr;
		}
		rr  = super.get( roleid);
		if (rr == null) {
			rr= this.rebuildOne(roleid);
			if(rr==null){
				return rr;
			}
		}
		this.localCache.put(roleid,rr);
		return rr;
		
	}

	 
	public Map<String, Qx> rebuildOne(String roleid) {
		Map<String, Qx> roleQxs=commonService.getRoleQxsFromRemote(roleid);
			if(roleQxs==null){
				return null;
			}
			this.put(roleid,roleQxs);
			return roleQxs;
	}

	public void clearRoleQxs(String ...roleids){
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
