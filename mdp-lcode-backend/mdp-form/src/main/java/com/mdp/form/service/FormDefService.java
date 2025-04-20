package com.mdp.form.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.core.utils.ObjectTools;
import com.mdp.dm.base.service.DdlBaseService;
import com.mdp.form.entity.FormDef;
import com.mdp.form.entity.FormField;
import com.mdp.form.mapper.FormDefMapper;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com  顶级模块 mdp 大模块 form 小模块 <br>
 * 实体 FormDef 表 form_def 当前主键(包括多主键): id; 
 ***/
@Service
public class FormDefService extends BaseService<FormDefMapper,FormDef> {


	@Autowired
	FormDefCacheService formDefCacheService;

	@Lazy
	@Autowired
	DdlBaseService ddlService;

	static Logger logger =LoggerFactory.getLogger(FormDefService.class);

	public static List<FormField> calcSetSqlFields(FormDef formDef, List<FormField> fields) {
		return fields;
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
 

	public FormDef checkFormDefQx(String formId,User currUser){
		FormDef formDef=this.getFormCacheFirst(formId);
		if(formDef==null){
			throw new BizException("form-0","表单不存在");
		}
		if(LoginUtils.isBranchAdmin(currUser.getBranchId()) || LoginUtils.isSuperAdmin() ){
			return formDef;
		}else{
			if(!currUser.getUserid().equals(formDef.getUserid())){
				throw new BizException("form-creater-err","您不是表单创建者，无权修改和删除表单配置信息");
			}
		}
		return formDef;
	}
  


	/**
	 * 更新tagIds,tagNames两个字段为最新的值
	 * @param formId
	 * @param tagIds
	 * @param tagNames
	 */
	public void updateTagsByFormId(String formId, String tagIds, String tagNames) {
		UpdateWrapper<FormDef> uw=new UpdateWrapper<>();
		uw.set("tag_ids",tagIds);
		uw.set("tag_names",tagNames);
		uw.eq("id",formId);
		super.update(uw);

	}


	public static List<FormField> calcDbFields(List<FormField> fieldList){
		return null;
 	}
	@Transactional
	public void addFormDefAndFields(FormDef formDef) {
		User user= LoginUtils.getCurrentUserInfo(); 
		String formId= ObjectTools.isEmpty(formDef.getId())?this.createKey("id"):formDef.getId();
		formDef.setId(formId);
		formDef.setCtime(new Date());
		formDef.setUserid(user.getUserid());
		formDef.setUsername(user.getUsername());
		formDef.setBranchId(user.getBranchId());
		formDef.setDeptid(user.getDeptid());
		formDef.setDeptName(user.getDeptName());
		super.save(formDef); 
		formDefCacheService.putForm(formId, formDef);
	}



	@Transactional
	public void editFormDefAndFields(FormDef formDef) {
		User user=LoginUtils.getCurrentUserInfo();
		formDef.setCtime(new Date());
		formDef.setUserid(user.getUserid());
		formDef.setUsername(user.getUsername());
		formDef.setBranchId(user.getBranchId());
		formDef.setDeptid(user.getDeptid());
		formDef.setDeptName(user.getDeptName());
		this.updateById(formDef,true);  
		if("3".equals(formDef.getDataType())){

		}
		formDefCacheService.putForm(formDef.getId(), null);

	}

	String getString(Object obj){
		if(obj==null){
			return null;
		}
		if(obj instanceof String){
			return (String)obj;
		}
		return obj.toString();
	}

	public FormDef getFormCacheFirst(String formId) {
		FormDef formDef= formDefCacheService.getForm(formId);
		if(formDef==null){
			formDef=getById(formId);
			formDefCacheService.putForm(formId,formDef);
		}
		return formDef;
	}
}

