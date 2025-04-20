package com.mdp.menu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.service.BaseService;
import com.mdp.menu.entity.MenuRole;
import com.mdp.menu.entity.MenuToRolesVo;
import com.mdp.menu.entity.MenusToRoleVo;
import com.mdp.menu.entity.MenusToRolesVo;
import com.mdp.menu.mapper.MenuRoleMapper;
import com.mdp.safe.client.cache.RoleRedisCacheService;
import com.mdp.safe.client.entity.Role;
import com.mdp.safe.client.entity.User;
import com.mdp.safe.client.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 menu 小模块 <br>
 * 实体 MenuRole 表 ADMIN.menu_role 当前主键(包括多主键): id; 
 ***/
@Service("mdp.menu.menuRoleService")
public class MenuRoleService extends BaseService<MenuRoleMapper,MenuRole> {

	@Autowired
	RoleRedisCacheService roleRedisCacheService;

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
	 * 分配多个菜单给一个角色，
	 * 也就是从角色列表界面选中一个角色，然后批量设置角色的菜单
	 * 要求：删除角色原来多余的菜单
	 * 要考虑企业自定义的角色，如果是企业分配给公共角色（无权限-忽略），如果是企业分配给自定义角色，则允许
	 * 如果是超级管理员分配给公共角色，则通过，但不删除原来已分配给自定义角色的菜单，也就是超级管理只负责赋值给公共角色，其它的角色不考虑。
	 * @param menusToRoleVo
	 */
	@Transactional
	public Tips batchEditMenusToRole(MenusToRoleVo menusToRoleVo) {
		
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		boolean isBranchAdmin=LoginUtils.isBranchAdmin();
		User user=LoginUtils.getCurrentUserInfo();
		if( !isSuperAdmin && ! isBranchAdmin){
			return LangTips.errMsg("not-org-adm-not-allow-oper-menu","您不是机构管理员，不允许分配菜单。");
		}
		Role role=roleRedisCacheService.getRole(menusToRoleVo.getRoleid());
		if(role==null){
			return LangTips.errMsg("role-not-exists","角色已不存在，不允许操作");
		}
		if(!isSuperAdmin && !user.getBranchId().equals(role.getBranchId())){
			return LangTips.errMsg("no-qx-for-role","您无权限分配菜单给该角色");
		}
		MenuRole mrquery=new MenuRole();
		mrquery.setRoleid(menusToRoleVo.getRoleid());
		List<MenuRole> menuRolesDb=this.selectListByWhere(mrquery);


		Set<MenuRole> othBranchMenuRoles=new HashSet<>();
		List<MenuRole> noQxDels=new ArrayList<>();
		List<String> noQxAddMids=new ArrayList<>();
		List<MenuRole> dels=new ArrayList<>();
		List<String> addMids=new ArrayList<>();

		for (MenuRole menuRole : menuRolesDb) {
			if(!menusToRoleVo.getMids().stream().filter(i->i.equals(menuRole.getMid())).findAny().isPresent()){

				if("1".equals(role.getRoletype())){
					if(isSuperAdmin){
						dels.add(menuRole);
					}else{
						noQxDels.add(menuRole);
					}
				}else {
					if(isSuperAdmin || user.getBranchId().equals(role.getBranchId())){
						dels.add(menuRole);
					}else{
						othBranchMenuRoles.add(menuRole);
					}

				}

			}
		}
		for (String mid : menusToRoleVo.getMids()) {
			if(!menuRolesDb.stream().filter(i->mid.equals(i.getMid())).findAny().isPresent()){

				if("1".equals(role.getRoletype())){
					if(isSuperAdmin){
						addMids.add(mid);
					}else{
						noQxAddMids.add(mid);
					}
				}else {
					if(isSuperAdmin || user.getBranchId().equals(role.getBranchId())){
						addMids.add(mid);
					}else{
						noQxAddMids.add(mid);
					}
				}
			}
		}
		if(dels.size()>0){
			this.batchDelete(dels);
		}
		List<MenuRole> adds=new ArrayList<>();
		if(addMids.size()>0){
			for (String addMid : addMids) {
				MenuRole mr=new MenuRole();
				mr.setMid(addMid);
				mr.setRoleid(menusToRoleVo.getRoleid());
 				adds.add(mr);
			}
			this.batchInsert(adds);
		}

		List<String> msgs=new ArrayList<>();
		if(adds.size()>0){
			msgs.add(LangTips.transMsg("set-menus-to-role-ok","成功将%s个菜单分配给角色.",adds.size()));
		}
		if(dels.size()>0){
			msgs.add(LangTips.transMsg("del-menus-from-role-ok","成功从角色取消分配%s个菜单.",dels.size()));
		}
		if(noQxAddMids.size()>0){
			msgs.add(LangTips.transMsg("some-menus-link-pub-role-no-qx-dist-menu","有%s个菜单关联了公共角色，您无权限分配菜单给公共角色，忽略不处理.",noQxAddMids.size()));
		}
		if(noQxDels.size()>0){
			msgs.add(LangTips.transMsg("some-menus-link-pub-role-no-qx-cancel-menu","有%s个菜单关联了公共角色，您无权限取消公共角色的菜单，忽略不处理.",noQxDels.size()));
		}
		if(adds.size()>0||dels.size()>0){
			return LangTips.okMsg(null,msgs.stream().collect(Collectors.joining()));
		}else {
			if(msgs.size()>0){
				return LangTips.errMsg(null,msgs.stream().collect(Collectors.joining()));
			}else {
				return LangTips.okMsg(null,"ok","成功");
			}
		}
	}

	/**
	 * 分配一个菜单给多个角色
	 * 也就是从菜单列表界面选中一个菜单，然后批量设置给到选中的多个角色
	 * 要求：删除菜单中多余的角色,
	 * 要考虑企业自定义的角色，如果是企业分配给公共角色（无权限-忽略），如果是企业分配给自定义角色，则允许
	 * 如果是超级管理员分配给公共角色，则通过，但不删除原来已分配给自定义角色的菜单，也就是超级管理只负责赋值给公共角色，其它的角色不考虑。
	 * @param menuToRolesVo
	 */
	@Transactional
	public Tips batchEditMenuToRoles(MenuToRolesVo menuToRolesVo) {
		
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		boolean isBranchAdmin=LoginUtils.isBranchAdmin();
		if( !isSuperAdmin && ! isBranchAdmin){
			return LangTips.errMsg("not-org-adm-not-allow-oper-menu","您不是机构管理员，不允许分配菜单。");
		}
		User user=LoginUtils.getCurrentUserInfo();
		List<MenuRole> menuRolesDb=this.selectListByMidAndBranchId(menuToRolesVo.getMid(),user.getBranchId());
		List<MenuRole> noQxDels=new ArrayList<>();
		List<String> noQxAddRoleids=new ArrayList<>();
		List<MenuRole> dels=new ArrayList<>();
		Map<String,Role> othBranchRoles=new HashMap<>();
		List<String> addRoleids=new ArrayList<>();
		Map<String, Role> rolesMap=new HashMap<>();
		for (String roleid : menuToRolesVo.getRoleids()) {
			rolesMap.put(roleid,roleRedisCacheService.getRole(roleid));
		}

		for (MenuRole menuRole : menuRolesDb) {
			if(!menuToRolesVo.getRoleids().stream().filter(i->i.equals(menuRole.getRoleid())).findAny().isPresent()){
				Role role=rolesMap.get(menuRole.getRoleid());
				if(role==null){
					dels.add(menuRole);
					continue;
				}
				if("1".equals(role.getRoletype())){
					if(isSuperAdmin){
						dels.add(menuRole);
					}else{
						noQxDels.add(menuRole);
					}
				}else {
					if(user.getBranchId().equals(role.getBranchId())){
						dels.add(menuRole);
					}else{
						othBranchRoles.put(role.getRoleid(),role);
					}
				}

			}
		}
		for (String roleid : menuToRolesVo.getRoleids()) {
			if(!menuRolesDb.stream().filter(i->roleid.equals(i.getRoleid())).findAny().isPresent()){
				Role role=rolesMap.get(roleid);
				if(role==null){
					continue;
				}
				if("1".equals(role.getRoletype())){
					if(isSuperAdmin){
						addRoleids.add(roleid);
					}else{
						noQxAddRoleids.add(roleid);
					}
				}else {
					if(user.getBranchId().equals(role.getBranchId())){
						addRoleids.add(roleid);
					}else{
						othBranchRoles.put(role.getRoleid(),role);
					}

				}
			}
		}
		if(dels.size()>0){
			this.batchDelete(dels);
		}
		List<MenuRole> adds=new ArrayList<>();
		if(addRoleids.size()>0){
			for (String addRoleid : addRoleids) {
				MenuRole mr=new MenuRole();
				mr.setRoleid(addRoleid);
				mr.setMid(menuToRolesVo.getMid());
				mr.setHalf("0");
				adds.add(mr);
			}
			this.batchInsert(adds);
		}

		List<String> msgs=new ArrayList<>();
		if(adds.size()>0){
			msgs.add(LangTips.transMsg("set-menu-to-roles-ok","成功将菜单分配给%s个角色.",adds.size()));
		}
		if(dels.size()>0){
			msgs.add(LangTips.transMsg("cancel-role-menus-ok","成功将菜单从%s个角色中取消.",dels.size()));
		}
		if(noQxAddRoleids.size()>0){
			msgs.add(LangTips.transMsg("some-roles-is-pub-no-qx-oper-menu","以下%s个角色属于公共角色，您无权限分配菜单，忽略不处理.",noQxAddRoleids.size()));
		}
		if(noQxDels.size()>0){
			msgs.add(LangTips.transMsg("some-roles-is-pub-no-qx-cancel-menu","以下%s个角色属于公共角色，您无权限取消其菜单，忽略不处理.",noQxDels.size()));
		}
		if(othBranchRoles.size()>0){
			msgs.add(LangTips.transMsg("some-roles-oth-org-self-def-ignore","有%s个角色属于其它机构自定义角色，忽略不处理.",othBranchRoles.size()));
		}
		if(adds.size()>0||dels.size()>0){
			return LangTips.okMsg(null,msgs.stream().collect(Collectors.joining()));
		}else {
			if(msgs.size()>0){
				return LangTips.errMsg(null,msgs.stream().collect(Collectors.joining()));
			}else {
				return LangTips.okMsg();
			}

		} 

	}

	private List<MenuRole> selectListByMidAndBranchId(String mid, String branchId) {
		return baseMapper.selectListByMidAndBranchId(map("mid",mid,"branchId",branchId));
	}

	/**
	 * 将多个菜单分配到选中的角色中，不进行删除，只增加
	 * @param menuVo
	 * @return
	 */
	public Tips batchEditMenusToRoles(MenusToRolesVo menuVo) {
		
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		boolean isBranchAdmin=LoginUtils.isBranchAdmin();
		User user=LoginUtils.getCurrentUserInfo();
		if( !isSuperAdmin && ! isBranchAdmin){
			return LangTips.errMsg("not-org-adm-not-allow-oper-menu","您不是机构管理员，不允许分配菜单。");
		}
		List<MenuRole> pks=new ArrayList<>();
		for (String mid : menuVo.getMids()) {
			for (String roleid : menuVo.getRoleids()) {
				MenuRole menuRole=new MenuRole();
				menuRole.setMid(mid);
				menuRole.setRoleid(roleid);
				pks.add(menuRole);
			}
		}
		List<MenuRole> canAdd=new ArrayList<>();
		List<MenuRole> menuRoles=this.selectListByIds(pks);
		List<MenuRole> finalCanAdd=new ArrayList<>();
		if(menuRoles==null || menuRoles.size()==0){
			canAdd=pks;
		}else{
			for (MenuRole menuRole : pks) {
				if(!menuRoles.stream().filter(i->i.getRoleid().equals(menuRole.getRoleid()) && i.getMid().equals(menuRole.getMid())).findAny().isPresent()){
					canAdd.add(menuRole);
				}
			}
		}

		if(canAdd.size()>0){
			Map<String, Role> rolesMap=new HashMap<>();
			for (String roleid : canAdd.stream().map(i->i.getRoleid()).collect(Collectors.toSet())) {
				rolesMap.put(roleid,roleRedisCacheService.getRole(roleid));
			}
			for (MenuRole menuRole : canAdd) {
				Role role=rolesMap.get(menuRole.getRoleid());
				if(role==null){
					continue;
				}
				if("1".equals(role.getRoletype())){
					if(isSuperAdmin){
						finalCanAdd.add(menuRole);
					}
				}else {
					if(isSuperAdmin||user.getBranchId().equals(role.getBranchId())){
						finalCanAdd.add(menuRole);
					}
				}
			}
		}
		if(finalCanAdd.size()>0){
			this.batchInsert(finalCanAdd);
			return LangTips.okMsg();
		}else{
			return LangTips.errMsg("menu-had-dist","菜单已分配到角色中，无须再分配");
		}
	}
}

