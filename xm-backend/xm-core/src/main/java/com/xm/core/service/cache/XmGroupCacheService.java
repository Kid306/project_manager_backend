package com.xm.core.service.cache;

import com.mdp.core.api.CacheHKVService;
import com.mdp.mq.sp.Publish;
import com.xm.core.vo.XmGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class XmGroupCacheService {
	
	@Autowired
    CacheHKVService cacheHKVService;

	Map<String, List<XmGroupVo>> prjCache =new ConcurrentHashMap<>();
	Map<String, List<XmGroupVo>> prdCache =new ConcurrentHashMap<>();

	@Autowired
	Publish publish;

	String currPrdDateKey="";
	public String getProductKey(String productId){
		Calendar currCal=Calendar.getInstance();
		String dateKey=currCal.get(Calendar.YEAR)+"-"+currCal.get(Calendar.DAY_OF_YEAR)+"_prd";
		String finalKey=this.getCacheKey()+":"+dateKey+":"+productId;
		if(dateKey.equals(currPrdDateKey)){
			return finalKey;
		}else {
			currPrdDateKey=dateKey;
			this.cacheHKVService.expire(finalKey,24,TimeUnit.HOURS);
			return finalKey;
		}
	}
	String currPrjDateKey="";
	public String getProjectKey(String projectId){
		Calendar currCal=Calendar.getInstance();
		String dateKey=currCal.get(Calendar.YEAR)+"-"+currCal.get(Calendar.DAY_OF_YEAR)+"_prj";
		String finalKey=this.getCacheKey()+":"+dateKey+":"+projectId;
		if(dateKey.equals(currPrjDateKey)){
			return finalKey;
		}else {
			currPrjDateKey=dateKey;
			this.cacheHKVService.expire(finalKey,24,TimeUnit.HOURS);
			return finalKey;
		}
	}
	String getCacheKey() {
 		return "xm_group";
	}

	public  List<XmGroupVo>  getProjectGroups(String projectId){
		String key=this.getProjectKey(projectId);
		List<XmGroupVo> groupVoList=this.prjCache.get(key);
		if(groupVoList==null){
			groupVoList=  (List<XmGroupVo>) cacheHKVService.values(key);
			if(groupVoList!=null){
				this.prjCache.put(key,groupVoList);
			}
			return groupVoList;
		}else {
			return groupVoList;
		}


	}
	public XmGroupVo getProjectGroup(String projectId, String groupId){
		String key=this.getProjectKey(projectId);
		return (XmGroupVo) cacheHKVService.get(key,groupId);
	}
	public  void  putProjectGroup(XmGroupVo group){
		String key=this.getProjectKey(group.getProjectId());
		cacheHKVService.put(key, group.getId(), group);
	}

	/**
	 *
	 * @param projectId
	 */
	public  void  clearProjectGroup(String projectId){
		this.clearProjectGroups(projectId);
	}
	public void putProjectGroups(String projectId,List<XmGroupVo> groups){

		String key=this.getProjectKey(projectId);
		if(groups==null || groups.size()==0){
			this.clearProjectGroups(projectId);
			return;
		}
		for (XmGroupVo group : groups) {
			String hashKey= group.getId();
			cacheHKVService.put(key, hashKey, group);
		}
		if(groups==null){
			this.prjCache.remove(key);
		}else{
			this.prjCache.put(key,groups);
		}
		publish.push("XM_GROUP_PRJ_CACHE",projectId);
	}
	public void clearProjectGroups(String projectId){
		String key=this.getProjectKey(projectId);
		Set<String> keySet=cacheHKVService.keys(key);
		if(keySet!=null && keySet.size()>0){
			cacheHKVService.delete(key,keySet.toArray());
		}
		this.prjCache.remove(key);
		publish.push("XM_GROUP_PRJ_CACHE",projectId);
	}

	public  List<XmGroupVo>  getProductGroups(String productId){
		String key=this.getProductKey(productId);
		List<XmGroupVo> groupVoList=this.prdCache.get(key);
		if(groupVoList==null){

			groupVoList= (List<XmGroupVo>) cacheHKVService.values(key);
			if(groupVoList!=null){
				this.prdCache.put(key,groupVoList);
			}
			return groupVoList;
		}else {
			return groupVoList;
		}
	}
	public XmGroupVo getProductGroup(String productId, String groupId){
		String key=this.getProductKey(productId);
		return (XmGroupVo) cacheHKVService.get(key,groupId);
	}
	public  void  putProductGroup(XmGroupVo group){
		String key=this.getProductKey(group.getProductId());
		cacheHKVService.put(key, group.getId(), group);
	}
	public  void  clearProductGroup(String productId){
		String key=this.getProductKey(productId);
		this.clearProductGroups(productId);
	}
	public void putProductGroups(String productId,List<XmGroupVo> groups){
		String key=this.getProductKey(productId);
		if(groups==null || groups.size()==0){
			 this.clearProductGroups(productId);
			return;
		}
		for (XmGroupVo group : groups) {
			String hashKey= group.getId();
			cacheHKVService.put(key, hashKey, group);
		}
		if(groups==null){
			this.prdCache.remove(key);
		}else{
			this.prdCache.put(key,groups);
		}
		publish.push("XM_GROUP_PRD_CACHE",productId);
	}
	public void clearProductGroups(String productId){
		String key=this.getProductKey(productId);
			Set<String> keySet=cacheHKVService.keys(key);
			if(keySet!=null && keySet.size()>0){
				cacheHKVService.delete(key,keySet.toArray());
			}
		this.prdCache.remove(key);
		publish.push("XM_GROUP_PRD_CACHE",productId);
	}

	public void clearLocalPrjectCache(String projectId) {
		this.prjCache.remove(getProjectKey(projectId));
	}

	public void clearLocalProductCache(String productId) {
		this.prdCache.remove(getProductKey(productId));
	}

	/*每30分钟清除一次过期的本地缓存*/
	@Scheduled(cron = "* */30 * * * *")
	public void timer() {
		String currPrdKey=this.getCacheKey()+":"+currPrdDateKey+":";

		String currPrjKey=this.getCacheKey()+":"+currPrjDateKey+":";
		for (String key : this.prdCache.keySet()) {
			if(!key.startsWith(currPrdKey)){
				this.prdCache.remove(key);
			}
		}
		for (String key : this.prjCache.keySet()) {
			if(!key.startsWith(currPrjKey)){
				this.prjCache.remove(key);
			}
		}
	}
}
