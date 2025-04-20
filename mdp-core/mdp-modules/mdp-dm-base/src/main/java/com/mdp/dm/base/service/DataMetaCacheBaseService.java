package com.mdp.dm.base.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.core.entity.DmField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DataMetaCacheBaseService {
	@Autowired
	CacheHKVService cacheService;

	String preKey="mdp_meta";
	
	public void putFields(String dataSource, String tableName, List<DmField> fields) {
 		cacheService.put(preKey+"_"+dataSource,tableName,fields);
		cacheService.expire(dataSource,1,TimeUnit.DAYS);

	}
	
	public List<DmField>  getFields(String dataSource, String tableName) {
 		return (List<DmField>) cacheService.get(preKey+"_"+dataSource,tableName);
 	}

}
