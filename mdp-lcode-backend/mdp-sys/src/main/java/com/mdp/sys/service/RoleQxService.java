package com.mdp.sys.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.safe.client.cache.RoleQxsRedisCacheService;
import com.mdp.safe.client.entity.Qx;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.RoleQx;
import com.mdp.sys.entity.RoleQxVo;
import com.mdp.sys.mapper.RoleQxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 RoleQx 表 ADMIN.sys_role_qx 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.roleQxService")
public class RoleQxService extends BaseService<RoleQxMapper,RoleQx>{
	
	/** 请在此类添加自定义函数 */

	
	@Autowired
	QxService qxService;

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

	/**
	 * 批量更新权限,除非是超级管理员/机构管理员，否则无权操作我不拥有的权限
	 * @param roleQxVo
	 */ 
	@Transactional
	public Tips batchEdit(RoleQxVo roleQxVo) {

		Tips tips = new Tips("成功");
		//处理操作权限 数据范围权限两部分内容
		if(roleQxVo.dbQxIds==null){
			roleQxVo.dbQxIds=new ArrayList<>();
		}

		/**
		 * 找出我拥有的所有权限，我智能操作我拥有的权限，我没有的忽略，不处理。
		 *
		 */
		Map<String, Qx> myQxs = LoginUtils.getMyQxs();
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		boolean isBranchAdmin=LoginUtils.isBranchAdmin();

		RoleQx rq=new RoleQx();
		rq.setRoleid(roleQxVo.roleid);
		List<RoleQx> roleQxsFromDb=this.selectListByWhere(rq);
		List<RoleQx> addRoleQxlist=new ArrayList<>();
		List<RoleQx> delRoleQxlist=new ArrayList<>();
		if(!isSuperAdmin && !isBranchAdmin){
			for (String dbQxId : roleQxVo.dbQxIds) {
				if(!myQxs.containsKey(dbQxId)){
					throw new BizException(LangTips.errMsg("no-qx-dispatch-to-role","您未拥有权限【%s】，因此无权将其分配给角色【%s】",dbQxId,roleQxVo.roleid));
				}
			}
		}

		//计算未选中的权限，从角色移除相应的权限
		for (RoleQx roleQx : roleQxsFromDb) {
			boolean exists=false;
			for (String qxId : roleQxVo.dbQxIds) {
				if(roleQx.getQxId().equals(qxId) && roleQx.getRoleid().equals(roleQxVo.roleid)) {
					exists=true;
					
				}
			}
			if(exists==false) {
				if( !isSuperAdmin && myQxs.containsKey(roleQx.getQxId())){
					delRoleQxlist.add(roleQx);
				}else if(isSuperAdmin){
					delRoleQxlist.add(roleQx);
				}
			}
			
		}
		if(delRoleQxlist.size()>0) {
			this.batchDelete(delRoleQxlist);
		}
		//计算已经存在的权限，只插入新增加的权限到角色
		for (String qxId : roleQxVo.dbQxIds) {
			RoleQx rqx=new RoleQx();
			rqx.setQxId(qxId);
			rqx.setRoleid(roleQxVo.roleid);
			boolean exists=false;
			for (RoleQx roleQx : roleQxsFromDb) { //计算已经存在的权限
				if(roleQx.getQxId().equals(qxId) && roleQx.getRoleid().equals(roleQxVo.roleid)) {
					exists=true; 
				}
			}
			if(exists==false) {
				if( !isSuperAdmin &&  myQxs.containsKey(qxId)){
					addRoleQxlist.add(rqx);
				}else if(isSuperAdmin){
					addRoleQxlist.add(rqx);
				}

			}
			
		}
		
		if(addRoleQxlist.size()>0) {
			this.batchInsert(addRoleQxlist);
		}
		if(addRoleQxlist.size()>0 || delRoleQxlist.size()>0){
			roleQxsRedisCacheService.rebuildOne(roleQxVo.roleid);
		}
		return tips;
	}


}

