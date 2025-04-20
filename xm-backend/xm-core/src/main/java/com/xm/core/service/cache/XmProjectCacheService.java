package com.xm.core.service.cache;

import com.mdp.core.api.CacheHKVService;
import com.mdp.mq.sp.Publish;
import com.xm.core.entity.XmProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class XmProjectCacheService {
	
	@Autowired
	CacheHKVService cacheHKVService;
	String currDateKey="";

	Map<String, XmProject> cache=new ConcurrentHashMap<>();

	@Autowired
	Publish publish;

	public String getKey(){
		Calendar currCal=Calendar.getInstance();
		String dateKey=currCal.get(Calendar.YEAR)+"-"+currCal.get(Calendar.DAY_OF_YEAR);
		if(dateKey.equals(currDateKey)){
			return this.getCacheKey()+":"+dateKey;
		}else {
			currDateKey=dateKey;
			this.cacheHKVService.expire(this.getCacheKey()+":"+dateKey,24,TimeUnit.HOURS);
			return this.getCacheKey()+":"+dateKey;
		}
	}
	String getCacheKey() {
 		return "xm_project";
	} 
	public void putProject(String projectId,XmProject project){
		String key=this.getKey();
		String hashKey=projectId;
		cacheHKVService.put(key, hashKey, project);
		if(project!=null){
			this.cache.put(hashKey+hashKey,project);
		}else{
			this.cache.remove(hashKey+hashKey);
		}
		publish.push("XM_PROJECT_CACHE",projectId);
	}
	
	public  XmProject  getProject(String projectId){

		String key = this.getKey();
		XmProject project=cache.get(key+projectId);
		if(project==null) {
			String hashKey = projectId;
			project= (XmProject) cacheHKVService.get(key, hashKey);
			if(project!=null){
				cache.put(hashKey+hashKey,project);
			}
			return project;
		}else{
			return project;
		}
		
	}
	public void clear(String projectId){
		String key=this.getKey();
		cache.remove(key+projectId);
		cacheHKVService.delete(key,projectId);

		publish.push("XM_PROJECT_CACHE",projectId);
	}

	public void clearLocalCache(String projectId) {
		this.cache.remove(getKey()+projectId);
	}

	@Scheduled(cron = "* */30 * * * *")
	public void timer() {
		String currPrdKey=this.getKey();
		for (String key : this.cache.keySet()) {
			if(!key.startsWith(currPrdKey)){
				this.cache.remove(key);
			}
		}
	}
}
