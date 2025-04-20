package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Branch;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BranchRedisCacheService extends SafeRedisCacheService<Branch> {

    private static final Logger logger = LoggerFactory.getLogger(BranchRedisCacheService.class);

	Map<String, Branch> localCache=new HashMap<>(50);
	@Override
	public String getCacheKey() {
		
		return BranchRedisCacheService.class.getName();
	}
    
    @Autowired
	UserResourceRemoteService cs;
     

      
 
  /**
   * 获取某个机构
   * @param branchId 部门编号
   * @return
   */
   public Branch getBranch(String branchId){
	   Branch branch=this.localCache.get(branchId);
	   if(branch!=null){
	   	return branch;
	   }
	   branch=this.get(branchId);
    	 if(branch==null) {
    		 branch=buildAndPutBranchToCache(branchId);
    		 if(branch==null){
    		 	return null;
			 }
    	 }
    	 this.localCache.put(branchId,branch);
    	return branch;
    	
    }

    public void clear(String branchId){
		this.remove(branchId);
		this.localCache.remove(branchId);
	}
    
    /**
     * 将某公司加入缓存
     * @param branchId
     * @return
     */
    public Branch buildAndPutBranchToCache(String branchId){
		Branch branch = cs.getBranchFromRemote(branchId);
    	if(branch==null ){
    	    return null;
        }
		this.put(branchId,branch);
		return branch;

   }
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void autoClearLocalCache(){
		this.localCache.clear();
	}
    
}