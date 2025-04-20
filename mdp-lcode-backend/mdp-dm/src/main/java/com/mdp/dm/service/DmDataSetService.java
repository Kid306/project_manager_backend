package com.mdp.dm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.service.BaseService;
import com.mdp.dm.entity.DmDataSet;
import com.mdp.dm.mapper.DmDataSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author maimeng-mdp code-gen
 * @since 2024-4-25
 */
@Service
public class DmDataSetService extends BaseService<DmDataSetMapper, DmDataSet> {
	static Logger logger =LoggerFactory.getLogger(DmDataSetService.class);

	String cacheSpaceKey= DmDataSet.class.getSimpleName();

	@Autowired
	CacheHKVService cacheHKVService;

	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}

	public DmDataSet getDataSetFromCacheFirst(String id){
		DmDataSet dataSet= (DmDataSet) cacheHKVService.get(cacheSpaceKey,id);
		if(dataSet==null){
			dataSet=this.getById(id);
			cacheHKVService.put(cacheSpaceKey,id,dataSet);
			cacheHKVService.expire(cacheSpaceKey,1, TimeUnit.DAYS);
		}
		return dataSet;
	}
	public void removeDataSetFromCache(String id){
		cacheHKVService.remove(cacheSpaceKey,id);
	}
}

