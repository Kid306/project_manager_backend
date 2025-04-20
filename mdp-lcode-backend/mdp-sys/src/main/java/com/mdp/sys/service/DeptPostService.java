package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.sys.entity.BatchSetDeptsToPostVo;
import com.mdp.sys.entity.BatchSetPostsToDeptVo;
import com.mdp.sys.entity.DeptPost;
import com.mdp.sys.mapper.DeptPostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 post<br>
 * 实体 DeptPost 表 ADMIN.sys_dept_post 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.post.deptPostService")
public class DeptPostService extends BaseService<DeptPostMapper,DeptPost> {

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
	@Transactional
	public void batchAddDeptPost(List<DeptPost> deptPosts) {
		 List<DeptPost> dps=this.listByIds(deptPosts);
		 List<DeptPost> insertDeptPosts=new ArrayList<>();
		 if(dps!=null && dps.size()>0) {
			 for (DeptPost deptPost : deptPosts) {
				 boolean exists=false;
				for (DeptPost deptPost2 : dps) {
					if(deptPost2.getDeptid().equals(deptPost.getDeptid()) && deptPost2.getPostId().equals(deptPost.getPostId())) {
						exists=true;
					}
				}
				if(exists==false) {
					insertDeptPosts.add(deptPost);
				}
			}
		 }else {
			 insertDeptPosts=deptPosts;
		 }
		 super.batchInsert(insertDeptPosts);
	}
	
	//根据角色列表查找该机构下所有的岗位及部门列表
	public List<Map<String,Object>> selectListDeptPostRoleByRoleids(String branchId,String... roleids) {
		Map<String,Object> p=new HashMap<>();
		p.put("roleids", roleids);
		p.put("branchId", branchId);
		return baseMapper.selectListDeptPostRoleByRoleids(p);
	}

	/**
	 * 查出已有该岗位的部门岗位列表，已有的不处理，只处理新增的，也就是不覆盖部门其它的岗位。
	 * @param batchSetDeptsToPost
	 */
	public void batchSetDeptsToPost(BatchSetDeptsToPostVo batchSetDeptsToPost) {
		Map<String,Object> map=new HashMap<>();
		String postId=batchSetDeptsToPost.getPostId();
		List<String> deptids=batchSetDeptsToPost.getDeptids();
		if(StringUtils.isEmpty(postId)){
			throw new BizException("岗位编号不能为空");
		}
		DeptPost query=new DeptPost();
		query.setPostId(postId);
		//查出已有该岗位的部门岗位列表，已有的不处理，只处理新增的，也就是不覆盖部门其它的岗位。
		List<DeptPost> dpsDb=this.selectListByWhere(query);

		if(deptids==null || deptids.size()==0){
			return;
		}else{
			List<DeptPost> deptPosts=new ArrayList<>();
			for (String deptid : deptids) {
				boolean isExists=false;
				for (DeptPost deptPost : dpsDb) {
					if(deptPost.getDeptid().equals(deptid)){
						isExists=true;
						break;
					}
				}
				if(isExists==false){
					DeptPost deptPost=new DeptPost();
					deptPost.setPostId(postId);
					deptPost.setDeptid(deptid);
					deptPosts.add(deptPost);
				}
			}
			if(deptPosts.size()>0){
				this.batchInsert(deptPosts);
			}
		}
	}

	public void batchSetPostsToDept(BatchSetPostsToDeptVo batchSetPostsToDept) {
		Map<String,Object> map=new HashMap<>();
		String deptid=batchSetPostsToDept.getDeptid();
		if(StringUtils.isEmpty(deptid)){
			throw new BizException("部门编号不能为空");
		}
		map.put("deptid",deptid);
		removeByMap(map);
		List<String> postIds=batchSetPostsToDept.getPostIds();
		if(postIds==null || postIds.size()==0){
			return;
		}else{
			List<DeptPost> deptPosts=new ArrayList<>();
			for (String postId : postIds) {
				DeptPost deptPost=new DeptPost();
				deptPost.setPostId(postId);
				deptPost.setDeptid(deptid);
				deptPosts.add(deptPost);
			}
			if(deptPosts.size()>0){
				this.batchInsert(deptPosts);
			}
		}
	}

	public List<Map<String, Object>> selectListMapByDeptids(String[] deptids) {
		return baseMapper.selectListMapByDeptids(deptids);
	}


	/** 请在此类添加自定义函数 */

}

