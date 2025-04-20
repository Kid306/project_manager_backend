package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Role;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleRedisCacheService extends SafeRedisCacheService<Role> {

    private static final Logger logger = LoggerFactory.getLogger(RoleRedisCacheService.class);

    Map<String,Role> localCache=new HashMap<>(50);

	@Override
	public String getCacheKey() {
		
		return RoleRedisCacheService.class.getName();
	}
    
    @Autowired
	UserResourceRemoteService cs;
     

      
 
  /**
   * 获取某个角色
   * @param roleid 角色编号
   * @return
   */
   public Role getRole(String roleid){
   		Role role=localCache.get(roleid);
   		if(role!=null){
   			return role;
		}
	   role=this.get(roleid);
    	 if(role==null) {
			 role= buildAndPutRoleToCache(roleid);
			 if(role==null){
			 	return null;
			 }
    	 }
    	localCache.put(roleid,role);
    	 return role;
    	
    }

    public void clear(String roleid){
   		this.localCache.remove(roleid);
		this.remove(roleid);
	}
    
    /**
     * 将某角色加入缓存
     * @param roleid
     * @return
     */
    public Role buildAndPutRoleToCache(String roleid){
		Role role = cs.getRoleFromRemote(roleid);
    	if(role==null ){
    	    return null;
        }
		this.put(roleid,role);
		return role;

   }

   @Scheduled(cron = "0 0 0/1 * * ?")
	public void autoClearLocalCache(){
    	this.localCache.clear();
   }
    
    
}