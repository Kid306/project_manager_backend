package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.service.BaseService;
import com.mdp.sys.entity.DeptPostUser;
import com.mdp.sys.mapper.DeptPostUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 DeptPostUser 表 ADMIN.sys_dept_post_user 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.post.deptPostUserService")
public class DeptPostUserService extends BaseService<DeptPostUserMapper, DeptPostUser> {
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
	 * 查询某个用户下的所有的角色
	 * 
	 * @param p {userid,branchId,deptid,postId}
	 * @return [{userid,branchId,deptid,postId,roleid}]
	 */
	public List<Map<String,Object>>  listDeptPostUserRoles(Map<String, Object> p) {
		
		return baseMapper.listDeptPostUserRoles( p);
	}

	@Transactional
	public void setPostMaster(String userid, String id, String master, String branchId) {
		if("1".equals(master)){
			this.baseMapper.setAllPostNotMaster(map("userid",userid,"id",id,"master",master,"branchId",branchId));
			this.baseMapper.setPostMaster(map("userid",userid,"id",id,"master",master,"branchId",branchId));
		}else {
			this.baseMapper.setPostMaster(map("userid",userid,"id",id,"master",master,"branchId",branchId));
		}

	}

	public List<Map<String, Object>> listDeptPostUserMenus(Map<String, Object> deptPostUser) {
		return baseMapper.listDeptPostUserMenus(deptPostUser);
	}

    public void delByUseridAndDeptids(Map<String, Object> delParams) {
    }

    /** 请在此类添加自定义函数 */

}

