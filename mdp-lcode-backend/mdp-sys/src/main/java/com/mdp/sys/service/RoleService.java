package com.mdp.sys.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.BaseService;
import com.mdp.qx.DataLvl;
import com.mdp.safe.client.cache.RoleQxsRedisCacheService;
import com.mdp.safe.client.cache.RoleRedisCacheService;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.Role;
import com.mdp.sys.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 Role 表 ADMIN.sys_role 当前主键(包括多主键): roleid; 
 ***/
@Service("mdp.sys.roleService")
public class RoleService extends BaseService<RoleMapper,Role>{

	@Autowired
	RoleRedisCacheService roleRedisCacheService;

	@Autowired
	RoleQxsRedisCacheService roleQxsRedisCacheService;	/**
	 * 自定义查询，支持多表关联
	 * @param page 分页条件
	 * @param ew 一定要，并且必须加@Param("ew")注解
	 * @param ext 如果xml中需要根据某些值进行特殊处理，可以通过这个进行传递，非必须，注解也可以不加
	 * @return
	 */
	public List<Map<String,Object>> selectListMapByWhere(IPage page, QueryWrapper ew, Map<String,Object> ext){
		return baseMapper.selectListMapByWhere(page,ew,ext);
	}

	public List<Map<String, Object>> selectListNotInPostId(Map<String, Object> role) {
		 
		return baseMapper.selectListNotInPostId(role);
	}
	
	public Tips checkPublicRoles(List<Role> roles) {
		
		List<Role> dbRoles=baseMapper.selectPublicRoles(roles);

			for (Role role : roles) {
				com.mdp.safe.client.entity.Role role1=roleRedisCacheService.getRole(role.getRoleid());
				if("1".equals(role1.getRoletype())){
					return LangTips.errMsg("checkPublicRoles", "存在公共角色，不允许操作公共角色");

				}
			}
		return LangTips.okMsg();
	}


	public Tips roleOpCheck(User user, boolean isSuperAdmin, String  roleid, Integer targetDataLvl, String targetRoletype){
		com.mdp.safe.client.entity.Role role=roleRedisCacheService.getRole(roleid);
		Tips tips = new Tips("检查通过");
		if(role!=null){//新增角色的时候，可能为空
			if(!user.getBranchId().equals(role.getBranchId())) {
				if(!isSuperAdmin ) {
					if("1".equals(role.getRoletype())){
						return LangTips.errMsg("no-qx-to-oper-public-data","无权操作公共数据");
					}else{
						return LangTips.errMsg("no-qx-to-edit-other-org","无权跨机构操作数据");
					}
				}
			}
			if(targetDataLvl==null){
				targetDataLvl=role.getDataLvl();
			}
			if( !isSuperAdmin && targetDataLvl!=null ){
				DataLvl myMaxDataLvl= LoginUtils.getMaxDataLvl();
				if(user.getBranchId().equals(role.getBranchId())){//机构管理员不受控制
					return tips;
				}
				tips=this.dataLvlCheck(myMaxDataLvl,targetDataLvl,role);
				if(tips.isOk()==false){
					return tips;
				}
			}

			if(targetRoletype==null){
				targetRoletype=role.getRoletype();
			}else{
				if( !isSuperAdmin && !targetRoletype.equals(role.getRoletype())){
					return LangTips.errMsg("no-qx-to-edit-role-type","权限不足,不是超级管理员，不允许修改角色类型。");
 				}
			}


		}else{
			return LangTips.errMsg("role-0","角色不存在");
		}
		tips= LoginUtils.checkPublicRoles("", roleid);
		return tips;
	}

	public Tips dataLvlCheck(DataLvl myMaxDataLvl, Integer targetDataLvl, com.mdp.safe.client.entity.Role role){
 		if( targetDataLvl !=null){
			if(role!=null && role.getDataLvl()!=null){
				if( role.getDataLvl() != targetDataLvl ){
					if( myMaxDataLvl.getLvl() > role.getDataLvl()){
						return LangTips.errMsg("dataLvl002",  "权限不足，您不能操作等级比你高的角色");
 					}
					if( myMaxDataLvl.getLvl() <= targetDataLvl ){
						return LangTips.errMsg("dataLvl003",  "权限不足，您不能把低等级角色调高到与您同等级或者您更比高的等级上");
 					}
				}else{
					if( myMaxDataLvl.getLvl()<=role.getDataLvl()){
						return LangTips.errMsg("dataLvl002",  "权限不足，您只能操作等级比您低的角色");
 					}
				}
			}else{
				if( myMaxDataLvl.getLvl() <= targetDataLvl ){
					return LangTips.errMsg("dataLvl002",  "权限不足，您只能操作等级比您低的角色");
 				}
			}
		}
		return LangTips.okMsg();
	}
}

