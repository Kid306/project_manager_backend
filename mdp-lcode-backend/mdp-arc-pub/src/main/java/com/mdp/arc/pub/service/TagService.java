package com.mdp.arc.pub.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mdp.arc.cache.TagCacheService;
import com.mdp.arc.pub.entity.Tag;
import com.mdp.arc.pub.entity.TagCategory;
import com.mdp.arc.pub.mapper.TagMapper;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.mdp  顶级模块 arc 大模块 pub 小模块 <br>
 * 实体 Tag 表 arc_tag 当前主键(包括多主键): branch_id,id; 
 ***/
@Service("mdp.arc.pub.service.TagService")
@DS("arc-ds")
public class TagService extends BaseService<TagMapper,Tag> {
	static Logger logger =LoggerFactory.getLogger(TagService.class);

	@Value(value = "${mdp.platform-branch-id:platform-branch-001}")
	String platformBranchId;

	/** 请在此类添加自定义函数 */
	@Autowired
	private TagCategoryService tagCategoryService;

	@Autowired
	TagCacheService tagCacheService;

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


	/** 请在此类添加自定义函数 */
	public List<Map<String, Object>>  getAllTag(String branchId) {
		List<Map<String,Object>> pubTags= this.selectPubTagFromCacheFirst();
		List<Map<String,Object>> notPubTags= this.selectNotPubTagFromCacheFirst(branchId);
		pubTags=pubTags==null?new ArrayList<>():pubTags;
		if(notPubTags!=null  && notPubTags.size()>0){
			pubTags.addAll(notPubTags);
		}
		return pubTags;
	}

	/** 删除*/
	public int deleteTagCategory(TagCategory tagCategory) {
		String categoryId=tagCategory.getId();
		String branchId=tagCategory.getBranchId();
		String shopId=tagCategory.getShopId();
		if(StringUtils.isEmpty(categoryId)) {
			throw new BizException("请传递标签分类id");
		}else if(StringUtils.isEmpty(branchId)) {
			throw new BizException("请传递branchId");
		}
//		TagCategory customerDeleteTagCategoryObject=new TagCategory();
//		customerDeleteTagCategoryObject.setShopId(shopId);
//		customerDeleteTagCategoryObject.setBranchId(branchId);
//		customerDeleteTagCategoryObject.setId(categoryId);
		int i=tagCategoryService.deleteByPk(tagCategory);
		Tag customerDeleteTagObject=new Tag();
		customerDeleteTagObject.setCategoryId(categoryId);
		customerDeleteTagObject.setShopId(shopId);
		customerDeleteTagObject.setBranchId(branchId);
		baseMapper.deleteByCategoryId(customerDeleteTagObject);
		return i;

	}

	/**
	 * @param branchId
	 * @return
	 */
	public String createTagId(String branchId) {
		 return this.createKey("id");
 	}
	public List<Map<String,Object>> selectNotPubTagFromCacheFirst(String branchId){
		List<Map<String,Object>> datas=this.tagCacheService.getNotPubTags(branchId);
		if(datas==null){
			datas=selectNotPubTag(branchId);
			tagCacheService.putNotPubTags(branchId,datas);
		}
		return datas;
	}

	private List<Map<String, Object>> selectNotPubTag(String branchId) {
		return baseMapper.selectNotPubTag(branchId);
	}

	public List<Map<String,Object>> selectPubTagFromCacheFirst(){
		List<Map<String,Object>> datas=this.tagCacheService.getPubTags();
		if(datas==null){
			datas=selectPubTag();
			tagCacheService.putPubTags(datas);
		}
		return datas;
	}

	public List<Map<String, Object>> selectPubTag() {
		return baseMapper.selectPubTag(platformBranchId);
	}
}

