package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.UserRole;
import com.mdp.sys.entity.UserRoleVo;
import com.mdp.sys.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserRole 表 ADMIN.sys_user_role 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.userRoleService")
public class UserRoleService extends BaseService<UserRoleMapper,UserRole>{
	
	/** 请在此类添加自定义函数 */

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
	
	/**
	 * 批量更新权限
	 * 超级管理员>平台管理员>机构管理员 
	 * 超级管理员可以增加删除平台管理员，平台管理员可以增删机构管理员
	 */ 
	@Transactional
	public void batchEdit(UserRoleVo userRoleVo ) {
		  
		UserRole userRole=new UserRole();
		userRole.setUserid(userRoleVo.getUserid()); 
		List<String> delRoleids=userRoleVo.getDelRoleids();
		List<UserRole> delUserRoles=new ArrayList<UserRole>();
		boolean hasSuperAdmin=LoginUtils.isSuperAdmin();
		
		//超级管理员>平台管理员>机构管理员 超级管理员可以增加删除平台管理员，平台管理员可以增删机构管理员
		if(delRoleids!=null && delRoleids.size()>0) {
			for (String roleid : delRoleids) {
				UserRole ur=new UserRole();
				ur.setUserid(userRoleVo.getUserid());
				ur.setRoleid(roleid);
				
				if(hasSuperAdmin) {
					delUserRoles.add(ur);
				}else {
					if( !roleid.equals("superAdmin")  && !roleid.equals("branchAdmin") ) {
						delUserRoles.add(ur);
					}else {
						throw new BizException("您不是超级管理员，无权限删除该用户的机构管理员角色");
					}
				}
			}
			//this.batchDelete(delUserRoles);

			
			
		}
		List<String> roleids=userRoleVo.getRoleids();
		List<UserRole> userRolelist=new ArrayList<>();
		List<UserRole> userRolesAdd=new ArrayList<>();
		UserRole urq=new UserRole();
		urq.setUserid(userRoleVo.getUserid());
		List<UserRole> existsUrs=this.selectListByWhere(urq);
		if(roleids!=null && roleids.size()>0){
			for (String roleid : roleids) {
				UserRole ur=new UserRole();
				ur.setUserid(userRoleVo.getUserid());
				ur.setRoleid(roleid);
				userRolelist.add(ur);
			}
			for (UserRole ur : userRolelist) {
				boolean exists=false;
				for (UserRole urexists : existsUrs) {
					if(ur.getUserid().equals(urexists.getUserid()) && ur.getRoleid().equals(urexists.getRoleid())) {
						exists=true;
					}
				}
				if(exists==false) {
					if(hasSuperAdmin) {
						userRolesAdd.add(ur);
					}else {
						if( !ur.getRoleid().equals("superAdmin") && !ur.getRoleid().equals("branchAdmin") ) {
							userRolesAdd.add(ur);
						} else {
							throw new BizException("您不是超级管理员，无权添加该用户为机构管理员角色");
						}
					} 
				}
			}
			
			

			
		}
		if(delUserRoles.size()>0) {
			this.baseMapper.delUserRolesByUseridAndRoleid(delUserRoles);
		}
		if(userRolesAdd.size()>0) {
			this.batchInsert(userRolesAdd);  
		}
		
	}

	public List<UserRole> listUsers(Map<String, Object> userRole) {
		return baseMapper.listUsers(userRole);
	}
}

