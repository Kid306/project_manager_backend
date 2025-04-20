package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Module;
import com.mdp.safe.client.service.remote.ModuleRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleRedisCacheService extends SafeRedisCacheService<Module> {

    private static final Logger logger = LoggerFactory.getLogger(ModuleRedisCacheService.class);

	@Override
	public String getCacheKey() {
		
		return ModuleRedisCacheService.class.getName();
	}
    
    @Autowired
	ModuleRemoteService mrs;

	Map<String,Module> freeMap=new HashMap<>();
	Map<String, Module> localCache=new HashMap<>(50);

	/**
	 * check module is Free
	 * @param moduleId module id
	 * @return
	 */
	public boolean checkIsFree(String moduleId){
		if(freeMap.isEmpty()){
			List<Module> moduleList=mrs.getFreeModules();
			if(moduleList!=null && moduleList.size()>0){
				for (Module module : moduleList) {
					freeMap.put(module.getId(),module);
				}
			}else{
				freeMap.put("mdp-sys",new Module());
				freeMap.put("mdp-schedule",new Module());
				freeMap.put("oa-meeting",new Module());
				freeMap.put("oa-file",new Module());
				freeMap.put("oa-seal",new Module());
				freeMap.put("oa-supervision",new Module());
				freeMap.put("oa-office",new Module());
				freeMap.put("oa-attendance",new Module());
			}
		}
		return freeMap.containsKey(moduleId);

	}
      
 
  /**
   * 获取某个角色
   * @param moduleId 角色编号
   * @return
   */
   public Module getModule(String moduleId){
	   Module module=localCache.get(moduleId);
	   if(module!=null){
	   	return module;
	   }
	   module=this.get(moduleId);
    	 if(module==null) {
    		 module = buildAndPutModuleToCache(moduleId);
    		 if(module==null){
    		 	return null;
			 }
    	 }
    	 localCache.put(moduleId,module);
    	return module;
    	
    }

    public void clear(String moduleId){
		this.remove(moduleId);
		this.localCache.remove(moduleId);
	}
    
    /**
     * 将某角色加入缓存
     * @param moduleId
     * @return
     */
    public Module buildAndPutModuleToCache(String moduleId){
		Module module = mrs.getModule(moduleId);
		return module;

   }

   @Scheduled(cron = "* * 0/1 * * ?")
   public void clearFreeMap(){
    	this.freeMap.clear();
    	this.localCache.clear();
   }
    
    
}