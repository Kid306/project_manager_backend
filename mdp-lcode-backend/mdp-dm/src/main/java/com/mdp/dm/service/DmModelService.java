package com.mdp.dm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.api.CacheHKVService;
import com.mdp.core.query.QueryTools;
import com.mdp.core.service.BaseService;
import com.mdp.dm.entity.DmModel;
import com.mdp.dm.mapper.DmModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author maimeng-mdp code-gen
 * @since 2024-5-4
 */
@Service
public class DmModelService extends BaseService<DmModelMapper, DmModel> {
	static Logger logger =LoggerFactory.getLogger(DmModelService.class);

	String cacheSpacePreKey= DmModel.class.getSimpleName();
	@Autowired
	CacheHKVService cacheHKVService;

	public List<DmModel> getModelsByBranchIdCacheFirst(String branchId){
		String spaceKey=cacheSpacePreKey+"-"+branchId;
		List<DmModel> list=cacheHKVService.getValus(spaceKey);
		if(list==null || list.size()==0){
			list=super.list(QueryTools.initQueryWrapper(DmModel.class).eq("branch_id",branchId));
			if(list!=null && list.size()>0){
				for (DmModel model : list) {
					cacheHKVService.put(spaceKey,model.getId(),model);
				}
			}else{
				list=new ArrayList<>();
			}
		}
		return list;
	}
	public void removeModelFromCache(String branchId,String id){
		String spaceKey=cacheSpacePreKey+"-"+branchId;
		cacheHKVService.remove(spaceKey,id);
	}
	public void putModelToCache(String branchId, String id, DmModel model){
		String spaceKey=cacheSpacePreKey+"-"+branchId;
		cacheHKVService.put(spaceKey,id,model);
	}
	public DmModel getModelCacheFirst(String branchId, String id){
		String spaceKey=cacheSpacePreKey+"-"+branchId;
		DmModel model= (DmModel) cacheHKVService.get(spaceKey,id);
		if(model!=null){
			return model;
		}else{
			model=getById(id);
			if(model!=null){
				cacheHKVService.put(spaceKey,id,model);
			}
		}
		return model;
	}

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
}

