package com.mdp.form.service;

import com.mdp.core.api.CacheHKVService;
import com.mdp.form.entity.FormDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormDefCacheService {
	
	@Autowired
	CacheHKVService cacheHKVService;
	
	String spaceKey ="form_def";
	
	public void putForm(String formId, FormDef formDef) {
		cacheHKVService.put(spaceKey,formId,formDef);
 	}
	
	public FormDef getForm(String formId) {
		return (FormDef) cacheHKVService.get(spaceKey,formId);
	}

	
}
