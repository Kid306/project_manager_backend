package com.mdp.meta.client.service;

import com.mdp.core.utils.BaseUtils;
import com.mdp.core.utils.ObjectTools;
import com.mdp.meta.client.entity.ItemVo;
import com.mdp.micro.client.CallBizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 meta 小模块 <br>
 * 实体 ItemOption 表 ADMIN.meta_item_option 当前主键(包括多主键): id; 
 ***/
@Service("com.mdp.meta.client.ItemService")
public class ItemService {

	
	Map<String,ItemVo> itemVoMap=new HashMap<>();
	Map<String,ItemVo> sysParamsMap=null;

	@Autowired
    CallBizService callBizService;

	@Value(value = "${mdp.meta.dicts-query-uri:/lcode/mdp/meta/item/dicts}")
	String dictsQueryUri;

	Logger log= LoggerFactory.getLogger(ItemService.class);

	/**
	 * 根据分类编号，字典编码获取字典列表
	 * @param categoryIds all,sysParam等，逗号分割
	 * @param itemCodes 字典代码，多个则以逗号分割
	 * @return
	 */
	public List<ItemVo> getDicts(String categoryIds,String itemCodes){
		List<Map<String,Object>> items = getItemsByCategoryIdsFromMetaServer(categoryIds,itemCodes);
		if(items==null || items.size()==0){
			return null;
		}else {
			List<ItemVo> dicts=new ArrayList<>();
			for (Map<String, Object> item : items) {
				ItemVo itemVo= BaseUtils.fromMap(item,ItemVo.class);
				itemVo.parse();
				dicts.add(itemVo);
			}
			return dicts;

		}
	}
	/**
	 * 根据分类编号，字典编码获取字典列表
	 * @param categoryId all,sysParam等，逗号分割
	 * @param itemCode 字典代码，多个则以逗号分割
	 * @return
	 */
	public ItemVo getDict(String categoryId,String itemCode){
		ItemVo itemVo=this.itemVoMap.get(itemCode);
		if(itemVo!=null){
			return itemVo;
		}
		List<ItemVo> getDicts=this.getDicts(categoryId,itemCode);
		if(getDicts==null || getDicts.size()==0){
			return null;
		}else{
			this.itemVoMap.put(itemCode,getDicts.get(0));
			return getDicts.get(0);

		}
	}
	public List<Map<String,Object>> getItemsByCategoryIdsFromMetaServer(String categoryIds,String itemCodes){
		Map<String,Object> params=new HashMap<>();
		if(ObjectTools.isEmpty(categoryIds)){
			categoryIds="all";
		}
		params.put("categoryIds",categoryIds);
		params.put("itemCodes",itemCodes);
		String url=dictsQueryUri+"?categoryIds={categoryIds}";
		if(ObjectTools.isNotEmpty(itemCodes)){
			url=url+"&itemCodes={itemCodes}";
		}
		Map<String,Object> result=callBizService.getForMap(url,params);
		List<Map<String,Object>> items= (List<Map<String, Object>>) result.get("data");
		return items;
	}

	
	public String getSysParam(String itemCode,String defaultValue) {
		if(sysParamsMap==null) {
			 this.initSysParam();
		}
		if(sysParamsMap==null){
			 return defaultValue;
		}
		ItemVo itemVo = sysParamsMap.get(itemCode);
		if(itemVo==null){
			return defaultValue;
		}
		return itemVo.getDvalues();
		
	}
	
	@Scheduled(cron="0 0/5 * * * ?")
	public void initSysParam() {
		log.info("从系统参数表刷新全部平台通用系统参数，每10分钟执行一次");
		String categoryId="sysParam";
		List<ItemVo> itemVos=this.getDicts(categoryId,null);
		Map<String,ItemVo> sysParamsMap=new HashMap<>();
		if(itemVos==null){
			return;
		}
		for (ItemVo itemVo : itemVos) {
			sysParamsMap.put(itemVo.getItemCode(),itemVo);
		}
		this.sysParamsMap=sysParamsMap;
	}
	@Scheduled(cron="0 0/5 * * * ?")
	public void initItemVoMap() {
		log.info("从系统参数表刷新全部平台通用列表，每5分钟执行一次");
		if(!this.itemVoMap.isEmpty()){
			String itemCodes=String.join(",",this.itemVoMap.keySet());
			List<ItemVo> itemVos=this.getDicts("all,sysParam", itemCodes);
			if(itemVos==null){
				return;
			}
			for (ItemVo dict : itemVos) {
				this.itemVoMap.put(dict.getItemCode(),dict);
			}
		}

	}
}

