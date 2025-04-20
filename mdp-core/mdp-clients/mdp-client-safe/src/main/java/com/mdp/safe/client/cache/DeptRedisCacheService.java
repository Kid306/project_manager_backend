package com.mdp.safe.client.cache;

import com.mdp.safe.client.entity.Dept;
import com.mdp.safe.client.service.remote.UserResourceRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeptRedisCacheService extends SafeRedisCacheService<Dept> {

    private static final Logger logger = LoggerFactory.getLogger(DeptRedisCacheService.class);

	Map<String, Dept> localCache=new HashMap<>(50);
	@Override
	public String getCacheKey() {
		
		return DeptRedisCacheService.class.getName();
	}
    
    @Autowired
	UserResourceRemoteService cs;
     

      
 
  /**
   * 获取某个机构下面的所有部门数据
   * @param deptid 部门编号
   * @return
   */
   public Dept getDept(String deptid){
	   Dept dept=this.localCache.get(deptid);
	   if(dept!=null){
	   	return dept;
	   }
	   dept=this.get(deptid);
    	 if(dept==null) {
    		 dept=buildAndPutDeptVoToCache(deptid);
    		 if(dept==null){
    		 	return null;
			 }
    	 }
    	 this.localCache.put(deptid,dept);
    	return dept;
    	
    }

    public void clear(String deptid){
		this.remove(deptid);
		this.localCache.remove(deptid);
	}
    
    /**
     * 将某公司下所有部门加入缓存
     * @param deptid
     * @return
     */
    public Dept buildAndPutDeptVoToCache(String deptid){
    	Dept dept = cs.getDeptFromRemote(deptid);
    	if(dept==null ){
    	    return null;
        }
		this.put(deptid,dept);
		return dept;

   }
	@Scheduled(cron = "0 0 0/1 * * ?")
	public void autoClearLocalCache(){
		this.localCache.clear();
	}
    
}