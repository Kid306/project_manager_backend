package com.mdp.arc.cache;

import com.mdp.core.api.CacheHKVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TagCacheService {
	
	
	@Autowired
	CacheHKVService cacheHKVService;
	
	String publicTagCacheKey="publicTagCacheKey_";

	String pub_key="_pub";
	
	
	public void putPubTags(List<Map<String,Object>> tags) {

		cacheHKVService.put(publicTagCacheKey, pub_key, tags);
		cacheHKVService.expire(publicTagCacheKey, 1, TimeUnit.DAYS);
	}
	
	public List<Map<String,Object>> getPubTags(){
		return (List<Map<String, Object>>)cacheHKVService.get(publicTagCacheKey, pub_key);
		
	}

	public void removePubTabs(){
		cacheHKVService.remove(publicTagCacheKey,pub_key);
	}
	public void putNotPubTags(String branchId,List<Map<String,Object>> tags) {

		cacheHKVService.put(publicTagCacheKey, branchId, tags);
		cacheHKVService.expire(publicTagCacheKey, 1, TimeUnit.DAYS);
	}

	public List<Map<String,Object>> getNotPubTags(String branchId){
		return (List<Map<String, Object>>) cacheHKVService.get(publicTagCacheKey, branchId);

	}

	public void removeNotPubTabs(String branchId){
		cacheHKVService.remove(publicTagCacheKey,branchId);
	}

}
