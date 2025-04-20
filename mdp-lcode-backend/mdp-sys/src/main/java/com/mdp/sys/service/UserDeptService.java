package com.mdp.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mdp.core.entity.LangTips;
import com.mdp.core.entity.Tips;
import com.mdp.core.err.BizException;
import com.mdp.core.service.BaseService;
import com.mdp.safe.client.utils.LoginUtils;
import com.mdp.sys.entity.DeptPostUser;
import com.mdp.sys.entity.UserDept;
import com.mdp.sys.entity.UserDeptVo;
import com.mdp.sys.mapper.UserDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
/**
 * 父类已经支持增删改查操作,因此,即使本类什么也不写,也已经可以满足一般的增删改查操作了.<br> 
 * 组织 com.qqkj  顶级模块 mdp 大模块 sys 小模块 <br>
 * 实体 UserDept 表 ADMIN.sys_user_dept 当前主键(包括多主键): id; 
 ***/
@Service("mdp.sys.deptidsService")
public class UserDeptService extends BaseService<UserDeptMapper,UserDept>{
	
	@Autowired
	DeptPostUserService deptPostUserService;
	
	@Autowired
	DeptPostService deptPostService;
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
	public Tips checkOpQx(String branchId) {
		
		if(LoginUtils.isSuperAdmin()){
			return LangTips.okMsg();
		}else if(LoginUtils.isBranchAdmin(branchId)){
			return LangTips.okMsg();
		}
		return LangTips.errMsg("no-qx","无权限操作");
	}
	

	boolean findRoleByRoleidAndDeptids(String roleid,List<String> deptids, Map<String,Object> role){ 
		if(deptids==null || deptids.size()<=0) {
			return false;
		}
		String deptidRole=(String) role.get("deptid");
		String  roleidRole=(String) role.get("roleid");
		for (String deptid : deptids) { 
				if(deptid.equals(deptidRole) && roleid.equals(roleidRole)) {
					return true;
				} 
		}
		
		return false;
	}
	

	boolean findRoleByRoleidAndPosts(String roleid,List<DeptPostUser> deptPostUsers, Map<String,Object> role){ 
		if(deptPostUsers==null || deptPostUsers.size()<=0) {
			return false;
		}
		String postIdRole=(String) role.get("postId");
		String  roleidRole=(String) role.get("roleid");
		for (DeptPostUser post : deptPostUsers) {  
				if(post.getPostId().equals(postIdRole) && roleid.equals(roleidRole)) {
					return true;
				} 
		}
		
		return false;
	}
	
	/**
	 * 批量更新用户所属部门
	 */ 
	@Transactional
	public Tips batchEdit(UserDeptVo userDeptVo) {
		if(StringUtils.isEmpty(userDeptVo.getBranchId())) {
			return LangTips.errMsg("branch-id-required","机构编号不能为空");
		}
		boolean isSuperAdmin=LoginUtils.isSuperAdmin();
		boolean isBranchAdmin=LoginUtils.isBranchAdmin(userDeptVo.getBranchId());
		//先把我没有权限操作的部门的数据过滤掉
		Map<String,String> isHasQxDeptidsMap=new HashMap<>();
		Map<String,String> deptidsTempMap=new HashMap<>();
		if(userDeptVo.getDelDeptids()!=null){
			for (String delDeptid : userDeptVo.getDelDeptids()) {
				deptidsTempMap.put(delDeptid,delDeptid);
			}
		}
		if(userDeptVo.getDelDeptPostUsers()!=null){
			for (DeptPostUser delDeptPostUser : userDeptVo.getDelDeptPostUsers()) {
				deptidsTempMap.put(delDeptPostUser.getDeptid(),delDeptPostUser.getDeptid());
			}
		}
		if(userDeptVo.getAddDeptPostUsers()!=null){
			for (DeptPostUser addDeptPostUser : userDeptVo.getAddDeptPostUsers()) {
				deptidsTempMap.put(addDeptPostUser.getDeptid(),addDeptPostUser.getDeptid());
			}
		}
		if(userDeptVo.getDeptids()!=null){
			for (String deptid : userDeptVo.getDeptids()) {
				deptidsTempMap.put(deptid,deptid);
			}
		}
		for (String deptid : deptidsTempMap.values()) {
			if(isSuperAdmin || isBranchAdmin || LoginUtils.checkIsMyDeptScope(deptid)){
				isHasQxDeptidsMap.put(deptid,deptid);
			}
		}


		  
		UserDept userDept=new UserDept();
		userDept.setUserid(userDeptVo.getUserid());
		List<String> delDeptids=userDeptVo.getDelDeptids();
		if(delDeptids!=null && delDeptids.size()>0) {
			List<String> canDelDeptids=new ArrayList<>();
			for (String delDeptid : delDeptids) {
				if(isHasQxDeptidsMap.containsKey(delDeptid)){
					canDelDeptids.add(delDeptid);
				}
			}
			Map<String,Object> delParams=new HashMap<>();
			delParams.put("branchId",userDeptVo.getBranchId() );
			delParams.put("userid", userDeptVo.getUserid());
			delParams.put("deptids", canDelDeptids);
			baseMapper.delByUseridAndDeptids(delParams);
			deptPostUserService.delByUseridAndDeptids( delParams);
		} //需要删除的用户部门id
		
		List<DeptPostUser> delDeptPostUsersF=userDeptVo.getDelDeptPostUsers(); 
		List<DeptPostUser> delDeptPostUsers=new ArrayList<>(); 
		if(delDeptPostUsersF!=null && delDeptPostUsersF.size()>0) {
			for (DeptPostUser deptPostUser : delDeptPostUsersF) {
				if(StringUtils.isEmpty(deptPostUser.getId())) {
					continue;
				}
				if(isHasQxDeptidsMap.containsKey(deptPostUser.getDeptid())){
					delDeptPostUsers.add(deptPostUser);
				}

			}
			if(delDeptPostUsers.size()>0) {
				deptPostUserService.batchDelete(delDeptPostUsers);
			}
			
		}
		
		
		List<UserDept> userDeptlist=new ArrayList<>();
		List<UserDept> userDeptsAdd=new ArrayList<>();
		UserDept udq=new UserDept();
		udq.setUserid(userDeptVo.getUserid());
		List<UserDept> userDeptExists=this.selectListByWhere(udq);
		List<String> deptids=userDeptVo.getDeptids();
		if(deptids!=null && deptids.size()>0){
			for (String deptid : deptids) {
				UserDept ur=new UserDept();
				ur.setUserid(userDeptVo.getUserid());
				ur.setDeptid(deptid);
				ur.setEnabled("1");
				userDeptlist.add(ur);
			}
			for (UserDept ud : userDeptlist) {
				boolean exists=false;
				for (UserDept udexists : userDeptExists) {
					if(ud.getDeptid().equals(udexists.getDeptid()) && ud.getUserid().equals(udexists.getUserid())) {
						exists=true;
					}
				}
				if(exists==false) {
					if (isHasQxDeptidsMap.containsKey(ud.getDeptid())) {
						userDeptsAdd.add(ud);
					}
				}
			}
			if(userDeptsAdd.size()>0) {
				this.batchInsert(userDeptsAdd);  
			}
			
		}
		
		List<DeptPostUser> addDeptPostUsers=userDeptVo.getAddDeptPostUsers();
		List<DeptPostUser> insertDeptPostUsers=new ArrayList<>();
		List<DeptPostUser> updateDeptPostUsers=new ArrayList<>();
		if(addDeptPostUsers!=null && addDeptPostUsers.size()>0) {
			for (DeptPostUser deptPostUser : addDeptPostUsers) {
				
				deptPostUser.setEnabled("1");
				if(deptPostUser.getStartDate()==null) {
					deptPostUser.setStartDate(new Date());
				}
				deptPostUser.setLastDate(new Date());
				if(StringUtils.isEmpty(deptPostUser.getId())) {
					deptPostUser.setId(this.createKey("id"));
					if (isHasQxDeptidsMap.containsKey(deptPostUser.getDeptid())) {
						insertDeptPostUsers.add(deptPostUser);
					}
				}else {
					if (isHasQxDeptidsMap.containsKey(deptPostUser.getDeptid())) {
						updateDeptPostUsers.add(deptPostUser);
					}

				}
			}
			if(insertDeptPostUsers.size()>0) {
				deptPostUserService.batchInsert(insertDeptPostUsers);
			}
			if(updateDeptPostUsers.size()>0) {
				deptPostUserService.batchUpdate(updateDeptPostUsers);
			}
			
		}
		if(isHasQxDeptidsMap.isEmpty() ){
			return LangTips.errMsg("adjust-error","调整失败！你无权限操作该用户数据。");
		}else{
			if(deptidsTempMap.size()!=isHasQxDeptidsMap.size()){
				return LangTips.okMsg("some-success-some-ignore","部分成功！有部分部门或者岗位由于您无权限操作，自动忽略。");
			}
		}
		return LangTips.okMsg();
	}
	
	
	
	 
	/**
	 * 同时删除用户部门表及用户部门岗位表两张表
	 * @param userDept
	 * @return
	 */
	@Transactional
	public   int deleteByPk(UserDept userDept) { 
		if(StringUtils.isEmpty(userDept.getUserid())) {
			throw new BizException("userid-not-empty", "userid", "主键userid不能为空");
		}
		if(StringUtils.isEmpty(userDept.getDeptid())) {
			throw new BizException("deptid-not-empty", "deptid", "主键deptid不能为空");
		}

		List<String> userDeptiIds=new ArrayList<>();
		userDeptiIds.add(userDept.getDeptid());
		Map<String,Object> p=new HashMap<>();
		p.put("userid", userDept.getUserid());
		p.put("deptids", userDeptiIds);
		deptPostUserService.delByUseridAndDeptids(p);
		return super.deleteByPk(userDept);
	}
	
	/**
	 * 同时删除用户部门表及用户部门岗位表两张表
	 * @param userDepts
	 */
	@Transactional
	public  void batchDeleteWithRelate( List<UserDept> userDepts){ 
		Map<String,List<String>> m=new HashMap<>();
		for (UserDept userDept : userDepts) {
			List<String> deptids=m.get(userDept.getUserid());
			if(deptids==null) {
				deptids=new ArrayList<>();
				deptids.add(userDept.getDeptid());
				m.put(userDept.getUserid(), deptids);
			}else {
				deptids.add(userDept.getDeptid());
				m.put(userDept.getUserid(), deptids);
			}
			  
		}
		 Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String userid = (String) it.next();
            List<String> deptids = (List<String> ) m.get(userid); 
    		Map<String,Object> p=new HashMap<>();
    		p.put("userid", userid);
    		p.put("deptids", deptids);
    		deptPostUserService.delByUseridAndDeptids(p);
        } 
		if(userDepts.size()>0) { 
			super.batchDelete(userDepts);
		}
		
		
	}

}

