package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.ModuleBranch;
import com.mdp.safe.client.service.remote.ModuleRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ModuleBranchRedisCacheService extends SafeRedisCacheService<ModuleBranch> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleBranchRedisCacheService.class);

	Map<String, ModuleBranch> localCache=new HashMap<>(50);
	@Override
	public String getCacheKey() {
		
		return ModuleBranchRedisCacheService.class.getName();
	}
    
    @Autowired
	ModuleRemoteService mrs;
     

      
 
  /**
   * 获取某个角色
   * @param moduleId 角色编号
   * @return
   */
   public ModuleBranch getModuleBranch(String branchId,String moduleId){
   	String key=branchId+":"+moduleId;
	   ModuleBranch module=this.localCache.get(key);
	   if(module!=null){
	   	return module;
	   }
	   module=this.get(branchId+":"+moduleId);
    	 if(module==null) {
    		 module=buildAndPutModuleBranchToCache(branchId,moduleId);
    		 if(module==null){
    		 	return module;
			 }
    	 }
    	 this.localCache.put(key,module);
    	return module;
    	
    }

    public void clear(String branchId,String moduleId){
		this.remove(branchId+":"+moduleId);
		this.localCache.remove(branchId+":"+moduleId);
	}
    
    /**
     * 将某角色加入缓存
     * @param moduleId
     * @return
     */
    public ModuleBranch buildAndPutModuleBranchToCache(String branchId,String moduleId){
		ModuleBranch module = mrs.getModuleBranch(branchId,moduleId);
    	if(module==null ){
    	    return null;
        }
		this.put(branchId+":"+moduleId,module);
		return module;

   }
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void autoClearLocalCache(){
		this.localCache.clear();
	}
    
}